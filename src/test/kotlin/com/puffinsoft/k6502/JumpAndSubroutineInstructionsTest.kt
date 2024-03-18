package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class JumpAndSubroutineInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test JMP Absolute (0x4c)`() {
        val instruction = instructionSet.instructions[0x4c]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("JMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4cu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x0200.toShort())
        }
    }

    @Test
    fun `test JMP Indirect (0x6c)`() {
        val instruction = instructionSet.instructions[0x6c]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("JMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IND")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6cu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x70u
        memory[0x7000] = 0x00u
        memory[0x7001] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x0200.toShort())
        }
    }

    @Test
    fun `test JSR Absolute (0x20)`() {
        val instruction = instructionSet.instructions[0x20]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("JSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x20u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x0200.toShort())
            it.assertThat(cpu.stackPointer).isEqualTo(0xfb.toUByte())
            it.assertThat(memory[0x01fd]).isEqualTo(0x80.toUByte())
            it.assertThat(memory[0x01fc]).isEqualTo(0x02.toUByte())
        }
    }

    @Test
    fun `test RTS Implied (0x60)`() {
        val instruction = instructionSet.instructions[0x60]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("RTS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x20u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x60u

        val cpu = createCpu(DefaultMemory(memory))

        // Add in cycles for JSR
        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 6)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8003.toShort())
            it.assertThat(cpu.stackPointer).isEqualTo(0xfd.toUByte())
        }
    }
}