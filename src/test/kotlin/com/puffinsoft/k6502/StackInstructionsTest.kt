package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class StackInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test PHA (0x48)`() {
        val instruction = instructionSet.instructions[0x48]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("PHA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x48u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0xfc.toUByte())
            it.assertThat(memory[0x01fd]).isEqualTo(0x7f.toUByte())
        }
    }

    @Test
    fun `test PHP (0x08)`() {
        val instruction = instructionSet.instructions[0x08]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("PHP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x08u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = true
        cpu.statusRegister.interruptDisabledFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0xfc.toUByte())
            it.assertThat(memory[0x01fd]).isEqualTo(0b10110100.toUByte())
        }
    }

    @Test
    fun `test PLA (0x68) no flags set`() {
        val instruction = instructionSet.instructions[0x68]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("PLA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x68u
        memory[0x01ff] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.stackPointer = 0xfeu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test PLA (0x68) zero flag set`() {
        val instruction = instructionSet.instructions[0x68]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("PLA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x68u
        memory[0x01ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.stackPointer = 0xfeu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test PLA (0x68) negative flag set`() {
        val instruction = instructionSet.instructions[0x68]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("PLA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x68u
        memory[0x01ff] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.stackPointer = 0xfeu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test PLP (0x28)`() {
        val instruction = instructionSet.instructions[0x28]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("PLP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x28u
        memory[0x01ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.stackPointer = 0xfeu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.ignoredFlag).isFalse
            it.assertThat(cpu.statusRegister.breakFlag).isFalse
            it.assertThat(cpu.statusRegister.decimalFlag).isTrue
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isTrue
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }
}