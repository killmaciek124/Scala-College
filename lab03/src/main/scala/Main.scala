import scala.annotation.tailrec
// def reverse(str: String): String = {
//     @tailrec
//     def reverseHelper(str: String, akum: String = ""): String = {
//       if (str.size == 0) akum
//       else {
//         val letter = str.take(1)
//         val restbody = str.substring(1);
//         reverseHelper(restbody, letter+akum)
//       }
//     }
//         reverseHelper(str)
// }

// @main
// def lab3zad1(): Unit = {
//   println(reverse("penis"))
//   // println("bc"+"ab") // to sie rozni od "ab" + "bc"
// }

// def isPrime(num: Int): Boolean = {
//   @tailrec
//   def isPrimeHelper(num: Int, licznik: Int=0, divisor: Int=1): Boolean = {
//     if (divisor > num ) {
//       if (licznik == 2) {
//       return true
//       }else {
//         return false
//       }
//     }
//     if (num % divisor == 0) {
//       isPrimeHelper(num,licznik+1,divisor+1) 

//     } else {
//       isPrimeHelper(num,licznik,divisor+1) 

//     }
// }
//         isPrimeHelper(num)
// }

// @main
// def lab3zad2(): Unit = {
//   println(isPrime(7))
// }

// def fib(n: Int): Int = { 
//   @tailrec
//   def fibHelper(first: Int, second: Int, range: Int, licznik: Int = 1): Int = {
//     if (range == 1) {
//       1
//     } else if (range == 0) {
//       2
//     }else if (range == licznik) {
//       second
//     } else {
//       val newFirst = second
//       val newSecond = first + second
//       fibHelper(newFirst, newSecond, range, licznik+1)
//     }
//   }
//   fibHelper(2,1,n)
// }
// @main
// def lab3zad3(): Unit = {
//   println(fib(2))
// }
def shuffle(l1: List[Int], l2: List[Int]): List[Int] = {
  @tailrec
  def helper(l1: List[Int], l2: List[Int], acc: List[Int] = List()): List[Int] = {
    if (l1.isEmpty && l2.isEmpty) return acc //jesli obie sa puste to oddajemy akum

    if (l2.isEmpty || (l1.nonEmpty && l1.head <= l2.head)) { // 
      if (acc.nonEmpty && l1.head == acc.last) helper(l1.tail, l2, acc)
      else helper(l1.tail, l2, acc.appended(l1.head))
    } else {
      if (acc.nonEmpty && l2.head == acc.last) helper(l1, l2.tail, acc)
      else helper(l1, l2.tail, acc.appended(l2.head))
    }
  }

  helper(l1, l2)
}

@main
def lab3zad4(): Unit = { // REPEAT THIS 
  println(shuffle(List(2, 4, 3, 5), List(1, 2, 2, 3, 1, 5)))
}