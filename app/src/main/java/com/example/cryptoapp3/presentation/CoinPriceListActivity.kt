package com.example.cryptoapp3.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp3.R
import com.example.cryptoapp3.databinding.ActivityCoinPrceListBinding
import com.example.cryptoapp3.domain.pojo.CoinInfo
import com.example.cryptoapp3.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CoinViewModel
    private val binding by lazy {
        ActivityCoinPrceListBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                launchRightMode(coinInfo)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.coinList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun launchRightMode(coinInfo: CoinInfo) {
        if (binding.fragmentContainer == null) {
            val intent = CoinDetailActivity.newIntent(
                this@CoinPriceListActivity,
                coinInfo.fromSymbol
            )
            startActivity(intent)
        } else {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    CoinDetailFragment.newInstance(coinInfo.fromSymbol)
                )
                .addToBackStack(null)
                .commit()
        }
    }
}
