import akka.actor.{ActorSystem, Actor, ActorRef, Props}
import scala.collection.immutable.LazyList.cons
import scala.PartialFunction.Lifted

case class  Piłeczka(odbicia: Int, max: Int)
case class Graj(przeciwnik: ActorRef)
case class Graj2(przeciwnik: ActorRef, maks: Int)
case class Graj3(przeciwnik1: ActorRef, przeciwnik2: ActorRef)
case class Ball3(nextPlayer1: ActorRef, nextPlayer2: ActorRef)
//zad4 klasy
case class Ball4(players: List[ActorRef], playersNumber: Int)
case class Play4(players: List[ActorRef], playersNumber: Int)

//zad1
class Gracz35 extends Actor {
    def receive: Receive = {
      case Graj(przeciwnik) => przeciwnik ! Piłeczka 
      case Piłeczka => {println(s"[${self.path}]Odebralem pileczke") 
      sender() ! Piłeczka}
    }
}
// zad2
class Gracz36 extends Actor {
    def receive: Receive =  Start
    def Start: Receive = {
      case Graj2(gracz2, odbicia) => {
        gracz2 ! Piłeczka(0, odbicia)
      }
      case Piłeczka(odbicia, max) => {
        if (odbicia < max) {
          println(s"${self.path} otrzymalem pileczke")
          sender() ! Piłeczka(odbicia+1,max)
        } else context.system.terminate()
        
    }
}
}

//zad3

class Gracz37 extends Actor {
  def receive: Receive = Start 
  def Start: Receive = {
    case Graj3(przeciwnik1,przeciwnik2) => {
      przeciwnik1 ! Ball3(przeciwnik2, self)
    }
    case Ball3(nextPlayer1, nextPlayer2) => {
      println(s"[${self.path}]Otrzymałem piłeczkę ")
      nextPlayer1 ! Ball3(nextPlayer2, self)
    }
  }
}

// zad4

class Gracz38 extends Actor {
  def receive: Receive = { 
    case Play4(players, playersNumber) => {
      players(playersNumber) ! Ball4(players, 2)
    }
    case Ball4(players,playersNumber) => {
      if (playersNumber < players.size) {
        println(s"[${self.path}]Otrzymałem piłeczkę ")
        players(playersNumber) ! Ball4(players, playersNumber+1)
      } else {
        println(s"[${self.path}]Otrzymałem piłeczkę ")
        players(0) ! Ball4(players, 1)
      }
    }
  }
}


@main
def main: Unit = {
  val system = ActorSystem("HaloAkka") 
  
  //ZADANIE 1 I 2 JEDNOCZESNIE 

  // val gracz1 = system.actorOf(Props[Gracz36](),"gracz1")
  // val gracz2 = system.actorOf(Props[Gracz36](), "gracz2")   

  // gracz1 ! Graj2(gracz2,3)

  //ZADANIE 3

  // val gracz1 = system.actorOf(Props[Gracz37](),"gracz1")
  // val gracz2 = system.actorOf(Props[Gracz37](), "gracz2")
  // val gracz3 = system.actorOf(Props[Gracz37](), "gracz3")
  

  // gracz1 ! Graj3(gracz2,gracz3)

  //ZADANIE 4 
  val gracz1 = system.actorOf(Props[Gracz38](), "gracz1")
  val gracz2 = system.actorOf(Props[Gracz38](), "gracz2")
  val gracz3 = system.actorOf(Props[Gracz38](), "gracz3")
  val gracz4 = system.actorOf(Props[Gracz38](), "gracz4")

  gracz1 ! Play4(List(gracz1, gracz2,gracz3,gracz4),1)
    
}
