package com.itrsiam.rsiamuslimat.petunjuk

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.ketentuan.KetentuanFragment

import kotlinx.android.synthetic.main.fragment_petunjuk.*
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * A simple [Fragment] subclass.
 */
@Suppress("UNREACHABLE_CODE")
class PetunjukFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_petunjuk, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_petunjuk.onClick {
            val ketentuanFragment = KetentuanFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, ketentuanFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId
        if (id==R.id.action_help){
            var bantuanFragment = BantuanFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, bantuanFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        return super.onOptionsItemSelected(item)

    }

}
