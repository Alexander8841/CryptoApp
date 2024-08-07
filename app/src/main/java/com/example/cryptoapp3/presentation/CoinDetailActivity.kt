package com.example.cryptoapp3.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp3.R

class CoinDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        if (savedInstanceState == null) supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                CoinDetailFragment.newInstance(intent.getStringExtra(EXTRA_FROM_SYMBOL)
                    ?: throw RuntimeException("fromSymbol was not found"))
            )
            .commit()
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
