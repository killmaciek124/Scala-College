import akka.actor.{ActorSystem, Actor, ActorRef, Props}

// object ActorsIntro extends App {

  class WordCountActor extends Actor {
    // some data
    var totalWords = 0

    // zachowanie 
    def receive: PartialFunction[Any,Unit] = { // PartialFunction[Any,Unit] === Receive 
      case message: String => 
        println(s"I have received : $message")
        totalWords += message.split(" ").size
      case msg => println(s"Co to ${msg.toString}")
    }
    }

// }

@main // wraz z MAINEM , muszę dodać '()' do Propsa
def main: Unit = {
  // inicjacja aktor system 
  val system = ActorSystem("NazwaSystemu") // nie moze zawierac dziwnych znakow
  println(system.name)

  //INICJACJA AKTORÓW 
  val wordCounter = system.actorOf(Props[WordCountActor](), "wordCounter")
  val anotherWordCounter = system.actorOf(Props[WordCountActor](), "anotherWordCounter")

  // KOMUNIKACJA ! 

  // wordCounter.!("tak też dziala bo wykrzyknik to metoda") 
  wordCounter ! "Bardzo tajna zawolana wiadomosc ! "
  anotherWordCounter ! "Inna tajna wiadomosc"
}

// ==================================
// DODATKOWA WIEDZA TWORZENIA AKTORÓW : 
// object Person {
//   def props(name: String) = Props(new Person(name))
// }

// val newPerson = system.actorOf(Person.props("Bob"))

//=======================================