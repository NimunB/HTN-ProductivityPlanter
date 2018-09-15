import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainEdits1 extends JFrame implements ActionListener{

 private Timer myTimer;
 JLabel runtimeStats = new JLabel("Current runtimes:");
 String[] appNames = new String[100];
 JLabel title, logo, app;
 JButton start;
 //public JPanel cards = new JPanel();
 //read task manager
 Runtime rt = Runtime.getRuntime();
 String[] commands = {"tasklist"};
 Process proc;
 BufferedReader stdInput;
 public void init() {
  title = new JLabel("Welcome to Productivity Planter!");
   add(title);
  logo = new JLabel(new ImageIcon("htnlogo.jpg"));
   add(logo);
   app = new JLabel("");
   start = new JButton("Let's get started!");
   start.setActionCommand("start");
   start.addActionListener(this);
 }
 public MainEdits1() throws IOException {
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
  for (int i = 0; i < appNames.length; i++) {
    appNames[i] = s;
  }
  setVisible(true);
  
  myTimer = new Timer(100,this);
  myTimer.start();
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {
   if (e.getActionCommand().equals("start")) {
     title.setText("Current Run-time Statistics:");
     logo.setVisible(false);
     start.setVisible(false);
     for (int i = 0; i < appNames.length; i++) {
       app.setText(appNames[i]);
       add(app);
     }
   }
 }
 
 public static void main(String[] args) throws IOException {
  MainEdits1 frame = new MainEdits1();
 }

 protected static ImageIcon createImageIcon (String path) { 
    java.net.URL imgURL = skiRace.class.getResource (path);
 if (imgURL != null)
 return new ImageIcon (imgURL);
 else
 {
 System.err.println ("Couldn't find file: " + path);
 return null;
 }
 } 
}
