/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int = 1): List[List[A]] = {
  def helper[A](list: List[A], akum: List[List[A]]= List(), smallList: List[A] = List(), licznik: Int = 0)(len: Int, shift: Int = 1): List[List[A]] = (list) match {
    case Nil => akum
    case head::tail => akum.size match {
      case licznik => smallList.size match {
        case len => helper(tail, akum :+ smallList, List(), licznik+1)(len,shift)
        case _ =>  helper(tail, akum, smallList.appended(head+licznik), licznik )(len,shift)
    } 
      
    }
  }
  helper(list)(len,shift) 
  
}

@main 
def main3(): Unit = {
  val ( list, len, shift ) = ( List(1,2,3,4,5), 3, 1 )
  val res = group(list)(len,shift)
  println(res)
}
/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/
