/*
Zadanie 32: Korzystając z „wyliczenia” for/yield zdefiniuj funkcję:

def threeNumbers(n: Int): List[(Int, Int, Int)] = {
    Nil
}
która zwróci listę zawierającą wszystkie trójki: (a, b, c), gdzie a, b oraz c są liczbami z przedziału [1, n], takimi, że a < b oraz spełniony jest warunek: a2 + b2 = c2

Przypomnienie 1: Typ kolekcji zwracanej przez „wyliczenie” for/yield zależy od typu kolekcji użytej jako „pierwszy generator” – np. wartość wyrażenia:

for {
    a <- List(1,2,3)
    b <- Set(1,2)
} yield a * b
będzie typu List, a wartość

for {
    b <- Set(1,2)
    a <- List(1,2,3)
} yield a * b
będzie zbiorem (kolekcją typu Set).

Przypomnienie 2: W konstrukcji wyliczeń for/yield można stosować „filtry” (używając if), które pozwalają odpowiednio odsiewać interesujące nas elementy „produkowane” przez poszczególne generatory.
*/

def threeNumbers(n: Int): List[(Int, Int, Int)] = {
    var ret = for {
        a <- 1 to n
        b <- 1 to n
        c <- 1 to n
        if a < b && a*a + b*b == c*c 
    } yield (a, b, c)
    return ret.toList
}

@main 
def zadanie_32: Unit = { 
  println(threeNumbers(10))
}


