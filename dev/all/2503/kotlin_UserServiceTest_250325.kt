package `2503`

@SpringBootTest
class UserServiceTest(
        private val userRepository: UserRepository,
        private val userService: UserService,
        private val userLoanHistoryRepository: UserLoanHistoryRepository,
) : FuncSpec({
        test ("유저 조회가 정상 동작한다") {
                // given
                userRepository.saveAll(
                        listOf(
                                User("A", 20),
                                User("B", null),
                        )
                )

                // when
                val results = userService.getUsers()

                // then
                assertThat(results).hasSize(2)
                assertThat(results).extracting("name").contatinsExactlyInAnyOrder("A", "B")
                assertThat(results).extracting("age").containsExactlyInAnyOrder(20, null)
        }

        test ("유저 업데이트가 정상 동작한다") {
                // given
                val saveUser = userRepository.save(User("A", null))
                val request = UserUpdateRequest(savedUser.id!!, "B)

                // when
                userService.updateUserName(request)

                // then
                val result = userRepository.findAll()[0]
                assertThat(result.name).isEqualTo("B")
        }

        test ("유저 삭제가 정상 동작한다") {

                // given
                userRepository.save(User("A", null))

                // when
                userService.deleteUser("A")

                // then
                assertThat(userRepository.findAll()).isEmpty()
        }
})