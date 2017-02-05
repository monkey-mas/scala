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

  @Test
  def map() = {
    val test = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val plusOne = (i: Int) => i + 1

    val actual = test.map(plusOne)
    val expected = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    assert( actual.deep == expected.deep )
  }
}
