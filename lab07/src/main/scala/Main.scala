// def sumOpts(l: List[Option[Double]]): Option[Double] = { // DONE ! 
//   // 1. filter aby odsiac None (List -> List)
//   val filtered = l.filter(_.isDefined)

//   // 2. jesli lista jest pusta => zwroc None
//   if (filtered.size == 0) {
//     return None
//   }

//   // 3. reduce
//   filtered.reduce((acc, element) => {
//     Option(acc.get + element.get) // elem.get == ZWRACA ELEM JEŚLI ELEM ISTNIEJE == FUNKCJA DLA OPTIONS
//   })
// }

// @main
// def main(): Unit = {
//   val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
//   assert( sumOpts(lista) == Some(7.0) )       // ==> OK
//   assert( sumOpts(List()) == None)            // ==> OK
//   assert( sumOpts(List(None, None)) == None)  // ==> OK
// }

def position[T](l: List[T], el: T): Option[Int] = {
  val filtered = l.filter(_ != None)  // filtrowanie Noneów! 

  if (filtered.length == 0) {
    return None
  }

  val result = l.indexOf(el,3) // DRUGI PARAMETR TO INDEX OD KTÓREGO ZACZYNAMY SZUKANIE WŁĄCZNIE
  if (result == -1) { // JEŚLI INDEX OF NIE ZNAJDZIE ELEMENTU TO ZWRÓCI -1 !!!!
    return None
  }
  Some(result)
}

@main
def main2(): Unit = {
  val lista = List(2, 1,5, 1, 1,5)
  println(position(lista, 1)) // ==> Some(1)
  println(position(lista, 3.5)) // ==> None

}

def indices[A](l: List[A], el: A): Set[Int] = {
    Set()
}

@main3
def zadanie_26(): Unit = {
  val lista = List(1, 2, 1, 1, 5)
  indices(lista, 1) // ==> Set(0, 2, 3).
  indices(lista, 7) // ==> Set()

}
