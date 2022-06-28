import akka.actor.{ActorSystem, Actor, ActorRef, Props}
import scala.collection.immutable.LazyList.cons
import scala.PartialFunction.Lifted

class SimpleActor extends Actor {
    // println(context.self.path) // albo bez path, pokazuje nam info dokladne o naszym SimpleAktor
    // zatem możemy wyslac wiadomosc do samych siebie 
    // context.self ===  this, self ==== context.self
    def receive: Receive = {
        case "Hi" => sender() ! "Hi (reply)" // REPLYING TO A MESSEGE, context.sender() === Bob, czyli ta dostająca "Hi" // sender() === context.sender()// BO BOB WYSYLA DO ALICE "HI REPLY"
        // case "Hi" => println(s"Jestem ${self} i powiedziano mi czesc!") // receiving 
        case msg: String => println(s"[$self]Tutaj nasz string received: $msg")
        case msg: Int => println(s"Tutaj received nasza cyferka : $msg")
        case SpecialMessage(contents) => println(s"SPECJALNA WIADOMOSC Z KLASY : $contents ")
        case SendSthToMyself(content) => self ! content //  wysyłamy wiadomość sobie czyli temu aktorowi w ktorym jestesmy (SimpleActor) 
        case SayHi(ref) => ref ! "Hi" // Alice is being passed as a sendeer
        case WirelessPhoneMessage(content,ref) =>  ref forward (content + "s") // I keep the orginal sender of WPM
    }
}

// SPECJALNE KLASY 

case class SpecialMessage(contents: String)
case class SendSthToMyself(content: String)
case class SayHi(ref: ActorRef)
case class WirelessPhoneMessage(content: String, ref: ActorRef)


//ZADANIE 1 : 

// DOMENA COUNTER ACTORA : 
object CounterActor {
    case object  Increment
    case object  Decrement
    case object  Print // case object jesli nie przyjmuje argumentow
    // case class jesli przyjmuje argumenty 
}
//=========
class CounterActor extends Actor {
    import CounterActor._

    def receive: Receive = CounterStart(0)

    def CounterStart(num: Int): Receive = {
        case Increment => context.become(CounterStart(num+1))
        case Decrement => context.become(CounterStart(num-1))
        case Print => println(s"Tutaj wynik funkcji CounterActor: $num")
    }
}


import CounterActor._
//===================================


//ZADANIE 2 

object BankAccount {
    case class Deposit(amount: Int)
    case class Withdraw(amount: Int)
    case object Statement
    case class Success(msg: String)
    case class Failure(msg: String)
}

class BankAccount extends Actor {

    import BankAccount._

    var moneyBag = 0 

    def receive: Receive = {
        case Deposit(amount) => {
            moneyBag+= amount
            sender() ! Success("Success")
        }
        case Withdraw(amount) => {
            if (moneyBag > amount) {
                moneyBag-= amount
                sender() !  Success("Success")
            } else sender() ! Failure("Failure")
        }
    
        case Statement => sender() ! s"YOur balance : $moneyBag"
    }
}



// druga osoba w zadaniu 2 

object Person {
    case class LiveTheLife(account: ActorRef)
}

class Person extends Actor {
    import Person._
    import BankAccount._

    def receive: Receive = {
        case LiveTheLife(account) => 
            account ! Deposit(1000)
            account ! Withdraw(400)
            account ! Statement
        case msg => println(s"Jakas wiadomosc: $msg")
    }
}

//ZADANIE 3 (VOTING SYSTEM CONTEXT.BECOME) // TO DO ! 

case class Vote(candidate: String)
case object VoteStatusRequest
case class VoteStatusReply(candidate: Option[String])

class Citizen extends Actor {
    def receive: Receive = defaultState

    def defaultState: Receive = {

    }

}

case class AggregateVotes(citizens: Set[ActorRef])
class VoteAggregator extends Actor {
    def receive: Receive = ???
}


@main
def main2: Unit = {
    val system = ActorSystem("KolejnySystem")
    val simpleActor = system.actorOf(Props[SimpleActor](), "simpleActor")

    simpleActor ! 4321

    // SPECJALNA METODA PRZESYLU WIADOMOSCI JAKO KLASA :

    simpleActor ! SpecialMessage("#$ROSJA")

    // PRZESYL INFO DO MNIE ! : 
    simpleActor ! SendSthToMyself("WIADOMOSC DO MNIE ! ")

    //KOMUNIKACJA DWOCH BOTÓW : 

    val alice = system.actorOf(Props[SimpleActor](), "alice")
    val bob = system.actorOf(Props[SimpleActor](), "bob")

    alice ! SayHi(bob)

    // DEAd letters : // FAKE ACTOR 
    // alice ! "Hi" // Alice receives "Hi"

    // FORWARDING CZYLI PRZEKAZYWANIE PIERWOTNEGO SENDERA DALEJ 

    alice ! WirelessPhoneMessage("Hi", bob)

    /**
     * EXERCISES :
     *1. Counter Actor 
     *2. Bank Account 
    */

    //ZADANIE 1 (odświezone z context.become)
    val counter1 = system.actorOf(Props[CounterActor](), "counter1")
    (1 to 5).foreach(x=> counter1 ! Increment) // CIEKAWY FOR EACH
    counter1 ! Decrement
    counter1 ! Print
    

    //ZADANIE 2 // interakcja dwoch aktorów

    import Person._

    val bank1 = system.actorOf(Props[BankAccount](), "bank1")
    val milioner = system.actorOf(Props[Person](), "milioner")

    milioner ! LiveTheLife(bank1)

    //ZADANIE 3 (VOTING SYSTEM CONTEXT.BECOME)

    val alice = system.actorOf(Props[Citizen]())
    val john = system.actorOf(Props[Citizen]())
    val bob = system.actorOf(Props[Citizen]())

    alice ! Vote("Andrzej Duda")
    john ! Vote("Andrzej Duda")
    bob ! Vote("Bronislaw Komorowski")

    val voteAggregator = system.actorOf(Props[VoteAggregator]())

    voteAggregator ! AggregateVotes(Set(alice, john, bob))




    

} 
