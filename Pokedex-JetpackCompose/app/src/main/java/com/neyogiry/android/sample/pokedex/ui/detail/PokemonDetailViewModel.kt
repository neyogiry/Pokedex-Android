package com.neyogiry.android.sample.pokedex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.neyogiry.android.sample.pokedex.data.RemoteDataSource
import com.neyogiry.android.sample.pokedex.domain.PokedexRepository
import com.neyogiry.android.sample.pokedex.domain.PokemonDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val url: String,
    private val repository: PokedexRepository = PokedexRepository(RemoteDataSource()),
) : ViewModel() {

    // Holds our view state which the UI collects via [state]
    private val _state = MutableStateFlow(PokemonDetailViewState())

    val state: StateFlow<PokemonDetailViewState>
        get() = _state

    init {
        viewModelScope.launch {
            repository.pokemonDetail(url).flowOn(Dispatchers.IO).collect {
                _state.value = PokemonDetailViewState(pokemon = it)
            }
        }
    }

    /**
     * Factory for CartViewModel that takes SnackbarManager as a dependency
     */
    companion object {
        fun provideFactory(
            url: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PokemonDetailViewModel(url) as T
            }
        }
    }

}

data class PokemonDetailViewState(
    val pokemon: PokemonDetail? = null,
    val errorMessage: String? = null,
)