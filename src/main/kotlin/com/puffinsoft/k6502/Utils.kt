package com.puffinsoft.k6502

import java.nio.ByteBuffer
import java.nio.ByteOrder

class Utils {
    companion object {
        private val addressBuffer = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN)

        fun getBit(value: Byte, position: Int): Boolean {
            return (value.toInt() shr position) and 1 == 1
        }

        fun getBit(value: UByte, position: Int): Boolean {
            return (value.toInt() shr position) and 1 == 1
        }

        fun getBit(value: Short, position: Int): Boolean {
            return (value.toInt() shr position) and 1 == 1
        }

        fun getBit(value: Int, position: Int): Boolean {
            return (value shr position) and 1 == 1
        }

        fun convertBytesToAddress(lo: UByte, hi: UByte): Short {
            return addressBuffer.clear().put(lo.toByte()).put(hi.toByte()).getShort(0)
        }

        fun getHiByte(value: Short): Byte {
            return addressBuffer.clear().putShort(value).get(1)
        }

        fun getLoAndHiBytes(value: Short): LoAndHiByte {
            val byteBuffer = addressBuffer.clear().putShort(value)
            return LoAndHiByte(byteBuffer.get(0).toUByte(), byteBuffer.get(1).toUByte())
        }

        data class LoAndHiByte(val lo: UByte, val hi: UByte)
    }
}