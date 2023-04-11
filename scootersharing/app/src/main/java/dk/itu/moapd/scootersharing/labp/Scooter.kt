package dk.itu.moapd.scootersharing.labp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Scooter(val name: String, var location: String, var timestamp: Long) : Parcelable {
    override fun toString(): String {
        return "[Scooter] $name is placed at $location. Ride was started $timestamp"
    }
}