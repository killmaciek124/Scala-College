import akka.actor.{ActorSystem, Actor, Props}
import scala.concurrent.duration._
/*
  W konfiguracji projektu wykorzystana została wtyczka
  sbt-revolver. W związku z tym uruchamiamy program poleceniem

    reStart

  a zatrzymujemy pisząc (mimo przesuwających się komunikatów)

     reStop

  i naciskając klawisz ENTER. Jeśli czynności powyższe
  już wykonywaliśmy to możemy też przywołać poprzednie
  polecenia używając strzałek góra/dół na klawiaturze.
*/


object SiłaWyższa {
  case object Cyk
  case object Strzelać
  case object Strzal
  case object Hit
  case object Miss
}
class SiłaWyższa extends Actor {
  import SiłaWyższa._

  def receive = {
    case Cyk =>
    val Zamek = context.actorOf(Props[Zamek](), name = "Zamek1")
    Zamek ! SiłaWyższa.Strzal
      // println("Cyk")
      // wysyłamy polacenie „Strzelać” do obu Zamków
  }
}

class Zamek extends Actor {
  // import SiłaWyższa._
  def receive: Receive = {
    case SiłaWyższa.Strzal => {
      val rand = scala.util.Random
      val res = rand.nextBoolean //-> true albo false 50% szans
      if (res == true) {
        val Obrońca = context.actorOf(Props[Obrońca](), name = "obronca")
        Obrońca ! SiłaWyższa.Hit
      } else {
        val Obrońca = context.actorOf(Props[Obrońca](), name = "obronca")
        Obrońca ! SiłaWyższa.Miss

      }
    }

  }
}

class Obrońca extends Actor {
  def receive: Receive = {
    case SiłaWyższa.Hit => println("AUA DOSTALEM")
    case SiłaWyższa.Miss => println("haha missed") 
  }
}

@main
def bitwa: Unit = {
  val system = ActorSystem("Jabberwocky")
  import system.dispatcher

  // UWAGA: „nazwy”, tworzące ścieżkę do aktora muszą być zapisywane
  // z użyciem znaków znaków ASCII (a nie np. UTF8) – stąd „SilaWyzsza”
  val siłaWyższa = system.actorOf(Props[SiłaWyższa](), "SilaWyzsza")

  // Do „animacji” SiłyWyższej wykorzystamy „Planistę” (Scheduler)
  val pantaRhei = system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,     // opóźnienie początkowe
    1000.milliseconds, // odstęp pomiedzy kolejnymi komunikatami
    siłaWyższa,        // adresat „korespondencji”
    SiłaWyższa.Cyk     // komunikat
  )

  // Oczywiście zatrzymanie symulacji NIE MOŻE się odbyć tak, jak poniżej
  Thread.sleep(3000)
  val res = if pantaRhei.cancel()
    then "Udało się zakończyć „cykanie”"
    else "Coś poszło nie tak – dalej „cyka”"
  println(res)
  system.terminate()
}

// package lab11

// import akka.actor.{ActorSystem, Actor, ActorRef, Props}

// object Ball1
// case class Play1(opponent: ActorRef)

// class Player1 extends Actor {

//   def receive: Receive = {
//     case Play1(actor) => actor ! Ball1
//     case Ball1 =>
//       println(s"Piłeczka ${self.path.name}")
//       sender() ! Ball1
//   }
// }

// @main
// def lab11zad1(): Unit = {
//   val system = ActorSystem("tableTennis")
//   val player1 = system.actorOf(Props[Player1](), "player1")
//   val player2 = system.actorOf(Props[Player1](), "player2")

//   player1 ! Play1(player2)
// }
