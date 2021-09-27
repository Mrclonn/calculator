class Square(
    override val left: Number?,
    override val right: Number?
) : Exponentiation(left, right) {//if(right == null) 0.5 else 1.0/right.toDouble()) {
    companion object {
        val delim = "sqr"
    }
}
