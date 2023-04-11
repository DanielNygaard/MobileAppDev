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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import dk.itu.moapd.scootersharing.dlha.databinding.FragmentMainBinding

/**
 * This is the main class that handles the main page for adding a scooter.
 */
class MainFragment : Fragment() {
    companion object {
        lateinit var ridesDB : RidesDB
        private lateinit var adapter: CustomArrayAdapter

    }

    //private lateinit var mainBinding: ActivityMainBinding
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
        }

    private val scooter: Scooter = Scooter("", "", 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ridesDB = RidesDB.get(requireContext())
        adapter = CustomArrayAdapter(requireContext(), R.layout.list_rides, ridesDB.getRidesList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(_binding) {
            this?.startRideButton?.setOnClickListener {
                val intent = Intent(view.context, StartRideActivity::class.java)
                startActivity(intent)

            }
            this?.updateRideButton?.setOnClickListener {
                val intent = Intent(view.context, UpdateRideActivity::class.java)
                startActivity(intent)
            }
            this?.listView?.adapter = adapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

/**
 * Prints the added scooter to the console. This is for testing purpose.
 */
