package android.project.loginsignupscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.project.loginsignupscreen.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private var isVisibilityOn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
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

            signupArrowBack.setOnClickListener {
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            }

        }

    }
}