
import android.util.Log
import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinRemoteDataSource(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    CryptoApiServiceInterface {
    override suspend fun getList(): MarketListData = withContext(ioDispatcher) {


        return@withContext try{

//            val s = list.joinToString(",")
            val r =  CryptoApiClient.retrofitService.getListData()

            if(r.isSuccessful){
                Log.d("MyData!!",r.body()!!.crypto.toString())
                r.body()!!
            }else{

                Log.d("MyData!!","Not Working")
                MarketListData()
            }
        }catch (exception :Exception){
            Log.d("MyData!!",exception.message!!)
            MarketListData()
        }

    }

    override suspend fun getLive(): MarketLiveData = withContext(ioDispatcher) {

        return@withContext try{

//            val s = list.joinToString(",")
            val r =  CryptoApiClient.retrofitService.getLiveData()

            if(r.isSuccessful){
                Log.d("MyData!!",r.body()!!.toString())
                r.body()!!
            }else{

                Log.d("MyData!!","Not Working")
                MarketLiveData()
            }
        }catch (exception :Exception){
            Log.d("MyData!!",exception.message!!)
            MarketLiveData()
        }

    }
}