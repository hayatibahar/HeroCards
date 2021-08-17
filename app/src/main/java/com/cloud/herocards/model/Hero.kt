package com.cloud.herocards.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Hero(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name : String?,
    @ColumnInfo(name = "quirk")
    @SerializedName("quirk")
    val quirk : String?,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description : String?,
    @ColumnInfo(name = "affiliation")
    @SerializedName("affiliation")
    val affiliation : String?,
    @ColumnInfo(name = "birthday")
    @SerializedName("birthday")
    val birthday : String?,
    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height : String?,
    @ColumnInfo(name = "bloodtype")
    @SerializedName("bloodtype")
    val bloodtype : String?,
    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    val likes : String?,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image : String?, ) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}