package code.blue.hanabiabc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {
    lateinit var scoreTitleTextView: TextView
    lateinit var scoreTextView: TextView
    lateinit var victoryImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getStringExtra("score").toInt()
        scoreTitleTextView =findViewById(R.id.score_title_textView)
        scoreTextView =findViewById(R.id.score_textView)
        victoryImage =findViewById(R.id.victory_image)
        scoreTextView.text = "You scored $score out of 20"

        if (score == 20){
            scoreTitleTextView.text = "Outstanding!!!"
            victoryImage.setImageResource(R.drawable.happy)
        }
        if (score in 15..19){
            scoreTitleTextView.text = "Excellent!!"
            victoryImage.setImageResource(R.drawable.happy)
        }
        if (score in 10..14){
            scoreTitleTextView.text = "Not Bad!"
            victoryImage.setImageResource(R.drawable.happy)
        }
        if (score <= 9){
            scoreTitleTextView.text = "Nice attempt... Try again"
            victoryImage.setImageResource(R.drawable.sad)
        }

    }
}
