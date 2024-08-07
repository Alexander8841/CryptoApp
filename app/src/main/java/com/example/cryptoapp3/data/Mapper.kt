package com.example.cryptoapp3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cryptoapp3.data.database.CoinInfoDbModel
import com.example.cryptoapp3.data.network.model.CoinInfoDto
import com.example.cryptoapp3.domain.pojo.CoinInfo
import com.example.cryptoapp3.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp3.data.network.model.CoinNamesListDto
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Mapper {
    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.jsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapListDtoToListDbModel(coinInfoDtoList: List<CoinInfoDto>) =
        coinInfoDtoList.map {
            CoinInfoDbModel(
                it.fromSymbol,
                it.toSymbol,
                it.price,
                it.lastUpdate,
                it.highDay,
                it.lowDay,
                it.lastMarket,
                BASE_IMAGE_URL + it.imageUrl
            )
        }

    fun mapListDbModelToListEntity(coinInfoDbModel: LiveData<List<CoinInfoDbModel>>) =
        coinInfoDbModel.map { it ->
            it.map {
                CoinInfo(
                    it.fromSymbol,
                    it.toSymbol,
                    it.price,
                    convertTimestampToTime(it.lastUpdate),
                    it.highDay,
                    it.lowDay,
                    it.lastMarket,
                    it.imageUrl
                )
            }
        }

    fun mapDbModelToEntity(coinPriceInfoDtoList: LiveData<CoinInfoDbModel>) =
        coinPriceInfoDtoList.map {
            CoinInfo(
                it.fromSymbol,
                it.toSymbol,
                it.price,
                convertTimestampToTime(it.lastUpdate),
                it.highDay,
                it.lowDay,
                it.lastMarket,
                it.imageUrl
            )
        }

    fun mapNamesListToString(namesList: CoinNamesListDto): String {
        return namesList.names?.map {
            it.coinName?.name
        }?.joinToString(",").orEmpty()
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}