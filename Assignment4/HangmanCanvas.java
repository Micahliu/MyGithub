/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	 
	    // Constants 
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
			initCanvas();
			initMan();
		}
	 
	/** Adds components to the structure (GCompound) and places it on canvas. */
		private void initCanvas(){
			
			full_scaffold.setLocation(image_origin_x, image_origin_y);
						
			GLine scaffold = new GLine(0, 0, 0, SCAFFOLD_HEIGHT);
			full_scaffold.add(scaffold);
	 			
			GLine beam = new GLine(0, 0, BEAM_LENGTH, 0);
			full_scaffold.add(beam);
	 	
			GLine rope = new GLine(BEAM_LENGTH, 0, BEAM_LENGTH, ROPE_LENGTH);
			full_scaffold.add(rope);
			
			add(full_scaffold);
		}
		public void noteIncorrectGuess(char letter) {
			addIncorrectGuess(letter);
			dramMan();
		}
		private void addIncorrectGuess(char letter){
			wrong_guesses = wrong_guesses += letter;
			wrong_guesses_label.setLabel(wrong_guesses);
		}
		private void dramMan(){
			
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
					
				} break;
			}
		}
	/** Adds "man" (GCompound), currently empty, to the canvas. */
		private void initMan(){
			man.setLocation(image_origin_x+BEAM_LENGTH, image_origin_y+ROPE_LENGTH);
			add(man);
		}
		
	/** Adds current_word_label (GLabel) and wrong_guesses label to the canvas. */
		public void Labels(String current_word){
			
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
	 	
		public void word(String word) {
			current_word_label.setLabel(word);
		}
			


		
	 

		
	}
