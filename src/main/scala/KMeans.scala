import java.util
import java.util.concurrent.ThreadLocalRandom
import scala.concurrent.duration.Duration.Infinite

class KMeans {

  private var irises : util.ArrayList[Iris] = null
  private val centroids = new util.ArrayList[Iris]()
  private val clusters = new util.ArrayList[util.ArrayList[Iris]]()
  private val previousCentroids = new util.ArrayList[Iris]()

  def this(irises: util.ArrayList[Iris], k: Int) {
    this()
    this.irises = irises
    for (i <- 0 until k) {
      centroids.add(new Iris(irises.get(ThreadLocalRandom.current.nextInt(irises.size / k) * (i + 1))))

      previousCentroids.add(new Iris(centroids.get(i)))
      clusters.add(new util.ArrayList[Iris])
    }
  }

  def cluster(): Unit = {
    var isOver = false
    var counter = 0
    while (!isOver) {
      clearClusters()
      addPointsToClusters()
      moveCentroids()
      counter += 1
      isOver = !haveCentroidsMoved
    }
    println("Nous avons eu " + counter + " iterations pour clusteriser.")
    println("---------------------------------------------------------------------------------------------------------------")
    println("Centroids :")
    var i = 0
    while (i < centroids.size()) {
      if (centroids.get(i).name == "Centroid"
        && !centroids.get(i).getX.isInfinity
        && !centroids.get(i).getY.isInfinity
        && !centroids.get(i).getW.isInfinity
        && !centroids.get(i).getZ.isInfinity)
      {
        println(centroids.get(i))
      }
      i+=1
    }
    println("---------------------------------------------------------------------------------------------------------------")
  }

  def getCentroids: util.ArrayList[Iris] = {
    val centroidsList = new util.ArrayList[Iris]
    var i = 0
    while (i < centroids.size()) {
      if (centroids.get(i).name == "Centroid"
        && !centroids.get(i).getX.isInfinity
        && !centroids.get(i).getY.isInfinity
        && !centroids.get(i).getW.isInfinity
        && !centroids.get(i).getZ.isInfinity)
      {
        centroidsList.add(centroids.get(i))
      }
      i+=1
    }
    centroidsList
  }

  def getPoints: util.ArrayList[util.ArrayList[Iris]] = {
    val pointsList = new util.ArrayList[util.ArrayList[Iris]]
    var i = 0
    while (i < clusters.size) {
      pointsList.add(clusters.get(i))
      i=i+1
    }
    pointsList
  }


  def printClusters(): Unit = {
    var i = 0
    while ( i < clusters.size) {
      println("Cluster " + (i + 1))
      var j = 0
      while (j < clusters.get(i).size) {
        println(clusters.get(i).get(j))
        j += 1
      }
      println("---------------------------------------------------------------------------------------------------------------")
      i += 1
    }
  }

  private def haveCentroidsMoved: Boolean = {
    var i = 0
    while ( i < centroids.size) {
      if (!centroids.get(i).equals(previousCentroids.get(i))) return true
      i += 1
    }
    false
  }

  private def clearClusters(): Unit = {
    var i = 0
    while (i < clusters.size) {
      clusters.get(i).clear()
      i += 1
    }
  }

  private def addPointsToClusters(): Unit = {
    var i = 0
    while (i < irises.size) {
      clusters.get(findClosestCentroid(irises.get(i))).add(irises.get(i))
      i += 1
    }
  }

  private def distance(a: Iris, b: Iris) = {
    Math.sqrt((a.getX - b.getX) * (a.getX - b.getX) + (a.getY - b.getY) * (a.getY - b.getY) + (a.getZ - b.getZ) * (a.getZ - b.getZ) + (a.getW - b.getW) * (a.getW - b.getW))
  }

  private def moveCentroids(): Unit = {
    var x = 0.0d
    var y = 0.0d
    var z = 0.0d
    var w = 0.0d
    var i = 0
    while ( i < clusters.size) {
      var j = 0
      while ( j < clusters.get(i).size) {
        x += clusters.get(i).get(j).getX
        y += clusters.get(i).get(j).getY
        z += clusters.get(i).get(j).getZ
        w += clusters.get(i).get(j).getW
        j += 1
      }
      x /= clusters.get(i).size
      y /= clusters.get(i).size
      z /= clusters.get(i).size
      w /= clusters.get(i).size
      previousCentroids.set(i, new Iris(centroids.get(i)))
      centroids.set(i, new Iris(x, y, z, w, "Centroid"))
      i += 1
    }
    i = 0
    /* Afficher toutes les étapes des mouvements des centroids

    while (i < centroids.size()) {
      if (centroids.get(i).name == "Centroid") {
        println(centroids.get(i))
        i+=1
      }
    }

     */
  }

    private def findClosestCentroid(a: Iris) : Int = {
      var minDistance = this.distance(a, centroids.get(0))
      var distance = .0
      var closestCentroid = 0
      var i = 1
      while ( i < centroids.size) {
        distance = this.distance(a, centroids.get(i))
        if (distance < minDistance) {
          minDistance = distance
          closestCentroid = i
        }
        i += 1
      }
      closestCentroid
    }

}
