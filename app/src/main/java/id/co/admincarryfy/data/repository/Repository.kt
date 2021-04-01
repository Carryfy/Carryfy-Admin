package id.co.admincarryfy.data.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.RemoteDataSource
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
){
    val remoteDataSource = remoteDataSource
}