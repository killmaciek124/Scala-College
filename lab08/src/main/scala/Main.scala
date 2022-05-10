import scala.runtime.Tuples
def black(guess: List[Int], secret: List[Int]): Int = {
  guess.zip(secret).count(p => p._1 == p._2)
}

def white(black: Int, guess: List[Int], secret: List[Int]): Int = {
  val result = guess.intersect(secret)
  result.size-black
}

@main 
def zadanie_30: Unit = {
  val blackBalls = black(List(1, 3, 2, 2, 4, 5), List(2, 1, 2, 4, 7, 2))
  println(blackBalls)
  println(white(blackBalls, List(1, 3, 2, 2, 4, 5), List(2, 1, 2, 4, 7, 2)))
}


// def addPartGrade(player: List[Any], partGrade: List[Any]): List[Any] = {
//   val grade1 = partGrade(2) + player(2)
//   val grade2 = partGrade(3) + player(3)
//   player(2) = grade1
//   player(3) = grade2
//   player 

  
// }

// @main 
// def zadanie_31: Unit = { // TO DO ! 
// val player1=("Mike", "Myers", 0, 0)
// val player2=("Jan", "Kowalski", 0,0)
// player1 = addPartGrade(player1,List("Mike", "Myers", 4, 10))
// println(player1)
// }
def isPrime3(n: Int): Boolean = {
    if (n == 2) {
      true
    } else if (n < 2 || n % 2 == 0) {
      false
    } else {
      Stream.from(3, 2).takeWhile(i => i * i < n + 1).forall(i => n % i != 0)
    }
  }

def threeNumbers(n: Int): List[(Int, Int, Int)] = {
    val a = (1,n)
    val b = (2,n)
    Map(a,b).forall(p => (p._1*p._2))
}

@main 
def zadanie_32: Unit = { 
  println(threeNumbers(3))
}



