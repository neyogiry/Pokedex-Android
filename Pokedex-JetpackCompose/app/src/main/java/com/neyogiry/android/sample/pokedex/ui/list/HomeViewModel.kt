package com.neyogiry.android.sample.pokedex.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neyogiry.android.sample.pokedex.data.RemoteDataSource
import com.neyogiry.android.sample.pokedex.domain.PokedexRepository
import com.neyogiry.android.sample.pokedex.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PokedexRepository = PokedexRepository(RemoteDataSource()),
) : ViewModel() {

    // Holds our view state which the UI collects via [state]
    private val _state = MutableStateFlow(HomeViewState())

    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            repository.pokedex.flowOn(Dispatchers.IO).collect {
                _state.value = HomeViewState(pokemonList = it)
            }
        }
    }

}

data class HomeViewState(
    val pokemonList: List<Pokemon> = emptyList(),
    val errorMessage: String? = null,
)