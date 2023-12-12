file://<WORKSPACE>/src/test/scala/gcd/Formal_UpDownCounter.scala
### java.lang.NullPointerException

occurred in the presentation compiler.

action parameters:
offset: 255
uri: file://<WORKSPACE>/src/test/scala/gcd/Formal_UpDownCounter.scala
text:
```scala
package Counters

import chisel3._
import chiseltest._
import chiseltest.formal._
import chiseltest.experimental._
import org.scalatest.flatspec.AnyFlatSpec

class Formal_UpDownCounter extends AnyFlatSpec with ChiselScalatestTester with Formal {
    def (@@)
        "UpDownCounter" should "pass" in {
            verify(new UpDownCounter(64.U), Seq(BoundedCheck(100)))
    }
    
    
}

class UpDownCounter_Test extends AnyFlatSpec with ChiselScalatestTester {
    "UpDownCounter" should "pass" in {
        test(new UpDownCounter(64.U)) { dut =>
            dut.io.upOrDown.poke(1.U)
            dut.io.reset.poke(0.U)
            dut.clock.step(50)
            dut.io.upOrDown.poke(0.U)
            dut.clock.step(50)
        }
    }
}
```



#### Error stacktrace:

```
scala.reflect.internal.Definitions$DefinitionsClass.isByNameParamType(Definitions.scala:428)
	scala.reflect.internal.TreeInfo.isStableIdent(TreeInfo.scala:140)
	scala.reflect.internal.TreeInfo.isStableIdentifier(TreeInfo.scala:113)
	scala.reflect.internal.TreeInfo.isPath(TreeInfo.scala:102)
	scala.tools.nsc.interactive.Global.stabilizedType(Global.scala:974)
	scala.tools.nsc.interactive.Global.typedTreeAt(Global.scala:822)
	scala.meta.internal.pc.SignatureHelpProvider.signatureHelp(SignatureHelpProvider.scala:23)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$signatureHelp$1(ScalaPresentationCompiler.scala:300)
```
#### Short summary: 

java.lang.NullPointerException