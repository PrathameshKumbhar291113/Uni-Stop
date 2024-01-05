package com.unistop.show_university_details_feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.unistop.R
import com.unistop.databinding.FragmentShowUniversityDetailBinding
import com.unistop.databinding.FragmentShowUniversityListBinding
import com.unistop.network.api_response.GetUniversityListResponse
import com.unistop.show_university_list_feature.presentation.fragment.ShowUniversityListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowUniversityDetailFragment : Fragment() {
    private lateinit var binding: FragmentShowUniversityDetailBinding
    private val showUniversityDetailsViewModel : ShowUniversityDetailsViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowUniversityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpObservers()
    }

    private fun setUpUi() {
        showUniversityDetailsViewModel.setUniversityDetails(arguments?.getParcelable<GetUniversityListResponse>(ShowUniversityListFragment.UNIVERSITY_DATA)!!)
    }

    private fun setUpObservers() {
        showUniversityDetailsViewModel.universityDetails.observe(viewLifecycleOwner){
            it?.let {
                binding.showUniversityNameValue.text =it.name
                binding.showUniversityCountryValue.text =it.country
                binding.showUniversityWebsiteValue.text =it.webPages?.get(0)
                binding.showUniversityCountryCodeValue.text =it.alphaTwoCode
            }
        }
    }

}