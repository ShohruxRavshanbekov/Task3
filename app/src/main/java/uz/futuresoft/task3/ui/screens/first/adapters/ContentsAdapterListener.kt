package uz.futuresoft.task3.ui.screens.first.adapters

import uz.futuresoft.task3.ui.screens.first.models.HomeModel

interface ContentsAdapterListener {
    fun onItemClicked(item: HomeModel)
}