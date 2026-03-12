package dev.himanshu.imagesearchapp.ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import dev.himanshu.imagesearchapp.domain.useCase.GetImagesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update

class ImageViewModel(
    private val getImagesUseCase: GetImagesUseCase
): ViewModel() {

    private val _query = MutableStateFlow(viewModelScope,"")

    @NativeCoroutinesState
    val query: StateFlow<String> = _query

    private val _uiState = MutableStateFlow(viewModelScope,ImageUiState())

    @NativeCoroutinesState
    val uiState: StateFlow<ImageUiState> = _uiState

    init {
        viewModelScope.launch {
            _query
                .filter {it.isNotBlank()}
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query->
                    _uiState.update { it.copy(isLoading = true) }
                    getImagesUseCase.invoke(query)
                        .onSuccess { data->
                        _uiState.update { ImageUiState(data = data) }
                        }.onFailure { error->
                            _uiState.update { ImageUiState(error = error.message ?: "Something went wrong") }
                        }
                }
        }
    }

    fun updateQuery(q: String){
        _query.update { q }

    }




}