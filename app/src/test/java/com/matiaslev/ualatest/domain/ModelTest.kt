package com.matiaslev.ualatest.domain

import com.matiaslev.ualatest.UnitTestUtils.mealData
import org.junit.Test

class ModelTest {

    @Test
    fun `should return the ingredients with value`() {
        assert(mealData.formattedIngredients.contains("a"))
        assert(mealData.formattedIngredients.contains("b"))
        assert(mealData.formattedIngredients.contains("c"))
        assert(mealData.formattedIngredients.contains(" ").not())
    }
}