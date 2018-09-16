
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
import java.awt.Graphics;


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
  static int screenNum=1;
  static BufferedImage logo;
  static BufferedImage logo2;

 
  
  //start button
  static int startButtonX= 365;
  static int startButtonY= 310;
  static int startButtonW= 150;
  static int startButtonH= 40;
  
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
              
          }
          else if (screenNum==2) {
        	  g.setColor (Color.white);
              g.fillRect(0,0,900,567);
              g.setColor(Color.gray);
              g.fillRect(0, 0,900, 50);
              
              g.drawImage(logo2,0,10,this); 
//              Color customC=new Color(155,255,157);
//              g.setColor(customC);
              g.fillRect(355, 490, 190, 40);
              
              
          }
          else if (screenNum==3) {
        	  
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
}//ProductivityPlanner class

