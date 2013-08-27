package cc.hypo.utilities

import java.util.zip._

object Zlib {
  def inflate(bytes: Array[Byte]): Array[Byte] = {
    val inflater = new Inflater
    inflater.setInput(bytes, 0, bytes.length)

    var decompressedBytes = Array[Byte]()

    while (!inflater.finished) {
      val buffer = new Array[Byte](1024 * 10)
      val length = inflater.inflate(buffer)
      decompressedBytes = decompressedBytes ++ buffer.take(length)
    }
    decompressedBytes
  }
}