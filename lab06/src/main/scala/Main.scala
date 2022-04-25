def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
    val result = list.take(endIdx+1)
    val finalresult = result.drop(begIdx)
    println(finalresult)
     return finalresult
}


@main
def zadanie_19: Unit = {
    val lista = List(1, 2, 3, 4, 5, 6, 7, 8)
    // subseq(lista,1,2)
    assert( subseq(lista, 2, 3) == List(3, 4) ) // ==> OK
    assert( subseq(lista, 2, 1) == Nil )        // ==> OK
    assert( subseq(lista, -1, 10) == lista )    // ==> OK
}

def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
    val (result1, result2) = list.partition(x=>{x < 0})
    val finalresult2 = result2.filter(_ > 0)
     var tuple = (result1, finalresult2)
     println(tuple)  
      tuple  
}

@main
def zadanie_20: Unit = {
    val lista: List[Double] = List(1, -2, 0, 4, 5, 0, -7, 8)
    assert( pairPosNeg(lista) == ( List(-2, -7), List(1, 4, 5, 8) ) ) // ==> OK
      pairPosNeg(lista)

}

def deStutter[A](list: List[A]): List[A] = {
 val res = list.foldLeft(0)((m, n) {
   m+n
  }
  
 )}

@main
def zadanie_21: Unit = { // NIE DZIALA 
    val l = List(1, 1, 2, 4, 4, 4, 1, 3)
    assert( deStutter(l) == List(1, 2, 4, 1, 3) ) // ==> OK
    val result = deStutter(l)
    println(result)
}