fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    val arg = args.first()
    println(polska(splitArgBracket(arg)))
}

fun priority(delimiter: String): Int {
    return when (delimiter) {
        "(", ")" -> 0
        Plus.delim, Minus.delim -> 1
        Multiply.delim, Divide.delim -> 2
        Exponentiation.delim, Square.delim -> 3
        else -> TODO()
    }
}

fun splitArgBracket(
    string: String
): MutableList<String> {
    val result: MutableList<String> = mutableListOf()
    val stack: MutableList<String> = mutableListOf()
    var mode = "none"
    var temp = ""
    string.forEach {
        if (it.isDigit()) {
            when (mode) {
                "none" -> {
                    mode = "number"
                    temp = it.toString()
                    result.add(temp)
                }
                "number" -> {
                    temp += it.toString()
                    result.removeLast()
                    result.add(temp)
                }
                "symbol" -> {
                    mode = "number"
                    temp = it.toString()
                    result.add(temp)
                }
                "letter" -> {
                    mode = "number"
                    temp = it.toString()
                    result.add(temp)
                }
                else -> {
                    TODO()
                }
            }
        } else if (it == '(' || it == ')') {
            if (it == '(') {
                temp = it.toString()
                stack.add(temp)
            } else {
                stack.reversed().forEach { el ->
                    if (el == "(") {
                        stack.remove("(")
                    } else {
                        result.add(el)
                        stack.remove(el)
                    }
                }
            }

        } else {
            when (mode) {
                "none" -> {
                    throw Exception("Выражение должно начитнаться с чисел")
                }
                "number" -> {
                    if (it.isLetter()) {
                        mode = "letter"
                        temp = it.toString()
                        stack.add(temp)
                    } else {
                        mode = "symbol"
                        temp = it.toString()
                        if (stack.isEmpty()) {
                            stack.add(temp)
                        } else {
                            if (priority(stack.last()) == priority(temp)) {
                                result.add(stack.removeLast())
                                stack.add(temp)
                            } else if (priority(stack.last()) < priority(temp)) {
                                stack.add(temp)
                            } else if (priority(stack.last()) > priority(temp)) {
                                result.add(stack.removeLast())
                                stack.add(temp)
                            }
                        }
                    }
                }
                "letter" -> {
                    temp += it.toString()
                    stack.removeLast()
                    stack.add(temp)
                    if (temp.length == 3) {
                        stack.removeLast()
                        if (stack.isEmpty()) {
                            stack.add(temp)
                        } else if (priority(stack.last()) == priority(temp)) {
                            result.add(stack.removeLast())
                            stack.add(temp)
                        } else if (priority(stack.last()) < priority(temp)) {
                            stack.add(temp)
                        } else if (priority(stack.last()) > priority(temp)) {
                            result.add(stack.removeLast())
                            stack.add(temp)
                        }
                    }

                }
                else -> {
                    TODO()
                }
            }
        }
    }
    return (result + stack.reversed()).toMutableList()
}

fun operators(x: Double, y: Double?, delimiter: String): Double {
    return when (delimiter) {
        "+" -> {
            Plus(x, y).calculate()
        }
        "-" -> {
            Minus(x, y!!.unaryMinus()).calculate()
        }
        "*" -> {
            Multiply(x, y).calculate()
        }
        "/" -> {
            Divide(x, y).calculate()
        }
        "exp" -> {
            Exponentiation(x, y).calculate()
        }
        "sqr" -> {
            Square(x, if (y == null) 0.5 else 1.0 / y).calculate()
        }
        else -> TODO()
    }
}

fun polska(list: MutableList<String>): Number {
    val stack2: MutableList<String> = mutableListOf()
    list.forEach {
        if (it[0].isDigit()) {
            stack2.add(it)
        } else {
            val num1: Double = stack2.removeLast().toDouble()
            val num2: Double = stack2.removeLast().toDouble()
            stack2.add(operators(num2, num1, it).toString())
        }
    }
    return if (stack2.last().toDouble() % 1 == 0.0) {
        stack2.removeLast().toDouble().toLong()
    } else {
        stack2.removeLast().toDouble()
    }
}