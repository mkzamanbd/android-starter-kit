package me.kzaman.dna_solution.interfaces

import me.kzaman.dna_solution.network.Resource
import me.kzaman.dna_solution.utils.NoNetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false,
                            throwable.code(),
                            throwable.response()?.message().toString(),
                            throwable.response()?.errorBody()
                        )
                    }
                    is NoNetworkException -> {
                        Resource.Failure(true, 559, "No Network", null)
                    }
                    else -> {
                        Resource.Failure(false, 1001, "Unknown Error", null)
                    }
                }
            }
        }
    }
}