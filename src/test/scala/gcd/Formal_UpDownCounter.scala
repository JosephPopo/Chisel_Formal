package Counters

import chisel3._
import chiseltest._
import chiseltest.formal._
import chiseltest.experimental._
import org.scalatest.flatspec.AnyFlatSpec

class Formal_UpDownCounter extends AnyFlatSpec with ChiselScalatestTester with Formal {
    "UpDownCounter" should "pass" in {
        verify(new UpDownCounter(64.U), Seq(BoundedCheck(100)))
    }
}