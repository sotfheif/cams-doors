package io.github.sotfheif.camsdoors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sotfheif.camsdoors.data.CamsDoorsRepository
import io.github.sotfheif.camsdoors.data.CardDb
import io.github.sotfheif.camsdoors.data.getCardDbStub
import io.github.sotfheif.camsdoors.data.transformRemoteDataToUniformData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val _activeTab = MutableLiveData<Int>(0)
    val activeTab: LiveData<Int>
        get() = _activeTab

    fun switchTab(index: Int) {
        _activeTab.value = index
    }


    private val _cards = MutableLiveData(listOf<CardDb>())
    val cards: LiveData<List<CardDb>> get() = _cards

    private val _camsUi = MutableLiveData(listOf<CardDb>())
    val camsUi: LiveData<List<CardDb>> get() = _camsUi

    private val _doorsUi = MutableLiveData(listOf<CardDb>())
    val doorsUi: LiveData<List<CardDb>> get() = _doorsUi

    private val _revealedCardIdsList = MutableLiveData(listOf<Int>())
    val revealedCardIdsList: LiveData<List<Int>> get() = _revealedCardIdsList

    init {
        getFakeData()
    }

    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<CardDb>()
                repeat(20) { testList += getCardDbStub(it) }
                _cards.postValue(testList)
            }
        }
    }

    fun onItemExpanded(cardId: Int) {
        if (_revealedCardIdsList.value!!.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value!!.toMutableList().also { list ->
            list.add(cardId)
        }
    }

    fun onItemCollapsed(cardId: Int) {
        if (!_revealedCardIdsList.value!!.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value!!.toMutableList().also { list ->
            list.remove(cardId)
        }
    }

    fun loadUiData(repository: CamsDoorsRepository) {
        viewModelScope.launch {
            val remoteData = getRemoteData(repository)
            val uiData = transformRemoteDataToUniformData(remoteData)
            updateDataInUi(uiData)
        }
    }

    fun updateDataInUi(uiData: Pair<List<CardDb>, List<CardDb>>) {
        updateCamsInUi(uiData.first)
        updateDoorsInUi(uiData.second)
    }

    fun updateCamsInUi(camsUiData: List<CardDb>) {
        _camsUi.value = camsUiData
    }

    fun updateDoorsInUi(doorsUiData: List<CardDb>) {
        _doorsUi.value = doorsUiData
    }


    suspend fun getRemoteData(repository: CamsDoorsRepository) =
        repository.getCamsDoors()
}