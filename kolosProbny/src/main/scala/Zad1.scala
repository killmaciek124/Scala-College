import akka.actor._

object Zad1 {

  case class Init1(num: Int)
  case class Init2(krotka: (Float,Float))

  class Manager extends Actor {
    def receive: Receive = {
      case Init1(n: Int) => 

        val actors = (1 to n).map(y => {
          val r = util.Random
          val obronca = context.actorOf(Props[Pracownik](), s"pracownik$y")
          val value1 = r.nextFloat
          val value2 = r.nextFloat
          val krotka = (value1, value2)
          obronca ! Init2(krotka)
        })



    }
  }

  class Pracownik extends Actor {
    def receive: Receive = {
      case Init2(krotka: (Float,Float)) => 
        println(s"$self = (${krotka} )")
        

    }
  }

  class KumulatorWyniku extends Actor {
    def receive: Receive = {
      ???
    }
  }

  def main(args: Array[String]): Unit = {
    val r = util.Random
    // r jest „generatorem”, którego należy użyć do generowania wartości
    // losowych różnych typów (i zakresów) np.
    // r.nextInt (zwraca pseudolosową liczbę całkowitą z zakresu od 0 do 1),
    // r.nextInt(100) (zwraca pseudolosową liczbę całkowitą z zakresu od 0 do 99),
    // r.nextFloat (zwraca pseudolosową liczbę zmiennoprzecinkową z zakresu od 0 do 1).

    // wyliczenie pierwiastka z liczby 4: math.sqrt(4)

    val system = ActorSystem("NienawidzeScali")
    val menago = system.actorOf(Props[Manager](), "menago1")

    menago ! Init1(5)



  }

}
