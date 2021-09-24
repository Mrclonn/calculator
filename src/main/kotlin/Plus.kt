open class Plus(
    override val left: Number?,
    override val right: Number?,
) : Operation {
    override fun calculate(): Double {
        val right2 = right ?: left //if(right == null) left else right
        return left!!.toDouble() + right2!!.toDouble()
    }
    companion object {
        val delim = "+"
    }
}