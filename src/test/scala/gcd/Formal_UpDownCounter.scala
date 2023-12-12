package Counters

import chisel3._
import chiseltest._
import chiseltest.formal._
import chiseltest.experimental._
import org.scalatest.flatspec.AnyFlatSpec

class UpDownCounter_Test extends AnyFlatSpec with ChiselScalatestTester {
    "UpDownCounter" should "pass" in {
        test(new UpDownCounter(64.U)) { dut =>
            dut.io.upOrDown.poke(1.U)
            dut.io.reset.poke(0.U)
            dut.clock.step(100)
            dut.io.upOrDown.poke(0.U)
            dut.clock.step(100)
        }
    }
}

class Formal_UpDownCounter extends AnyFlatSpec with ChiselScalatestTester with Formal {
    "UpDownCounter" should "pass" in {
        verify(new UpDownCounter(64.U), Seq(BoundedCheck(100)))
    }
}