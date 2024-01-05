package com.unistop.show_university_list_feature.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.unistop.R
import com.unistop.databinding.FragmentShowUniversityListBinding
import com.unistop.network.api_response.GetUniversityListResponse
import com.unistop.show_university_list_feature.presentation.adapter.UniversityListAdapter
import com.unistop.show_university_list_feature.presentation.view_model.ShowUniversityListViewModel
import com.unistop.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

@AndroidEntryPoint
class ShowUniversityListFragment : Fragment() {
    private lateinit var binding : FragmentShowUniversityListBinding
    private val universityListViewModel: ShowUniversityListViewModel by activityViewModels()
    private lateinit var universityAdapter: UniversityListAdapter

    companion object{
        const val UNIVERSITY_DATA = "university_data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentShowUniversityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setUpObserver()
    }

    private fun setUpObserver() {

        universityListViewModel.getUniversityList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.circularProgressBar.isVisible = true
                    binding.universityRecyclerView.isVisible = false
                }
                is NetworkResult.Success -> {
                    binding.circularProgressBar.isVisible = false
                    binding.universityRecyclerView.isVisible = true
                    it.data?.body()?.let { universityList ->
                        setupRecyclerView(universityList)
                    }
                }

                is NetworkResult.Error -> {
                    binding.circularProgressBar.isVisible = false
                    binding.universityRecyclerView.isVisible = true
                }
            }

        }
    }

    private fun setupUi() {
        handleOnBackPress()
        setUpSearchFeature()
        setupFloatingActionButton()
    }

    private fun setupFloatingActionButton() {
        binding.searchUniversityByName.setOnClickListener {
            binding.searchUniversity.isVisible = !binding.searchUniversity.isVisible
        }
    }

    private fun setUpSearchFeature() {
        binding.searchUniversity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()){
                    universityListViewModel.getUniversityList("United States")
                }else{
                    universityListViewModel.getUniversityList(newText.toString())
                }

                return true
            }
        })
    }

    private fun setupRecyclerView(productList: List<GetUniversityListResponse>) {
        universityAdapter = UniversityListAdapter(productList, this::onClick)
        binding.universityRecyclerView.adapter = universityAdapter
    }

    private fun onClick(data: GetUniversityListResponse) {
        val universityDataBundle: Bundle = Bundle().apply {
            putParcelable(UNIVERSITY_DATA, data)
        }

        findNavController().navigate(R.id.showUniversityDetailFragment, universityDataBundle)

    }


    private fun handleOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        if (binding.searchUniversity.query.isEmpty()){
            universityListViewModel.getUniversityList("United States")
        }
    }

}