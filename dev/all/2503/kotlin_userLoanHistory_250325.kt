package `2503`

import javax.annotation.processing.Generated

@Entity
class UserLoanHistory ()

    @ManyToOne
    val user: User,
    val bookName: String,
    val isReturn: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    fun doReturn() {
        isReturn = true
    }
}