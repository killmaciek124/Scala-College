import akka.actor.{ActorSystem, Actor, ActorRef, Props}
import scala.collection.immutable.LazyList.cons

case class Init(liczbaPracowników: Int)
case class Zlecenie(tekst: List[String])
case class Wykonaj(zadanie: String)
case class Wynik(liczbaLiter: Int)
case object Print

//============ SZEF ==============
class Szef extends Actor {
  def receive: Receive = default
  def default: Receive = {
    // case msg => println(s"Odebrałem wiadomość: $msg") // dziala zawsze
    case Init(n) => {
    val res = for {
      pracownik <- 1 to n
    } yield pracownik
    // println(res)
    val finalRes = res.map(x=> {
      context.actorOf(Props[Pracownik](), s"pracownik${x}")
    }).toList
    context.become(zPracownikami(finalRes,0,0,0))  
    }
  }
  // DZIALANIE PROGRAMU LICZY LITERY W PLIKU 
  def zPracownikami(pracownicy: List[ActorRef], liczbaLiter: Int, liczbaLiniiStala: Int, liczbaLiniiCurr: Int): Receive = {
    case Zlecenie(plik) => {
      val pracownicyNumber = pracownicy.size
      val res = plik.zipWithIndex
      // println(res)
      res.foreach(x => {
        context.become(zPracownikami(pracownicy,liczbaLiter,res.size, liczbaLiniiCurr))
        pracownicy(x(1) % pracownicyNumber) ! Wykonaj(x(0))
      })
    }
    case Wynik(liczba) => {
      println(s"$liczba <== liczba, liczbaLiniiCurr => $liczbaLiniiCurr")
      if (liczbaLiniiCurr+1 == liczbaLiniiStala) {
        self ! Print
      } else {
        context.become(zPracownikami(pracownicy, liczbaLiter+liczba,liczbaLiniiStala, liczbaLiniiCurr+1))
      }
      
    }
    case Print => {
      // println("DZIALA PRINT ")
      println(liczbaLiter)
      context.become(zPracownikami(pracownicy,0,0,0))
    }
  }


}

//========= PRACOWNIK =============
class Pracownik extends Actor {
  def receive: Receive = {
    case Wykonaj(linia) => {
      val doneWork = linia.toUpperCase
      sender() ! Wynik(doneWork.size)
    }
  }
}

@main
def zadanie_39: Unit = {
  val system = ActorSystem("HaloAkka") 
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList

  val szef1 = system.actorOf(Props[Szef](),"szef1")
  szef1 ! Init(4)
  szef1 ! Zlecenie(lista)

}
// bushingi https://www.amazon.co.uk/Independent-Trucks-Rebuild-Kit-Black/dp/B074BDJZL1
/// scala course https://www.udemy.com/course/akka-essentials/

// https://github.com/hubcio2115/aisd-zadania