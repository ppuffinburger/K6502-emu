package com.puffinsoft.k6502

abstract class Cpu(private val bus: BusConfiguration) {
    /*
    PC  program counter             (16 bit)
    A   accumulator                 (8 bit)
    X   X register                  (8 bit)
    Y   Y register                  (8 bit)
    SR  status register [NV-BDIZC]  (8 bit)
    SP  stack pointer               (8 bit)
    */

    var programCounter: Short = 0x0000
    var a: UByte = 0x00u
    var x: UByte = 0x00u
    var y: UByte = 0x00u
    val statusRegister = StatusRegister()

    val instructionSet = InstructionSet()

    private var _currentInstruction: Instruction = instructionSet.instructions[0x00]
    private var _cycles = 0
    private var interruptRequested = false
    private var nmInterruptRequested = false

    val cycles: Int
        get() = _cycles

    var stackPointer: UByte
        get() = bus.stackPointer
        set(value) { bus.stackPointer = value }

    val instructionDetails: InstructionDetails
        get() = InstructionDetails(_currentInstruction.opcode, _currentInstruction.addressMode.opcode)

    init {
        reset()
    }

    fun read(address: Short) = bus.read(address)
    fun write(address: Short, value: UByte) = bus.write(address, value)
    fun readStack() = bus.readStack()
    fun writeStack(value: UByte) = bus.writeStack(value)

    fun issueInterruptRequest() {
        interruptRequested = true
    }

    fun issueNmiInterruptRequest() {
        nmInterruptRequested = true
    }

    fun nextClock() {
        if (_cycles == 0) {
            if (!interruptRequested) {
                // get next opcode from memory
                val opcode = read(programCounter).toInt()
                programCounter++

                _currentInstruction = instructionSet.instructions[opcode]
                _cycles = _currentInstruction.execute(this)
            } else {
                handleInterruptRequest()

                if (nmInterruptRequested) {
                    handleNMInterruptRequest()
                }
            }
        }

        _cycles--
    }

    fun reset() {
        a = 0x00u
        x = 0x00u
        y = 0x00u

        statusRegister.clear()

        bus.resetStack()

        val lo = read(RES_VECTOR)
        val hi = read((RES_VECTOR + 1).toShort())
        programCounter = Utils.convertBytesToAddress(lo, hi)

        _cycles = 8
    }

    private fun handleInterruptRequest() {
        if (!statusRegister.interruptDisabledFlag) {
            val (lo, hi) = Utils.getLoAndHiBytes(programCounter)
            // write hi-byte of programCounter to stack
            writeStack(hi)
            // write lo-byte of programCounter to stack
            writeStack(lo)

            statusRegister.breakFlag = false
            statusRegister.ignoredFlag = true

            // write flags to stack
            writeStack(statusRegister.flags)

            statusRegister.interruptDisabledFlag = true

            val irqVecLo = read(IRQ_VECTOR)
            val irqVecHi = read((IRQ_VECTOR + 1).toShort())
            programCounter = Utils.convertBytesToAddress(irqVecLo, irqVecHi)

            _cycles = 7
            interruptRequested = false
        }
    }

    private fun handleNMInterruptRequest() {
        val (lo, hi) = Utils.getLoAndHiBytes(programCounter)

        // write hi-byte of programCounter to stack
        writeStack(hi)
        // write lo-byte of programCounter to stack
        writeStack(lo)

        statusRegister.breakFlag = false
        statusRegister.ignoredFlag = true

        // write flags to stack
        writeStack(statusRegister.flags)

        statusRegister.interruptDisabledFlag = true

        val irqVecLo = read(NMI_VECTOR)
        val irqVecHi = read((NMI_VECTOR + 1).toShort())
        programCounter = Utils.convertBytesToAddress(irqVecLo, irqVecHi)

        _cycles = 7
        nmInterruptRequested = false
    }

    companion object {
        /*
        $FFFA, $FFFB ... NMI (Non-Maskable Interrupt) vector, 16-bit (LB, HB)
        $FFFC, $FFFD ... RES (Reset) vector, 16-bit (LB, HB)
        $FFFE, $FFFF ... IRQ (Interrupt Request) vector, 16-bit (LB, HB)
        */
        const val NMI_VECTOR: Short = 0xfffa.toShort()
        const val RES_VECTOR: Short = 0xfffc.toShort()
        const val IRQ_VECTOR: Short = 0xfffe.toShort()
    }
}

data class InstructionDetails(val instructionText: String, val addressModeText: String)
