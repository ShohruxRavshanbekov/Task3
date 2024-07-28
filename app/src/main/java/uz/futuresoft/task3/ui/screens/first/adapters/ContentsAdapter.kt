package uz.futuresoft.task3.ui.screens.first.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.futuresoft.task3.databinding.RecyclerViewItemFirstScreenContentBinding
import uz.futuresoft.task3.ui.screens.first.models.HomeModel

class ContentsAdapter(
    private val listener: ContentsAdapterListener,
) : ListAdapter<HomeModel, ContentsAdapterViewHolder>(ContentsAdapterDiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsAdapterViewHolder {
        val item = RecyclerViewItemFirstScreenContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContentsAdapterViewHolder(binding = item, listener = listener)
    }

    override fun onBindViewHolder(holder: ContentsAdapterViewHolder, position: Int) {
        holder.bind(item = getItem(position))
    }
}