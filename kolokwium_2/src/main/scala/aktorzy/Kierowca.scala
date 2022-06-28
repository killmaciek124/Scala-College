import akka.actor.*

object Kierowca {
  case object Cyk2
  case object PrzygotujAuto
  case class ReakcjaAuta(ov: Option[Int])
  case object PodajTrasę
  case class WynikNaprawy(efekt: Option[ActorRef])
}
class Kierowca extends Actor with ActorLogging {
  import Kierowca._
  import Samochód.Dalej
  import Warsztat._

  def receive: Receive = {
    case PrzygotujAuto => 
      val auto = context.actorOf(Props[Samochód](),"samochod")
      println(auto)
      context.become(Ready(auto))

    
  }

  def Ready(auto: ActorRef): Receive = {
    case Cyk2 => auto ! Dalej
    case ReakcjaAuta(reakcja: Option[Int]) =>  // None = awaria, Some[int], int= predkosc do konca wyscigu 
    // === LEGENDA ==== 
    // s – droga przebyta do tej pory (początkowo równa 0 i aktualizowana zgodnie ze wzorem s = s + dt*v w reakcji na każdy sygnał („cyk”) „zegara” – 
    // trzeba pamiętać, że prędkość wyrażamy w km/h, a wartość dt wyrażona jest w minutach)
    // dt – stały „przyrost czasu” (wartość otrzymana od Organizatora w fazie Inicjalizacji) – wyrażony w minutach.
    // v – ostatnio zarejestrowana prędkość (otrzymana od samochodu, w reakcji na wciśnięcie gazu) – wyrażona w km/h
      if (reakcja == None) {
        println("AWARIE MAMY HERE ") // OPCJA AWARII 
        // wysylamy swoja tozsamosc (siebie jako aktor) do warsztatu 
        context.become(AfterCheck)// po sprawzeniu aut stan 
        val warsztat1 = context.actorOf(Props[Warsztat](),"warsztat") // prowizorka warsztatu zeby nie przenosic go 10 razy tutaj
        warsztat1 ! Awaria(self)
      } else {
        val finishReakcja = reakcja.get // WYCIAGNIETA Z OPTION PREDKOSC
        println(s"Reakcja Auta: $finishReakcja") 
        context.become(AfterCheck) // po sprawdzeniuw wszysttkich aut 
      }

  }

  def AfterCheck: Receive = { // co sie dzieje przy kazdym cyku PANTHAREIA Cyki dodaj gdzies moze inna tozsamosc 
    case WynikNaprawy(efekt: Option[ActorRef]) => 
      if (efekt == None) {
        // auto zakonczylo przebieg z 0 dystansem 
        // i ten efekt przesylamy do jakiegos liczenia kilometrow chyba 
        println("Pokonalem 0 kilometrow! ")
      } else {
        val newEfekt = efekt.get // efekt bez optiona 
        // i ten efekt przesylamy do jakiegos liczenia kilometrow chyba
         
      }
   
  }
}
