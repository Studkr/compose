package com.cinema.data

import com.cinema.IoDispatcher
import com.cinema.data.repositortory.ApiRepository
import com.cinema.utils.Result
import com.example.weather.data.model.MovieDetailsResponceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ShowDetailsUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    @IoDispatcher private val defaultScope: CoroutineDispatcher
) {
    operator fun invoke(id:Long): Flow<Result<MovieDetailsResponceModel>> = flow {
        emit(Result.success(apiRepository.getMovieDetails(id)))
    }.catch { e->
        emit(Result.error(e.message.toString(),null))
    }.flowOn(defaultScope)
}