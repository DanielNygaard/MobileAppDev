package dk.itu.moapd.scootersharing.dlha

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dk.itu.moapd.scootersharing.dlha.databinding.ListRidesBinding

class CustomAdapter(private val data: List<Scooter>, private val clickListener: OnItemClickListener) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }

        class ViewHolder(private val binding: ListRidesBinding, private val clickListener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
            fun bind(scooter: Scooter){
                binding.scooterName.text = scooter.name
                binding.scooterLocation.text = scooter.location
                binding.scooterTimestamp.text = scooter.timestamp.toString()

                itemView.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
                    Toast.makeText(
                        binding.root.context,
                        "${scooter.name} deleted!",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListRidesBinding.inflate(inflater, parent, false)
            return  ViewHolder(binding, clickListener)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int){
            val scooter = data[position]
            holder.bind(scooter)
        }
}