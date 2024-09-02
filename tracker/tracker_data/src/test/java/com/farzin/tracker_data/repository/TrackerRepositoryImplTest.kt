package com.farzin.tracker_data.repository

import com.farzin.tracker_data.remote.OpenFoodApi
import com.google.common.truth.Truth.assertThat
import com.plcoding.tracker_data.remote.malformedFoodResponse
import com.plcoding.tracker_data.remote.validFoodResponse
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TrackerRepositoryImplTest {


    private lateinit var repository: TrackerRepositoryImpl
    private lateinit var mockkWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: OpenFoodApi

    @Before
    fun setUp() {
        mockkWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1L, TimeUnit.SECONDS)
            .connectTimeout(1L, TimeUnit.SECONDS)
            .readTimeout(1L, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .baseUrl(mockkWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenFoodApi::class.java)


        repository = TrackerRepositoryImpl(
            trackerDao = mockk(relaxed = true),
            api = api
        )
    }

    @After
    fun tearDown() {
        mockkWebServer.shutdown()
    }

    @Test
    fun `Search food, valid response, return Result success`() = runBlocking {
        mockkWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validFoodResponse)
        )
        val result = repository.searchFood("kebab", 1, 50)

        assertThat(result.isSuccess).isTrue()

    }


    @Test
    fun `Search food, invalid response, return Result failure`() = runBlocking {
        mockkWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validFoodResponse)
        )
        val result = repository.searchFood("kebab", 1, 50)

        assertThat(result.isFailure).isTrue()

    }

    @Test
    fun `Search food, malformed response, return Result failure`() = runBlocking {
        mockkWebServer.enqueue(
            MockResponse()
                .setBody(malformedFoodResponse)
        )
        val result = repository.searchFood("kebab", 1, 50)

        assertThat(result.isFailure).isTrue()

    }
}