package com.example.s190265lykkehjulet.viewModel

import androidx.lifecycle.ViewModel
import com.example.s190265lykkehjulet.data.DataSource
import com.example.s190265lykkehjulet.model.Round

class GameViewModel : ViewModel() {

    private val wordCatList : List<Round> = DataSource.rounds
    private lateinit var round : Round
    private lateinit var currentWordPhrase : String
    private val wheelOptions : List<Int> = listOf(100, 300, 500, 700, 900, 1100, 1300, 1500, 1, -1)

    //Player "stats"
    private var _score = 0
    val score: Int get() = _score
    private var _lives = 0
    val lives : Int get() = _lives

    /**
     * Player related stats
     */
    fun setScore(scoreInc : Int) {
        _score += scoreInc
    }

    fun getTotalScore(): Int {
        return score
    }

    fun setLives(livesInc : Int) {
        _lives += livesInc
    }

    fun getTotalLives() : Int {
        return lives
    }

    /**
     * String related stuff
     */
    private fun setWordPhraseCategory(){
        round = wordCatList.random()
    }

    fun getCurrentWordPhrase() : String {
        return currentWordPhrase
    }

    fun getWheelOption() : Int {
        return wheelOptions.random()
    }

    fun getRound() : Round {
        return round
    }

    fun setStartString() : String{
        setWordPhraseCategory()

        val tempCharArray = round.wordOrPhrase.toCharArray()

        for (i in tempCharArray.indices) {
            if(tempCharArray[i].toString() != "-"){
                tempCharArray[i] = ' '
            }
        }
        currentWordPhrase = String(tempCharArray)
        return String(tempCharArray)
    }

    fun searchAndReplaceWithLetter(letter: String) : String{
        val indexes = mutableListOf<Int>()
        var index = 0

        while(index != -1) {
            index = round.wordOrPhrase.indexOf(letter.single(), index)
            if (index != -1) {
                indexes.add(index)
                index++
            }
        }

        val tempCharArray = round.wordOrPhrase.toCharArray()
        for (i in indexes)
            tempCharArray[i] = letter.single()

        currentWordPhrase = String(tempCharArray)
        return String(tempCharArray)
    }

    fun checkLetterInString(letter: String, wordPhrase: String): Boolean{
        return wordPhrase.contains(letter)
    }
}