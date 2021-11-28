package com.example.s190265lykkehjulet.data

import com.example.s190265lykkehjulet.model.Round

object DataSource {

    val rounds: List<Round> = listOf(
        Round(
            "Person/actor",
        "Matt-Leblanc"
        ), Round(
            "Person/actor",
            "Matthew-Perry"
        ), Round(
            "Programming Language",
            "Python"
        ), Round(
            "Movie",
            "No-Time-To-Die"
        )
    )

    val rules : List<String> = listOf(
        "You start the game with 5 lives.",
        "When the game starts, a word is randomly chosen from predefined categories and displayed \n" +
                "along with the category.",
        "A word or phrase will be displayed with the letters hidden. If it's a phrase the words will be separated by a '-'",
        "Tap 'get word' to get the new word, to be guessed.",
        "You can get up to 1500 points per. letter you get right.",
        "If you hit extra turn you will get an extra life, and if you hit miss turn, you will loose a life and the round.",
        "If you hit the 'bankrupt' you will loose all your points.",
        "Game is won when all the letters have been guessed.",
        "Game is lost when you have no life left and the word is not found."
    )
}