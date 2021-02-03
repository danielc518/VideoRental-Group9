package video.rental.demo;

import video.rental.demo.presentation.GraphicUI;

public class Main {
	private static GraphicUI ui;

	public static void main(String[] args) {
		ui = new GraphicUI();
		ui.start();
	}
}
