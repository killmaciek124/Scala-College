import scala.io.Source

/*
    Korzystając wyłącznie z mechanizmów kolekcji języka Scala znajdź kraj o najdłużej rosnącym wskaźniku LadderScore.
    Innymi słowy, korzystając z załączonych danych szukamy kraju, dla którego wskaźnik LadderScore najdłużej
    utrzymał „dobrą passę” (z roku na rok się zwiększał).

    Zwróć uwagę na to, że w danych mogą wystąpić „linie” z brakującymi danymi. Takie linie powinny zostać
    POMINIĘTE. Brakujące dane oznaczają, że w linii występują sekwencje postaci: ,,, przykładowo:

        Kosovo,2020,6.294,,0.792,,0.880,,0.910,0.726,0.201

    Linie takie, jako „niewiarygodne” należy pominąć (oczywiście nie zmieniając samego pliku danych)
    zanim program rozpocznie analizę.

    W razwiązaniu nie wolno uzywać zmiennych, ani konstrukcji imperatywnych, takich jak pętle
*/



    // case class CountryData(
    //     CountryName : String,
    //     Year : String,
    //     LadderScore : String,
    //     LogGDPPerCapita : String,
    //     SocialSupport : String,
    //     HealthyLifeExpectancyAtBirth : String,
    //     FreedomToMakeLifeChoices : String,
    //     Generosity : String,
    //     PerceptionsOfCorruption : String,
    //     PositiveAffect : String,
    //     NegativeAffect : String
    // )

 // Ladder Score == 2 index '
    @main
    def main4(): Unit = {
        val wyniki = Source
            .fromFile("world-happiness-report.csv")
            .getLines
            .toList
            .map(x=> x.split(",").toList)
            .filter(x=> x.exists(y=> y == "") == false)
            .map(x=> (x(0),x(1), x(2)))
            .groupBy(x=> x(0)).toList
            .map(x=> (x(0),x(1).map(y=> (y(1),y(2)))))
            .map(x=> (x(0),x(1).sortBy(y=> y(0))))
            .map(x=> (x(0),x(1).map(y=> y(1).toDouble) ))
            // ==================== maxPassa, currentPassa, prevValue ======= c 
            .map(x=> (x(0), x(1).foldLeft((0,0,0.0))((akum,currentValue) => (akum,currentValue) match {
                case ((0,0,0.0),currentValue) => (0,1,currentValue)
                case ((maxPassa,currentPassa,prevValue),currentValue) => {
                    if (prevValue < currentValue) { // jezeli passa sie utrzymuej
                        if (maxPassa <= currentPassa+1) (currentPassa+1,currentPassa+1,currentValue)
                        else (maxPassa,currentPassa+1,currentValue)
                    } else {
                        if (maxPassa < currentPassa) {
                            (currentPassa,0,currentValue)
                        } else  (maxPassa,0,currentValue)

                    }
                }
            }
        
            
            )))
            .map(x=> (x(0),x(1)(0)))
            .groupBy(x=> x(1))
            .toList
            .sortBy(x=> x(0))
            .last
            
            
            // .map(x=> x.map(y => y.sortBy(y(1)))) // or sortBy
            // .map(x=> (x(0), x(1)))

        println(wyniki)

    }


