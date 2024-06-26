package uz.futuresoft.task3.screens.first.adapters

import uz.futuresoft.task3.screens.first.models.HomeModel

interface ContentsAdapterListener {
    fun onItemClicked(item: HomeModel)
}