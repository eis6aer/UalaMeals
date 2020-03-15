package com.matiaslev.ualatest.data

import io.mockk.mockk
import org.junit.Test

class MealDataRepositoryTest {
    val api = mockk<Api>(relaxed = true)
    val database = mockk<AppDatabase>(relaxed = true)
    private val bookRepository = MealMealRepositoryImpl(api, database)

    @Test
    fun `should test something`() {

    }
}