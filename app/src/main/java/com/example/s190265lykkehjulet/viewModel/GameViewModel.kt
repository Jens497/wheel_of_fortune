package com.example.s190265lykkehjulet.viewModel

import androidx.lifecycle.ViewModel
import com.example.s190265lykkehjulet.data.DataSource
import com.example.s190265lykkehjulet.model.Round

class GameViewModel : ViewModel() {

    private val wordCatList : List<Round> = DataSource.rounds
    private lateinit var round : Round

    private fun setWordPhraseCategory(){
        round = wordCatList.random()
    }

    fun setStartString() : String{
        setWordPhraseCategory()

        val tempCharArray = round.wordOrPhrase.toCharArray()

        for (i in tempCharArray.indices) {
            if(tempCharArray[i].toString() != "-"){
                tempCharArray[i] = ' '
            }
        }
        return String(tempCharArray)
    }

    fun searchAndReplaceWithLetter(letter: String, wordPhrase : String) : String{
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

        return String(tempCharArray)
    }

    fun checkLetterInString(letter: String, wordPhrase: String): Boolean{
        return wordPhrase.contains(letter)
    }
}