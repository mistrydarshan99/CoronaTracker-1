package org.ucarsu.coronaexample.dashboard.data

import dagger.Module
import dagger.Provides
import data.datasource.DataSource
import data.error.ErrorFactory
import org.ucarsu.coronaexample.dashboard.domain.ContinentDataResponse
import org.ucarsu.coronaexample.dashboard.domain.CountriesDataResponse
import org.ucarsu.coronaexample.dashboard.domain.DashBoardRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DashBoardDataModule {
    @Provides
    @Singleton
    fun provideDashBoardServices(retrofit: Retrofit): DashBoardServices =
        retrofit.create(DashBoardServices::class.java)

    @Provides
    fun provideGetDashBoardCountryRemoteDataSource(
        dashBoardServices: DashBoardServices,
        errorFactory: ErrorFactory
    ): DataSource.RemoteDataSource.FetchDataSource<List<CountriesDataResponse>> =
        GetDashBoardCountryRemoteDataSource(
            dashBoardServices,
            errorFactory
        )

    @Provides
    fun provideGetDashBoardContinentRemoteDataSource(
        dashBoardServices: DashBoardServices,
        errorFactory: ErrorFactory
    ): DataSource.RemoteDataSource.FetchDataSource<List<ContinentDataResponse>> =
        GetDashBoardContinentRemoteDataSource(
            dashBoardServices,
            errorFactory
        )

    @Singleton
    @Provides
    fun provideDashBoardRepository(
        dashBoardCountryRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<CountriesDataResponse>>,
        dashBoardContinentRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<List<ContinentDataResponse>>,
        errorFactory: ErrorFactory
    ): DashBoardRepository = DashBoardRepositoryImpl(
        errorFactory,
        dashBoardCountryRemoteDataSource,
        dashBoardContinentRemoteDataSource
    )
}