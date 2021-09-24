class Square(
    override val left: Number?,
    override val right: Number?
) : Exponentiation(left, if(right == null) 0.5 else 1.0/right.toDouble())
