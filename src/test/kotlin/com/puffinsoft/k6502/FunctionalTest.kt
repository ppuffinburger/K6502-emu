@file:OptIn(ExperimentalUnsignedTypes::class)

package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultBus
import com.puffinsoft.k6502.inbuilt.DefaultCpu
import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class FunctionalTest {
    @Test
    fun `test with 6502 functional test binary`() {
        // This test uses the 6502 functional test binary from
        // https://github.com/Klaus2m5/6502_65C02_functional_tests
        //
        // The test program will loop during success OR failures
        //
        // The success loop is at PC = 3469 at which point we'll break
        // out of the test loop and end the test
        //
        val fileBytes = File(javaClass.getResource("/6502_functional_test.bin")!!.toURI()).readBytes().toUByteArray()
        val memory = DefaultMemory(fileBytes.toTypedArray())
        val cpu = DefaultCpu(DefaultBus(memory))

        cpu.programCounter = 0x0400.toShort()

        while (true) {
            if (cpu.programCounter == 0x3469.toShort()) {
                println("Successful test!")
                break
            }
            cpu.nextClock()
            while (cpu.cycles > 0) {
                cpu.nextClock()
            }
        }
    }
}