package com.example.myapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.model.User

class DatabaseHelper(context : Context) :
    SQLiteOpenHelper(context, "AndroidDB", null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val queryCreateUser = "" +
                "CREATE TABLE User(" +
                "UserID Integer PRIMARY KEY AUTOINCREMENT," +
                "UserName TEXT," +
                "UserPhone TEXT," +
                "UserPassword TEXT" +
                ")"

        db?.execSQL(queryCreateUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User")
        onCreate(db)
    }

    fun insertUser(user : User) {
        val db = writableDatabase

        print(user)
        val userValues = ContentValues().apply {
            put("UserName", user.userName)
            put("UserPhone", user.userPhone)
            put("UserPassword", user.userPassword)
        }

        db.insert("User", null, userValues)
        db.close()
    }

    fun getUser(username : String, password : String) : User? {
        val db = readableDatabase


        val query = "SELECT * FROM User WHERE UserName = ? AND UserPassword = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.rawQuery(query, selectionArgs)

        return if(cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow("UserPhone"))

            cursor.close()
            db.close()
            User(id, username, phone, password)
        } else {

            cursor.close()
            db.close()
            null;
        }
    }

    fun getUsers() : ArrayList<User> {
        val db = readableDatabase

        val query = "SELECT * FROM User"

        val cursor = db.rawQuery(query, null)

        val dataList = ArrayList<User>()
        if (cursor.moveToFirst()) {
            do {
                val dataModel = User(
                    cursor.getInt(cursor.getColumnIndexOrThrow("UserID")),
                    cursor.getString(cursor.getColumnIndexOrThrow("UserName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("UserPhone")),
                    ""
                )
                dataList.add(dataModel)
                println(dataList)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()


        return dataList
    }


}