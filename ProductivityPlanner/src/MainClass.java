
//Import files
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*; 
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;


public abstract class MainClass implements MouseListener{ 
  
 // Game Window properties
  static JFrame window;
  static GraphicsPanel canvas;
  static final int WIDTH = 900;
  static final int HEIGHT = 567;
  static final int TOP = 0;
  static final int BOTTOM = 500;
  static final int LEFT = 0;
  static final int RIGHT = 860; 
  
  // mouse listeners
  static MyMouseListener mouseListener = new MyMouseListener();
  
	//read from comp stuff
    private Timer myTimer;
	private Runtime rt = Runtime.getRuntime();
	private String[] commands = {"tasklist","/v"};
	private Process proc;
	private BufferedReader stdInput;
	private BufferedReader stdError;
	private ArrayList<String> programs = new ArrayList<String>();
    private ArrayList<Long> times = new ArrayList<Long>();
  
  //variables 
  static int screenNum=1;
  static BufferedImage logo;
  static BufferedImage logo2;
  static BufferedImage goal;
  static BufferedImage flower;
  static BufferedImage back;
  static boolean change;
  //static boolean numProcesses=30;
  
  //start button
  static int startButtonX= 365;
  static int startButtonY= 310;
  static int startButtonW= 150;
  static int startButtonH= 40;
  
  //images
  static int goalX= 480;
  static int goalY= 485;
  static int goalW=50;
  static int goalH=50;
  
  static int flowerX= 405;
  static int flowerY= 485;
  static int flowerW=60;
  static int flowerH=50;
  
  static int backX= 350;
  static int backY= 485;
  static int backW=50;
  static int backH=50;
  
  static int boxX= 30;
  static int boxY= 70;
  static int boxW=830;
  static int boxH=400;
  
  
//------------------------------------------------------------------------
  public static void main(String[] args)throws IOException{
      window = new JFrame("Productivity Planner");
      window.setSize(WIDTH, HEIGHT);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //JPanel panel = new JPanel();
      canvas = new GraphicsPanel();
      canvas.addMouseListener(mouseListener);
      window.add(canvas); 
      
      //Upload images
      try {                
          logo = ImageIO.read(new File("images/logo.png"));
      } catch (IOException ex){} 
      try {                
          logo2 = ImageIO.read(new File("images/logo2.png"));
      } catch (IOException ex){} 
      try {                
          goal = ImageIO.read(new File("images/goal.png"));
      } catch (IOException ex){} 
      try {                
          flower = ImageIO.read(new File("images/flower.png"));
      } catch (IOException ex){} 
      
      try {                
          back = ImageIO.read(new File("images/back.png"));
      } catch (IOException ex){} 
      

      window.setVisible(true);
      runLoop();
  }
  
  //------------graphics panel----------------
  static class GraphicsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public GraphicsPanel(){
          setFocusable(true);
          requestFocusInWindow();
      }
      public void paintComponent(Graphics g){ 
          super.paintComponent(g); 
          
       //----------------------------------
          if (screenNum==1){
              g.setColor (Color.white);
              g.fillRect(0,0,900,567);
              //Logo 
              g.drawImage(logo,199,65,this); 
              //Start
              g.drawRect(startButtonX,startButtonY,startButtonW,startButtonH);
              int smallSize= 23;
              Font smallFont= new Font("Special Elite",Font.PLAIN, smallSize);
              g.setFont(smallFont);
              g.setColor(Color.gray);
              g.drawString("Start", startButtonX+50, startButtonY+30);
              while (change) {
            	  Color customC=new Color(155,255,157);
            	  g.setFont(smallFont);
                  g.setColor(customC);
                  g.drawString("Start", startButtonX+50, startButtonY+30);
              }
              
          }
          else if (screenNum==2) {
        	  g.setColor (Color.white); 
              g.fillRect(0,0,900,567);
              g.setColor(Color.gray);
              g.fillRect(0, 0,900, 50);
              
              //selection bar
              g.drawImage(logo2,0,10,this); 
              g.drawImage(goal,goalX,goalY,this);
              g.drawImage(flower,flowerX,flowerY,this);
              g.drawImage(back,backX,backY,this);
              int size= 20;
              Font font= new Font("Special Elite",Font.BOLD, size);
              g.setFont(font);
              g.drawString("Processing:", 50, 100);
              g.drawString("Time (s):", 550, 100);
              
              
              //process
              g.setColor(Color.black);
              g.drawRect(boxX,boxY,boxW,boxH);
              //--------------------------------------DO when get variables from Rahma's code!!----------             
              //int smallestSize= 15;
              //Font smallestFont= new Font("Special Elite",Font.PLAIN, smallestSize);
              // g.setFont(smallFont);
              
              //iterate through---
              //g.drawString("process..", boxX+20, boxY+increment);
              //--------
              //------------------------------------------------------------------------------------------
              //last=boxY+increment
                 
          }
          else if (screenNum==3) {
        	  g.setColor (Color.white); 
              g.fillRect(0,0,900,567);
              g.setColor(Color.gray);
              g.fillRect(0, 0,900, 50);
              g.drawImage(logo2,0,10,this);
        	  
          }
      
      }
      
  }         
//----------------------------
  public static void runLoop() {
	  while (true) {
          window.repaint();
          try  {Thread.sleep(20);} catch(Exception e){}
      }	                
  }
//  public static void scroll() {
//	  do {
//		  boxY=boxY-10;  
//	  }while(lastY>(boxY+boxH));
//  }
  //----------------------------
  //Mouse listener
  static class MyMouseListener implements MouseListener{
      public void mouseClicked(MouseEvent e){ 
    	  int xValue= e.getX();
          int yValue= e.getY();
    	  

          //Select play button
		  if (screenNum==1) {
              if (xValue>=startButtonX && xValue<=startButtonX+startButtonW && yValue>=startButtonY && yValue<=startButtonY+startButtonH){
            	  screenNum=2;  
            	  change=false;
              }
		  }
		  //Select menu options
		  if (screenNum==2) {
			  if (xValue>=goalX && xValue<=goalX+goalW && yValue>=goalY && yValue<=goalY+goalH){
            	  screenNum=3;
              }
			  if (xValue>=flowerX && xValue<=flowerX+flowerW && yValue>=flowerY && yValue<=flowerY+flowerH){
            	  boxY=70;
              }
			  if (xValue>=backX && xValue<=backX+backW && yValue>=backY && yValue<=backY+backH){
            	  screenNum=1;
              }
		  }
   
    	  
              
      }
      public void mousePressed(MouseEvent e){
    	  
      }
      
      public void mouseReleased(MouseEvent e){
      }
      
      public void mouseEntered(MouseEvent e){
    	  int xValue= e.getX();
          int yValue= e.getY();
    	  //view play button
		  if (screenNum==1) {
              if (xValue>=startButtonX && xValue<=startButtonX+startButtonW && yValue>=startButtonY && yValue<=startButtonY+startButtonH){
            	  change=true;
              }
		  }
//		  else if (screenNum==2) {
//			  //--------need code-------
////			  if (lastY>yValue) {
////				  if (xValue>=boxX && xValue<=boxX+boxW && yValue>=boxY && yValue<=boxY+boxH){
//			          //scroll();
//
////			  }
//		  }
    	  
      }
      
      public void mouseExited(MouseEvent e){
      }
  }
}//ProductivityPlanner class

