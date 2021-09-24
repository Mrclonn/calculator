class Division(
    override val left: Number?,
    override val right: Number?
) : Operation {
    override fun calculate(): Double {
        val right2 = right ?: left
        return left!!.toDouble() / right2!!.toDouble()
    }
}