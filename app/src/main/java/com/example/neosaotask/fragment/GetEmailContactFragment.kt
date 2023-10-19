package com.example.neosaotask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anton46.stepsview.StepsView
import com.example.neosaotask.MainActivity
import com.example.neosaotask.R
import com.example.neosaotask.databinding.FragmentGetEmailContactBinding
import com.example.neosaotask.util.SessionManager
import com.example.neosaotask.util.SessionObject

class GetEmailContactFragment : Fragment() {
lateinit var binding:FragmentGetEmailContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentGetEmailContactBinding.inflate(layoutInflater)

        if(MainActivity.currentstate>=0&& MainActivity.currentstate< MainActivity.discriptiondata.size-1)
        {
            MainActivity.currentstate=4
            var stepview1=requireActivity().findViewById<StepsView>(R.id.stepsView)
            MainActivity.stepviewupdate(stepview1,4)

        }
        if(SessionManager.getSharedPreferences(requireContext()).getString(SessionObject.EMAIL,null)!=null) {
            binding.emailtxt.setText("Selected Email id: "+
                SessionManager.getSharedPreferences(requireContext())
                    .getString(SessionObject.EMAIL, null)
            )
        }
        if(SessionManager.getSharedPreferences(requireContext()).getString(SessionObject.CONTACT,null)!=null) {
            binding.contacttxt.setText(" Selected Contact="+
                SessionManager.getSharedPreferences(requireContext())
                    .getString(SessionObject.CONTACT, null)
            )
        }

        binding.btnfinish.setOnClickListener {
            if(MainActivity.currentstate>=0&& MainActivity.currentstate< MainActivity.discriptiondata.size-1)
            {
              MainActivity.currentstate=5
                var stepview1=requireActivity().findViewById<StepsView>(R.id.stepsView)
                MainActivity.stepviewupdate(stepview1,5)
                SessionManager.removeAll(requireContext())


            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}