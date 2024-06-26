package uz.futuresoft.task3.screens.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.futuresoft.task3.R
import uz.futuresoft.task3.databinding.ScreenSecondBinding

class SecondScreen : Fragment() {

    private val binding by lazy { ScreenSecondBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}