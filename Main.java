import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener{

	private Timer myTimer;
	
	//read task manager
	private Runtime rt = Runtime.getRuntime();
	private String[] commands = {"tasklist","/v"};
	private Process proc;
	private BufferedReader stdInput;
	private BufferedReader stdError;
	
	private ArrayList<String[]> programs = new ArrayList<String[]>();
	
	public Main() throws IOException {
		super("Productivity Planter");
		setSize(1200,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//read task manager
		proc = rt.exec(commands);
		
		stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		
		String s = null;
		int counter = 0;
		while (true) {
			ArrayList<String> tempList = new ArrayList<String>();
			s = stdInput.readLine();
			if (s != null) {
				String[] tempSplitStrings;
				if (counter >= 8) {
					tempSplitStrings = s.split(" ");
					//remove spaces
					for (int i = 0; i < tempSplitStrings.length; i++) {
						if (tempSplitStrings[i].length() > 1) {
							tempList.add(tempSplitStrings[i]);
						}
					}
					//remove uneccesary processes
					for (int i = 0; i < tempList.size(); i++) {
						if (tempList.get(i).equals("0:00:00")) {
							tempList = null;
							break;
						}
						else if(tempList.get(i).equals("Services")) {
							tempList = null;
							break;
						}
						else if (tempList.get(i).equals("Unknown")) {
							tempList = null;
							break;
						}
						else if (tempList.get(i).equals("N/A")) {
							tempList = null;
							break;
						}
					}
				
					//this is where I add the temp list to the main list -- or parts of it
					if (tempList != null) {
						String[] a = {tempList.get(tempList.size()-1),tempList.get(7)};
						programs.add(a); //list of String[] - 
					}
				}
				counter++;
			}
			else {
				break;
			}
		}
		
		Collections.sort(programs, new Comparator<ArrayList<String[]>>() {
			@Override
			public int compare(String[] a, String[] b) {
				return a.get(1).compareTo(b.get(1));
			}
		});
		
				
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