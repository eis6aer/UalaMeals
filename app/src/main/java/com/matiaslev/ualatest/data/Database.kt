package com.matiaslev.ualatest.data

import androidx.room.*

@Database(entities = arrayOf(MealLocal::class), version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}

@Dao
interface MealDao {
    @Query("SELECT * FROM MealLocal")
    fun getAll(): List<MealLocal>

    @Insert
    fun insertAll(books: List<MealLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mealLocal: MealLocal)

    @Query("SELECT * FROM MealLocal WHERE strMeal LIKE '%' || :search || '%' or strCategory LIKE '%' || :search || '%'")
    fun search(search : String) : List<MealLocal>
}