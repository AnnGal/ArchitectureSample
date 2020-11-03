package an.maguste.android.archsamples.models

import kotlinx.coroutines.Deferred

interface AuthRepository {
    suspend fun login(email: String, password: String): Deferred<String>
}