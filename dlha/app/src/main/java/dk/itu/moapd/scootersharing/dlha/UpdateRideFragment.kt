/*
MIT License

Copyright (c) 2023 Laurits Brok Pedersen

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
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.dlha.databinding.FragmentUpdateRideBinding

/**
 * This is the main class that handles the main page for adding a scooter.
 */
class UpdateRideFragment : Fragment() {

    companion object {
        private val TAG = MainActivity :: class . qualifiedName
    }

    private lateinit var updateRideFragment: FragmentUpdateRideBinding
    private val scooter: Scooter = Scooter("", "", System.currentTimeMillis())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateRideFragment =
            FragmentUpdateRideBinding.inflate(layoutInflater, container, false)
        return updateRideFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateRideFragment.apply {
            updateRideButton.setOnClickListener {
                // Checks that fields are not empty
                if (editTextName.text?.isNotEmpty() == true && editTextLocation.text?.isNotEmpty() == true) {
                    //val name = editTextName.text.toString().trim()
                    val location = editTextLocation.text.toString().trim()

                    //assigns properties to scooter object
                    //scooter.name = name
                    scooter.location = location

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
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(it.windowToken, 0)

                    showMessage()
                    val bundle = bundleOf("scooter" to scooter)
                    Navigation.findNavController(view).navigate(R.id.action_updateRideFragment_to_mainFragment, bundle)
                }
            }
        }
    }
    /**
     * Prints the added scooter to the console. This is for testing purpose.
     */
    private fun showMessage() {
        Log.d(TAG, scooter.toString())
        Snackbar.make(updateRideFragment.root, scooter.toString(), Snackbar.LENGTH_SHORT)
            .show()
    }

}