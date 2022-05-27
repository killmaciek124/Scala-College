import java.io.FileNotFoundException

/*
    Korzystając wyłącznie z operacji na kolekcjach (w szczególności nie możesz uzyć rekurencji
    ani mechanizmów imperatywnych, takich jak zmienne i pętle) zdefiniuj funkcję:

        def findPairs(n: Int): Set[(Int,Int)]

    taką, że dowolnej liczby całkowitej N > 1

        findPairs(N)

    zawiera wszystkie pary postaci (p1, p2), gdzie p1 i p2 są liczbami
    pierwszymi takimi, że p1 + p2 = 2 * N oraz p1 <= p2.

*/ //
    def findPairs(n: Int): Set[(Int,Int)]  = {
        val res = for {
        p1 <- 1 to n*2
        p2 <- 1 to n*2
        if (Range(2, p1-1).filter(p1 % _ == 0).length == 0) // CHECK IF THE NUMBER IS PRIME !!!!!
        if (Range(2, p2-1).filter(p2 % _ == 0).length == 0) 
        if (p1 + p2 == 2 * n && p1 <= p2 )
         } yield (p1,p2)
        res.toSet
    }
    @main
    def main5(): Unit = {
        val res = findPairs(50)
        println(res)
    }

