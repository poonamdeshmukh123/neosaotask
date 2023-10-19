package com.example.neosaotask.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.anton46.stepsview.StepsView
import com.example.neosaotask.MainActivity
import com.example.neosaotask.R
import com.example.neosaotask.databinding.FragmentGetProfilePicBinding
import com.example.neosaotask.util.SessionManager
import com.example.neosaotask.util.SessionObject
import com.example.neosaotask.viewmodel.ImageViewmodel


class GetProfilePicFragment : Fragment() {

lateinit var binding:FragmentGetProfilePicBinding
lateinit var viewmodel: ImageViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel= ViewModelProvider(this).get(ImageViewmodel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentGetProfilePicBinding.inflate(layoutInflater)



        if(MainActivity.currentstate>=0&& MainActivity.currentstate< MainActivity.discriptiondata.size-1)
        {
            MainActivity.currentstate=3
            var stepview1=requireActivity().findViewById<StepsView>(R.id.stepsView)
            MainActivity.stepviewupdate(stepview1,3)

        }
        if(viewmodel.selectedImageUri!=null)
        {
            binding.imgprofilepic1.setImageURI(Uri.parse(viewmodel.selectedImageUri))
        }
        binding.btnback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        binding.btnnext.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.llpage, GetEmailContactFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}