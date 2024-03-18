package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class SubtractWithBorrowBinaryInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test SBC Immediate (0xe9) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Immediate (0xe9) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Immediate (0xe9) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Immediate (0xe9) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Immediate (0xe9) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Immediate (0xe9) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Immediate (0xe9) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xe9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe9u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Zero Page (0xe5) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Zero Page (0xe5) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Zero Page (0xe5) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Zero Page (0xe5) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Zero Page (0xe5) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Zero Page (0xe5) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Zero Page (0xe5) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xe5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Zero Page,X (0xf5) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Zero Page,X (0xf5) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Zero Page,X (0xf5) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Zero Page,X (0xf5) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Zero Page,X (0xf5) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Zero Page,X (0xf5) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Zero Page,X (0xf5) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xf5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf5u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute (0xed) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute (0xed) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute (0xed) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Absolute (0xed) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute (0xed) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute (0xed) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute (0xed) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xed]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xedu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
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
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x00 - 0x00 = 0xff (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x01 - 0x01 = 0xff (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x01 - 0x01 - C = 0x00 (ZnoC) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
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
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0xff - 0x01 = 0xfd (zNoC) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x01 - 0x81 = 0x7f (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0xff - 0x80 = 0x7e (znoC) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,X (0xfd) 0x80 - 0x01 = 0x7e (znOC) with cross page`() {
        val instruction = instructionSet.instructions[0xfd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
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
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x00 - 0x00 = 0xff (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x01 - 0x01 = 0xff (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x01 - 0x01 - C = 0x00 (ZnoC) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
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
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0xff - 0x01 = 0xfd (zNoC) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x01 - 0x81 = 0x7f (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0xff - 0x80 = 0x7e (znoC) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Absolute,Y (0xf9) 0x80 - 0x01 = 0x7e (znOC) with cross page`() {
        val instruction = instructionSet.instructions[0xf9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
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
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
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
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
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
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x00 - 0x00 = 0xff (zNoc) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x01 - 0x01 = 0xff (zNoc) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x01 - 0x01 - C = 0x00 (ZnoC) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Indirect,X (0xe1) 0xff - 0x01 = 0xfd (zNoC) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x01 - 0x81 = 0x7f (znoc) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0xff - 0x80 = 0x7e (znoC) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,X (0xe1) 0x80 - 0x01 = 0x7e (znOC) with wrap`() {
        val instruction = instructionSet.instructions[0xe1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe1u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x00 - 0x00 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x01 = 0xff (zNoc)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x01 - C = 0x00 (ZnoC)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Indirect,Y (0xf1) 0xff - 0x01 = 0xfd (zNoC)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x81 = 0x7f (znoc)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0xff - 0x80 = 0x7e (znoC)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x80 - 0x01 = 0x7e (znOC)`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xfeu
        memory[0x00ff] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x00 - 0x00 = 0xff (zNoc) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x01 = 0xff (zNoc) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x01 - C = 0x00 (ZnoC) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Indirect,Y (0xf1) 0xff - 0x01 = 0xfd (zNoC) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x81 = 0x7f (znoc) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0xff - 0x80 = 0x7e (znoC) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x80 - 0x01 = 0x7e (znOC) with wrap`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xfeu
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x00 - 0x00 = 0xff (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
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
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x01 = 0xff (zNoc) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x01 - C = 0x00 (ZnoC) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test SBC Indirect,Y (0xf1) 0xff - 0x01 = 0xfd (zNoC) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x01 - 0x81 = 0x7f (znoc) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0xff - 0x80 = 0x7e (znoC) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SBC Indirect,Y (0xf1) 0x80 - 0x01 = 0x7e (znOC) with cross page`() {
        val instruction = instructionSet.instructions[0xf1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SBC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7e.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }
}