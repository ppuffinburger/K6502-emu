package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class StoreAccumulatorInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test STA Zero Page (0x85)`() {
        val instruction = instructionSet.instructions[0x85]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x85u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Zero Page,X (0x95)`() {
        val instruction = instructionSet.instructions[0x95]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x95u
        memory[0x8001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Absolute (0x8d)`() {
        val instruction = instructionSet.instructions[0x8d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x8du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Absolute,X (0x9d)`() {
        val instruction = instructionSet.instructions[0x9d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x9du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Absolute,X (0x9d) with cross page`() {
        val instruction = instructionSet.instructions[0x9d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x9du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Absolute,Y (0x99)`() {
        val instruction = instructionSet.instructions[0x99]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x99u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Absolute,Y (0x99) with cross page`() {
        val instruction = instructionSet.instructions[0x99]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x99u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Indirect,X (0x81)`() {
        val instruction = instructionSet.instructions[0x81]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x81u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x200]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Indirect,X (0x81) with wrap`() {
        val instruction = instructionSet.instructions[0x81]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x81u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Indirect,Y (0x91)`() {
        val instruction = instructionSet.instructions[0x91]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x91u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x02ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Indirect,Y (0x91) with wrap`() {
        val instruction = instructionSet.instructions[0x91]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x91u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x02ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STA Indirect,Y (0x91) with cross page`() {
        val instruction = instructionSet.instructions[0x91]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x91u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x42.toUByte())
        }
    }
}