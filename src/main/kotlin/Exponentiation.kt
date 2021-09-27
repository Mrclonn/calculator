import kotlin.math.pow

open class Exponentiation(
    override val left: Number?,
    override val right: Number?
) : Operation {
    override fun calculate(): Double {
        val right2 = right ?: left
        return left!!.toDouble().pow(right2!!.toDouble())
    }
    companion object {
        val delim = "exp"
    }
}