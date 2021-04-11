package id.co.admincarryfy.data.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.LocalDataSource
import id.co.admincarryfy.data.RemoteDataSource
import javax.inject.Inject

@ActivityRetainedScoped
class DriverRepository @Inject constructor(
    localDataSource: LocalDataSource,
    remoteDataSource: RemoteDataSource
) {
    val local = localDataSource
    val remote = remoteDataSource
}