def sumOpts(l: List[Option[Double]]): Option[Double] = {
  // 1. filter aby odsiac None (List -> List)
  val filtered = l.filter(_.isDefined)

  // 2. jesli lista jest pusta => zwroc None
  if (filtered.length == 0) {
    return None
  }

  // 3. reduce uzywajac +
  filtered.flatten.reduceOption(_ + _)
}

@main
def main(): Unit = {
  val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
  assert( sumOpts(lista) == Some(7.0) )       // ==> OK
  assert( sumOpts(List()) == None)            // ==> OK
  assert( sumOpts(List(None, None)) == None)  // ==> OK
}

def position[T](l: List[T], el: T): Option[Int] = {
  val filtered = l.filter(_ != None)  // XXX

  if (filtered.length == 0) {
    return None
  }

  val result = l.indexOf(el,0)
  if (result == -1) {
    return None
  }
  Some(result)
}

@main
def main2(): Unit = {
  val lista = List(2, 1, 1, 5)
  position(lista, 1) // ==> Some(1)
  println(position(lista, 3.5)) // ==> None

}
