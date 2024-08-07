package com.example.cryptoapp3.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp3.R
import com.example.cryptoapp3.databinding.ItemCoinInfoBinding
import com.example.cryptoapp3.domain.pojo.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallBack) {
    private lateinit var binding: ItemCoinInfoBinding

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                tvSymbols.text = context.getString(R.string.symbols_template, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text = context.getString(
                    R.string.last_update_template,
                    lastUpdate
                )
                Picasso.get().load(imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }
}