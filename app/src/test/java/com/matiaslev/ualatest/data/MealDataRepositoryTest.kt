package com.matiaslev.ualatest.data

import arrow.core.Either
import com.matiaslev.ualatest.UnitTestUtils.mealRemote
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MealDataRepositoryTest {
    val api = mockk<Api>(relaxed = true)
    val database = mockk<AppDatabase>(relaxed = true)
    private val bookRepository = MealMealRepositoryImpl(api, database)

    @Test
    fun `should not go to the database on banner updates`() {
        every { api.getRandomMeal() } returns Either.right(listOf(mealRemote))
        bookRepository.addRandomMeal()
        verify(exactly = 0) { database.mealDao() }
    }
}