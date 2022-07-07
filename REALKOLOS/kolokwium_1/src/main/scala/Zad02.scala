/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int): List[List[A]] = { //List[List[A]]
    require(shift>0)
    require(len>0)
   // funkcja ktora tworzy podliste dlugisci len (cyzli zwraca np (1,2,3), (2,3,4) // dziala ! 
  // println(list)
  // println(len)
  def podLista[A](lista: List[A], len1: Int): List[A] = { // DZIALA  // DODAJ FUNKCJONALNOSC NA OSTATNIE ELEMENTY 
    @annotation.tailrec
    def helper(lista: List[A], len2: Int, akum: List[A] = List()): List[A] = (len2, lista) match {
      
      case (0, _) => 
        println("case1")
        akum
        
      case (0, head) => 
        println("case2")
        akum
      case (0, Nil) => 
        println("case3")
        akum
        
      case (_, Nil) => 
        akum
      case (_, head::tail) =>
        // println("case last")
        if (len1 <= lista.size) {
          helper(tail, len-1, akum.appended(head)) 
        } else if (len1 == lista.size) {
          helper(Nil, len2, akum.appended(head))
        } else {
          helper(Nil, 0, akum.appended(head))
          // akum
        }


      case (_, head::Nil) => 
        if (len <= lista.size) {
          helper(Nil,0, akum.appended(head))
        } else if (len == lista.size) {
          helper(Nil, 0, akum.appended(head))
        } else {
          helper(Nil, 0, akum.appended(head))
          // akum
        }
        // helper(Nil, 0, akum.appended(head))
    
      
    }
    helper(lista,len1)
  }
  // val res = podLista(list,len)
  // println(res)

  // == == ==  = = ===== = =  = 

  // funkcja ktora transformuje funkcje na taka bez kilku pierwszych wartosci (w zaleznosci od parametru shift)

  def shifting[A](lista: List[A], shift: Int): List[A] = { // done // DODAJ FUNKCJONALNOSC NA OSTATNIE ELEMENTY 
    val x = lista.size
    def helper(lista: List[A], shift: Int, licznik: Int = x): List[A] = (lista, shift) match {
      case (head::tail, 0) => lista
      case (head, 0) => lista
      case(Nil, 0) => lista
      case(Nil, _) => lista
      case (head::tail, _) => helper(tail, shift-1)
      case (head::Nil, _) => helper(Nil, shift-1) 
      
    }
    helper(lista, shift)
  }
  // val res2 = shifting(list, 2)
  // println("TEST2")
  // println(res2)


  // MAIN FUNKCJA ! 
  // tworzymy podliste, dodajemy do akumulatora i potem ucinamy GLOWNA LISTE ! 
  def helper[A](list: List[A], akum: List[List[A]]= List())(len: Int, shift: Int): List[List[A]] = (list) match {
    case Nil => 
      println("CASE NILOWY ")
      println(akum)
      akum
    case lista => 
      val podLista1 = podLista(lista, len)
      val mainNowaLista = shifting(lista, shift)

      println("TEST")
      println(podLista1)
      println(mainNowaLista)
      println(akum)

      val x = akum.appended(podLista1)
      helper(mainNowaLista, x)(len, shift)
    // case head:+head2:+head3 => 
    //   val podLista1 = podLista(head:+head2:+head3, len)
    //   val mainNowaLista = shifting(head:+head2:+head3, shift)
    //   helper(mainNowaLista, akum.appended(podLista1))(len, shift)

  } 
      
  helper(list)(len,shift) 
  
}

@main 
def main3(): Unit = {
  val ( list, len, shift ) = ( List(1,2,3,4,5,6,7,8,9,10), 3, 1 ) // dziala dla shift len 1 1 
  val res = group(list)(len,shift)
  println("FINAL TEST")
  println(res)
}
/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/


/* PROBLEMY : 
1. Jesli jest za malo elementow w main liscie to wychodzimy ! (wszystkie podlisty musza byc rowne LEN ! )
*/