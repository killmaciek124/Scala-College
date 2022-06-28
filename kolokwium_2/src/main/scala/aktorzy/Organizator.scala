import akka.actor.*

object Organizator {
  case class UstawMaksCyk(maksCyk: Int) {
    assert(maksCyk > 0)
  }

  case object Cyk
  case class PrzejechanaTrasa(liczbaKm: Int)
}
class Organizator extends Actor with ActorLogging {
  import Organizator.*
  import Kierowca.PrzygotujAuto
  import Kierowca.Cyk2
  def receive = {
    case UstawMaksCyk(mc) =>
      log.info(s"liczba cyknięć do wykonania: $mc")
      // println("DDSADSAD")
      // log.info("DUPA")
      context.become(poInicjalizacji(mc))
    case _ => // inne pomijamy
  }

  def poInicjalizacji(maksCyk: Int): Receive = {
    case Cyk =>
      log.info("Cyk")
      val warsztat = context.actorOf(Props[Warsztat](),"warsztat")
      // 3 kierowcow 
      val kierowcy = (1 to 3).map(x=> {
        context.actorOf(Props[Kierowca](),s"kierowca$x")
      }).toList
      kierowcy.foreach(x=> x ! PrzygotujAuto)
      kierowcy.foreach(x=> x ! Cyk2) // KOMUNIKAT DO WSZYSTKICH KIEROWCOW KTORZY WYSYLAJA KOMUNIKAT DO SWOICH SAMOCHODOW 'DALEJ' JAKO WCISNIECIE PEDALA GAZU 
      context.become(Ready(warsztat, kierowcy))

  }

  def Ready(warsztat: ActorRef, kierowcy: List[ActorRef]): Receive = {
    case Cyk => // warunek cykania defaultowego po stworzeniu aut i sprawdzeinu predkosci 
      kierowcy.foreach(x=> x ! Cyk2) // kazdemu kierowcy przesylamy cyka 

  }
}

