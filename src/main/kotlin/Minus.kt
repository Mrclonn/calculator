class Minus(
    override val left: Number?,
    override val right: Number?,
    d: Double? = right?.toDouble()?.let { 0 - it }
) : Plus(left, d)
