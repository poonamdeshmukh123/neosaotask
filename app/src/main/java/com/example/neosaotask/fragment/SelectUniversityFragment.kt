package com.example.neosaotask.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton46.stepsview.StepsView
import com.example.neosaotask.MainActivity
import com.example.neosaotask.R
import com.example.neosaotask.api.adapter.UniversityAdapter
import com.example.neosaotask.api.api.viewmodel.UniverSityDataViewmodel
import com.example.neosaotask.databinding.FragmentSelectUniversityBinding


class SelectUniversityFragment : Fragment() {
lateinit var binding:FragmentSelectUniversityBinding
lateinit var viewmodel: UniverSityDataViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSelectUniversityBinding.inflate(layoutInflater)
        viewmodel=ViewModelProvider(this).get(UniverSityDataViewmodel::class.java)
        // Inflate the layout for this fragment
        if(MainActivity.currentstate>=0&& MainActivity.currentstate< MainActivity.discriptiondata.size-1)
        {
            MainActivity.currentstate=2
            var stepview1=requireActivity().findViewById<StepsView>(R.id.stepsView)
            MainActivity.stepviewupdate(stepview1,2)

        }
        viewmodel.getUniversitydataResponse()
        viewmodel.getUpdatedResponse().observe(viewLifecycleOwner,{post->
            try {


            if(post.entries!=null) {
                val data = post.entries// Replace with your actual data
                val adapter = UniversityAdapter(data)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
                //binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = adapter
            }
            }
            catch (e:Exception)
            {
e.printStackTrace()
            }
           // Log.d("data","count="+post.entries.get(0).link)
        })
        binding.btnback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        binding.btnnext.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.llpage, GetProfilePicFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return binding.root
    }


}