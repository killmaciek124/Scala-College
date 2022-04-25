import scala.annotation.tailrec
def reverse(str: String): String = {
  @tailrec
  def helper(str: String, acc: String = ""): String = {
    if (str.isEmpty) return acc

    helper(str.tail, str.head + acc )
  }

  helper(str)
}

@main
def lab3zad1(): Unit = {
  println(reverse("owoc"))
}

def isPrime(num: Int): Boolean = {
  @tailrec
  def helper(num: Int, divisor: Int = 2): Boolean = {
    if (num < 2 && num > -2) return false
    if (num == 2) return true
    if (num % divisor == 0) return false
    if (divisor * divisor > num) return true

    helper(num, divisor + 1)
  }

  helper(num)
}

@main
def lab3zad2(): Unit = {
  println(isPrime(-7))
}

def fib(n: Int): Int = {
  @tailrec
  def helper(n: Int, acc1: Int = 2, acc2: Int = 1): Int = {
    if (n == 0) return acc1

    helper(n - 1, acc2, acc1 + acc2)
  }

  helper(n)
}

@main
def lab3zad3(): Unit = {
  println(fib(5))
}

def shuffle(l1: List[Int], l2: List[Int]): List[Int] = {
  @tailrec
  def helper(l1: List[Int], l2: List[Int], acc: List[Int] = List()): List[Int] = {
    if (l1.isEmpty && l2.isEmpty) return acc

    if (l2.isEmpty || (l1.nonEmpty && l1.head <= l2.head)) {
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
def lab3zad4(): Unit = {
  println(shuffle(List(2, 4, 3, 5), List(1, 2, 2, 3, 1, 5)))
}

//git test

