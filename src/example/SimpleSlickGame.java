package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SimpleSlickGame extends BasicGame
{
	
	public boolean tetrisOver = false;
	public boolean [][] blockMatrix = new boolean [10][20];
	public int points = 0;
	public int typeOfNextBlock = 0;
	public boolean tetrisPause = false;
	public Block current = new Block(2);
	public Timer timer = new Timer(2);
	public int gamePositionX = 0;
	public int gamePositionY = 0;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		timer.start();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		if(timer.triggering() == true){
			if(checkLine() == true){
				
				for(int x = 0; x < 4; x++){
					for(int y = 0; y < 4; y++){
					blockMatrix[current.positionX+x][current.positionY+y] = false;
					}
				}
				
				
				current.positionX += 1;
				
				for(int x = 0; x < 4; x++){
					for(int y = 0; y < 4; y++){
					blockMatrix[current.positionX+x][current.positionY+y] = current.form[x][y];
					}
				}
			}
			timer.start();
		}
		
		if(tetrisOver == false){
			//g.drawString("Hello World!", 250, 200);
			}
			/*
			g.drawString(current.form[0][0]+" "+current.form[0][1]+" "+current.form[0][2]+" "+current.form[0][3], 200, 200);
			g.drawString(current.form[1][0]+" "+current.form[1][1]+" "+current.form[1][2]+" "+current.form[1][3], 200, 250);
			g.drawString(current.form[2][0]+" "+current.form[2][1]+" "+current.form[2][2]+" "+current.form[2][3], 200, 300);
			g.drawString(current.form[3][0]+" "+current.form[3][1]+" "+current.form[3][2]+" "+current.form[3][3], 200, 350);
			*/
		
			for(int x=0; x < 10; x++){
				for(int y=0; y< 20; y++){
					if(blockMatrix[x][y] == true){
			g.fillRect(gamePositionX+ x*11, gamePositionY+y*11, 10, 10);
					}
				}
			}
			
			

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public boolean checkLine(){ //Returns true if the shape has enough space to fit into the next line
		boolean tempResult = true;
		
		for(int x = 0; x < 4; x++){
			if(blockMatrix[current.positionX+x][current.positionY+5] == true && current.form[x][3] == true){
				tempResult = false;
			}
		}
		
		
		if(tempResult == true){
		return(true);
		}
		else{
		return(false);
		}
	}

	
}