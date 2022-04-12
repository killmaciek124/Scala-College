// LAB02 DONE !!!

import scala.util.control.Breaks._
  def parzysta(n: Int): Boolean = {
    n % 2 == 0
}
@main
def zadanie_02: Unit = {
    println(s"parzysta(6) == ${parzysta(6)}")
}





def nwd(A: Int, B: Int): Int = {
  var a= A
  var b= B
    while(a!=b){
      if(a>b) {
        a-=b
      } else {
        b -=a
      }
    }
     a 
}

@main
def zadanie_03: Unit = {
    println(s"nwd(30,100) == ${nwd(30,100)}")
}

def pierwsza(n: Int): Boolean = {
    assert(n >= 2)
    for (i <- 2 to (n-1)) {
    println(s"$i, $n")
    if (n % i == 0) return false;
}
    return  true;
}

@main
def zadanie_04(liczba: Int): Unit = {
    println(s"pierwsza($liczba) == ${pierwsza(liczba)}")
}

def hipoteza(n: Int): Unit = {
    breakable {
    for (i <- 2 to (n/2)) {
      if (pierwsza(i) == true) {
        if (pierwsza(n-i) == true) {
          val result = (n-i)
          println(s"wynik : $result, $i")
          break
        }
      }
    }
    print("Nie ma liczb takich!")
  }
}

@main
def zadanie_05(n: Int): Unit = {
    assert(n >2)
    assert(n%2==0)   
    hipoteza(n)
    
    //   TAK -> wywołujemy hipoteza(n)
    //   NIE -> kończymy z odpowiednim komunikatem.
}