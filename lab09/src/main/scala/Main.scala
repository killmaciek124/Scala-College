// import java.util.jar.Attributes.Name
// @main
// def zad_33: Unit = {
//   val osoby = io.Source.fromFile("nazwiska.txt").getLines.toList // CZYTANIE Z PLIKU!!! <====
//     // 3 step : z tych imion wybieerz nazwiska ktore sa najkrotsze
  
//   val Names = osoby.map(x => {
//   val index = x.indexOf(" ")
//   x.toLowerCase.slice(0,index).toList.distinct.size // DISTINCT USUWA POWTORZENIA 
// })
//   val maxValue = Names.max
//   val secondNames = osoby.map(x => {
//   val index = x.indexOf(" ")
//   x.toLowerCase.slice(index,x.size-1).toList.distinct.size // DISTINCT USUWA POWTORZENIA 
// })
// val minValue = secondNames.min
// println(secondNames)

 
// } 

// string zwraca 
// def histogram(max: Int): Unit = {
//   import scala.io.Source
//   val dane = Source.fromFile("ogniem_i_mieczem.txt").toList
//   val filteredDane = dane.filter(x=> x.isLetter).map(x=> x.toLower).groupBy(x=> x).toList.map(x => (x(0),x(1).size))
//   println(filteredDane)
  


// }

// @main 
// def zad_34(): Unit = {
//   val res = histogram(10)
//   println(res)
// }