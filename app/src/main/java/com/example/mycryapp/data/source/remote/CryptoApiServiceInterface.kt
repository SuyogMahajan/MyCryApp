import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData

interface CryptoApiServiceInterface {
    suspend fun getList():MarketListData
    suspend fun getLive():MarketLiveData
}
