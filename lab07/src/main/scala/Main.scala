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

// def position[T](l: List[T], el: T): Option[Int] = {
//   val filtered = l.filter(_ != None)  // filtrowanie Noneów! 

//   if (filtered.length == 0) {
//     return None
//   }

//   val result = l.indexOf(el,3) // DRUGI PARAMETR TO INDEX OD KTÓREGO ZACZYNAMY SZUKANIE WŁĄCZNIE
//   if (result == -1) { // JEŚLI INDEX OF NIE ZNAJDZIE ELEMENTU TO ZWRÓCI -1 !!!!
//     return None
//   }
//   Some(result)
// }

// @main
// def main2(): Unit = {
//   val lista = List(2, 1,5, 1, 1,5)
//   println(position(lista, 1)) // ==> Some(1)
//   println(position(lista, 3.5)) // ==> None

// }

// def indices[A](l: List[A], el: A): Set[Int] = { // DONE ! 
//   val res = l.zipWithIndex.filter(_(0) == el).map(n => n(1))
//   res.toSet
// }

// @main
// def zadanie_26(): Unit = {
//   val lista = List(1, 2, 1, 1, 5)
//   println(indices(lista, 1)) // ==> Set(0, 2, 3).
//   println(indices(lista, 7)) // ==> Set()

// }

// def swap[A](l: List[A]): List[A] = { //  TO DO ! 
//     val res = l.groupBy(l.indexOf(_) % 2) // groupBy grupuje nam na Hashmape elementy wzgledem wyniku warunku jakby 
//     val evens = res(0).map(List(_)) // parzysta zawsze dluzsza
//     val odds = res(1).map(List(_))
//     println(odds)
//     println(evens)

//     // TEORIA : 
// //       scala> Seq(1,2,3,4,5).zipAll(Seq('a','b','c','d'),9,'z') // ZIP ALL TWORZY DWU ELEMENTOWE KROTKI Z DWOCH LIST ! 
// // res113: Seq[(Int, Char)] = List((1,a), (2,b), (3,c), (4,d), (5,z)) (ostatnie dwie wartosci to jakby listy byly roznej dlugosci co dodamy)
//     val ress = evens.zipAll(odds,Nil,Nil).flatMap(Function.tupled(_ ::: _))
//     println(ress)
//     l
    
    
    
// }

// @main
// def zadanie_27(): Unit = {
//   val lista = List(1, 2, 3, 4, 5)
//   println(swap(lista)) // ==> List(2, 1, 4, 3, 5)
// }



// def sortZones(l: List[String]): List[String] = { // DONE ! 
//   val res = l.filter(_.slice(0,6) == "Europe").map(x => x.stripPrefix("Europe/")).sorted // SLICE WYLAPUJE ZE STRINGA LUB LISTY WARTOSCI OD INT DO INT
//   res // .SORTED == SORTUJE 
// }

// @main
// def main(): Unit = {
//   // def stripPrefix(prefix: String): String // funkcja wbudowana == OBCINA NAM STRINGA !!!!
//   val strefy: List[String] = java.util.TimeZone.getAvailableIDs.toList
//   println(sortZones(strefy))
//   // println(strefy)
//   println("ala ma kota".stripPrefix("ala ")) // ==> "ma kota"

// }
// def freq[A](l: List[A]): List[(A, Int)] = { // DONE ! 
//     l.groupBy(x => x).toList.map(x => (x(0),x(1).size) ) // WAŻNE ZASTOSOWANIE GROUPBY (POWTARZAJACE SIE ELEMENTY)

// }

// @main 
// def main(): Unit = {
//   val lista = List('a','b','a','c','c','a')
//   println(freq(lista)) // ==> List(('a', 3),('b', 1),('c', 2))
// }