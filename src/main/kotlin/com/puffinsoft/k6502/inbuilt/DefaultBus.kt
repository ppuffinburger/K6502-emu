package com.puffinsoft.k6502.inbuilt

import com.puffinsoft.k6502.BusConfiguration

class DefaultBus(override val memory: DefaultMemory): BusConfiguration() {
    override fun read(address: Short): UByte {
        val addressAsInt = convertAddress(address)

        return memory.read(addressAsInt)
    }

    override fun write(address: Short, value: UByte) {
        val addressAsInt = convertAddress(address)

        memory.write(addressAsInt, value)
    }

    private fun convertAddress(address: Short): Int {
        return address.toUShort().toInt()
    }
}