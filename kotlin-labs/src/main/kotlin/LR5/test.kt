package LR5

class Test : ITest {

}

interface ITest {
    fun flex() = println("FLEX")
}

fun main() {
    val test = Test()
    test.flex()
}