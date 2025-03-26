package `2503`

import java.lang.IllegalArgumentException

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository UserLoanHistoryRepository,
) {
    @Transactional
    fun saveBook(request: BookRequest) {
        val newBook = Book(request.name, null)
        bookRepository.save(newBook)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        if (userLoanHistoryRepository.findByBookNameAndIsReturn(request.bookName, false) != null) {
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }
        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }
}
