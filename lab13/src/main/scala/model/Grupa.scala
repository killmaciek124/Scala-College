package jp1.akka.lab13.model

import akka.actor.{Actor, ActorRef}

object Grupa {
<<<<<<< HEAD
  case class  Rundak(aktors: List[ActorRef])
=======
  case object Runda
>>>>>>> 6f8c0215d4d58ff25bf97ac0550d3011e2f1b70e
  // Zawodnicy mają wykonać swoje próby – Grupa
  // kolejno (sekwencyjnie) informuje zawodników
  // o konieczności wykonania próby i „oczekuje”
  // na ich wynik (typu Option[Ocena])
  case object Wyniki
  // Polecenie zwrócenia aktualnego rankingu Grupy
  // Oczywiście klasyfikowani są jedynie Zawodnicy,
  // którzy pomyślnie ukończyli swoją próbę
  case class Wynik(ocena: Option[Ocena])
  // Informacja o wyniku Zawodnika (wysyłana przez Zawodnika do Grupy)
  // np. Wynik(Some(Ocena(10, 15, 14)))
  // Jeśli zawodnik nie ukończy próby zwracana jest wartość Wynik(None)
  case object Koniec
  // Grupa kończy rywalizację
}
class Grupa(zawodnicy: List[ActorRef]) extends Actor {
<<<<<<< HEAD
  import Grupa._
  def receive: Receive = {
    case Rundak(zawodnicy: List[ActorRef]) => println("DZIALA COS ") 
=======
  def receive: Receive = {
    case msg => println(msg)
>>>>>>> 6f8c0215d4d58ff25bf97ac0550d3011e2f1b70e
  }
}
