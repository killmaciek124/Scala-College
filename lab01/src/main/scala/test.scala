@main 
def ćwiczenie_01: Unit = {
    var licznik = 1 

    for (i <- 1 to 10) {
        println(i)
    }

    while ( licznik <= 10) {
        println(licznik)
        licznik += 1
    }

    val napis2 = "* " + napis + " *"

    val wynik = if (napis2.length > 4) "aqq" else "bueee"
    println(wynik)
    println(s"dlugosc napis2 to ${napis2}" )




















}