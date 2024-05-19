package LR6

import org.junit.jupiter.api.Test
import java.util.Date

class UserTest {

    @Test
    fun users_with_same_attributes_are_equal() {
        val birthDate = Date(1990, 5, 21)
        val user1 = User("Ivanov", "Ivan", "Ivanovich", "ivan_tg", birthDate)
        val user2 = User("Ivanov", "Ivan", "Ivanovich", "ivan_tg", birthDate)
        assert(user1 == user2)
    }

    @Test
    fun equals_with_null_and_non_user_objects() {
        val birthDate = Date(1990, 5, 21)
        val user = User("Petrov", "Petr", "Petrovich", "petr_tg", birthDate)
        val nonUserObject = "This is a string"
        assert(!user.equals(null))
        assert(!user.equals(nonUserObject))
    }

    @Test
    fun compareTo_orders_users_correctly() {
        val birthDate1 = Date(1990, 5, 21)
        val birthDate2 = Date(1995, 3, 15)
        val user1 = User("Ivanov", "Ivan", "Ivanovich", "ivan_tg", birthDate1)
        val user2 = User("Petrov", "Petr", "Petrovich", "petr_tg", birthDate2)

        val result = user1.compareTo(user2)

        assert(result < 0)
    }

    @Test
    fun hashcode_consistent_with_equals() {
        val birthDate = Date(1990, 5, 21)
        val user1 = User("Ivanov", "Ivan", "Ivanovich", "ivan_tg", birthDate)
        val user2 = User("Ivanov", "Ivan", "Ivanovich", "ivan_tg", birthDate)
        assert(user1.hashCode() == user2.hashCode())
    }

    @Test
    fun user_creation_with_minimal_required_fields() {
        val birthDate = Date(1990, 5, 21)
        val user = User("Ivanov", "Ivan", birth = birthDate)
        assert(user.lastname == "Ivanov")
        assert(user.name == "Ivan")
        assert(user.fathername == "")
        assert(user.tg == "")
        assert(user.birth == birthDate)
    }
}