package com.ntgclarity.authenticator.database

import androidx.room.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?
)

@Dao
interface UserDao {
    @Query("select * From user")
    fun getAllUsers(): List<User>

    @Insert
    fun insertUser(user: User)
}

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}