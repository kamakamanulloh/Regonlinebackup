package com.itrsiam.rsiamuslimat.rekam_medis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.itrsiam.rsiamuslimat.R

/**
 * A simple [Fragment] subclass.
 */
class RMFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_r_m, container, false)

        return root
    }

}
