import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class SpeedReader {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		while (text.hasNext()) {
			String display = text.next();
			int fontHeight = metrics.getHeight()/2-metrics.getDescent();
			int advance = metrics.stringWidth(display);
			g.drawString(display, width/2-advance/2, height/2+fontHeight);
			Thread.sleep(60000/wpm);
			panel.clear();
		}
		g.drawString(Integer.toString(text.getWordCount()), 100, 100);
		Thread.sleep(2000);
		g.drawString(Integer.toString(text.getSentenceCount()), 100, 100);
		Thread.sleep(2000);
	}

}
