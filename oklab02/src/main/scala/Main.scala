def parzysta(n: Int): Boolean = {
    n % 2 == 0
}

@main
def zadanie_02: Unit = {
    println(s"parzysta(5) == ${parzysta(5)}")
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
    return a 
}

@main
def zadanie_03: Unit = {
    println(s"nwd(10, 30) == ${nwd(100,25)}")
}

def pierwsza(n: Int): Boolean = {
    assert(n >= 2)
    for (i <- 2 to (n-1)) {
    if (n % i == 0) {
      false
    }
}
true
}

@main
def zadanie_04(liczba: Int): Unit = {
    println(s"pierwsza(15) == ${pierwsza(15)}")
}