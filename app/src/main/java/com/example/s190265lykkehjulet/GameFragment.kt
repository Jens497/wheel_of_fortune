package com.example.s190265lykkehjulet

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.s190265lykkehjulet.databinding.FragmentGameBinding
import com.example.s190265lykkehjulet.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayout = requireView().findViewById<LinearLayout>(R.id.layout_border)

        val btn = view.findViewById<Button>(R.id.test_btn)
        btn.setOnClickListener {
            addBorderedView(requireContext(), linearLayout, "467816dgshja")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addBorderedView(context: Context, layout: LinearLayout, stringDisplay: String){
        //Log.d("Binding", "In method")
        val stringArray: MutableList<String> = stringDisplay.split("").toMutableList()
        stringArray.removeAt(0)
        stringArray.removeAt(stringArray.size-1)
        //Log.d("Testing", stringDisplay)
        layout.removeAllViews()
        for(i in stringArray.indices) {
            //Log.d("Testing", stringArray[i])
            val borderedTextView: TextView =
                LayoutInflater.from(context).inflate(R.layout.border_text_view, null) as TextView
            borderedTextView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            borderedTextView.gravity = Gravity.CENTER
            borderedTextView.text = stringArray[i]
            val params : LinearLayout.LayoutParams =  borderedTextView.layoutParams as LinearLayout.LayoutParams
            params.setMargins(2, 0, 2, 0)
            layout.addView(borderedTextView)
        }
    }
}