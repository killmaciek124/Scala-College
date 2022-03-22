def reverse(str: String): String = {
  str.toList match {
    case Nil => None
    case head::tail => tail
  }

    


}
//głowa :: reszta => podowjny dwukorpek oodziela pierwszy od ostatniego elementu listy 
@main
def zadanie_06(arg: String): Unit = {
  reverse(arg)
    
    
}


