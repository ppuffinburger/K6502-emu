package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class AndInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test AND Immediate (0x29) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x29]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x29u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Immediate (0x29) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x29]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x29u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Immediate (0x29) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x29]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x29u
        memory[0x8001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Immediate (0x29) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x29]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x29u
        memory[0x8001] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Zero Page (0x25) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x25]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x25u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Zero Page (0x25) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x25]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x25u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Zero Page (0x25) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x25]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x25u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Zero Page (0x25) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x25]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x25u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Zero Page,X (0x35) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x35]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x35u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Zero Page,X (0x35) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x35]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x35u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Zero Page,X (0x35) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x35]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x35u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Zero Page,X (0x35) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x35]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x35u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Absolute (0x2d) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x2d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute (0x2d) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x2d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute (0x2d) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x2d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute (0x2d) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x2d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0x00 & 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0xff & 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0xff & 0x0f = 0x0f with cross page`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,X (0x3d) 0xff & 0xf0 = 0xf0 with cross page`() {
        val instruction = instructionSet.instructions[0x3d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0x00 & 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0xff & 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0xff & 0x0f = 0x0f with cross page`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Absolute,Y (0x39) 0xff & 0xf0 = 0xf0 with cross page`() {
        val instruction = instructionSet.instructions[0x39]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x39u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0x00 & 0x00 = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0xff & 0x00 = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0xff & 0x0f = 0x0f with wrap`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,X (0x21) 0xff & 0xf0 = 0xf0 with wrap`() {
        val instruction = instructionSet.instructions[0x21]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x21u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0x00 & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0x0f = 0x0f`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0xf0 = 0xf0`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0x00 & 0x00 = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0x00 = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0x0f = 0x0f with wrap`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0xf0 = 0xf0 with wrap`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0x00 & 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0x0f = 0x0f with cross page`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x0f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test AND Indirect,Y (0x31) 0xff & 0xf0 = 0xf0 with cross page`() {
        val instruction = instructionSet.instructions[0x31]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("AND")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x31u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xf0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }
}