package sv.edu.api.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import sv.edu.api.ui.main.MainActivity
import sv.udb.api.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

 
        val rootView: View = findViewById(android.R.id.content)
        rootView.setOnClickListener {
            goToMain()
        }


        Handler(Looper.getMainLooper()).postDelayed({
            goToMain()
        }, 10000) // 10000ms = 10 segundos
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() 
    }
}
