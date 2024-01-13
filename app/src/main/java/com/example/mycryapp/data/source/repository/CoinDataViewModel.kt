import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import com.focus.cryptotracker.data.source.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinDataViewModel(application: Application) : AndroidViewModel(application) {

    val repository = CoinRepository(application.baseContext, Dispatchers.IO)

    fun getList(): LiveData<MarketListData> {
        val v = MutableLiveData<MarketListData>()

        viewModelScope.launch(Dispatchers.IO){
            val r = repository.getList()
            v.postValue(r)
        }

        return v
    }

    fun getLive(): LiveData<MarketLiveData> {
        val v = MutableLiveData<MarketLiveData>()

        viewModelScope.launch(Dispatchers.IO){
            val r = repository.getLive()
            v.postValue(r)
        }

        return v
    }
}