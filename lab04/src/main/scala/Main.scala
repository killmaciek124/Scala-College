import scala.annotation.tailrec

/*
def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
   @tailrec
  def maksimumHelper(l1: List[Double], l2: List[Double], listAkumulator: List[Double]=List()): List[Double] = (l1,l2) match {
    case (head::tail, head2::tail2) => {
      if (head > head2) {
        
      }
    }                                     
}
maksimumHelper(l1,l2)
}
*/

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

@tailrec
def everyOther(list: List[String]): List[String] = list match {
  case Nil          => list
  case _ :: Nil     => list
  case x :: _ :: xs => { x :: everyOther(xs) }
}

def reccount(l1: List[String]): Long = l1 match {
    case Nil => 0
    case head :: tail => {
        1 + reccount(tail)
    }
}

@main
def zadanie_11: Unit = {
    //val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
    //val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
    //val wynik = maksimum(lista1, lista2) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
    //println(wynik)
    println(everyOther(List("a", "b", "c", "d")))
}