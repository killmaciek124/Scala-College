// Map[Char, Int]
def zliczPodane(tekst: String)(znaki: Set[Char]): Unit ={ // DONE ! 
    val res1 = tekst.toList.groupBy(x=>x).toList.map(x=> (x(0), x(1).size))
    val res2 = znaki.groupBy(x=> x).toList.map(x=> (x(0), 0))
    // val finalRes = res1 ++ res2
    // val FINALRES = finalRes.groupBy(x=> x(0)).filter(x=> (x(1).size != 1) || x(1).size != 1 && x(1)(1)(1) != 1)
    // println(res1)
    // println(res2)
    val res = res2.map(p => (p(0), (res1.find(el => el(0) == p(0))).getOrElse((0,0))._2))
    println(res)
}

@main 
def main6(): Unit = {
    println(zliczPodane("spaghetti")(Set('a', 'b', 'u', 't', 'p'))) // Map(t -> 2, u -> 0, a -> 1, b -> 0, p -> 1)

}
