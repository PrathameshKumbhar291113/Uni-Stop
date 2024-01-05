package com.unistop.show_university_list_feature.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.unistop.databinding.ItemUniversityBinding
import com.unistop.network.api_response.GetUniversityListResponse
import java.util.Locale

class UniversityListAdapter(
    private val initialUniversityList: List<GetUniversityListResponse>,
    val onClick: (GetUniversityListResponse) -> Unit
): Adapter<UniversityListAdapter.UniversityListViewHolder>() , Filterable{

    private var filteredUniversity: List<GetUniversityListResponse> = initialUniversityList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityListViewHolder {
        return UniversityListViewHolder(
            ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UniversityListViewHolder, position: Int) = holder.bind(filteredUniversity[position])
    override fun getItemCount(): Int = filteredUniversity.size

    inner class UniversityListViewHolder(
        val binding : ItemUniversityBinding
    ): ViewHolder(binding.root){

        fun bind(getUniversityResponse: GetUniversityListResponse){
            binding.txtUniversityName.text = getUniversityResponse.name
            Log.e("Pratham", "bind: ${getUniversityResponse.name}")
            binding.cardContainer.setOnClickListener {
                onClick(getUniversityResponse)
            }

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString().toLowerCase(Locale.getDefault())
                filteredUniversity = if (charString.isEmpty()) {
                   filteredUniversity
                } else {
                   filteredUniversity.filter {
                        it.name?.toLowerCase(Locale.getDefault())
                            ?.contains(charString) == true
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredUniversity
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                filteredUniversity = filterResults?.values as List<GetUniversityListResponse>
                notifyDataSetChanged()
            }
        }
    }
}