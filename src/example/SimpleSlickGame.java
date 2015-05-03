package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import java.util.Random;

public class SimpleSlickGame extends BasicGame
{
	public boolean tetrisOver = false;
	public boolean [][] blockMatrix = new boolean [10][24];
	public int points = 0;
	public int typeOfNextBlock = 0;
	public boolean tetrisPause = false;
	public Block current = new Block(2);
	public Block nextBlock = new Block(2);
	public Timer timer = new Timer(2);
	public Timer keyTime = new Timer(0.5);
	public int gamePositionX = 300;
	public int gamePositionY = 50;
	public int guiPanelX = 500;
	public int guiPanelY = 15;
	public Random getRandomNumber = new Random();
	
	public Input input = new Input(480);
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		timer.start();
		keyTime.start();
		typeOfNextBlock = getRandomNumber.nextInt(7);
		nextBlock = new Block(typeOfNextBlock);
		current = new Block(0);
		current.forceLeft(blockMatrix);
		
		
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		//Getting the key input
		
		if(input.isKeyDown(Input.KEY_LEFT) && keyTime.triggering()  == true && 0 < current.positionX && checkLeft(false) == true){
			lift(current, blockMatrix);
			current.positionX--;
			push(current, blockMatrix);
			keyTime.start();
		}
		else if(input.isKeyDown(Input.KEY_LEFT) && keyTime.triggering()  == true && checkLeft(true) == true){
			current.forceLeft(blockMatrix);
			keyTime.start();
		}
			
		if(input.isKeyDown(Input.KEY_RIGHT) && keyTime.triggering()  == true && current.positionX < 6 && checkRight(false) == true){
			lift(current, blockMatrix);
			current.positionX++;
			push(current, blockMatrix);
			keyTime.start();
		}
		else if(input.isKeyDown(Input.KEY_RIGHT)&& keyTime.triggering()  == true && checkRight(true) == true){
			current.forceRight(blockMatrix);
			keyTime.start();
		}
		
		if(input.isKeyDown(Input.KEY_SPACE) && keyTime.triggering()  == true){
			current.rotate(blockMatrix);
			keyTime.start();
		}
		
		if(input.isKeyDown(Input.KEY_DOWN) && keyTime.triggering() == true){
			timer.changeTimerTime(0.0001);
			keyTime.start();
		}
		
		
		checkFinishedLine();
		
		//Movement
		if(timer.triggering() == true){
			if(18 < current.positionY || checkLine() == false){  //Spawning a new block
			current = new Block(typeOfNextBlock);
			typeOfNextBlock = getRandomNumber.nextInt(7);
			nextBlock = new Block(typeOfNextBlock);
			timer.changeTimerTime(2);
			}
			else if(checkLine() == true){ //Moving the current block one unit down
				lift(current, blockMatrix);
				current.positionY += 1;
				push(current, blockMatrix);
			}
			timer.start();
		}
		
		//Updating the matrix
		
		}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		g.drawString("positionX: "+current.positionX, guiPanelX, guiPanelY+100);
		
		if(tetrisPause == false){
			
		//BACKGROUND
		g.setColor(new Color(255,140,0));
		g.fillRect(gamePositionX,gamePositionY+22, 110, 230);
		g.setColor(new Color(255,255,255));
		
		
			for(int x=0; x < 10; x++){
				for(int y=0; y< 24; y++){
					if(blockMatrix[x][y] == true){
			g.fillRect(gamePositionX+ x*11, gamePositionY+y*11, 10, 10);
					}
				}
			}
			
			g.setColor(new Color(255,140,0));
			//GUI PANEL
			g.drawString("Points: "+points, guiPanelX, guiPanelY);
			g.drawString("Next shape:", guiPanelX, guiPanelY+40);
			for(int x=0; x < 4; x++){
				for(int y=0; y< 4; y++){
					if(nextBlock.form[x][y] == true){
			g.fillRect(guiPanelX+30+ x*11, guiPanelY+50+y*11, 10, 10);
					}
				}
			}
			g.setColor(new Color(255,255,255));
		}
			
			
			

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Tetris"));
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
			if(blockMatrix[current.positionX+x][current.positionY+4] == true && current.form[x][3] == true){
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
	
	public boolean checkLeft(boolean force){ //Returns true if the shape has enough space to fit into the next line
		boolean tempResult = true;
		byte forceOrNot =1; 
		byte forceOrNot2 = 0;
		if(force == true){
			forceOrNot = 0;
			forceOrNot2 = 1;
		}
		for(int y = 0; y < 4; y++){
			if(blockMatrix[current.positionX-forceOrNot][current.positionY+y] == true && current.form[forceOrNot2][y] == true){
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
	
	public boolean checkRight(boolean force){ //Returns true if the shape has enough space to fit into the next line
		boolean tempResult = true;
		byte forceOrNot =1; 
		byte forceOrNot2 = 3;
		if(force == true){
			forceOrNot = 0;
			forceOrNot2 =2;
		}
		for(int y = 0; y < 4; y++){
			if(blockMatrix[current.positionX+3+forceOrNot][current.positionY+y] == true && current.form[forceOrNot2][y] == true){
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
	
	public void checkFinishedLine(){
	
		for(int y=0; y<24; y++){
		boolean tempResult = true;
	for(int x=0; x<10; x++){
		if(blockMatrix[x][y] == false){
			tempResult = false;
		}
	}
	
	if(tempResult == true){
		boolean [][] temp = blockMatrix;
		for(int x = 0; x<10; x++){
			for(int tempy = 0; tempy < y; tempy++){
				blockMatrix[x][tempy+1]=temp[x][tempy];
			}
		}
		points = points + 10;
		checkFinishedLine();
	}
		}
	
	}

	//Functions for adding and removing the current shape to the block matrix
	
	public static void lift(Block tempCurrent, boolean tempBlockMatrix[][]){ //Removes the current block from the matrix
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				if(tempCurrent.form[x][y] == true){
			tempBlockMatrix[tempCurrent.positionX+x][tempCurrent.positionY+y] = false;
				}
			}
		}
	}
	
	public static void push(Block tempCurrent, boolean tempBlockMatrix[][]){ //Inserts the current block into the matrix
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				if(tempCurrent.form[x][y] == true){
			tempBlockMatrix[tempCurrent.positionX+x][tempCurrent.positionY+y] = tempCurrent.form[x][y];
				}
			}
		}
		
	}
	
	public void moveLeft(){
		
	}
	
	

	
}