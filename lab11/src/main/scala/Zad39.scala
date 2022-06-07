import akka.actor.{ActorSystem, Actor, ActorRef, Props}
  case class Init(liczbaPracowników: Int)

class Szef extends Actor {
  def receive: Receive = {
    // case msg => println(s"Odebrałem wiadomość: $msg") // dziala zawsze
    case Init(n) => {
    val res = for {
      pracownik <- 1 to n
    } yield pracownik
    }
  }

  def zPracownikami(pracownicy: List[ActorRef]): Receive = {
    case Init(workersCount) => {
      
    }
  }
}

class Pracownik extends Actor {
  def receive: Receive = {
    case msg => println(s"Odebrałem wiadomość: $msg")
  }
}

@main
def zadanie_39: Unit = {
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList
}
// bushingi https://www.amazon.co.uk/Independent-Trucks-Rebuild-Kit-Black/dp/B074BDJZL1
/// scala course https://www.udemy.com/course/akka-essentials/

// https://github.com/hubcio2115/aisd-zadania