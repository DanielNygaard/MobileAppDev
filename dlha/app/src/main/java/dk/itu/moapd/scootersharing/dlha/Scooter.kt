package dk.itu.moapd.scootersharing.dlha

data class Scooter(var name: String, var location: String, var timestamp: Long) {
    override fun toString(): String {
        return "[Scooter] $name is placed at $location"
    }
}