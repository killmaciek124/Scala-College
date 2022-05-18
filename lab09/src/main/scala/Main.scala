@main
def zad_33: Unit = {
  val osoby = io.Source.fromFile("nazwiska.txt").getLines.toList // CZYTANIE Z PLIKU!!! <====
    // Znajdz te imiona ktore maja najwieksza liczbe roznych liter
    // i z tych imion znajdz najkrotsze imiona // duze male litery no matter brah
    val imionaNazwiska = osoby.map { line => line.split(" ").toList}.toList
    val imiona = imionaNazwiska.map(list => list.head)
    val res = imiona.map().map(e => (e._1, e._2.length)).toList
    println(res)
    println(imiona)
} 