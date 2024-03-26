package com.bennysamuel.formsroomdb.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_details_table")
data class Form(
    @PrimaryKey(autoGenerate = true)
    var userID: Int = 1,
    @ColumnInfo(name = "Name")
    var name: String = "",
    @ColumnInfo(name = "Email")
    var email:String = "",
    @ColumnInfo(name = "Gender")
    var gender:String = "",
    @ColumnInfo(name = "Age")
    var age: String = ""+-1
) {


}