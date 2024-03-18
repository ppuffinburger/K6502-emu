package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class DecAndIncInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test DEC Zero Page (0xc6) no flags set`() {
        val instruction = instructionSet.instructions[0xc6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc6u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x43u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Zero Page (0xc6) zero flag set`() {
        val instruction = instructionSet.instructions[0xc6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc6u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Zero Page (0xc6) negative flag set`() {
        val instruction = instructionSet.instructions[0xc6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc6u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x90u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test DEC Zero Page,X (0xd6) no flags set`() {
        val instruction = instructionSet.instructions[0xd6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd6u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x43u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Zero Page,X (0xd6) zero flag set`() {
        val instruction = instructionSet.instructions[0xd6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd6u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Zero Page,X (0xd6) negative flag set`() {
        val instruction = instructionSet.instructions[0xd6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd6u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x90u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test DEC Absolute (0xce) no flags set`() {
        val instruction = instructionSet.instructions[0xce]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xceu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x43u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Absolute (0xce) zero flag set`() {
        val instruction = instructionSet.instructions[0xce]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xceu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Absolute (0xce) negative flag set`() {
        val instruction = instructionSet.instructions[0xce]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xceu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x90u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test DEC Absolute,X (0xde) no flags set`() {
        val instruction = instructionSet.instructions[0xde]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xdeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x43u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Absolute,X (0xde) zero flag set`() {
        val instruction = instructionSet.instructions[0xde]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xdeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Absolute,X (0xde) negative flag set`() {
        val instruction = instructionSet.instructions[0xde]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xdeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x90u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test DEC Absolute,X (0xde) no flags set with cross page`() {
        val instruction = instructionSet.instructions[0xde]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xdeu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x43u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Absolute,X (0xde) zero flag set with cross page`() {
        val instruction = instructionSet.instructions[0xde]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xdeu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEC Absolute,X (0xde) negative flag set with cross page`() {
        val instruction = instructionSet.instructions[0xde]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xdeu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x90u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test DEX (0xca) with no flags set`() {
        val instruction = instructionSet.instructions[0xca]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xcau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x43u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEX (0xca) with zero flag set`() {
        val instruction = instructionSet.instructions[0xca]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xcau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEX (0xca) with negative flag set`() {
        val instruction = instructionSet.instructions[0xca]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xcau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x90u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test DEY (0x88) with no flags set`() {
        val instruction = instructionSet.instructions[0x88]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x88u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x43u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEY (0x88) with zero flag set`() {
        val instruction = instructionSet.instructions[0x88]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x88u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test DEY (0x88) with negative flag set`() {
        val instruction = instructionSet.instructions[0x88]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("DEY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x88u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x90u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INC Zero Page (0xe6) no flags set`() {
        val instruction = instructionSet.instructions[0xe6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe6u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x41u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Zero Page (0xe6) zero flag set`() {
        val instruction = instructionSet.instructions[0xe6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe6u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Zero Page (0xe6) negative flag set`() {
        val instruction = instructionSet.instructions[0xe6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe6u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x8eu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INC Zero Page,X (0xf6) no flags set`() {
        val instruction = instructionSet.instructions[0xf6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf6u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x41u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Zero Page,X (0xf6) zero flag set`() {
        val instruction = instructionSet.instructions[0xf6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf6u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Zero Page,X (0xf6) negative flag set`() {
        val instruction = instructionSet.instructions[0xf6]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf6u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x8eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INC Absolute (0xee) no flags set`() {
        val instruction = instructionSet.instructions[0xee]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xeeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x41u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Absolute (0xee) zero flag set`() {
        val instruction = instructionSet.instructions[0xee]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xeeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Absolute (0xee) negative flag set`() {
        val instruction = instructionSet.instructions[0xee]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xeeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x8eu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INC Absolute,X (0xfe) no flags set`() {
        val instruction = instructionSet.instructions[0xfe]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x41u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Absolute,X (0xfe) zero flag set`() {
        val instruction = instructionSet.instructions[0xfe]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Absolute,X (0xfe) negative flag set`() {
        val instruction = instructionSet.instructions[0xfe]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfeu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x8eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INC Absolute,X (0xfe) no flags set with cross page`() {
        val instruction = instructionSet.instructions[0xfe]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfeu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x41u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Absolute,X (0xfe) zero flag set with cross page`() {
        val instruction = instructionSet.instructions[0xfe]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfeu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INC Absolute,X (0xfe) negative flag set with cross page`() {
        val instruction = instructionSet.instructions[0xfe]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xfeu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x8eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0300]).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INX (0xe8) with no flags set`() {
        val instruction = instructionSet.instructions[0xe8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x41u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INX (0xe8) with zero flag set`() {
        val instruction = instructionSet.instructions[0xe8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INX (0xe8) with negative flag set`() {
        val instruction = instructionSet.instructions[0xe8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xe8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x8eu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test INY (0xc8) with no flags set`() {
        val instruction = instructionSet.instructions[0xc8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x41u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INY (0xc8) with zero flag set`() {
        val instruction = instructionSet.instructions[0xc8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test INY (0xc8) with negative flag set`() {
        val instruction = instructionSet.instructions[0xc8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("INY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x8eu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }
}