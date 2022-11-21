package com.cinema.data

import com.cinema.IoDispatcher
import com.cinema.data.repositortory.ApiRepository
import com.cinema.utils.Result
import com.cinema.model.MovieResponseModel
import com.cinema.repository.MediaModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class TrendingUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    @IoDispatcher private val defaultScope: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<Result<List<MediaModel>>> = flow {
       emit(Result.success(MediaModel.convertFromTrendingMovie(apiRepository.geTrending().results)))
    }.catch {e->
        emit(Result.error(e.message.toString(),null))
    }.flowOn(defaultScope)
}