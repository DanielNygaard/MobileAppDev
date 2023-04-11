package dk.itu.moapd.scootersharing.dlha

import android.content.Context
import java.util.Random
import kotlin.collections.ArrayList

class RidesDB private constructor(context: Context) {
    private val rides = ArrayList<Scooter>()
    private var currentRide = getCurrentScooter()
    companion object : RidesDBHolder<RidesDB, Context>(::RidesDB)

    init {
        rides.add(
            Scooter(" CPH001 ", "ITU ", randomDate())
        )
        rides.add(
            Scooter(" CPH002 ", " Fields ", randomDate())
        )
        rides.add(
            Scooter(" CPH003 ", " Lufthavn ", randomDate())
        )
        rides.add(
            Scooter(" CPH004 ", " Nørreport ", randomDate())
        )
        rides.add(
            Scooter(" CPH005 ", " Nørrebro ", randomDate())
        )
        rides.add(
            Scooter(" CPH006 ", " FBC ", randomDate())
        )
//You can add more ‘Scooter ‘ objects if you want to.
    }
    fun getRidesList() : List < Scooter > {
        return rides
    }
    fun addScooter ( scooter: Scooter ) {
            if(rides.contains(scooter))
            {
                println("Scooter already added")
            } else rides.add(scooter)
        currentRide = scooter
    }
    fun updateCurrentScooter (location : String) {
        currentRide.location = location
    }
    fun getCurrentScooter () : Scooter {
        return currentRide
    }
    fun getCurrentScooterInfo () : String {
        return currentRide.name
        return currentRide.location
    }
    /**
     * Generate a random timestamp in the last 365 days .
     *
     * @return A random timestamp in the last year .
     */
    private fun randomDate () : Long {
        val random = Random ()
        val now = System . currentTimeMillis ()
        val year = random . nextDouble () * 1000 * 60 * 60 * 24 * 365
        return ( now - year ) . toLong ()
    }
}
open class RidesDBHolder <out T: Any, in A>( creator: (A) -> T ) {
    private var creator : (( A ) -> T ) ? = creator
    @Volatile private var instance : T ? = null
    fun get (arg: A) : T {
        val checkInstance = instance
        if ( checkInstance != null )
            return checkInstance
        return synchronized ( this ) {
            val checkInstanceAgain = instance
            if ( checkInstanceAgain != null )
                checkInstanceAgain
            else {
                val created = creator !!( arg )
                instance = created
                creator = null
                created
            }
        }
    }
}
