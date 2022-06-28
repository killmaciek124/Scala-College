/*
SZYBKIE NOTATKI Z ZMIANY STANU (CONTEXT.BECOME)

def receive: Receive = happyReceive (default stan)

def happyReceive: Receive = {
    case Food(Veg) => sender() ! KidAccept
    case Food(Chocolate) => context.become(sadReceive) // ZMIENIAMY STAN Z LINII 4 NA SMUTNY
}

def sadReceive: Receive = {
    case Food(CHOCOLATE) => context.become(happyReceive)
}


*/