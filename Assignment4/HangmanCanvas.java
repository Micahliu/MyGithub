/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	 
	/* Constants for the scaffold */
		private static final int SCAFFOLD_HEIGHT = 360;
		private static final int BEAM_LENGTH = 144;
		private static final int ROPE_LENGTH = 18;
		private static final int HEAD_RADIUS = 36;
		private static final int BODY_LENGTH = 144;
		private static final int ARM_OFFSET_FROM_HEAD = 28;
		private static final int UPPER_ARM_LENGTH = 72;
		private static final int LOWER_ARM_LENGTH = 44;
		private static final int HIP_WIDTH = 60;
		private static final int LEG_LENGTH = 108;
		private static final int FOOT_LENGTH = 28;
		private static final int IMAGE_WIDTH = BEAM_LENGTH + UPPER_ARM_LENGTH;
		private static final int IMAGE_HEIGHT = SCAFFOLD_HEIGHT;
	 
	 
	/* Instance variables */
		private GCompound full_scaffold= new GCompound();
		private GCompound man = new GCompound();
		private GCompound face = new GCompound();
		private String wrong_guesses = "";
		private GLabel wrong_guesses_label = new GLabel(wrong_guesses);
		private GLabel current_word_label = new GLabel("");
		private int WIDTH = 300;
		private int HEIGHT = 490;
		private int image_origin_x = WIDTH/2 - IMAGE_WIDTH/2;
		private int image_origin_y = HEIGHT/2 - IMAGE_HEIGHT/2 - 50;
	 
	/** Resets the display so that only the scaffold appears */
		public void reset() {
			removeAll();
			initScaffold();
			initMan();
		}
	 
	/** Adds components to "full_scaffold" structure (GCompound) and places it on canvas. */
		private void initScaffold(){
			/* Sets the location for "full_scaffold". */
			full_scaffold.setLocation(image_origin_x, image_origin_y);
			
			/*Adds the vertical scaffold to "full_scaffold". */
			GLine scaffold = new GLine(0, 0, 0, SCAFFOLD_HEIGHT);
			full_scaffold.add(scaffold);
	 
			/*Adds the horizontal beam to "full_scaffold". */
			GLine beam = new GLine(0, 0, BEAM_LENGTH, 0);
			full_scaffold.add(beam);
	 
			/*Adds the vertical rope to "full_scaffold". */
			GLine rope = new GLine(BEAM_LENGTH, 0, BEAM_LENGTH, ROPE_LENGTH);
			full_scaffold.add(rope);
			
			/*Adds "full_scaffold" to canvas. */
			add(full_scaffold);
		}
		
	/** Adds "man" (GCompound), currently empty, to the canvas. */
		private void initMan(){
			man.setLocation(image_origin_x+BEAM_LENGTH, image_origin_y+ROPE_LENGTH);
			add(man);
		}
		
	/** Adds current_word_label (GLabel) and wrong_guesses label to the canvas. */
		public void initLabels(String current_word){
			
			/* Adds current_word_label */
			current_word_label.setLocation(WIDTH/4 - current_word_label.getWidth()/2, HEIGHT-55);
			current_word_label.setLabel(current_word);
			current_word_label.setFont("Helvetica-36");
			add(current_word_label);
			
			/* Adds wrong_guesses_label */
			wrong_guesses_label.setLocation(WIDTH/4 - wrong_guesses_label.getWidth()/2, HEIGHT-30);
			wrong_guesses_label.setFont("Helvetica-14");
			wrong_guesses_label.setColor(Color.red);
			add(wrong_guesses_label);
			
		}
	 
	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
		
		public void displayWord(String word) {
			current_word_label.setLabel(word);
		}
		
	 
	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window and causes the next 
	 * body part to appear on the scaffold.
	 */
		
		public void noteIncorrectGuess(char letter) {
			addIncorrectGuess(letter);
			buildMan();
		}
		
	/** 
	 * Appends the wrong_guess that is passed in to the end of wrong_guesses
	 * 	and updates the wrong_guesses label accordingly.
	 */
		
		private void addIncorrectGuess(char letter){
			wrong_guesses = wrong_guesses += letter;
			wrong_guesses_label.setLabel(wrong_guesses);
		}
	 
	/** 
	 * Updates the image of the man by adding the number of pieces appropriate to the number
	 *  of wrong guesses.
	 */
		private void buildMan(){
	 
			switch (wrong_guesses.length()){
				case 1: {
					GOval head = new GOval(HEAD_RADIUS*2, HEAD_RADIUS*2);
					head.setLocation(-HEAD_RADIUS, 0);
					man.add(head);
	
				} break;
				
				case 2: {
					GLine body = new GLine(0, HEAD_RADIUS*2, 0, HEAD_RADIUS*2+BODY_LENGTH);
					man.add(body);
				} break;
				
				case 3: {
					GLine l_upper_arm = new GLine(0, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, -UPPER_ARM_LENGTH, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD );
					GLine l_lower_arm = new GLine(-UPPER_ARM_LENGTH, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, -UPPER_ARM_LENGTH, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
					man.add(l_upper_arm);
					man.add(l_lower_arm);
				} break;
				
				case 4: {
					GLine r_upper_arm = new GLine(0, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, UPPER_ARM_LENGTH, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD );
					GLine r_lower_arm = new GLine(UPPER_ARM_LENGTH, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, UPPER_ARM_LENGTH, HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
					man.add(r_upper_arm);
					man.add(r_lower_arm);
				} break;
				
				case 5: {
					GLine l_hip = new GLine(-HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH, 0, HEAD_RADIUS*2+BODY_LENGTH);
					man.add(l_hip);
					GLine l_leg = new GLine(-HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH, -HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
					man.add(l_leg);
				} break;
				
				case 6: {
					GLine r_hip = new GLine(0, HEAD_RADIUS*2+BODY_LENGTH, HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH);
					man.add(r_hip);
					GLine r_leg = new GLine(HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH, HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
					man.add(r_leg);
				} break;
				
				case 7: {
					GLine l_foot = new GLine(-HIP_WIDTH/2-FOOT_LENGTH, HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, -HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH );
					man.add(l_foot);
				} break;
				
				case 8: {
					GLine r_foot = new GLine(HIP_WIDTH/2, HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, HIP_WIDTH/2+FOOT_LENGTH, HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH );
					man.add(r_foot);
					face.removeAll();
					GLine l_eye_1 = new GLine(15, 30, 25, 40);
					face.add(l_eye_1);
					GLine l_eye_2 = new GLine(15, 40, 25, 30);
					face.add(l_eye_2);
					GLine r_eye_1 = new GLine(2*HEAD_RADIUS-15, 30, 2*HEAD_RADIUS-25, 40);
					face.add(r_eye_1);
					GLine r_eye_2 = new GLine(2*HEAD_RADIUS-15, 40, 2*HEAD_RADIUS-25, 30);
					face.add(r_eye_2);
				} break;
			}
		}
	 

		
	}
