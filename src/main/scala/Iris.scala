class Iris(var x: Double, var y: Double, var z: Double,var w: Double, var name: String) {

  def this() = this(0.0d,0.0d,0.0d,0.0d,null)

  def this(iris: Iris) {
    this()
    this.x = iris.x
    this.y = iris.y
    this.z = iris.z
    this.w = iris.w
    this.name = iris.name
  }

  def getX: Double = this.x

  def getY: Double = this.y

  def getZ: Double = this.z

  def getW: Double = this.w

  override def toString: String = {
    "Iris [ " + "Sepal.Length = " + x + ", Sepal.Width = " + y + ", Petal.Length = " + z + ", Petal.Width = " + w + ", espèce = '" + name + '\'' + " ]"
  }

  override def equals(arg: Any): Boolean = {
    arg match {
      case i:Iris =>
                    this.x == i.x &&
                    this.y == i.y &&
                    this.z == i.z &&
                    this.w == i.w &&
                    this.getClass == i.getClass

      case _      =>
                    false
    }
  }

}
