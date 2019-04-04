import example.Main
import org.scalatest.FunSuite

class CubeCalculatorTest extends FunSuite{
  test("Main.cube"){
    assert(Main.cube(3) === 27)
  }
}
