fun mainOld(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    val arg = args.first()
    val operation = if (Plus.delim in arg) {
        splitArguments(arg, Plus.delim).let { Plus(it.first, it.second) }
    } else if (Minus.delim in arg) {
        splitArguments(arg, Minus.delim).let { Minus(it.first, it.second!!.toDouble().unaryMinus()) }
    } else if (Multiply.delim in arg) {
        splitArguments(arg, Multiply.delim).let { Multiply(it.first, it.second) }
    } else if (Divide.delim in arg) {
        splitArguments(arg, Divide.delim).let { Divide(it.first, it.second) }
    } else if (Exponentiation.delim in arg) {
        splitArguments(arg, Exponentiation.delim).let { Exponentiation(it.first, it.second) }
    } else if (Square.delim in arg) {
        splitArguments(arg, Square.delim).let {
            Square(
                it.first,
                if (it.second == null) 0.5 else 1.0 / it.second!!.toDouble()
            )
        }
    } else {
        null
    }

    if (operation == null)
    {
        println("Unknown")
    } else
    {
        if (operation.calculate() % 1 == 0.0) {
            println(operation.calculate().toLong())
        } else {
            println(operation.calculate())
        }

    }
}
fun splitArguments(string: String, delimiter: String) = string
    .split(delimiter)
    .map { it.takeIf { it.isNotEmpty() }?.toDouble() }
    .let { it[0] to it[1] }