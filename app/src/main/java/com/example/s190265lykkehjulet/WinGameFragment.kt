package com.example.s190265lykkehjulet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import com.example.s190265lykkehjulet.databinding.FragmentGameBinding
import com.example.s190265lykkehjulet.databinding.FragmentWinGameBinding
import kotlin.system.exitProcess


/**
 * A simple [Fragment] subclass.
 * Use the [WinGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WinGameFragment : Fragment() {

    companion object{
        const val FINAL_SCORE = "score"
    }

    private var _binding: FragmentWinGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var finalScore : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let{
            finalScore = it.getString(FINAL_SCORE).toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWinGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.score_text).text = finalScore

        view.findViewById<Button>(R.id.btn_play_again).setOnClickListener{
            val action = WinGameFragmentDirections.actionWinGameFragmentToGameFragment2()
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.btn_exit).setOnClickListener{
            exitProcess(0)
        }
    }
}