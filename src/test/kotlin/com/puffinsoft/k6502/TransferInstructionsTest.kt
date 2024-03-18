package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class TransferInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test TAX (0xaa) no flags set`() {
        val instruction = instructionSet.instructions[0xaa]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TAX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xaau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.x = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TAX (0xaa) zero flag set`() {
        val instruction = instructionSet.instructions[0xaa]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TAX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xaau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TAX (0xaa) negative flag set`() {
        val instruction = instructionSet.instructions[0xaa]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TAX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xaau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x8fu
        cpu.x = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test TAY (0xa8) no flags set`() {
        val instruction = instructionSet.instructions[0xa8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TAY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x42u
        cpu.y = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TAY (0xa8) zero flag set`() {
        val instruction = instructionSet.instructions[0xa8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TAY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TAY (0xa8) negative flag set`() {
        val instruction = instructionSet.instructions[0xa8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TAY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x8fu
        cpu.y = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.y).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test TSX (0xba) no flags set`() {
        val instruction = instructionSet.instructions[0xba]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TSX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x00u

        while (cpu.stackPointer != 0x7f.toUByte()) {
            cpu.writeStack(0x00.toUByte())
        }

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TSX (0xba) zero flag set`() {
        val instruction = instructionSet.instructions[0xba]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TSX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x42u

        while (cpu.stackPointer != 0x00.toUByte()) {
            cpu.writeStack(0x00.toUByte())
        }

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TSX (0xba) negative flag set`() {
        val instruction = instructionSet.instructions[0xba]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TSX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbau

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.x).isEqualTo(0xfd.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test TXA (0x8a) no flags set`() {
        val instruction = instructionSet.instructions[0x8a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TXA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x8au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x42u
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TXA (0x8a) zero flag set`() {
        val instruction = instructionSet.instructions[0x8a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TXA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x8au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x00u
        cpu.a = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TXA (0x8a) negative flag set`() {
        val instruction = instructionSet.instructions[0x8a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TXA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x8au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x8fu
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test TXS (0x9a)`() {
        val instruction = instructionSet.instructions[0x9a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TXS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x9au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x7fu
        // No flags get modified, so fudge them to check
        cpu.statusRegister.zeroFlag = true
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.stackPointer).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test TYA (0x98) no flags set`() {
        val instruction = instructionSet.instructions[0x98]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TYA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x98u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x42u
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TYA (0x98) zero flag set`() {
        val instruction = instructionSet.instructions[0x98]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TYA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x98u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x00u
        cpu.a = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test TYA (0x98) negative flag set`() {
        val instruction = instructionSet.instructions[0x98]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("TYA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x98u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x8fu
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }
}