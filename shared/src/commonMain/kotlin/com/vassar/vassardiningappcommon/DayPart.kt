package com.vassar.vassardiningappcommon

import kotlinx.serialization.Serializable

@Serializable
data class DayPart (
    val label: String,
    val starttime: String,
    val endtime: String,
    val stations: List<Station>
){
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Mealtime: ").append(label).append("\n")
        sb.append("stations: ").append(stations).append("\n")
        return sb.toString()
    }
}
