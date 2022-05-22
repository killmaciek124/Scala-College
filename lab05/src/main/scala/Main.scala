import scala.annotation.tailrec
import javax.swing.text.StyledEditorKit.BoldAction

// def oczyść[A](l: List[A]): List[A] = { // DONE ! 
//     @tailrec 
//     def helper[A](l: List[A], akum: List[A]= List()): List[A] = (l) match {
//       case Nil => akum
//       case head::Nil => helper[A](Nil,akum.appended(head))
//       case head::tail => {
//         if (head == tail(0)) {
//           helper[A](tail,akum)
//         } else helper[A](tail,akum.appended(head))
//       }
//     }
//     helper[A](l)
// }

// @main
// def zadanie_15: Unit = {
//     val lista = List(1, 1, 2, 4, 4, 4, 1, 3)
//     val res = oczyść(lista) // ==> List(1, 2, 4, 1, 3)
//     println(res)
// }

// ===========================

// TROCHE WIEDZY ! : 
// val lista = List(('a', 2),(2,3))
// println(lista(0)(1))
// val res = lista.appended((1,1))
// println(res)
// ========
// val lista = List((3, 2),(2,3))
// val res = lista.updated(1,(10,10)) === 1 = index
// println(res)
// ========

// def skompresuj[A](l: List[A]): List[(A, Int)] = { // TO DO !!!! 
//     @tailrec
//     def helper[A](l: List[A], akum: List[(A, Int)], elemCounter: Int, listCounter: Int, helper: Boolean): List[(A, Int)] = (l,helper) match { // helper= kiedy robimy nową tuple
//       case (Nil,false) => akum // listCounter = określa indexy podwojnych tupli ; elemCounter = drugi element tej tupli 
//       case (head::tail,true) => { // true == robimy nową tuple 
//         if (head != tail(0)) {
//           helper[A](tail, akum.appended((head, elemCounter + 1 )), 0,listCounter + 1,true)
//         } else {
//           helper(tail, akum.appended((head, elemCounter+1)), elemCounter+1,listCounter,false)
//         }
//       }
//       case (head::tail,false) => { // false == nie robimy nowej tupli
//         if (head != tail(0)) {
//           helper(tail, akum.updated(listCounter,(head, elemCounter+1)), 0,listCounter+1,true)
//         } else {
//           helper(tail,akum.updated(listCounter,(head, elemCounter+1)), elemCounter+1,listCounter,false)
//         }

//       }
//     }
//     helper[A](l,Nil, 0,0,true)
// }

// @main
// def zadanie_16: Unit = {
//     val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')
//     val res = skompresuj(lista) // ==> List(('a', 2), ('b', 1), ('c', 3), ('a', 2), ('b', 1), ('d', 1)) // ZIP WITH INDEX 
//     println(res)
// }

// def isOrdered[T](leq: (T, T) => Boolean)(arr: List[T]): Boolean = { // DONE ! 
//   @tailrec
//   def helper(leq: (T, T) => Boolean)(arr: List[T]): Boolean = arr match {
//     case Nil | List(_) => true // binary OR , jeśli coś z tego jest prawdziwe to się wykona CASE ! 
//     case head::second::tail => if (leq(head,second)) { // tail to reszta listy bez dwoch elementow !!!!
//       helper(leq)(second::tail)
//     } else false
//   }
//   helper(leq)(arr)
// }


// @main
// def zadanie_17: Unit = {
//     val lt = (m: Int, n: Int) => m < n
//     val lte = (m: Int, n: Int) => m <= n
//     val lista = List(1, 2, 2, 5)
//     println(isOrdered(lt)(lista)) // ==> false
//     println(isOrdered(lte)(lista)) // ==> true
// }

// ==============================================

// Trochę TEORII: 
//   if(condition1 || condition2 || condition3)
// If condition1 is true, condition 2 and 3 will NOT be checked.

// if(condition1 | condition2 | condition3)
// This will check conditions 2 and 3, even if 1 is already true. As your conditions can be quite expensive functions, 
// you can get a good performance boost by using them.

// def applyForAll[A, B](l: List[A])(f: A => B): List[B] = { // DONE EASILY ! 
//   def helper[A, B](l: List[A],akum: List[B]=List())(f: A => B): List[B] = l match {
//     case Nil => akum
//     case head::tail => helper(tail, akum.appended(f(head)))(f) 
//   }
//   helper(l)(f)
// }


// @main
// def zadanie_18: Unit = {
//     val lista = List(1, 3, 5)
//     val f = (n: Int) => n + 3
//     val res = applyForAll(lista)(f) // ==> List(4, 6, 8)
//     println(res)
// }