// import scala.annotation.tailrec

// // MATCH PATTERN NOTES :
// //   l match {
// //   case Nil          => "the list is empty"
// //   case head :: Nil  => "the least has one element"
// //   case head :: tail => "thie list has a head element and a tail of at least one element"
// // }
// def sumuj(l: List[Option[Double]]): Option[Double] = { // DONE ! MOJE 
//   @tailrec
//   def helper(l: List[Option[Double]], akum: Double=0): Option[Double] = (l) match {
//     case Nil => {
//       if (akum == 0.0) {
//         None
//       } else Some(akum)
//     }
//     case head :: tail => {
//       if (head.getOrElse(0.0) >  0) { // getorElse = jeżeli option[costam] nie bedzie istniec to zwroci 0.0 
//         helper(tail,akum + head.get) // .get == daje ci element w option jesli istnieje a jesli nie to cos innego 
//       } else helper(tail,akum)
//     }
// }
//   helper(l)
// }
// @main
// def zadanie_10: Unit = {
//     val lista = List(Some(4.0), Some(-3.0), None, Some(1.0), Some(0.0))
//     val wynik = sumuj(lista) // ==> Some(5.0)
//     println(wynik)
// }

// ///=====================================================================================
//  /// MOJE ROZWIAZANIE MAKSIMUM : 
//   def maksimum(l1: List[Double], l2: List[Double]): List[Double] = { // DONE!!!!!! MOJE !!
//     @tailrec
//     def helper(l1: List[Double], l2: List[Double], akum: List[Double] = List()): List[Double] = (l1,l2) match {
//       case (Nil,Nil) => akum
//       case (head::tail,Nil) => helper(tail,Nil,akum.appended(head))
//       case (Nil,head::tail) => helper(Nil,tail,akum.appended(head))
//       case (head::tail,head2::tail2) => {
//         if (head > head2) {
//           helper(tail,tail2,akum.appended(head))
//         } else helper(tail,tail2,akum.appended(head2))

//       }

//     }
//     helper(l1,l2)
// }

// @main
// def zadanie_11: Unit = {
//     val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
//     val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
//     val wynik = maksimum(lista1, lista2) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
//     println(wynik)
// }
// // ===========================================

// def usun[A](l: List[A], el: A): List[A] = { // DONE ! 
//   @tailrec
//     def helper[A](l: List[A], el: A, akum: List[A]=List()): List[A] = (l) match {
//       case Nil => akum
//       case head::tail => {
//         if (head != el) {
//           helper(tail, el,akum.appended(head))
//         } else  helper(tail, el,akum)

//       }
//     }
//     helper[A](l,el)
// }

// @main
// def zadanie_12: Unit = {
//     val lista = List(2, 1, 4, 1, 3, 3, 1, 2)
//     val wynik = usun(lista, 3) // ==> List(2, 4, 3, 3, 2).
//     println(wynik)
// }

// def divide[A](l: List[A]): (List[A], List[A]) = { // DONE EASILY
//   @tailrec
//   def helper[A](l: List[A], akum1: List[A]= List(), akum2: List[A]= List(), iterator: Int=0): (List[A], List[A]) = (l) match {
//     case Nil => (akum1,akum2)
//     case  head::tail => {
//       if (iterator % 2 == 0) { 
//         helper(tail,akum1.appended(head),akum2,iterator+1)
//       } else  helper(tail,akum1,akum2.appended(head),iterator+1)

      
//     }
//   }
//   helper[A](l)
// } 

// @main
// def zadanie_13: Unit = {
//     val lista = List(1, 3, 5, 6, 7)
//     val res = divide(lista) // ==>  ( List(1, 5, 7), List(3, 6) ).  
//     println(res)
// } 

//// ===================================================

// TEORIA :
  //-----------------------------------------------
// Generyczność (rozwiązań/struktur/algorytmów)
//-----------------------------------------------

// Zdefiniujmy typ predykatów na liczbach całkowitych:
// type IntPred = Int => Boolean

// // a teraz – operację koniunkcji na IntPred 
// def intAnd(p: IntPred, q: IntPred): IntPred = {
//     n => p(n) && q(n)
// }

// // przykładowe predykaty
// val mniejszaNiż9 = (n: Int) => n < 9
// val większaNiż5 = (n: Int) => n > 5

// // i ich koniunkcja
// val r = intAnd(mniejszaNiż9, większaNiż5) // :IntPred

// r(3)
// r(7)
// r(10)

// PRZYKŁAD AND : 

type Pred[A] = A => Boolean
// // A predicate is a function that returns a Boolean. <==!!!!



def and[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    (x: A) => p(x) && q(x)
}

def and[A](p: Pred[A], q: Pred[A]): Pred[A] = {
  (x: A) => p(x) && q(x)
}

def or[A](p: Pred[A], q: Pred[A]): Pred[A] = {
  (x: A) => p(x) || q(x)
}

def not[A](p: Pred[A]): Pred[A] = {
  (x: A) => !p(x)
}

def imp[A](p: Pred[A], q: Pred[A]): Pred[A] = {
  (x: A) => !p(x) || q(x)
}

@main
def zadanie_14: Unit = {   // TO DO AND TO REPEAT!!!!
}










/// =============================================
//// JAKIES ROZWIAZANIA JACKOWE : 
/*
def recsum(l1: List[Double], l2: List[Double]): List[Double] = (l1, l2) match {
    case (Nil, Nil) => 0
    case (h1 :: t1, Nil) => {
      h1 + recsum(t1, Nil)
    }
    case (Nil, h2 :: t2) => {
      
    }
    case (h1 :: t1, h2 :: t2) => {

    }
}
*/

/*
def recelemlength(l1: List[String]): List[Long] = l1 match {
    case Nil => 0
    case head :: tail => {
        h
    }
}
*/

// @tailrec
// def everyOther(list: List[String]): List[String] = list match {
//   case Nil          => list
//   case _ :: Nil     => list
//   case x :: _ :: xs => { x :: everyOther(xs) }
// }

// def reccount(l1: List[String]): Long = l1 match {
//     case Nil => 0
//     case head :: tail => {
//         1 + reccount(tail)
//     }
// }

// @main
// def zadanie_11: Unit = {
//     //val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
//     //val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
//     //val wynik = maksimum(lista1, lista2) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
//     //println(wynik)
//     println(everyOther(List("a", "b", "c", "d")))
// }