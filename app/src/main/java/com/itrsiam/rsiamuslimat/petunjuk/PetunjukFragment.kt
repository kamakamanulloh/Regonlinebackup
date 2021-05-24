package com.itrsiam.rsiamuslimat.petunjuk

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.itrsiam.rsiamuslimat.R

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
        ButterKnife.bind(this, view)



        return view
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
