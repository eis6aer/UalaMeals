package com.matiaslev.ualatest.domain

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MealDataUseCaseTest {
    val bookRepository = mockk<MealRepository>(relaxed = true)
    private val useCaseListImpl = GetAllMealsUseCaseImpl(bookRepository)

    @Test
    fun `should delegate his job to the repository`() {
        useCaseListImpl()
        verify(exactly = 1) { bookRepository.getAllMeals() }
        confirmVerified(bookRepository)
    }
}