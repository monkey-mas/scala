package scala.collection.mutable

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test

@RunWith(classOf[JUnit4])
class ArrayOpsTest {
  @Test
  def foreach() = {
    val test = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    var idx = 0

    test.foreach { i =>
      assert(i == idx )
      idx += 1
    }
    assert( idx == 10 )
  }
}
