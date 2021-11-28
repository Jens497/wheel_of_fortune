package com.example.s190265lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    private var _binding: FragmentWinGameBinding? = null
    private val binding get() = _binding!!

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

        view.findViewById<Button>(R.id.btn_play_again).setOnClickListener{
            val action = WinGameFragmentDirections.actionWinGameFragmentToGameFragment2()
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.btn_exit).setOnClickListener{
            exitProcess(0)
        }
    }
}