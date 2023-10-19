package com.example.neosaotask.fragment

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.anton46.stepsview.StepsView
import com.example.neosaotask.MainActivity
import com.example.neosaotask.R
import com.example.neosaotask.databinding.FragmentSelectUserBinding
import com.example.neosaotask.myinterface.OnBackPressedListener
import com.example.neosaotask.util.SessionManager
import com.example.neosaotask.util.SessionObject
import com.example.neosaotask.viewmodel.ImageViewmodel


class SelectUserFragment : Fragment() {


    lateinit var binding: FragmentSelectUserBinding

    var uri: Uri? = null
    var uri1: Uri? = null
    lateinit var uristring: String
    private lateinit var viewModel: ImageViewmodel
    companion object {
        const val PICK_IMAGE_REQUEST = 1
        const val PICK_IMAGE_REQUEST2 = 2
        val YOUR_PERMISSION_REQUEST_CODE = 100
        var statecomplete = false


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectUserBinding.inflate(layoutInflater)
        if (MainActivity.currentstate >= 0 && MainActivity.currentstate < MainActivity.discriptiondata.size - 1) {
            MainActivity.currentstate=0
            MainActivity.stepviewupdate(
                requireActivity().findViewById<StepsView>(R.id.stepsView),
                0
            )
        }
        viewModel=ViewModelProvider(this).get(ImageViewmodel::class.java)
        binding.chkuser1.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    binding.edtuser1.isEnabled = true
                    binding.btnselectprofile1.isEnabled = true

                } else {
                    binding.edtuser1.isEnabled = false
                    binding.btnselectprofile1.isEnabled = false

                }
            }

        })
        binding.chkuser2.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked) {
                    binding.edtuser2.isEnabled = true
                    binding.btnselectprofile2.isEnabled = true

                } else {
                    binding.edtuser2.isEnabled = false
                    binding.btnselectprofile2.isEnabled = false

                }
            }

        })

        binding.btnselectprofile1.setOnClickListener {

            val pickImageIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST)


        }
        binding.btnselectprofile2.setOnClickListener {
            val pickImageIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST2)
        }




        binding.btnnext.setOnClickListener {
            //checkbox1 check then data


            if (binding.chkuser1.isChecked) {
                if (binding.edtuser1.text.toString().isNotEmpty() && uri != null) {
                    context?.let { it1 ->
                        SessionManager.saveString(
                            it1,
                            SessionObject.USERNAME1,
                            binding.edtuser1.text.toString()
                        )
                    } ?: "NULL"
                    Toast.makeText(
                        context,
                        uri.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    context?.let { it1 ->
                        SessionManager.saveString(
                            it1,
                            SessionObject.URI1,
                            uri.toString().trim()
                        )
                    }
                        ?: "NULL"
                    statecomplete = true
                } else {
                    Toast.makeText(
                        context,
                        "username or uri something is missing checkbox1",
                        Toast.LENGTH_LONG
                    )
                        .show()

                }
            }
            //checkbox2 check then data
            if (binding.chkuser2.isChecked) {
                if (binding.edtuser2.text.toString().isNotEmpty() && uri1 != null) {
                    context?.let { it1 ->
                        SessionManager.saveString(
                            it1,
                            SessionObject.USERNAME2,
                            binding.edtuser2.text.toString()
                        )
                    } ?: "NULL"
                    Toast.makeText(
                        context,
                        uri1.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    context?.let { it1 ->
                        SessionManager.saveString(
                            it1,
                            SessionObject.URI2,
                            uri.toString().trim()
                        )
                    }
                        ?: "NULL"
                    statecomplete = true
                } else {
                    Toast.makeText(
                        context,
                        "username or uri something is missing of checkbox2",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
//if we check both then pass both value of checkbox1 and checkbox2

            if (binding.chkuser1.isChecked && binding.chkuser2.isChecked) {
                if (!(binding.edtuser1.text.toString().isNotEmpty() && uri != null)) {
                    Toast.makeText(
                        context,
                        "both user is selected but checkbox1 users username or profile pic is missing",
                        Toast.LENGTH_LONG
                    ).show()
                    statecomplete = false
                }
                if (!(binding.edtuser2.text.toString().isNotEmpty() && uri1 != null)) {
                    statecomplete = false
                    Toast.makeText(
                        context,
                        "both user is selected but checkbox2 users username or profile pic is missing",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            //if we one of the edittext and select profile picyure then move next
            if (statecomplete == true) {


                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.llpage, SelectContactFragment())
                        .addToBackStack(null)
                        .commit()
                }

            } else {
                Toast.makeText(
                    context,
                    "username or uri something is missing checkbox",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            uri = data!!.data!!
            uristring = uri.toString()
viewModel.selectedImageUri=uri.toString()
            Toast.makeText(activity, "Profile picture selected suucessfully", Toast.LENGTH_LONG)
        }
        if (requestCode == PICK_IMAGE_REQUEST2 && resultCode == Activity.RESULT_OK) {
            uri1 = data!!.data!!
            viewModel.selectedImageUri=uri1.toString()
            Toast.makeText(activity, "Profile picture selected suucessfully", Toast.LENGTH_LONG)
        }
    }





}