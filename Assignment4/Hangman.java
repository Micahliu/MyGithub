/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	 // Constants 
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon lexicon = new HangmanLexicon();
	private String secret_word = "";
	private String current_word = "";
	private String guessed_letters = "";
	private String wrong_guesses = "";
	private int guesses_left;
	private int GUESS_CONSTANT = 8;
	private HangmanCanvas canvas;
	// init canvas
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.reset();
	}
	// init console program
    public void run() {
    	startGame();
	}
    // start game from console program
    private void startGame() {
    	println("Welcome to Hangman!");
    	getSecretWord();
		createBlankWord(secret_word);
		canvas.Labels(current_word);
		guesses_left = GUESS_CONSTANT;
    	while(play()){
    		playNext();
    	}	
    }
    // losing or winning condition setting
	private boolean play(){
		//losing 
		if (guesses_left == 0){
			println("The word was: " + secret_word);
			println("You lose.");
			return false;
		}
		//winning 
		else if (current_word.equals(secret_word)){
			println("You guessed the word: " + secret_word);
			println("You win.");
			return false;
		}
		// continues
		else {
			return true;
		}
	}
	// Process handling
	private void playNext(){
		println("The word now looks like this: " + current_word);
		println("You have " + guesses_left + " guesses left.");
		Character guess = readInput();
		compareWord(guess);
	}
	// read input var
	private Character readInput(){
		
		String str = readLine("Please guess a letter: ");
		
		// more than one ?
		if (str.length() != 1){
			println("Your must guess exactly one letter. Try again. ");
			return readInput();
		}
		
		//converts string to character
		Character ch = str.charAt(0);
		
		// a character ?
		if (Character.isLetter(ch) != true){
			println("You can only guess letters. Try again.");		
			return readInput();
		}		
		ch = Character.toUpperCase(ch);
		
		// guessed previously ?
		if (guessed_letters.indexOf(ch) >= 0){
			println("You've already guessed the letters \"" + guessed_letters + "\". Try again. ");
			return readInput();
		}
		
		//adds the guess to guessed letters.
		guessed_letters += ch;
		
		//returns guess
		return ch;
	}
	private void compareWord(Character guess){
		int n = secret_word.length();
		boolean hit = false;
		
		// loops.
		for (int i=0; i < n; i++ ){
			
			if (guess.equals(secret_word.charAt(i))){
				//replaces the guessed letter
				current_word =
					current_word.substring(0, i) 
					+ secret_word.charAt(i) 
					+ current_word.substring(i+1, n); //adding letter and the current word after the guess 
				hit = true;
			}
		}
		if (hit){
			println("That guess is correct.");
		}
		
		//no hits
		else {
			println("There are no " + guess + "'s in the word.");
			guesses_left -= 1;
			wrong_guesses += guess;
			canvas.noteIncorrectGuess(guess);
		}
		canvas.word(current_word);
	}
	// get random word from Lexicon array
    private void getSecretWord(){
		int i = rgen.nextInt(0,lexicon.getWordCount()-1);
		secret_word  = lexicon.getWord(i);
	}
    // replace each character of word with "-"
	private void createBlankWord(String secret_word){
		current_word = "";
		for (int i=0; i<secret_word.length(); i++){
			current_word += "-";
		}
	}
}
