package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class AddWithCarryBinaryInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test ADC Immediate (0x69) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0x01 + 0x01 + C = 3 (znoc)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Immediate (0x69) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x69]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x69u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Zero Page (0x65) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x65]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x65u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x65) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0eu
        memory[0x00fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Zero Page,X (0x75) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x75]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x75u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xf0u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
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
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute (0x6d) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x6d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
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
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x00 + 0x00 = 0x00 (Znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
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
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x00 + 0x01 = 0x01 (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x01 + 0x01 + C = 0x03 (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0xff + 0x01 = 0x00 (ZnoC) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x7f + 0x01 = 0x80 (zNOc) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x81 + 0x01 = 0x82 (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x80 + 0xff = 0x7f (znOC) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,X (0x7d) 0x80 + 0xff + C = 0x80 (zNoC) with cross page`() {
        val instruction = instructionSet.instructions[0x7d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
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
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x00 + 0x00 = 0x00 (Znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
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
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x00 + 0x01 = 0x01 (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x01 + 0x01 + C = 0x03 (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0xff + 0x01 = 0x00 (ZnoC) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x7f + 0x01 = 0x80 (zNOc) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x81 + 0x01 = 0x82 (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x80 + 0xff = 0x7f (znOC) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Absolute,Y (0x79) 0x80 + 0xff + C = 0x80 (zNoC) with cross page`() {
        val instruction = instructionSet.instructions[0x79]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x79u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xf0u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x00 + 0x00 = 0x00 (Znoc) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x00 + 0x01 = 0x01 (znoc) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x01 + 0x01 + C = 0x03 (znoc) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xefu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0xff + 0x01 = 0x00 (ZnoC) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x7f + 0x01 = 0x80 (zNOc) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x81 + 0x01 = 0x82 (zNoc) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x80 + 0xff = 0x7f (znOC) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,X (0x61) 0x80 + 0xff + C = 0x80 (zNoC) with wrap`() {
        val instruction = instructionSet.instructions[0x61]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x61u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xefu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x00 + 0x00 = 0x00 (Znoc)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x00 + 0x01 = 0x01 (znoc)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x01 + 0x01 + C = 0x03 (znoc)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0xffu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0xff + 0x01 = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x7f + 0x01 = 0x80 (zNOc)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x81 + 0x01 = 0x82 (zNoc)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x80 + 0xff = 0x7f (znOC)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x80 + 0xff + C = 0x80 (zNoC)`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0xffu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x00 + 0x00 = 0x00 (Znoc) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x00 + 0x01 = 0x01 (znoc) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x01 + 0x01 + C = 0x03 (znoc) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0xffu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0xff + 0x01 = 0x00 (ZnoC) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x7f + 0x01 = 0x80 (zNOc) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x81 + 0x01 = 0x82 (zNoc) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x80 + 0xff = 0x7f (znOC) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x80 + 0xff + C = 0x80 (zNoC) with wrap`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0xffu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x00 + 0x00 = 0x00 (Znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
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
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x00 + 0x01 = 0x01 (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x01 + 0x01 + C = 0x03 (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x03.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0xff + 0x01 = 0x00 (ZnoC) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x7f + 0x01 = 0x80 (zNOc) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x81 + 0x01 = 0x82 (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x82.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x80 + 0xff = 0x7f (znOC) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ADC Indirect,Y (0x71) 0x80 + 0xff + C = 0x80 (zNoC) with cross page`() {
        val instruction = instructionSet.instructions[0x71]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ADC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x71u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }
}