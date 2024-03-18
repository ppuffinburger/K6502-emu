package com.puffinsoft.k6502

abstract class BusConfiguration {
    abstract val memory: Memory
    abstract fun read(address: Short): UByte
    abstract fun write(address: Short, value: UByte)

    var stackPointer: UByte = 0xfdu

    fun writeStack(value: UByte) {
        write(getStackAddress(), value)
        stackPointer--
    }

    fun readStack(): UByte {
        stackPointer++
        return read(getStackAddress())
    }

    fun resetStack() {
        stackPointer = 0xfdu
    }

    private fun getStackAddress(): Short {
        return Utils.convertBytesToAddress(stackPointer, 0x01u)
    }
}

interface Memory