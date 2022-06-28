package jp1.akka.lab13.model
<<<<<<< HEAD
import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps
=======
>>>>>>> 6f8c0215d4d58ff25bf97ac0550d3011e2f1b70e

import akka.actor.{Actor, ActorRef, Props}

val akkaPathAllowedChars = ('a' to 'z').toSet union
  ('A' to 'Z').toSet union
  "-_.*$+:@&=,!~';.)".toSet

object Organizator {
  case object Start
  // rozpoczynamy zawody – losujemy 50 osób, tworzymy z nich zawodników
  // i grupę eliminacyjną
  case object Runda
  // polecenie rozegrania rundy (kwalifikacyjnej bądź finałowej) –  wysyłamy Grupa.Runda
  // do aktualnej grupy
  case object Wyniki
  // polecenie wyświetlenia klasyfikacji dla aktualnej grupy
  case class Wyniki(w: Map[ActorRef, Option[Ocena]])
  // wyniki zwracane przez Grupę
  case object Stop
  // kończymy działanie
}

class Organizator extends Actor {
  // importujemy komunikaty na które ma reagować Organizator
  import Organizator._
<<<<<<< HEAD
  import Grupa._
=======
>>>>>>> 6f8c0215d4d58ff25bf97ac0550d3011e2f1b70e

  def receive: Receive = {
    case Start =>
      // tworzenie 50. osób, opdowiadających im Zawodników
      // oraz Grupy eliminacyjnej
      val zawodnicy = List.fill(50) {
        val o = Utl.osoba()
        context.actorOf(Props(Zawodnik(o)), s"${o.imie}-${o.nazwisko}" filter akkaPathAllowedChars)
      }
<<<<<<< HEAD

      val grupa = context.actorOf(Props[Grupa](),"grupa")
      // grupa ! Rundak(zawodnicy)
      println(zawodnicy)

      // val oceny = for {
      //   oceny <- 1 to zawodnicy.size
      // } yield oceny
      // val res = zawodnicy.zip(oceny).map(x=> (x(0), Utl.ocena()))
      //   .map(x=> (x(0), x(1).filter(_. != None)))

      
      //   // .map(x=> (x(0), x(1).get))
      // println(res)
      // context.become(eliminacje(res))
    // Obsługa pozostałych komunikatów
=======
      // ...

    // Obsługa pozostałych komunikatów

>>>>>>> 6f8c0215d4d58ff25bf97ac0550d3011e2f1b70e
    case Stop =>
      println("kończymy zawody...")
      context.system.terminate()
  }
<<<<<<< HEAD

  // def eliminacje()
=======
>>>>>>> 6f8c0215d4d58ff25bf97ac0550d3011e2f1b70e
}
