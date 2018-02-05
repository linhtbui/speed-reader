import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	Scanner text;
	private static int wordCount = 0;
	private static int sentenceCount = 0;
	public  WordGenerator(String filename) throws IOException {
		text = new Scanner(new File(filename));	
	}
	
	/**
	 * Check if Scanner still have text left to process
	 * @param: void
	 * @return: a boolean, true or false
	 */
	public boolean hasNext() {
		return text.hasNext();
	}
	
	
	/**
	 * Return the next string of Scanner text if there is still text left to process
	 * @param: void
	 * @return: result, a string if there is text, else return NULL
	 */
	public String next() {
		if (text.hasNext()) {
			wordCount++;
			String result = text.next();
			char endChar = result.charAt(result.length()-1);
			if (endChar =='.' || endChar=='!' || endChar == '?') {
						sentenceCount++;
					}
			return result;
		} else {
			return null;
		}		
	}
	
	/**
	 * Get the number of words counted
	 * @param: void
	 * @return: wordCount, an int
	 */
	public int getWordCount() {
		return wordCount;
	}
	
	/**
	 * Get the number of sentences counted
	 * @param: void
	 * @return: sentenceCount, an int
	 */
	public int getSentenceCount() {
		return sentenceCount;
	}
}
