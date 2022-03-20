import scala.io.Source
import java.util

class DataLoader {

  def load(fileName: String): util.ArrayList[Iris] = {
    val iris = new util.ArrayList[Iris]
    try {
    Source.fromFile(fileName).getLines.withFilter(line => line != "").foreach(f = line => {
        val data = line.split(",")
        iris.add(new Iris(data(0).toDouble, data(1).toDouble, data(2).toDouble, data(3).toDouble, data(4)))
      })
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    iris
  }
}
