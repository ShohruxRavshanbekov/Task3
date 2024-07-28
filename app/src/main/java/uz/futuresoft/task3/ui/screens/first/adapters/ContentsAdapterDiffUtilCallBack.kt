package uz.futuresoft.task3.ui.screens.first.adapters

import androidx.recyclerview.widget.DiffUtil
import uz.futuresoft.task3.ui.screens.first.models.HomeModel

object ContentsAdapterDiffUtilCallBack : DiffUtil.ItemCallback<HomeModel>() {
    override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
        return oldItem == newItem
    }
}