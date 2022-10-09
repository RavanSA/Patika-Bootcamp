package android.project.loginsignupscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.project.loginsignupscreen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var isVisibilityOn: Boolean = false
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.apply {
            ivShowPassword.setOnClickListener {
                isVisibilityOn = if (isVisibilityOn) {
                    binding.ivShowPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    false
                } else {
                    binding.ivShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    true
                }
            }

            btnSignUp.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            }
        }

    }

}