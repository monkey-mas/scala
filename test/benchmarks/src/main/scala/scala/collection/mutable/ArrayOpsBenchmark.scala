package scala.collection.mutable

import org.openjdk.jmh.annotations.Benchmark
import scala.util.Random

object ArrayOpsBenchmark {
  val array: Array[Int] = Array.fill(100000)(Random.nextInt)
  val sum: Int = array.sum
  val double = (i: Int) => i * 2
}

class OldIntArrayOps(override val repr: Array[Int]) extends AnyVal with ArrayLike[Int, Array[Int]] {
  override def length: Int = repr.length
  override def apply(index: Int): Int = repr(index)
  override def update(index: Int, elem: Int): Unit = { repr(index) = elem }
  override def seq = new WrappedArray.ofInt(repr)
  override protected[this] def newBuilder = new ArrayBuilder.ofInt
}

class ArrayOpsBenchmark {
  @Benchmark def foreachNew: Unit = {
    var i = 0
    ArrayOpsBenchmark.array.foreach(i += _)
    assert(i == ArrayOpsBenchmark.sum)
  }

  @Benchmark def mapNew: Unit = {
    val doubledArray = ArrayOpsBenchmark.array.map(ArrayOpsBenchmark.double)
//    var i = 0
//    val length = ArrayOpsBenchmark.array.length
//    while (i < length) {
//      assert(ArrayOpsBenchmark.double(ArrayOpsBenchmark.array(i)) == doubledArray(i))
//      i += 1
//    }
  }

  @Benchmark def foreachOld: Unit = {
    var i = 0
    new OldIntArrayOps(ArrayOpsBenchmark.array).foreach(i += _)
    assert(i == ArrayOpsBenchmark.sum)
  }

  @Benchmark def mapOld: Unit = {
    val doubledArray = new OldIntArrayOps(ArrayOpsBenchmark.array).map(ArrayOpsBenchmark.double)
//    var i = 0
//    val length = ArrayOpsBenchmark.array.length
//    while (i < length) {
//      assert(ArrayOpsBenchmark.double(ArrayOpsBenchmark.array(i)) == doubledArray(i))
//      i += 1
//    }
  }
}
