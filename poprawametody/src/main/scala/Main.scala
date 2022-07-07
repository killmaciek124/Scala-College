import scala.annotation.tailrec
def sumOpts(l: List[Option[Double]]): Option[Double] = {
    if (l.size == 0)  return None

    val filtered= l
    .filter(_ != None)

    if (filtered.size == 0)  return None

    
    val res = filtered.foldLeft(0.0)((acum, elem) => {
      val sum = acum + elem.get
      sum
    })

    Some(res)


}
// @main
// def main: Unit = {
//   val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
//   println(sumOpts(lista))
//    assert( sumOpts(lista) == Some(7.0) )       // ==> OK
//    assert( sumOpts(List()) == None)            // ==> OK
//    assert( sumOpts(List(None, None)) == None)  // ==> OK
// }


 /// ===============================================================

/*
    Wykorzystując rekurencję (wyłącznie ogonową) zdefiniuj funkcję:

        def countResults[A,B,C](l1: List[A], l2: List[B])(f: (A, B) => C): Set[(C, Int)]

    która zaaplikuje funkcję „f” do elementów l1(i), l2(i), gdzie 0 <= i < min(l1.length, l2.length)
    oraz zwróci zbiór składający się z par:

        (wynik funkcji f, liczba par dla których f zwróciła dany wynik).

    Przykładowo dla:

        countResults(List(1,2,3), List(4,5,4,6))(_+_) == Set((5,1), (7,2))

    ponieważ: 1+4 = 5, 2+5 = 7, 4+3 = 7, 6 pomijamy bo to „nadmiarowy” element w drugiej z list.

    W rozwiązaniu należy skorzystać z mechanizmu dopasowania do wzorca (pattern matching).
    Nie używaj zmiennych ani „pętli” (while, for bez yield, foreach).
*/
def countResults[A,B,C](l1: List[A], l2: List[B])(f: (A, B) => C): Set[(C, Int)] = {
    @tailrec
      def helper[A,B,C](l1: List[A], l2: List[B], akum: List[C])(f: (A, B) => C): List[C] = (l1,l2) match {
        case (Nil, Nil) => akum
        case (Nil, head::tail) => akum
        case (head::tail, Nil) => akum
        case (head::tail, head2::tail2) =>
          val res = f(head, head2)
          helper(tail, tail2, akum.appended(res))(f)
      }
      val res = helper(l1,l2,List())(f)
      val FINAL= res
      .groupBy(x=> x)
      .map(x=> x(1))
      .map(x=> (x(0), x.size))
      .toSet

      // println(FINAL)
      FINAL
      }


@main
def main: Unit = {
    val res = countResults(List(1,2,3), List(4,5,4,6))(_+_) //== Set((5,1), (7,2))
    println(res)

}

// ===========================

/*
  Używając rekurencji ogonowej zdefiniuj funkcję:

    def pairwiseTest[A](l: List[A])(pred: (A, A) => Boolean)

  która sprawdzi, czy predykat „pred" jest spełniony dla wszystkich par
  elementów listy „l” o indeksach (i, długość(l) - i), dla i = 0.. długość(l)/2.

  Przykładowo, dla listy List(1,2,3,4,3,2,1) oraz predykatu równości, sprowadzi
  się to do następującej serii weryfikacji równości:

    l(0) == l(6)
    l(1) == l(5)
    l(2) == l(4)
    l(3) == l(3)

  Ogólnie, seria taka będzie miała postać:

    pred(l(0), l(l.length-1)) == true
    pred(l(1), l(l.length-2)) == true
    pred(l(2), l(l.length-3)) == true
    ...
    pred(l(l.length/2), l(l.length - l.length/2 - 1)) == true

  W przypadku pustej listy funkcja powinna zwrócić true

  Uwaga: w rozwiązaniu nie używaj zmiennych, ani mechanizmów imperatywnych jak pętle.
  Nie używaj też kolekcji języka Scala.
*/

def pairwiseTest[A](l: List[A])(pred: (A, A) => Boolean):Boolean = {
  @tailrec
  def helper(l: List[A])(pred: (A, A) => Boolean):Boolean = (l) match  {
    case Nil => true
    case head::Nil => true
    case head :: (mid:+last) => 
      if(pred(head, last) == true) {
        helper(mid)(pred)
      } else {
        false
      }
    
  }
  helper(l)(pred)
}

@main
def main2: Unit = {
  println(pairwiseTest(List(1,2,2,3,1))(_==_))
}



