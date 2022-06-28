// import akka.actor.{ActorSystem, Actor, ActorRef, Props, PoisonPill, Terminated}
import akka.actor.{ActorSystem, Actor, Props,ActorRef, PoisonPill, Terminated, ActorSelection}
import scala.concurrent.duration._
import concurrent.ExecutionContext.Implicits.global
import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps
// import javax.jws.soap.InitParam
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


// case object Cyk
// case object Hit
// case object Miss
// case class Strzal(listaObroncow: Int)
// case object Init
  // case class Walka(aktywniObroncy: List[ActorRef])



//=======================

object SiłaWyższa {
  case object Init
  case object Cyk
}

class SiłaWyższa extends Actor {
  import SiłaWyższa._
  def receive: Receive = {
    case Init => // inicjalizacja zamkow itp 
      val Zamek1 = context.actorOf(Props[Zamek](), name = "Zamek1")
      val Zamek2 = context.actorOf(Props[Zamek](), name = "Zamek2")
      Zamek1 ! Zamek.Init2
      Zamek2 ! Zamek.Init2
      context.become(Bitwa(List(Zamek1, Zamek2)))

  }

  def Bitwa(zamki: List[ActorRef]): Receive = {
    case Cyk => 
      val zamek1 = zamki(0)
      val zamek2 = zamki(1)
      zamek1 ! Zamek.Strzelać // inicjalizacje obroncow
      zamek2 ! Zamek.Strzelać
  }
}
//======================

object Zamek {
  case object Init2
  case object Strzelać
}

class Zamek extends Actor {
  import Zamek._
  import Obrońca._

  def receive: Receive = {
    case Zamek.Init2 => // Strzelac 
      val obroncy = (1 to 100).map(index => { // narazie 10 obroncow
        val obronca = context.actorOf(Props[Obrońca](), s"defender$index")
        context.watch(obronca) // watch dziala że mozemy uzyć Poison Pilla zeby go wylaczyc usunac czy cos tam

        obronca
      }).toList
      context.become(Walka(obroncy))
  }

  def Walka(listaObroncow: List[ActorRef]): Receive = {
    case Zamek.Strzelać => {
      println(s"${self.path.name}: ${listaObroncow.length} defenders [case Terminated]")
      listaObroncow.foreach(x=> x ! Obrońca.Strzal(listaObroncow.size))
    }

    case Terminated(actor: ActorRef) => { // Terminated jest samo wysylane z PoisonPill
      //println(s"${self.path.name}: ${listaObroncow.length} defenders [case Terminated]") // helper 

      val nowaListaObroncow = listaObroncow.filter(p => p != actor)


      if (nowaListaObroncow.isEmpty) {
        // println(nowaListaObroncow)
        println(s"\n${self.path.name} LOST")
        context.system.terminate() // wylaczenie 
        System.exit(0) // gowno jakies xd ! 
      } else {
        context.become(Walka(nowaListaObroncow))
      }
    }

   
  }

  
}
//=============================

object Obrońca {
  case class Strzal(liczbaObroncow: Int)

}

class Obrońca extends Actor {
  import Obrońca._
  def receive: Receive = {
    case Strzal(liczbaObroncow: Int) => 
      val rand = scala.util.Random
      val firstSzansaNaHit = (liczbaObroncow/(2.0*100)) // za 10 podstawiamy ilosc obrońców
      val szansaNaHit = if (firstSzansaNaHit < 0.1) {
        0.1
        }  // jesli bedzie malo obroncow to szansa na trafienie wynosi 10% (ulatwienie)
      else firstSzansaNaHit
      // println(s"${szansaNaHit} szansa na chita")
      val randomNum = (rand.nextFloat()*100).toInt // random liczba od 1 do 100
      // println(firstSzansaNaHit)
      println(s"szansaNaHit: ${szansaNaHit}")
      // println(randomNum)
      if (szansaNaHit*100 > randomNum) {
        self ! PoisonPill // jesli zostanie trafiony to zjada PoisonPilla 
      }
      

  }
}
//=============================
@main
def bitwa: Unit = {
  import SiłaWyższa._

  val system = ActorSystem("Jabberwocky")
  import system.dispatcher

  val siłaWyższa = system.actorOf(Props[SiłaWyższa](), "SilaWyzsza")

  siłaWyższa ! SiłaWyższa.Init



  // Do „animacji” SiłyWyższej wykorzystamy „Planistę” (Scheduler)
  system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,     // opóźnienie początkowe
    200.milliseconds, // odstęp pomiedzy kolejnymi komunikatami
    siłaWyższa,        // adresat „korespondencji”
    SiłaWyższa.Cyk     // komunikat
  )

  // Oczywiście zatrzymanie symulacji NIE MOŻE się odbyć tak, jak poniżej
  // Thread.sleep(3000)
  // val res = if pantaRhei.cancel()
  //   then "Udało się zakończyć „cykanie”"
  //   else "Coś poszło nie tak – dalej „cyka”"
  // println(res)
  // system.terminate()
}

