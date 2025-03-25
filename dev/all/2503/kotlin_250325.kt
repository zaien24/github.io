class Person {
        // 프로퍼티
        var name: String = ""
        var age: Int = 0

        // 메서드
        fun introduce() {
                println("")
        }

}

class Person(val name: String, val age: Int)

val 읽기 전용
var 변경 가능

val p = Person("태현, 30")
println(p.name)

class Person(val name: String, val age: Int) {
        init {
                println("")
        }
}

open class Animal {
        fun eat() = print()
}

class Dog : Animal() {
        fun bark() = println("멍멍")
}
// opne 키워드 없으면 상속 불가
// 메서드 오버라이드 open, override 필요

data class User(val name: String, val age: Int)
//equals, hashCode, toString, copy() 자동 생성
//DTO 나 request/response 객체에 많이 사용

object Singleton {
        fun sayHi() = print()
}

@SpringBootTest
class UserServiceTest(
        private val userRepository: UserRepository,
        private val userService: UserService,
        private val userLoanHistoryRepository: UserLoanHistoryRepository,
) : FunSpec({
        test("유저 저장이 정상 동작한다") {
                //given
                val request = UserCreeateRequest("최태현", null)

                // when
                userService.saveUser(request)

                // then
                val results = userRepository.findAll()
                assertThat(results).hasSize(1)
                assertThat(result[0].name).isEqualTo("최태현")
                assertThat(result[0].age).isNull()
        }
}) {
        override suspend fun afterEach(testCase: TestCase, result: TestResult) {
                super.afterEach(testCase, result)
                userRepository.deleteAll()
        }
}
