package com.example.neosaotask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.anton46.stepsview.StepsView
import com.example.neosaotask.databinding.ActivityMainBinding
import com.example.neosaotask.fragment.SelectUserFragment
import com.example.neosaotask.myinterface.OnBackPressedListener

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var stepview: StepsView
        var discriptiondata = arrayOf("step1", "step2", "step3", "step4","step5","step6")
        var currentstate = 0

        fun stepviewupdate(view:StepsView,position:Int)
        {
            view.setLabels(discriptiondata).apply {
                this.barColorIndicator = resources.getColor(R.color.red)
                this.progressColorIndicator = resources.getColor(R.color.green)
                this.labelColorIndicator = resources.getColor(R.color.purple_500)
                this.completedPosition = position
                this.drawView()


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        stepview= binding.stepsView.findViewById(R.id.stepsView)
        stepviewupdate(stepview,0)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.llpage, SelectUserFragment())

            commit()
        }


    }


    override fun onBackPressed() {
        val fragment = getCurrentFragment()

        // Check if the fragment handles the back press
        if (fragment is OnBackPressedListener && fragment.onBackPressed()) {
            // The fragment handled the back press
            return
        }

        super.onBackPressed()

    }

    private fun getCurrentFragment(): Fragment? {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) {
                return fragment
            }
        }
        return null
    }
}
