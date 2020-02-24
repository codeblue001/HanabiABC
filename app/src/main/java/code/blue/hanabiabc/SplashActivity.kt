package code.blue.hanabiabc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SplashActivity : AppCompatActivity() {

    private  lateinit var startButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startButton = findViewById(R.id.start_button)

        startButton.setOnClickListener { view: View? ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
