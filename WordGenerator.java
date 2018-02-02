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
	
	public boolean hasNext() {
		return text.hasNext();
	}
	
	public String next() {
		if (text.hasNext()) {
			wordCount++;
			String result = text.next();
			return result;
		} else {
			return null;
		}		
	}
	
	public int getWordCount() {
		return wordCount;
	}
	
	public int getSentenceCount() {
		return sentenceCount;
	}
}
