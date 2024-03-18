package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class InterruptInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test BRK Implied (0x00)`() {
        val instruction = instructionSet.instructions[0x00]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BRK")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0xfffe] = 0x00u
        memory[0xffff] = 0x02u
        memory[0x8000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.interruptDisabledFlag = false
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x0200.toShort())
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isTrue
            it.assertThat(cpu.statusRegister.ignoredFlag).isFalse
            it.assertThat(cpu.statusRegister.breakFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.stackPointer).isEqualTo(0xfa.toUByte())
            it.assertThat(memory[0x01fd]).isEqualTo(0x80.toUByte())
            it.assertThat(memory[0x01fc]).isEqualTo(0x02.toUByte())
            it.assertThat(memory[0x01fb]).isEqualTo(0xb0.toUByte())
        }
    }

    @Test
    fun `test RTI Implied (0x40)`() {
        val instruction = instructionSet.instructions[0x40]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("RTI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x40u

        val cpu = createCpu(DefaultMemory(memory))

        cpu.statusRegister.interruptDisabledFlag = true
        cpu.statusRegister.breakFlag = true
        cpu.statusRegister.negativeFlag = true
        cpu.writeStack(0x80.toUByte())
        cpu.writeStack(0x02.toUByte())
        cpu.writeStack(cpu.statusRegister.flags.toUByte())

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isTrue
            it.assertThat(cpu.statusRegister.breakFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.flags.toUByte()).isEqualTo(0xb4.toUByte())
            it.assertThat(cpu.stackPointer).isEqualTo(0xfd.toUByte())
        }
    }

}