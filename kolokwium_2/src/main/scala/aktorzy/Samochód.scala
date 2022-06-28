import akka.actor.*
object Samochód {
  case object Dalej
}
class Samochód extends Actor with ActorLogging {
  import Samochód._
  import Kierowca.ReakcjaAuta
  def receive: Receive = {
    case Dalej => //po wcisnieciu pedalu gazu samochod jakos reaguje  : 
      // licze szanse na predkosc i uwzgledniam zepsucie (None)
      // warunek na Awarie 15% szans stałe 
      // jesli nie ma awarii to losuje liczbe z przedzialu od 0 do 200

      val rand = scala.util.Random
      val szansa = 15 // 15%
      val randomNum = (rand.nextFloat()*100).toInt // random liczba od 1 do 100 do pomiaru 15% 
      // println(s"0-100 $randomNum")
      if (szansa > randomNum) {
        sender() ! ReakcjaAuta(None) // zepsucie auta 
      } else { // a co jesli auto sie nie zepsulo ?????
        val random = (rand.nextFloat()*200).toInt // randomowa liczba od 1 do 200 (predkosc) 
        // println(s"0-200 $random")
        sender() ! ReakcjaAuta(Some(random))
      }

  }
}
