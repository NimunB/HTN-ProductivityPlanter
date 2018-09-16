package ProductivityPlanner.src;

//Import files
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*; 
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public abstract class MainClass implements MouseListener{ 
  
 // Game Window properties
  static JFrame gameWindow;
  static GraphicsPanel canvas;
  static final int WIDTH = 900;
  static final int HEIGHT = 567;
  static final int TOP = 0;
  static final int BOTTOM = 500;
  static final int LEFT = 0;
  static final int RIGHT = 860; 
  
  // mouse listeners
  static MyMouseListener mouseListener = new MyMouseListener();
  
  //variables 
  public static int screenNum=1;
  static BufferedImage logo;
  static BufferedImage logo2;

 
  
  //start button
  static int startButtonX= 365;
  static int startButtonY= 310;
  static int startButtonW= 150;
  static int startButtonH= 40;
  //static Image flower = new Image("flower.png");
  
//------------------------------------------------------------------------
  public static void main(String[] args)throws IOException{
      gameWindow = new JFrame("Productivity Planner");
      gameWindow.setSize(WIDTH, HEIGHT);
      gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      canvas = new GraphicsPanel();
      canvas.addMouseListener(mouseListener);
      gameWindow.add(canvas); 
      
      //Upload images
      try {                
          logo = ImageIO.read(new File("images/logo.png"));
      } catch (IOException ex){} 
      try {                
          logo2 = ImageIO.read(new File("images/logo2.png"));
      } catch (IOException ex){} 

      gameWindow.setVisible(true);
      runLoop();
  }
  
  //------------graphics panel----------------
  static class GraphicsPanel extends JPanel{
      public GraphicsPanel(){
          setFocusable(true);
          requestFocusInWindow();
      }
      protected static ImageIcon createImage (String path) { 
  	    java.net.URL imgURL = MainClass.class.getResource (path);
  	 if (imgURL != null)
  	 return new ImageIcon (imgURL);
  	 else
  	 {
  	 System.err.println ("Couldn't find file: " + path);
  	 return null;
  	 }
  	 }
      
      public void paintComponent(Graphics g){ 
          super.paintComponent(g); 
          int[] goals = new int[100];
          
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
              
          }
          else if (screenNum==2) {
        	  g.setColor (Color.white);
              g.fillRect(0,0,900,567);
              g.setColor(Color.gray);
              g.fillRect(0, 0,900, 50);
              g.drawString("Review your application runtimes:", (gameWindow.getWidth()/8), (gameWindow.getHeight()/4));
              g.drawImage(logo2,0,10,this); 
//              Color customC=new Color(155,255,157);
//              g.setColor(customC);
              g.fillRect(355, 490, 190, 40);
              g.fillRect(800, 50, 30, 30);
              g.setColor(Color.black);
              g.drawString("Up", 810, 65);
              g.setColor(Color.gray);
              g.fillRect(800, 550, 30, 30);
              g.setColor(Color.black);
              g.
              g.drawString("Down", 810, 565);
              String[] appnames = new String [100];
              int i = 0;
              while (i < 100) {
            	  appnames[i] = "";
            	//names = appnames[i].getText();
            	g.drawString(appnames[i], 50, 50 + 20*i);
            	i++;
              }
        	  g.drawString("Achievements", 410, 515);
              
          }else {
        	  g.setColor(Color.green);
        	  g.fillRect(0, 0, 900, 567);
        	  gameWindow.setLayout(new GridLayout(10, 10));
        	  
        	  for (int i = 0; i < 10; i++) {
        		for (int j = 0; j < 10; j++) {
        			//flower[i][j].setIcon(new ImageIcon("flower.png"));
        			//ImageObserver observer;
					//g.drawImage(flower, i*50, j*50, observer);
        		}  
        	  }
        	  g.setColor(Color.gray);
        	  g.fillRect(355, 490, 190, 40);
        	  g.drawString("Your achievements:", 50, 50);
        	  g.setColor(Color.black);
        	  g.drawString("Back", 430, 515);
          }
      
      }
      
           
  }
  public static void runLoop() {
	  while (true) {
          gameWindow.repaint();
          try  {Thread.sleep(20);} catch(Exception e){}
      }	                
  }
  
  //Mouse listener
  static class MyMouseListener implements MouseListener{
      public void mouseClicked(MouseEvent e){ 
    	  int xValue= e.getX();
          int yValue= e.getY();
    	  
    	  if (screenNum==1){
              //Select play button
    		  if (screenNum==1) {
	              if (xValue>=startButtonX && xValue<=startButtonX+startButtonW && yValue>=startButtonY && yValue<=startButtonY+startButtonH){
	            	  screenNum=2;
	            	  System.out.println(xValue);
	            	  
	              }
    		  }
    		  } else if (screenNum==2) {
    			  if (xValue>=355 && xValue<=595 && yValue>=490 && yValue<=530){
	            		  screenNum=3;
	            	  System.out.println(xValue + ", " + yValue);
    		  }  if (xValue>=810 && xValue<=840 && yValue>=50 && yValue<=80){
        		//programs.setPosition(programs.xValue, programs.yValue + 30);
        	 
	  } if (xValue>=810 && xValue<=840 && yValue>=550 && yValue<=580){
  		//programs.setPosition(programs.xValue, programs.yValue - 30);
} 
    		  }  else if (screenNum==3) {
    			  if (xValue>=355 && xValue<=595 && yValue>=490 && yValue<=530){
            		  screenNum=2;
            	  System.out.println(xValue + ", " + yValue);
		  }
    		  
    		  }
      
      }
      
      public void mousePressed(MouseEvent e){
      }
      
      public void mouseReleased(MouseEvent e){
      }
      
      public void mouseEntered(MouseEvent e){
      }
      
      public void mouseExited(MouseEvent e){
      }
  }
  protected static ImageIcon createImageIcon (String path) { 
	    java.net.URL imgURL = MainClass.class.getResource (path);
	 if (imgURL != null)
	 return new ImageIcon (imgURL);
	 else
	 {
	 System.err.println ("Couldn't find file: " + path);
	 return null;
	 }
	 } 
}//ProductivityPlanner class

