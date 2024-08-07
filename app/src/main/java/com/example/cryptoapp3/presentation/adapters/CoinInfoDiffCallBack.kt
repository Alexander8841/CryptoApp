package com.example.cryptoapp3.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp3.domain.pojo.CoinInfo

object CoinInfoDiffCallBack: DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}