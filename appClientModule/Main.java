import java.awt.EventQueue;

import yang.frames.Frame;

/**
 * Èë¿Ú
 * 
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setSize(350, 550);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}