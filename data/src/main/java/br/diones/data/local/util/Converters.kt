package br.diones.data.local.util

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import java.util.*


class Converters {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}