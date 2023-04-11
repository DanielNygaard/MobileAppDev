package dk.itu.moapd.scootersharing.labp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.labp.databinding.FragmentUpdateRideBinding


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