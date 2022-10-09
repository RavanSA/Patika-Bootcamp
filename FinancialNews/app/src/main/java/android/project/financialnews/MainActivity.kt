package android.project.financialnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.NavHostFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeNavController()
    }


    private fun initializeNavController(){
        supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
    }

}