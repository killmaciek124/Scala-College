/*
  UWAGA! Uzupełnij kod funkcji „ranking” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/
//  
def ranking(): List[(Int, Int)] = {
  // 1 step : wyrzuc ludzi i stworz liste w ktorej sa tylko listy a w kazdej liscie sa odpowiedzi na 25 pytan 
  // .. . i przemapuj po kazdej liscie i daj jej zipwith Index i potem groupBy od indexu 
  val input = io.Source
    .fromResource("test.txt")
    .getLines
    .toList
    .map(x=> x.split(" ").toList)
    .tail
    .map(x=> x.tail)
    .map(x=> x.zipWithIndex)
    .map(x=> x.map(y=> (y(0), y(1)+1)))
    .flatten
    .groupBy(x=> x(1))
    .toList
    .map(x=>(x(0), x(1).map(y=> y(0))) )
    .map(x=> (x(0).toInt, x(1).map(x=> x.toInt)))
    
  val iloscOsob = input(0)(1).size
  val polowa = iloscOsob/2
  val res = input
    .map(x=> (x(0), x(1).filter(_ == 1).size))
    .filter(x=> x(1) > polowa)
    // .sortBy(x=> x(1))
    // .map(x=> x(0)) 
    .zipWithIndex
    // .map(x=> (x(1), x(0)))
    .map(x=> (x(0)(0), x(0)(1), x(1)+1))
    .foldLeft(List(): List[(Int, Int,Int)])((akum, elem) => { // EGZEKWO !!!! 
      if (akum.size== 0) {
        val x = akum.appended(elem)
        x
        
      } else {
        val index = akum.size-1 
        if (akum(index)(1) == elem(1)) {
            val newElem = (elem(0), elem(1), akum(index)(2))
            val x = akum.appended(newElem)
            x
        } else {
          val x = akum.appended(elem)
          x
        }
      }
    
    })
  
  println(res)
  return List((1,1))

}

@main
def zadanie_1(): Unit = {
  val msg = """
  Nie ma potrzeby definiowania ani uruchamiania funkcji @main.

    Zamiast polecenia „run” możesz wykorzystać polecenie „test”, które
    sprawdzi poprawność Twoich rozwiązań i zgłosi ewentualne problemy.

  Oczywiście jeśli bardzo chcesz to możesz użyć funkcji @main
  do „ręcznego” sprawdzania swoich rozwiązań.
  """
  // println(msg)
  val res = ranking()
  println(res)
}
