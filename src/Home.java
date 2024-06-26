import javax.swing.*;

public class Home {
	public static void main(String[] a) {
		JFrame frmMain = new JFrame("RPG Dynamic");
		
		frmMain.setSize(700, 700);
		frmMain.setVisible(true);
		
		GameView gameView = new GameView();
		
		frmMain.add(gameView);
		
		frmMain.revalidate();
	}
}
