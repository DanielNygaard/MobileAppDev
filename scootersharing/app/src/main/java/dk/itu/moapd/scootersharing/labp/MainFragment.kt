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
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package dk.itu.moapd.scootersharing.labp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dk.itu.moapd.scootersharing.labp.databinding.FragmentMainBinding


/**
 * This is the main class that handles the main page for adding a scooter.
 */

class MainFragment : Fragment(), CustomAdapter.OnItemClickListener {

    companion object {
        private lateinit var adapter: CustomAdapter
        lateinit var ridesDB : RidesDB
    }

    private lateinit var mainBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ridesDB = RidesDB.get(requireContext())
        adapter = CustomAdapter(ridesDB.getRidesList(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding =
            FragmentMainBinding.inflate(layoutInflater, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainBinding.apply {

            startRideLink.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_startRideFragment)
            }
            updateRideLink.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_updateRideFragment)
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

        }

        if (arguments != null) {
            val scooter = UpdateRideFragmentArgs.fromBundle(requireArguments()).scooter
            ridesDB.addScooter(scooter.name, scooter.location)
            adapter.notifyItemInserted(ridesDB.getRidesList().size)
        }
    }

    override fun onItemClick(position: Int) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Do you want to delete ride?")
                .setMessage("Confirm if you want to delete.")
                .setNegativeButton("Cancel") { _, _ ->

                }
                .setPositiveButton("Delete") { _, _ ->
                    ridesDB.removeScooter(position)
                    adapter.notifyItemRemoved(position)
                }
                .show()
        }
    }


}