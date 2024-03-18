package com.puffinsoft.k6502.inbuilt

import com.puffinsoft.k6502.Memory

class DefaultMemory(private val memory: Array<UByte> = Array(65536) { 0u }): Memory {
    val memorySize: Int
        get() = memory.size

    fun read(addressAsInt: Int): UByte {
        return memory[addressAsInt]
    }

    fun write(addressAsInt: Int, value: UByte) {
        memory[addressAsInt] = value
    }

    fun load(program: Array<UByte>, loadAt: Int) {
        program.copyInto(memory, loadAt)
    }
}