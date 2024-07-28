package uz.futuresoft.task3.ui.screens.first.adapters

import androidx.recyclerview.widget.RecyclerView
import uz.futuresoft.task3.databinding.RecyclerViewItemFirstScreenContentBinding
import uz.futuresoft.task3.ui.screens.first.models.HomeModel

class ContentsAdapterViewHolder(
    private val binding: RecyclerViewItemFirstScreenContentBinding,
    private val listener: ContentsAdapterListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeModel) {
        binding.image.setImageResource(item.image)
        binding.title.text = item.title

        binding.item.setOnClickListener {
            listener.onItemClicked(item = item)
        }
    }
}