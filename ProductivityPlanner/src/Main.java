import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener{

	private Timer myTimer;
	
	//read task manager
	Runtime rt = Runtime.getRuntime();
	String[] commands = {"tasklist"};
	Process proc;
	BufferedReader stdInput;

	public Main() throws IOException {
		super("Productivity Planner");
		setSize(1200,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//read task manager
		proc = rt.exec(commands);
		
		stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	
		//print output
		System.out.println("OUTPUT: \n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		setVisible(true);
		
		myTimer = new Timer(100,this);
		myTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public static void main(String[] args) throws IOException {
		Main frame = new Main();
	}

}