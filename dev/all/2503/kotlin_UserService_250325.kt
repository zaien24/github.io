package `2503`

import java.lang.IllegalArgumentException

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun saveUser(request: UserCreateRequest) {
        val newUser = User(Request.name, request.age)
        userRepository.save(newUser)
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> = userRepository.findAll().map { UserResponse(it) }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        val user = userRepository.findById(request.id).orElseThrow { IllegalArgumentException }
        val user = userRepository.findById(request.id!!) ?: throw IllegalArgumentException()
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String) {
        //val user = userRepository.findByname(name).orElseThrow { java.lang.IllegalArgumentException }
        val user = userRepository.findByName(name!!) ?: throw java.lang.IllegalArgumentException()
        userRepository.delete(user)
    }



}