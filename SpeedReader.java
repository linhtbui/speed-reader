import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class SpeedReader {
	
	/**
	 * Display words that are centered and have a red letter focused based on the length of the word
	 * @param: word, a String
	 *         metrics, FontMetrics
	 *         width, an int
	 *         height, an int
	 *         g, Graphics
	 * @return: void
	 */
	
	public static void wordCentered (String word, FontMetrics metrics, int width, int height, Graphics g) {
		int fontCenter = (metrics.getAscent() + (height - (metrics.getAscent() + metrics.getDescent())) / 2);
		g.setColor(Color.black);
		int wordPosition;
		if (word.length() <= 2) {
			wordPosition = 0;
		} else if (word.length() <= 5) {
			wordPosition = 1;		
		} else if (word.length() <= 9) {
			wordPosition = 2;
		} else if (word.length() <= 13) {
			wordPosition = 3;	
		} else {
			wordPosition = 4;
		}
		g.drawString(word, width/2 - metrics.charsWidth(word.toCharArray(), wordPosition, wordPosition + 1), fontCenter);
		g.setColor(Color.red);
		g.drawString(word.substring(wordPosition,wordPosition + 1), width/2 - metrics.charsWidth(word.toCharArray(), wordPosition, 1), fontCenter);
	}
	
	/**
	 * Main method to display a Speed Reader with command-line input to indicate
	 * width and height of the panel, fontSize, Word-per-minute
	 * @param: args, an array of Strings
	 * @return: void
	 * @throws: IOException and InterruptedException
	 */

	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length != 5) {
			System.out.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
			return;
		}
		String fileName = args[0];
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);	
		DrawingPanel panel = new DrawingPanel(width, height);
		Graphics g = panel.getGraphics();
		Font f = new Font("Courier", Font.BOLD, fontSize);
		FontMetrics metrics = g.getFontMetrics(f);
		g.setFont(f);
		WordGenerator text = new WordGenerator(fileName);
		// loop to display all the words in the text file
		while (text.hasNext()) {
			String display = text.next();
			wordCentered (display, metrics, width, height, g);
			Thread.sleep(60000/wpm);
			panel.clear();
		}
		String wordCount = Integer.toString(text.getWordCount());
		String sentenceCount = Integer.toString(text.getSentenceCount());
		// print out the word count and sentence count to the panel
		wordCentered("Words:" + wordCount, metrics, width, height, g);
		Thread.sleep(2000);
		panel.clear();
		wordCentered("Sentences:" + sentenceCount, metrics, width, height, g);
		Thread.sleep(2000);
		panel.clear();
	}
}
