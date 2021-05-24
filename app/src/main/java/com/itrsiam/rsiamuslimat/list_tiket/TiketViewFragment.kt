package com.itrsiam.rsiamuslimat.list_tiket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.fragment_tiket_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TiketViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiketViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        setupViewPager(viewpager) // Inisiasi TabLayout
        return inflater.inflate(R.layout.fragment_tiket_view, container, false)
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager!!) {var fragmentList = arrayListOf<Fragment>()
        var titleList = arrayListOf<String>()
        fun populateFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }
        override fun getItem(position: Int) = fragmentList[position]
        override fun getCount() = fragmentList.size
        override fun getPageTitle(position: Int) = titleList[position]
    }

//    private fun setupViewPager(viewPager: ViewPager) {
//        val adapter = ViewPagerAdapter(TiketViewFragment)
//        adapter.populateFragment(TiketFragment(), "Tiket Aktif")
//        adapter.populateFragment(TiketFragment(), "Tiket NonAktif")
//
//        viewPager.adapter = adapter
//        tabs.setupWithViewPager(viewPager)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TiketViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TiketViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}