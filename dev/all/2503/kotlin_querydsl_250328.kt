package `2503`

@Transactional(readOnly = true)
fun getUserLoan(): LIst<UserLoanHistory> {
    return userRepository.findAll().map {
        UserLoanHistoryResponse(it.name, it.userLoanHistories.map {
            it1 -> BOokHistory(it1.bookName, it1.status == User)
        })
    }.toList()
}

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?

    @Query("select distinct u from User u left join fetch u.userLoanHistroies")
    fun findAllWithHistories(): List<User>
}


@Query("select new com.group.libraryapp.dto.book.response.BookStatResponse(b.type, count(b.id)) from Book b group by b.type")
fun getStat(): List<BookStatResponse>



interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {
    fun findByName(name: String): User?
}

interface UserRepositoryCustom {
    fun findAllWithHistories(): List<User>
}

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
): UserRepositoryCustom {
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory).on(userLoanHistroy.user.id.eq(user.id)).fetchJoin()
            .fetch()
    }
}

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {
    fun findByName(name: String): User?
}

interface UserRepositoryCustom {
    fun findAllWithHistories(): List<User>
}

class UserRepositoryCustomImpl(
    private val queryFactory: JpaQueryFacotry,
) : UserRepositoryCustom {
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistry).on(userLoanHistry)
    }
}
