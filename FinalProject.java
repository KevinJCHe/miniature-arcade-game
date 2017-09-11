// FinalProject01.java
// Kevin He
// 2015-08-08
// APCS, Chris Robinson
// It is a mouse-interactive game



import java.awt.*;
import java.applet.*;
import java.text.DecimalFormat;
import java.util.*;
import java.awt.event.MouseEvent;


public class FinalProject extends Applet
{
	
	int appletWidth;		
	int appletHeight;	
	Image virtualMem;
	Graphics gBuffer;
	int lives;
	int points;
	int option;
	long startTime;
	long stopTime;
	boolean running;
	ArrayList<Rectangle> rec;
	DecimalFormat output;
	
	public void init()
	{
		
    	appletWidth = getWidth();
		appletHeight = getHeight();
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
		rec=new ArrayList<Rectangle>();
		output= new DecimalFormat("0.000");
		option=1;
		startTime = 0;
		stopTime = 0;
		running = false;
		
		rec.add(new Rectangle(20,20, 40,40));
		rec.add(new Rectangle(20,20, 40,40));
		rec.add(new Rectangle(20,20, 40,40));
		rec.add(new Rectangle(20,20, 40,40));
		
	}
	
	
	public void paint(Graphics g)
	{
			
		if(option==1)
		{
			page1(g); 
		}
		else if(option==2)
		{
			page2(g);
		}
				
	}

	

	public void page1(Graphics g)
	{
		createWhiteBackGround(g);
		
		g.setColor(Color.RED);
		g.fillOval(100,470,180,50);
		rec.set(3,new Rectangle(100,470,180,50));
	
		g.setColor(Color.blue);
		g.setFont(new Font("Arial",Font.BOLD,50));
		g.drawString("Alien Obliterator",400,200);
		
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Instructions: Move cursor on aliens to kill them. Don't let them reach the other side. Good Luck!",100,400);
		g.drawString("Created By Kevin He",500,250);
		g.drawString("Click here to begin",100,500);
		
		g.setFont(new Font("Arial",Font.BOLD,10));
		g.drawString("Can you make it to a hundred points? ;)",200,550);
	}
	
	public void page2(Graphics g) 
	{
		Random rnd = new Random(12345);
		double t=50;
		start();
		lives=5;
		points=0;
		
		
		for (int k = 1; k <= 1000; k++)
	 	{
	 		int rndY = rnd.nextInt(660);
	 		Color rndC=new Color(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
	 		
	 		
	 		for (int x = 20; x < getWidth(); x += 5)
	    	{
	    		drawAlien(g,x,rndY,rndC);
	    		rec.set(0,new Rectangle(x+20,rndY, 40,40));
	    		
	    			if(rec.get(0).contains(mousePosition()))
	    			{
	    				x=1200;
	    				points++;
	    			}
	    			
	    			if(x==1195)
	    			{
	    				lives--;
	    			}
	    			
	    			if(lives==0)
	    			{
	    				k=1001;
	    				stop();
	    			}
	    			
	    			
	    			
	    		g.setColor(Color.green);
	    		g.drawString("Points:"+points, 50,50);
	    		g.drawString("Lives:"+lives, 200,50);
	    		g.drawString("Time:"+output.format(Time()/1000)+" seconds",350,50);
	    		delay(t);
			}
	 		t=t*0.9-0.01;
	 	}
		g.setColor(Color.green);
		g.setFont(new Font("Arial",Font.BOLD,100));
		g.drawString("GAME OVER", 400,225);
		
		g.setColor(Color.red);
		g.fillRect(50,600,200,50);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Replay?",75,625);
		rec.set(1,new Rectangle(50,600,200,50));
		
		g.setColor(Color.red);
		g.fillRect(50,500,200,50);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Back to Main Menu?",55,530);
		rec.set(2,new Rectangle(50,500,200,50));
	
	}
	
	public void drawAlien(Graphics g, int x, int y, Color C)
	{
		createBlackBackGround();
		gBuffer.setColor(C);
		gBuffer.fillRect(x+20,y,40,40);
        
        gBuffer.setColor(Color.white);
        gBuffer.fillOval(x+25,y+5,10,10);
        gBuffer.fillOval(x+45,y+5,10,10);
        
        g.drawImage (virtualMem,0,0, this);
	}
	
	public void createBlackBackGround()
	{
		gBuffer.setColor(Color.black);
 		gBuffer.fillRect(0,0,getWidth(),getHeight());
 		
	}
	
	public void createWhiteBackGround(Graphics g)
	{
		g.setColor(Color.white);
 		g.fillRect(0,0,getWidth(),getHeight());
 		
 		Random rnd = new Random();
 		Color[] color={Color.yellow,Color.pink,Color.orange,Color.green,Color.cyan};
	 	for (int k = 1; k <= 250; k++)
	 	{
	 		int rndX = rnd.nextInt(1160);
	 		int rndY = rnd.nextInt(660);
	 		int rndC = rnd.nextInt(5);
	 		
	 		g.setColor(color[rndC]);
	 		g.fillRect(rndX,rndY,40,40);
	 		g.setColor(Color.white);
	 		g.fillOval(rndX+25,rndY+5,10,10);
	        g.fillOval(rndX+5,rndY+5,10,10);
	        
	 	}
	}
	
	private Point mousePosition()
	{
		try
		{
			Point mp = this.getMousePosition();
            
			if(mp != null)
				return this.getMousePosition();
			else
				return new Point(0, 0);
		}
		catch (Exception e)
		{  
			return new Point(0, 0);
		}
	}
	
	public boolean mouseDown(Event e, int x, int y)
	{
		if(rec.get(3).inside(x, y))
			option=2;
		if(rec.get(2).inside(x, y))
			option=1;
		if(rec.get(1).inside(x, y))
			option=2;
		
		
		repaint();
		return true;
	}
	
	public void delay(double n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();
	}
	
	public void start() 
	{
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop() 
	{
		this.stopTime = System.currentTimeMillis();
	    this.running = false;
	}
	
	public double Time() 
	{
		double elapsed;
		
		  
		if (running) 
		{
			elapsed = (System.currentTimeMillis() - startTime);
		}
		else 
		{
			elapsed = (stopTime - startTime);
		}
		
		return elapsed;
	}
	public void upgrade(Graphics g)
	{
		paint(g);
	}

}
	
	
