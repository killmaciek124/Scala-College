import akka.actor.*
object Warsztat {
  case object Cyk
  case class Awaria(auto: ActorRef)
}
class Warsztat extends Actor with ActorLogging {
  import Warsztat._
  import Kierowca._
  def receive: Receive = {
    case Awaria(kierowcaAuta: ActorRef) =>
         // gotowosc na zakonczneie wyscigu i ....
      //... ifnormacja o pokonanym dystansie 
      // wynik naprawy : case class WynikNaprawy(efekt: Option[ActorRef]) 80% szans , jesli naprawa niemozliwa wysyla komunikat None i dystans przebyty == 0km
      // jesli samochod moge naprawic to tworze nowa tozsamosc Naprawa. Moze sie ona odbyc w przedziale 1-6 cykniec a po naprawie zwracamy taki komunikat : 
      // WynikNaprawy(Some(autoRef)) (warsztat do kierowcy) i ponownie pedal gazu i jazda skrrrrrrrr
      println("samochod jest w warsztacie")
      val rand = scala.util.Random
      val szansa = 80 // 80%
      val randomNum = (rand.nextFloat()*100).toInt // random liczba od 1 do 100 do pomiaru 80% 
      if (szansa > randomNum) {
        // AUTO POMYSLNIE NAPRAWIONE //auto konczy wyscig (wyslanie komunikatu do kierowcy o wyniku naprawy )
        kierowcaAuta ! WynikNaprawy(Some(kierowcaAuta)) // i przesylamy tego kierowce auta 


      } else { //auto konczy wyscig (wyslanie komunikatu do kierowcy o wyniku naprawy )
        kierowcaAuta ! WynikNaprawy(None)

      }
  }
}
