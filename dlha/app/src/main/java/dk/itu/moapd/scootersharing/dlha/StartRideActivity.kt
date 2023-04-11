/*
MIT License

Copyright (c) 2023 Daniel Lunddal Nygaard Hansen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package dk.itu.moapd.scootersharing.dlha

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import dk.itu.moapd.scootersharing.dlha.databinding.ActivityStartRideBinding
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

/**
 * This is the main class that handles the main page for adding a scooter.
 */
class StartRideActivity : AppCompatActivity() {
    companion object {
        lateinit var ridesDB : RidesDB
    }

    private fun randomDate () : Long {
        val random = Random ()
        val now = System . currentTimeMillis ()
        val year = random . nextDouble () * 1000 * 60 * 60 * 24 * 365
        return ( now - year ) . toLong ()
    }

    private lateinit var mainBinding: ActivityStartRideBinding

    private val scooter: Scooter = Scooter("", "", 0L)

    override fun onCreate(savedInstanceState: Bundle?) {

        //WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        mainBinding = ActivityStartRideBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        ridesDB = RidesDB.get ( this )



        // activity_main.xml
        with (mainBinding) {
            startRideButton.setOnClickListener {


                // Checks that fields are not empty
                if (editTextName.text?.isNotEmpty() == true && editTextLocation.text?.isNotEmpty() == true) {
                    val location = editTextLocation.text.toString().trim()


                    //assigns properties to scooter object
                    scooter.location = location
                    scooter.name = editTextName.text.toString()
                    scooter.timestamp = randomDate()

                    ridesDB.addScooter(scooter)
                    //clear fields
                    editTextName.setText("")
                    editTextLocation.setText("")
                    editTextName.clearFocus()
                    editTextLocation.clearFocus()

                    /*
                    mainBinding.root.clearFocus()
                    editTextLocation.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    editTextName.onEditorAction(EditorInfo.IME_ACTION_DONE);
                     */

                    // Hide the virtual keyboard.
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(it.windowToken, 0)


                }
                val intent = Intent(this.root.context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}