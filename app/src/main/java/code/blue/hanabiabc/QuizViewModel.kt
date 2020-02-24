package code.blue.hanabiabc

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    /*    init {
            Log.d(TAG, "ViewModel instance created")
        }

        override fun onCleared() {
            super.onCleared()
            Log.d(TAG, "ViewModel instance about to be destroyed")
        }
    */

    var currentIndex = 0
    var isCheater = false

    private val questionBank = listOf(
        Question(R.string.letter_t, true, R.drawable.tomato, "T", "N"),
        Question(R.string.letter_u, false, R.drawable.unicorn, "Y", "U"),
        Question(R.string.letter_w, false, R.drawable.wagon, "T", "W"),
        Question(R.string.letter_x, true, R.drawable.xray, "X", "S"),
        Question(R.string.letter_a, false, R.drawable.plane, "R", "A"),
        Question(R.string.letter_s, true, R.drawable.sun, "S", "R"),
        Question(R.string.letter_y, true, R.drawable.yoyo, "Y", "J"),
        Question(R.string.letter_z, true, R.drawable.zipper, "Z", "S"),
        Question(R.string.letter_r, false, R.drawable.rose, "L", "R"),
        Question(R.string.letter_q, false, R.drawable.queen, "K", "Q"),
        Question(R.string.letter_p, true, R.drawable.pig, "P", "B"),
        Question(R.string.letter_o, false, R.drawable.olive, "H", "O"),
        Question(R.string.letter_n, true, R.drawable.nest, "N", "R"),
        Question(R.string.letter_m, false, R.drawable.mountain, "Y", "M"),
        Question(R.string.letter_l, false, R.drawable.lime, "R", "L"),
        Question(R.string.letter_k, true, R.drawable.koala, "K", "C"),
        Question(R.string.letter_j, false, R.drawable.juice, "G", "J"),
        Question(R.string.letter_i, false, R.drawable.insect, "E", "I"),
        Question(R.string.letter_h, true, R.drawable.hamburger, "H", "J"),
        Question(R.string.letter_j, true, R.drawable.juice, "J", "G"),
        Question(R.string.letter_f, false, R.drawable.fruit, "G", "F"),
        Question(R.string.letter_e, true, R.drawable.elephant, "E", "H"),
        Question(R.string.letter_c, true, R.drawable.car, "C", "K"),
        Question(R.string.letter_b, false, R.drawable.boat, "M", "B"),
        Question(R.string.letter_d, true, R.drawable.dinosaur, "D", "V"),
        Question(R.string.letter_v, false, R.drawable.vegetable, "B", "V")
    )


    val  currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

    val currentQuestionImage: Int
        get() = questionBank[currentIndex].icon

    val currentChoice1: String
        get() = questionBank[currentIndex].choice_1

    val currentChoice2: String
        get() = questionBank[currentIndex].choice_2

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}