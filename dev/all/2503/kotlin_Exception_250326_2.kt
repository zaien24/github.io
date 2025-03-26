package `2503`
//커스텀 예외 클래스
class NotFoundException(message: String) : RuntimeException(message)

// 항상 예외를 던지는 함수
fun fail(message: String = "해당 객체를 찾을 수 없습니다."): Nothing {
    throw NotFoundException(message)
}

// CrudRepository 확장 함수
fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: fail("ID $id에 해당하는 데이터를 찾을 수 없습니다")
}

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getUser(id: Long): UserResponse {
        val user = userRepository.findByIdOrThrow(id)
        return UserResponse(user)
    }
}

fun <T,ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {

}