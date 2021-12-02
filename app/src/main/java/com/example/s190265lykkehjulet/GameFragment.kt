package com.example.s190265lykkehjulet

import android.content.Context
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s190265lykkehjulet.databinding.FragmentGameBinding
import com.example.s190265lykkehjulet.databinding.FragmentHomeBinding
import com.example.s190265lykkehjulet.model.Round
import com.example.s190265lykkehjulet.viewModel.GameViewModel
import com.google.android.material.textfield.TextInputEditText

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel : GameViewModel by viewModels()

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
        var boolSkipTurn = false
        val linearLayout = requireView().findViewById<LinearLayout>(R.id.layout_border)

        val btnGuess = view.findViewById<Button>(R.id.guess_btn)
        btnGuess.isClickable = false

        val btnSpinWheel = view.findViewById<Button>(R.id.spin_wheel_btn)
        val wheelText = view.findViewById<TextView>(R.id.spin_text)
        val guessLetterText = view.findViewById<TextInputEditText>(R.id.guess_letter_text)

        val startString : String = viewModel.setStartString()
        val round : Round = viewModel.getRound()

        view.findViewById<TextView>(R.id.category_text).text = round.category
        view.findViewById<TextView>(R.id.lives_text).text = getString(R.string.lives, viewModel.getTotalLives().toString())
        view.findViewById<TextView>(R.id.point_text).text = getString(R.string.points, viewModel.getTotalScore().toString())
        addBorderedView(requireContext(), linearLayout, startString)

        btnSpinWheel.setOnClickListener {

            btnSpinWheel.isClickable = false
            when(val wheelOption = viewModel.getWheelOption()) {
                1 -> {
                    viewModel.setLives(wheelOption)
                    wheelText.text = getString(R.string.extra_life) // Don't use hardcoded string
                    view.findViewById<TextView>(R.id.lives_text).text = getString(R.string.lives, viewModel.getTotalLives().toString())
                }

                -1 -> {
                    viewModel.setLives(wheelOption)
                    boolSkipTurn = true
                    btnSpinWheel.isClickable = true
                    //wheelText.text = "You lost a life, and your turn will be skipped!" // Don't use hardcoded string
                    wheelText.text = getString(R.string.lost_life)
                    view.findViewById<TextView>(R.id.lives_text).text = getString(R.string.lives, viewModel.getTotalLives().toString())

                    if(viewModel.getTotalLives() <= 0){
                        val action = GameFragmentDirections.actionGameFragmentToLoseGameFragment()
                        findNavController().navigate(action)
                    }
                }

                0 -> {
                    viewModel.setScore(-viewModel.getTotalScore())
                    wheelText.text = getString(R.string.bankrupt)
                    view.findViewById<TextView>(R.id.point_text).text = getString(R.string.points, viewModel.getTotalScore().toString())
                }
                else -> {
                    viewModel.setScore(wheelOption)
                    wheelText.text = getString(R.string.set_score, wheelOption.toString())
                }
            }
            if (!boolSkipTurn) {
                btnGuess.isClickable = true
                guessLetterText.isEnabled = true
            }

            boolSkipTurn = false

        }

        btnGuess.setOnClickListener {

            val letterGuessed : String = guessLetterText.text.toString()
            if(letterGuessed.isNotEmpty() && !viewModel.getCurrentWordPhrase().lowercase().contains(letterGuessed.lowercase())) {
                if (viewModel.getRound().wordOrPhrase.lowercase()
                        .contains(letterGuessed.lowercase())
                ) {
                    //Refreshing the borderedView to include the letters that has been guessed on.
                    addBorderedView(
                        requireContext(),
                        linearLayout,
                        viewModel.searchAndReplaceWithLetter(letterGuessed)
                    )
                    view.findViewById<TextView>(R.id.point_text).text =
                        getString(R.string.points, viewModel.getTotalScore().toString())

                    if (viewModel.getCurrentWordPhrase() == viewModel.getRound().wordOrPhrase) {
                        val action = GameFragmentDirections.actionGameFragmentToWinGameFragment()
                        findNavController().navigate(action)
                    }
                } else{
                    viewModel.setLives(-1)
                    view.findViewById<TextView>(R.id.lives_text).text = getString(R.string.lives, viewModel.getTotalLives().toString())
                    if(viewModel.getTotalLives() <= 0){
                        val action = GameFragmentDirections.actionGameFragmentToLoseGameFragment()
                        findNavController().navigate(action)
                    }
                }

                btnGuess.isClickable = false
                guessLetterText.isEnabled = false
                guessLetterText.text?.clear()
                btnSpinWheel.isClickable = true
            } else{
                if (letterGuessed.isEmpty()){
                    wheelText.text = getString(R.string.empty_letter_guess)
                } else{
                    wheelText.text = getString(R.string.duplicate_guess, letterGuessed)//"You have already guessed on '${letterGuessed}'"
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Function to create the boxes around the letters for a word/phrase
    private fun addBorderedView(context: Context, layout: LinearLayout, stringDisplay: String){
        //Change to charaArray, so removing first and last element of the list will become redundant
        val stringArray: MutableList<String> = stringDisplay.split("").toMutableList()
        stringArray.removeAt(0)
        stringArray.removeAt(stringArray.size-1)
        layout.removeAllViews()
        // Loop through the whole string, to creted a borderedView on everysingle letter in the LinearLayout
        for(i in stringArray.indices) {
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