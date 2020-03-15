package com.matiaslev.ualatest.domain

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GetAllMealUseCaseTest {
    val bookRepository = mockk<MealRepository>(relaxed = true)
    private val useCaseListImpl = GetAllMealsUseCaseImpl(bookRepository)

    @Test
    fun `should delegate his job to the repository`() {
        useCaseListImpl()
        verify(exactly = 1) { bookRepository.getAllMeals() }
        confirmVerified(bookRepository)
    }
}