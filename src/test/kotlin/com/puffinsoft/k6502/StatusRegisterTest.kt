package com.puffinsoft.k6502

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Test

internal class StatusRegisterTest {
    @Test
    internal fun `test initial values`() {
        val sr = StatusRegister()

        assertSoftly {
            it.assertThat(sr.carryFlag).isFalse
            it.assertThat(sr.zeroFlag).isFalse
            it.assertThat(sr.interruptDisabledFlag).isFalse
            it.assertThat(sr.decimalFlag).isFalse
            it.assertThat(sr.breakFlag).isFalse
            it.assertThat(sr.ignoredFlag).isFalse
            it.assertThat(sr.overflowFlag).isFalse
            it.assertThat(sr.negativeFlag).isFalse
        }
    }

    @Test
    internal fun `test flags getter property`() {
        val sr = StatusRegister()

        sr.carryFlag = true
        sr.zeroFlag = false
        sr.interruptDisabledFlag = true
        sr.decimalFlag = false
        sr.breakFlag = true
        sr.ignoredFlag = false
        sr.overflowFlag = true
        sr.negativeFlag = false

        assertThat(sr.flags).isEqualTo(0b01010101.toUByte())
    }

    @Test
    internal fun `test clear()`() {
        val sr = StatusRegister()

        sr.carryFlag = true
        sr.zeroFlag = true
        sr.interruptDisabledFlag = true
        sr.decimalFlag = true
        sr.breakFlag = true
        sr.ignoredFlag = true
        sr.overflowFlag = true
        sr.negativeFlag = true

        assertSoftly {
            it.assertThat(sr.carryFlag).isTrue
            it.assertThat(sr.zeroFlag).isTrue
            it.assertThat(sr.interruptDisabledFlag).isTrue
            it.assertThat(sr.decimalFlag).isTrue
            it.assertThat(sr.breakFlag).isTrue
            it.assertThat(sr.ignoredFlag).isTrue
            it.assertThat(sr.overflowFlag).isTrue
            it.assertThat(sr.negativeFlag).isTrue
        }

        sr.clear()

        assertSoftly {
            it.assertThat(sr.carryFlag).isFalse
            it.assertThat(sr.zeroFlag).isFalse
            it.assertThat(sr.interruptDisabledFlag).isFalse
            it.assertThat(sr.decimalFlag).isFalse
            it.assertThat(sr.breakFlag).isFalse
            it.assertThat(sr.ignoredFlag).isFalse
            it.assertThat(sr.overflowFlag).isFalse
            it.assertThat(sr.negativeFlag).isFalse
        }
    }
}