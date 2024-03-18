package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultBus
import com.puffinsoft.k6502.inbuilt.DefaultCpu
import com.puffinsoft.k6502.inbuilt.DefaultMemory

abstract class BaseInstructionsTest {
    protected val instructionSet = InstructionSet()

    protected fun createCpu(memory: DefaultMemory): Cpu {
        // create cpu
        val cpu = DefaultCpu(DefaultBus(memory))

        // step through reset cycles
        for (resetCycles in 1..8) {
            cpu.nextClock()
        }

        return cpu
    }

    protected fun executeStepCount(cpu: Cpu, expectedCycles: Int): Boolean {
        for (cycles in 1..expectedCycles) {
            cpu.nextClock()
        }
        return cpu.cycles == 0
    }
}