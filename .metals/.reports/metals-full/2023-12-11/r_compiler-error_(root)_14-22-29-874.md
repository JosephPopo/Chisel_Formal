file://<WORKSPACE>/src/main/scala/gcd/UpDownCounter.scala
### java.lang.StringIndexOutOfBoundsException: offset 602, count -8, length 762

occurred in the presentation compiler.

action parameters:
offset: 608
uri: file://<WORKSPACE>/src/main/scala/gcd/UpDownCounter.scala
text:
```scala
package gcd

import chisel3._
import chisel3.stage.ChiselOptions._
import chisel3.util._

class UpDownCounter(val max: UInt) extends Module {
    val io = IO(new Bundle {

        val upOrDown = Input(Bool())
        val reset    = Input(Bool())
        val out   = Output(UInt(max.getWidth.W))
    })

    val count = RegInit(0.U(max.getWidth.W))

    when(io.reset) {
        count := 0.U 
    } .elsewhen(io.upOrDown) { 
        count := Mux(count === max - 1.U, 0.U, count + 1.U)
    } .otherwise {
        count := Mux(count === 0.U, max - 1.U, count - 1.U)
    }

    io.out := count
    assert(io.out @@)
}

object UpDownCounter extends App {
  println("Generating the hardware")
  emitVerilog(new UpDownCounter(16.U), Array("--target-dir", "generated"))
}
```



#### Error stacktrace:

```
java.base/java.lang.String.checkBoundsOffCount(String.java:3304)
	java.base/java.lang.String.rangeCheck(String.java:280)
	java.base/java.lang.String.<init>(String.java:276)
	scala.tools.nsc.interactive.Global.typeCompletions$1(Global.scala:1245)
	scala.tools.nsc.interactive.Global.completionsAt(Global.scala:1283)
	scala.meta.internal.pc.SignatureHelpProvider.$anonfun$treeSymbol$1(SignatureHelpProvider.scala:390)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.pc.SignatureHelpProvider.treeSymbol(SignatureHelpProvider.scala:388)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCall$.unapply(SignatureHelpProvider.scala:205)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCallTraverser.visit(SignatureHelpProvider.scala:316)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCallTraverser.traverse(SignatureHelpProvider.scala:310)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCallTraverser.fromTree(SignatureHelpProvider.scala:279)
	scala.meta.internal.pc.SignatureHelpProvider.signatureHelp(SignatureHelpProvider.scala:27)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$signatureHelp$1(ScalaPresentationCompiler.scala:282)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: offset 602, count -8, length 762