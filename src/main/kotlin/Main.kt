fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    val arg = args.first()
    val operation = if (Plus.delim in arg) {
        splitArguments(arg, Plus.delim).let { Plus(it.first, it.second) }
    } else if ("-" in arg) {
        splitArguments(arg, "-").let { Minus(it.first, it.second) }
    } else if ("*" in arg) {
        splitArguments(arg, "*").let { Multiplication(it.first, it.second) }
    } else if ("/" in arg) {
        splitArguments(arg, "/").let { Division(it.first, it.second) }
    } else if ("exp" in arg) {
        splitArguments(arg, "exp").let { Exponentiation(it.first, it.second) }
    } else if ("sqr" in arg) {
        splitArguments(arg, "sqr").let { Square(it.first, it.second) }
    } else {
        null
    }
    if (operation == null) {
        println("Unknown")
    } else {
        if (operation.calculate() % 1 == 0.0) println(operation.calculate().toLong())
        else println(operation.calculate())

    }
}
// ((90 + (125 / 454)))

fun splitArguments(string: String, delimiter: String) = string
    .split(delimiter)
    .map { it.takeIf { it.isNotEmpty() }?.toDouble() }
    .let { it[0] to it[1] }

//enum class Test{
//    Plus,
//    Minus
//}
//fun testing(parametr : Test) {
//    val result = when(parametr) {
//        Test.Plus -> TODO()
//        //Test.Minus -> TODO()
//    }
//}


