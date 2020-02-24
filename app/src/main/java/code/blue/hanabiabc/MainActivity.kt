package code.blue.hanabiabc

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CHEAT_CODE = 0

class MainActivity : AppCompatActivity() {

    private lateinit var option_1: Button
    private lateinit var option_2: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button
    private lateinit var  questionImageView: ImageView

    //stash the QuizViewModel using lazy initialized property
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
//        val provider: ViewModelProvider = ViewModelProviders.of(this)
//        val quizViewModel =  provider.get(QuizViewModel::class.java)
//        Log.d(TAG, "Got a quizViewModel: $quizViewModel")

        option_1 = findViewById(R.id.true_button)
        option_2 = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)
        questionImageView = findViewById(R.id.question_image_view)

        //disable next button
        nextButton.isEnabled = false


        option_1.setOnClickListener { view: View ->
            checkAnswer(true)
            Log.d(TAG, "current count is $count")
            enableButtons()
        }

        option_2.setOnClickListener { view: View? ->
            checkAnswer(false)
            Log.d(TAG, "current count is $count")
            enableButtons()
        }

        nextButton.setOnClickListener { view: View ->
            //            currentIndex = (currentIndex+1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
            updateImage()
            choices()
            disable()
        }

        cheatButton.setOnClickListener { view: View ->
            //open new activity
//            val intent = Intent(this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val  options = ActivityOptions.makeClipRevealAnimation(view, 0,0, view.width, view.height)
//            startActivity(intent)
            startActivityForResult(intent, REQUEST_CHEAT_CODE, options.toBundle())}else{
                startActivityForResult(intent, REQUEST_CHEAT_CODE)
            }
        }
        updateQuestion()
    }

    //Logging Lifecycle callbacks
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun updateQuestion() {
//        val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
//        val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.success
            count++
            total++
            "Correct... your current score is $count out of $total"
        } else {
            R.string.failure
            total++
//            questionImageView.setImageResource(R.drawable.tomato2)
            "Incorrect... your current score is $count out of $total"
        }
//        val messageResId = when{
//            quizViewModel.isCheater -> R.string.judgement_toast
//            userAnswer == correctAnswer -> R.string.success
//            else -> R.string.failure
//        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        if (total == 20){
            val intent = Intent(this, ScoreActivity::class.java)
            val score = count.toString()
            intent.putExtra("score",  score)
            startActivity(intent)
        }
    }

    private fun updateImage() {
        val questionImageIcon = quizViewModel.currentQuestionImage
        questionImageView.setImageResource(questionImageIcon)
    }

    private fun choices() {
        val choiceText1 = quizViewModel.currentChoice1
        val choiceText2 = quizViewModel.currentChoice2
        option_1.setText(choiceText1)
        option_2.setText(choiceText2)
    }
    //Methods to enable and disable buttons
    private fun enableButtons() {
        option_1.isEnabled = false
        option_2.isEnabled = false
        nextButton.isEnabled = true
        nextButton.setBackgroundColor(Color.GREEN)
    }

    private fun disable() {
        option_1.isEnabled = true
        option_2.isEnabled = true
        nextButton.isEnabled = false
        nextButton.setBackgroundColor(Color.LTGRAY)
    }

    var count = 0
    var total = 0

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CHEAT_CODE){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?:  false
        }
    }
}
