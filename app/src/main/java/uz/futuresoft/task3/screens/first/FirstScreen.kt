package uz.futuresoft.task3.screens.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.futuresoft.task3.R
import uz.futuresoft.task3.databinding.ScreenFirstBinding
import uz.futuresoft.task3.screens.first.adapters.ContentsAdapter
import uz.futuresoft.task3.screens.first.adapters.ContentsAdapterListener
import uz.futuresoft.task3.screens.first.models.HomeModel

class FirstScreen : Fragment(), ContentsAdapterListener {

    private val binding by lazy { ScreenFirstBinding.inflate(layoutInflater) }

    private val contentsAdapter by lazy { ContentsAdapter(listener = this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Sample data for recycler view
        val contents = listOf(
            HomeModel(
                image = R.drawable.card_image1,
                title = getString(R.string.ko_p_qavatli_uylar)
            ),
            HomeModel(
                image = R.drawable.card_image2,
                title = getString(R.string.hovli_uylar)
            )
        )
        contentsAdapter.submitList(contents)
        binding.contents.adapter = contentsAdapter
    }

    override fun onItemClicked(item: HomeModel) {
        findNavController().navigate(R.id.action_firstScreen_to_secondScreen)
    }
}