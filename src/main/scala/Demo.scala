import breeze.plot._
import breeze.linalg._
import java.util


object Demo {

  def main(args: Array[String]): Unit = {

    val dataLoader = new DataLoader()
    val kMeans = new KMeans(dataLoader.load("iris.data"), 3)
    kMeans.cluster()
    kMeans.printClusters()
    val centroidsList = kMeans.getCentroids
    val pointsList = kMeans.getPoints

    val figure = Figure("K-means Iris")
    val p = figure.subplot(0)
    p.title = "Sepal datas"

    var i= 0
    while (i < pointsList.get(0).size) {
      p+=plot(List(pointsList.get(0).get(i).getX), List(pointsList.get(0).get(i).getY), '.', "[0,185,39]", "Cluster 1")
      i=i+1
    }

    i=0
    while (i < pointsList.get(1).size) {
      p+=plot(List(pointsList.get(1).get(i).getX), List(pointsList.get(1).get(i).getY), '.', "[255,0,0]", "Cluster 2")
      i=i+1
    }

    i=0
    while (i < pointsList.get(2).size) {
      p+=plot(List(pointsList.get(2).get(i).getX), List(pointsList.get(2).get(i).getY), '.', "[0,201,255]", "Cluster 3")
      i=i+1
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