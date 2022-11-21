package com.cinema.data

import com.cinema.IoDispatcher
import com.cinema.data.repositortory.ApiRepository
import com.cinema.repository.MediaModel
import com.cinema.utils.Result
import com.example.weather.data.model.SerialsModelResponce
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SerialsTrendingUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    @IoDispatcher private val defaultScope: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<Result<List<MediaModel>>> = flow {
        emit(Result.success(MediaModel.convertFromSerialsTrending(apiRepository.getPopularSerials().results)))
    }.catch { e->
        emit(Result.error(e.message.toString(),null))
    }.flowOn(defaultScope)
}