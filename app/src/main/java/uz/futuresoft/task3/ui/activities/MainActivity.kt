package uz.futuresoft.task3.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.futuresoft.task3.R
import uz.futuresoft.task3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}