package com.puffinsoft.k6502.inbuilt

import com.puffinsoft.k6502.Cpu

class DefaultMachine {
    private val _memory = DefaultMemory()
    private val _bus = DefaultBus(_memory)
    private val _cpu = DefaultCpu(_bus)

    private var programLoadedAt: Int = 0
    private var programSize: Int = 0

    var stopExecution = false

    val cpu: Cpu
        get() = _cpu

    fun load(program: Array<UByte>, loadAt: Int) {
        programSize = program.size
        programLoadedAt = loadAt
        if (programSize + programLoadedAt > _memory.memorySize) {
            throw RuntimeException("Program does not fit in memory")
        }

        _memory.load(program, loadAt)

        _cpu.programCounter = programLoadedAt.toShort()
    }

    fun execute() {
        while (!isEndOfProgram() && !stopExecution) {
            _cpu.nextClock()
        }
    }

    private fun isEndOfProgram(): Boolean {
        return _cpu.programCounter >= programLoadedAt + programSize
    }
}