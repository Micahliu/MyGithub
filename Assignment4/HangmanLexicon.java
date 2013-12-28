/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import acm.util.*;
import acmx.export.java.util.ArrayList;

public class HangmanLexicon {
	
	/** Creates an ArrayList  * */
		private ArrayList word_list = new ArrayList();
	 
	/** HangmanLexicon Constructor  */
        public HangmanLexicon() {
                    try {
                            BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
                            while(true) {
                                    String line = rd.readLine();
                                    if(line == null) break;
                                    word_list.add(line);
                            }
                            rd.close();
                    } catch (IOException ex) {
                            throw new ErrorException(ex);
                    }
            }
		
		  
	/** For some reason, Eclipse made me write a println method to use it in the constructor above */
		private void println(String string){
			println(string);
		}
		
	/** Returns the number of words in the lexicon. */
		public int getWordCount() {
			return word_list.size();
		}
	 
	/** Returns the word at the specified index. */
		public String getWord(int index) {
			return (String) word_list.get(index);
		}
	}