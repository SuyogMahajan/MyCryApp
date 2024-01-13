
import android.util.Log
import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.mycryapp.utils.Result


class CoinRemoteDataSource(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    CryptoApiServiceInterface {
    override suspend fun getList(): Result<MarketListData> = withContext(ioDispatcher) {


        return@withContext try{

//            val s = list.joinToString(",")
            val r =  CryptoApiClient.retrofitService.getListData()

            if(r.isSuccessful){
                Log.d("MyData!!",r.body()!!.crypto.toString())
                Result.Success(r.body()!!)
            }else{

                Log.d("MyData!!","Not Working")
                Result.Loading
            }
        }catch (exception :Exception){
            Log.d("MyData!!",exception.message!!)
            Result.Error(exception)
        }

    }

    override suspend fun getLive(): Result<MarketLiveData> = withContext(ioDispatcher) {

        return@withContext try{

//            val s = list.joinToString(",")
            val r =  CryptoApiClient.retrofitService.getLiveData()

            if(r.isSuccessful){
                Log.d("MyData!!",r.body()!!.rates.toString())
                Result.Success(r.body()!!)
            }else{

                Log.d("MyData!!","Not Working")
                Result.Loading
            }
        }catch (exception :Exception){
            Log.d("MyData!!",exception.message!!)
            Result.Error(exception)
        }

    }
}