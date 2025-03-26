package `2503`

bookRepository.save(Book.fixture("이상한 나라의 엘리스"))

private fun Book.Companion.fixture(
    name: String = "책 이름",
    type: String = "COMPUTER",
    id: Long? = null,
): BOOK {
    return BOOK(
        name = name,
        type = type,
        id = id,
    )
}


private fun Book.Companion.fixture(
    name: String = "책 이름",
    type: String = "COMPUTER",
    id: Long? = null,
): Book = Book(name, type, id)