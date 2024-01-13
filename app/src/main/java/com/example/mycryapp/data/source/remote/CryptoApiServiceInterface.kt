import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import com.example.mycryapp.utils.Result
interface CryptoApiServiceInterface {
    suspend fun getList():Result<MarketListData>
    suspend fun getLive():Result<MarketLiveData>
}
