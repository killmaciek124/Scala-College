// import scala.annotation.tailrec
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

def fib(n: Int): Int = { // TUTAJ ZACZNIJ WWE WTOOREK <==============!
  0
}
@main
def lab3zad3(): Unit = {
  // println(fib(5))
}

// def shuffle(l1: List[Int], l2: List[Int]): List[Int] = {
  
// }

// @main
// def lab3zad4(): Unit = {
//   // println(shuffle(List(2, 4, 3, 5), List(1, 2, 2, 3, 1, 5)))
// }

// //git test

