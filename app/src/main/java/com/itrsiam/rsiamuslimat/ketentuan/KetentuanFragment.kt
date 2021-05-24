package com.itrsiam.rsiamuslimat.ketentuan

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.fragment_ketentuan.*
import kotlinx.android.synthetic.main.fragment_ketentuan.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class KetentuanFragment : Fragment() {

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ketentuan, container, false)
        view.tv_alur.onClick {
            showimage()

        }


        return view
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun showimage() {
        val builder = Dialog(requireContext())
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.getWindow()?.setBackgroundDrawable(
            ColorDrawable(Color.WHITE)
        )
        builder.setOnDismissListener(DialogInterface.OnDismissListener {
            //nothing;
        })

        val imageView = ImageView(requireContext())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            imageView.setImageDrawable(requireContext().getDrawable(R.drawable.alur_registrasi))
        }
        builder.addContentView(
            imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        builder.show()
    }

}
