// def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = { // DONE 
//     val result = list.take(endIdx+1) // take bierze pierwsze X elementow z listy 
//     val finalresult = result.drop(begIdx) // drop usuwa pierwsze X elemntow z listy 
//     println(finalresult)
//     finalresult
// }


// @main
// def zadanie_19: Unit = {
//     val lista = List(1, 2, 3, 4, 5, 6, 7, 8)
//     // subseq(lista,1,2)
//     assert( subseq(lista, 2, 3) == List(3, 4) ) // ==> OK
//     assert( subseq(lista, 2, 1) == Nil )        // ==> OK
//     assert( subseq(lista, -1, 10) == lista )    // ==> OK
// }

// def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = { // DONE ! 
//     val (result1, result2) = list.partition(x=>{x < 0}) // partition zwraca 2 listy, jedna ktora spelnia warunek i druga ktora nie ! 
//     val finalresult2 = result2.filter(_ > 0) // filtrem eliminujemy liczby równe 0.0 ! 
//      var tuple = (result1, finalresult2)
//      println(tuple)  
//       tuple  
// }

// @main
// def zadanie_20: Unit = { // done ! 
//     val lista: List[Double] = List(1, -2, 0, 4, 5, 0, -7, 8)
//     assert( pairPosNeg(lista) == ( List(-2, -7), List(1, 4, 5, 8) ) ) // ==> OK
//       pairPosNeg(lista)

// }
 // TEORIA : 
    //  List[Int]().foldLeft(0)(_ + _) <== FOLD LEFT przyjmuje 2 argumenty : wartość początkową = 0 oraz funkcję dwuargumentową
    // jesli metoda wywolana jest na liscie pustej, zwracana jest wartosc poczatkowa = 0 
    // jesli metoda wywolana jest na liscie 1 elementowej , zwracane jest wynik funkcji elementu poczatkowego i elementu z listy 
    // ------------- || ------------------- wielo elementowej, zwracany jest wynik funkcji na elemencie poczatkowym i pierwszym elemencie listy ...
    // ... a potem kolejne elementy 
// def deStutter[A](list: List[A]): List[A] = { //  TO DO ! 
//     val res = list.foldLeft(List[A].empty) {
//     case (acc, i) if acc.lastOption.contains(i) =>  acc
//     case (acc, i)                               =>  acc.appended(i)
// }

//     res
//  }
// TEORIA : 
// METODA .distinct == dziala na stringa albo liste, zwraca listę BEZ POWTORZEN 
// mamy liste i chcemy policzyc ile jest w niej elementow

// lista.toSeq.groupBy(identity).mapValues(_.size).toList --- output ====> List[(element, liczbaWystapien)]

// dodawanie do siebie list albo setow
// l1 ++ l2
// usuwanie z l1 el. l2
// l1 -- l2

// set.intersect(anotherset) - czesc wspolna


// import scala.io.Source
// val dane = Source.fromFile("ogniem_i_mieczem.txt").toList 
// .getLines - cale linie

// distinct - unique elements - dziala na stringi i listy 

// TEORIA : val list: List[Int] = List(2,2,3) // reverse.head == .last !!!!!!!! 
// println(list.reverse.head) // 3 
// def deStutter[A](list: List[A]): List[A] = { // DONE ! 
//     val res = list.foldLeft(List(list.head): List[A])((acc, element) => {if (acc.last != element)  acc :+ element else acc})
//     res 

// }
// @main
// def zadanie_21: Unit = { 
//     val l = List(1, 1, 2, 4, 4, 4, 1, 3)
//     // assert( deStutter(l) == List(1, 2, 4, 1, 3) ) // ==> OK
//     val res = deStutter(l)
//     println(res)
// }

/// TEORIA : 
    // scala> Seq(1,2,3).map(_ + 1) = WYKonuje operacje na elementach kolekcjio
    //     val finalresult2 = result2.filter(_ > 0) // filtrem eliminujemy liczby równe 0.0 ! / usuwamy elementy mniejsze lub rowne 0
    // ZIP WITH INDEX = tworzy krotki 2 elementowe (element + index)
//     scala> Seq('a','b','c','d').zipWithIndex
// res115: Seq[(Char, Int)] = List((a,0), (b,1), (c,2), (d,3))


// def remElems[A](list: List[A], k: Int): List[A] = { // DONE ! 
//     val indexValue = list.zipWithIndex.filter(_(0) != k).map(el => el(0)) // <== WAŻNE ZASTOSOWANIE MAP ! !! 
//     println(indexValue)
//     indexValue
// }

// @main
// def zadanie_22: Unit = {
//     val l = List(1, 1, 2, 4, 4, 1, 3)
//     println( remElems(l, 2) == List(1, 1, 4, 4, 1, 3) ) // ==> true
//     println( remElems(l, -1) == l ) // ==> true
//     println( remElems(l, 15) == l ) // ==> true
// }

def freqMax[A](list: List[A]): (Set[A],Int)  = { //  DONE ! 
    val res = list.toSeq.groupBy(x=>x).mapValues(_.size).toList//.groupBy(x=> x(1)) // MAPVALUES wykonuje operacje na wartosciach mapy
    val maxValue = res.map(x=> x(1)).max
    val foo = res.filter(x=> x(1) == maxValue).map(x=> x(0)).toSet
    (foo,maxValue)
}
@main
def zadanie_23: Unit = {
    val l = List(1, 1, 2, 4, 4, 3, 4, 1, 3)
    println(freqMax(l))
    assert( freqMax(l) == (Set(1,4), 3) ) // ==> OK
}