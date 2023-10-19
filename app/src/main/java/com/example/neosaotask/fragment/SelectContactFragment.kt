package com.example.neosaotask.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.anton46.stepsview.StepsView
import com.example.neosaotask.MainActivity
import com.example.neosaotask.R
import com.example.neosaotask.databinding.FragmentSelectContactBinding
import com.example.neosaotask.myinterface.OnBackPressedListener
import com.example.neosaotask.util.SessionManager
import com.example.neosaotask.util.SessionObject
import kotlin.concurrent.fixedRateTimer

class SelectContactFragment : Fragment() {

    lateinit var binding: FragmentSelectContactBinding
    var statecomplete = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectContactBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        if (MainActivity.currentstate >= 0 && MainActivity.currentstate < MainActivity.discriptiondata.size - 1) {
            MainActivity.currentstate = 1
            var stepview1 = requireActivity().findViewById<StepsView>(R.id.stepsView)
            MainActivity.stepviewupdate(stepview1, 1)

        }
        binding.btnback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        binding.chkuser1.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    binding.edtcontact.isEnabled = true


                } else {
                    binding.edtcontact.isEnabled = false


                }
            }

        })
        binding.chkuser2.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked) {
                    binding.edtemail.isEnabled = true


                } else {
                    binding.edtemail.isEnabled = false


                }
            }

        })




        binding.btnnext.setOnClickListener {
            //checkbox1 check then data


            if (binding.chkuser1.isChecked) {
                if (binding.edtcontact.text.toString().isNotEmpty()) {
                    context?.let { it1 ->
                        SessionManager.saveString(
                            it1,
                            SessionObject.CONTACT,
                            binding.edtcontact.text.toString()
                        )
                    } ?: "NULL"


                    statecomplete = true
                } else {
                    Toast.makeText(
                        context,
                        "please enter the contct number",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    statecomplete = false

                }
            }
            //checkbox2 check then data
            if (binding.chkuser2.isChecked) {
                if (binding.edtemail.text.toString().trim().isNotEmpty()) {
                    context?.let { it1 ->
                        SessionManager.saveString(
                            it1,
                            SessionObject.EMAIL,
                            binding.edtemail.text.toString()
                        )
                    } ?: "NULL"


                    statecomplete = true
                } else {
                    Toast.makeText(
                        context,
                        "please enter the email id",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    statecomplete = false

                }
            }
//if we check both then pass both value of checkbox1 and checkbox2

            if (binding.chkuser1.isChecked && binding.chkuser2.isChecked) {
                if (!(binding.edtcontact.text.toString()
                        .isNotEmpty() && binding.edtemail.text.toString().isNotEmpty())
                ) {
                    Toast.makeText(
                        context,
                        "both checkbox is checked then you must be fill email and contact",
                        Toast.LENGTH_LONG
                    )
                    statecomplete = false
                }

            }
            //if we one of the edittext and select profile picyure then move next
            if (statecomplete == true) {

                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.llpage, SelectUniversityFragment())
                        .addToBackStack(null)
                        .commit()
                }

            } else {
                Toast.makeText(
                    context,
                    "please fill the required data email or contact",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return binding.root

    }




}