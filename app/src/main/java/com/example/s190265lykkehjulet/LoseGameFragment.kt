package com.example.s190265lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlin.system.exitProcess


/**
 * A simple [Fragment] subclass.
 * Use the [LoseGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoseGameFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lose_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_play_again).setOnClickListener{
            val action = LoseGameFragmentDirections.actionLoseGameFragmentToGameFragment()
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.btn_exit).setOnClickListener{
            exitProcess(0)
        }
    }
}