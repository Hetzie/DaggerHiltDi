package com.excellentwebworld.demodaggerhiltdi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.excellentwebworld.demodaggerhiltdi.model.Resource
import com.excellentwebworld.demodaggerhiltdi.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KanyeWestViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    sealed class QuotesEvent {
        class Success(val resultText: String) : QuotesEvent()
        class Failure(val errorText: String) : QuotesEvent()
        object Loading : QuotesEvent()
        object Empty : QuotesEvent()
    }

    private val _conversion = MutableStateFlow<QuotesEvent>(QuotesEvent.Empty)
    val conversion: StateFlow<QuotesEvent> = _conversion


    fun getQuotesViewModel() {
        viewModelScope.launch {
            _conversion.value = QuotesEvent.Loading
            when (val quotesResponse = repository.getQuotes()) {
                is Resource.Error -> _conversion.value = QuotesEvent.Failure(quotesResponse.msg!!)
                is Resource.Success -> {
                    val quote = quotesResponse.data!!.quote
                    if (quote == null) {
                        _conversion.value = QuotesEvent.Failure("Unexpected error")
                    } else {
                        _conversion.value = QuotesEvent.Success(
                            "$quote"
                        )
                    }
                }
            }
        }
    }
}