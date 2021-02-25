package com.example.nycschools.ui.schooldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alanLobo.nycschools.databinding.SchoolDetailFragmentBinding
import com.example.nycschools.data.entities.SchoolDetailsItem
import com.example.nycschools.data.entities.SchoolListItem
import com.example.nycschools.utils.Resource
import com.example.nycschools.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private var binding: SchoolDetailFragmentBinding by autoCleared()
    private val viewModel: SchoolDetailViewModel by viewModels()
    lateinit var schoolItem: SchoolListItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SchoolDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        arguments?.getParcelable<SchoolListItem>("schoolItem")?.let { schoolItem = it }
        setupObservers()
        bindSchoolItem()
    }

    private fun setupObservers() {
        viewModel.schoolDetail.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        bindSchoolDetail(it.data!!)
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindSchoolItem() {
        binding.paragraph.text = schoolItem.overview_paragraph
        binding.name.text = schoolItem.school_name
        binding.addressLocation.text = schoolItem.location!!.substring(0, schoolItem.location!!.indexOf("("))
    }

    private fun bindSchoolDetail(schoolDetail: SchoolDetailsItem) {
        binding.name.text = schoolDetail.school_name
        binding.species.text = schoolDetail.sat_math_avg_score
        binding.status.text = schoolDetail.sat_writing_avg_score
        binding.gender.text = schoolDetail.sat_critical_reading_avg_score
    }
}