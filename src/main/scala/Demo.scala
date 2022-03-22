import breeze.plot._
import breeze.linalg._
import java.util
import scala.util.Random

object Demo {

  def main(args: Array[String]): Unit = {

    val dataLoader = new DataLoader()
    val kMeans = new KMeans(dataLoader.load("iris.data"), 3)
    kMeans.cluster()
    kMeans.printClusters()

    val centroidsList = kMeans.getCentroids
    val pointsList = kMeans.getPoints

    val rand = scala.util.Random
    val figure = Figure("K-means Iris")
    val p = figure.subplot(0)
    p.title = "Sepal datas"

    var i= 0
    var j= 0
    var randColor = 0
    val colorsArray = List("[0,255,39]","[0,255,251]","[220,0,255]","[255,0,108]","[106,45,245]","[245,112,45]","[20,159,255]")
    while (i < pointsList.size()) {
      j=0
      randColor = rand.nextInt(colorsArray.size)
      while (j < pointsList.get(i).size()) {
        p+=plot(List(pointsList.get(i).get(j).getX), List(pointsList.get(i).get(j).getY), '.', colorsArray(randColor), "Cluster")
        j+=1
      }
      i+=1
    }

    i=0
    while (i < centroidsList.size) {
      p+=plot(List(centroidsList.get(i).getX), List(centroidsList.get(i).getY), '+', "[0,0,0]")
      i=i+1
    }

    p.xlabel = "Sepal.Length"
    p.ylabel = "Sepal.Width"
    figure.saveas("test.png")

  }

}