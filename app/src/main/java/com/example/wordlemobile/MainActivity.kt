package com.example.wordlemobile

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


object FourLetterWordList {
    // List of most common 4 letter words from: https://7esl.com/4-letter-words/
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }
}



class MainActivity : AppCompatActivity() {
    var guesses = 0
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
    /*
    private fun guessCheckColored(guess: String, result: String): SpannableString{
        val sb = SpannableString(guess)
        val red = ForegroundColorSpan(Color.RED)
        val green = ForegroundColorSpan(Color.GREEN)
        val blue = ForegroundColorSpan(Color.BLUE)
        var colors = arrayOf(ForegroundColorSpan(Color.RED),ForegroundColorSpan(Color.RED),ForegroundColorSpan(Color.RED),ForegroundColorSpan(Color.RED))
        for(i in result.indices){
            colors[i] = when(result[i]){
                'O' -> green
                '+' -> blue
                else -> red
            }
            sb.setSpan(colors[i], i, i+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        return sb
    }
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val guessButton = findViewById<Button>(R.id.guessButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val guessInput = findViewById<EditText>(R.id.guessInput)
        val correctText = findViewById<TextView>(R.id.correctAnswerText)
        var guessArray = arrayOf(findViewById<TextView>(R.id.guess1),findViewById<TextView>(R.id.guess2),findViewById<TextView>(R.id.guess3))
        var guessCheckArray = arrayOf(findViewById<TextView>(R.id.guessCheck1),findViewById<TextView>(R.id.guessCheck2),findViewById<TextView>(R.id.guessCheck3))
        var answerArray = arrayOf(findViewById<TextView>(R.id.answer1),findViewById<TextView>(R.id.answer2),findViewById<TextView>(R.id.answer3))
        var answerCheckArray = arrayOf(findViewById<TextView>(R.id.answerCheck1),findViewById<TextView>(R.id.answerCheck2),findViewById<TextView>(R.id.answerCheck3))
        println(wordToGuess)
        guessButton.setOnClickListener {
            var guessString = guessInput.text.toString().uppercase()
            guessInput.text.clear()
            if(guessString.length==4){
                println(guessString)
                var result = checkGuess(guessString)
                System.out.println(result)
                if (result == "OOOO") {
                    println("word correct")
                    correctText.text = wordToGuess
                    correctText.visibility = View.VISIBLE
                    guessButton.visibility = View.INVISIBLE
                    resetButton.visibility = View.VISIBLE
                }
                for(i in guessArray.size-1 downTo  1){
                    println("giss")
                    guessArray[i].text = guessArray[i-1].text
                    guessCheckArray[i].text = guessCheckArray[i-1].text
                    answerArray[i].text = answerArray[i-1].text
                    answerCheckArray[i].text = answerCheckArray[i-1].text
                }
                guessArray[0].text = "Guess #"+(guesses + 1)
                guessCheckArray[0].text = "Guess #"+(guesses + 1)+" Check"
                answerArray[0].text = guessString
                answerCheckArray[0].text = result //guessCheckColored(guessString, result)
                println(guessArray[0].text)
                println(guessCheckArray[0].text)
                println(answerArray[0].text)
                println(answerCheckArray[0].text)
                guesses++
                if(guesses >= guessArray.size){
                    guessButton.visibility = View.INVISIBLE
                    resetButton.visibility = View.VISIBLE
                    correctText.text = wordToGuess
                    correctText.visibility = View.VISIBLE
                }
            }
        }
        resetButton.setOnClickListener {
            for(i in guessArray.indices){
                guessArray[i].text = ""
                guessCheckArray[i].text = ""
                answerArray[i].text = ""
                answerCheckArray[i].text = ""
            }
            guesses = 0
            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            println(wordToGuess+"heyyyyy")
            correctText.text = ""
            correctText.visibility = View.INVISIBLE
            guessButton.visibility = View.VISIBLE
            resetButton.visibility = View.INVISIBLE
        }
    }
}

