package com.puffinsoft.k6502

import java.util.*

class StatusRegister {
    private val _flags = BitSet(8)

    /*
    SR Flags (bit 7 to bit 0)

    N	Negative
    V	Overflow
    -	ignored
    B	Break
    D	Decimal (use BCD for arithmetics)
    I	Interrupt Disable (IRQ disable)
    Z	Zero
    C	Carry
    */

    var carryFlag: Boolean
        get() = _flags.get(CARRY_BIT_POSITION)
        set(value) = _flags.set(CARRY_BIT_POSITION, value)
    var zeroFlag: Boolean
        get() = _flags.get(ZERO_BIT_POSITION)
        set(value) = _flags.set(ZERO_BIT_POSITION, value)
    var interruptDisabledFlag: Boolean
        get() = _flags.get(INTERRUPT_DISABLED_BIT_POSITION)
        set(value) = _flags.set(INTERRUPT_DISABLED_BIT_POSITION, value)
    var decimalFlag: Boolean
        get() = _flags.get(DECIMAL_BIT_POSITION)
        set(value) = _flags.set(DECIMAL_BIT_POSITION, value)
    var breakFlag: Boolean
        get() = _flags.get(BREAK_BIT_POSITION)
        set(value) = _flags.set(BREAK_BIT_POSITION, value)
    var ignoredFlag: Boolean
        get() = _flags.get(IGNORED_BIT_POSITION)
        set(value) = _flags.set(IGNORED_BIT_POSITION, value)
    var overflowFlag: Boolean
        get() = _flags.get(OVERFLOW_BIT_POSITION)
        set(value) = _flags.set(OVERFLOW_BIT_POSITION, value)
    var negativeFlag: Boolean
        get() = _flags.get(NEGATIVE_BIT_POSITION)
        set(value) = _flags.set(NEGATIVE_BIT_POSITION, value)

    var flags: UByte
        get() = if (_flags.isEmpty) 0x00u else _flags.toByteArray()[0].toUByte()
        set(value) { setFlagsFromByte(value) }

    fun copy(): StatusRegister {
        val newRegister = StatusRegister()
        newRegister.carryFlag = carryFlag
        newRegister.zeroFlag = zeroFlag
        newRegister.interruptDisabledFlag = interruptDisabledFlag
        newRegister.decimalFlag = decimalFlag
        newRegister.breakFlag = breakFlag
        newRegister.ignoredFlag = ignoredFlag
        newRegister.overflowFlag = overflowFlag
        newRegister.negativeFlag = negativeFlag
        return newRegister
    }

    fun clear() {
        _flags.clear()
    }

    private fun setFlagsFromByte(value: UByte) {
        _flags.set(CARRY_BIT_POSITION, Utils.getBit(value, CARRY_BIT_POSITION))
        _flags.set(ZERO_BIT_POSITION, Utils.getBit(value, ZERO_BIT_POSITION))
        _flags.set(INTERRUPT_DISABLED_BIT_POSITION, Utils.getBit(value, INTERRUPT_DISABLED_BIT_POSITION))
        _flags.set(DECIMAL_BIT_POSITION, Utils.getBit(value, DECIMAL_BIT_POSITION))
        _flags.set(BREAK_BIT_POSITION, Utils.getBit(value, BREAK_BIT_POSITION))
        _flags.set(IGNORED_BIT_POSITION, Utils.getBit(value, IGNORED_BIT_POSITION))
        _flags.set(OVERFLOW_BIT_POSITION, Utils.getBit(value, OVERFLOW_BIT_POSITION))
        _flags.set(NEGATIVE_BIT_POSITION, Utils.getBit(value, NEGATIVE_BIT_POSITION))
    }

    companion object {
        const val CARRY_BIT_POSITION = 0
        const val ZERO_BIT_POSITION = 1
        const val INTERRUPT_DISABLED_BIT_POSITION = 2
        const val DECIMAL_BIT_POSITION = 3
        const val BREAK_BIT_POSITION = 4
        const val IGNORED_BIT_POSITION = 5
        const val OVERFLOW_BIT_POSITION = 6
        const val NEGATIVE_BIT_POSITION = 7
    }
}