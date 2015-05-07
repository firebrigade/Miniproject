package example;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import java.util.Random;


public class SimpleSlickGame extends BasicGame
{
	
	public boolean gameOver = true; //Checks if the game is over
	public boolean [][] blockMatrix = new boolean [10][24]; //The main matrix, where all the block and the leftovers are placed
	public int points = 0; //Point of the user
	public int typeOfNextBlock = 0; //Describes the type of the new block
	public boolean tetrisPause = false; //Describes if the game is paused
	public Block current = new Block(2); //The current block
	public Block nextBlock = new Block(2); //Shows the type of the next block
	public double difficulty = 2; //Sets the difficulty of the game (the lower the number, the faster the game)
	public Timer timer = new Timer(difficulty); //Timer that defines how fast should the blocks fall
	public Timer keyTime = new Timer(0.5); //Timer that protects the users from doing multiple actions, because they held the button for too long
	public Menu gameMenu = new Menu(); //The menu of the game
	public int gamePositionX = 250; //Sets the game's horizontal position within the window
	public int gamePositionY = 60;//Sets the game's vertical position within the window
	public int guiPanelX = 400; ////Sets the GUI's horizontal position within the window
	public int guiPanelY = 80; //Sets the GUI's vertical position within the window
	public Random getRandomNumber = new Random(); //Will be used to generate a random number
	public boolean goText = false;
	
	
	public Input input = new Input(480); //Gets input from the user
	
	//Default constructor
	public SimpleSlickGame(String gamename) 
	{
		super(gamename);
	}

	
	//Initialing the game
	@Override
	public void init(GameContainer gc) throws SlickException {
		timer.start(); //The blocks will start to fall after 2 seconds
		keyTime.start(); //The user will be able to access the controls after 0.5 seconds
		typeOfNextBlock = getRandomNumber.nextInt(7); //Generates a random type for the next block
		nextBlock = new Block(typeOfNextBlock); //Generates the next block
		current = new Block(0); //The first block will always be an I shape
		
		
		
		
	}

	//Handling the main sequence of the game
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		//Increasing the speed for each point that the user achieves (maximized in 0.4 sec/move)
		difficulty = 2 - (points/100);
		if(difficulty < 0.4){
			difficulty = 0.4;
		}
		
		//Checking the input from the menu buttons
		if(gameMenu.menuButtons[0].isClicked() == true){	//Resumes the game
			tetrisPause = false;
			timer.changeTimerTime(difficulty);
		}
		if(gameMenu.menuButtons[1].isClicked() == true){ 	//Starts a new game
			points = 0;
			goText = false;
			//Clearing the main matrix
			
			for(int x = 0; x<10; x++){
				for(int y = 0; y<24; y++){
					blockMatrix[x][y] = false;
				}
			}
		//Reseting the variables for the new game
		gameOver = false;
		tetrisPause = false;
		difficulty = 2;
		timer.changeTimerTime(difficulty);
		}
		if(gameMenu.menuButtons[2].isClicked() == true){ //Quit the game
			System.exit(0);
		}
		
		//Getting the key input
		//Moving the whole block to the left
		if(input.isKeyDown(Input.KEY_LEFT) && keyTime.triggering()  == true && 0 < current.positionX && checkLeft(false) == true){
			lift(current, blockMatrix); //Removing the current block from the main matrix
			current.positionX--; //Changing the position
			push(current, blockMatrix); //Inserting the current block back into the main matrix
			keyTime.start(); //Start the key protection
		}
		//If the shape matrix's position can not go any further to the left it will reorganize the shape by the force method
		else if(input.isKeyDown(Input.KEY_LEFT) && keyTime.triggering()  == true && checkLeft(true) == true){
			current.forceLeft(blockMatrix);
			keyTime.start();
		}
			
		//Moving the block to the right - Should work similarly to the code above
		if(input.isKeyDown(Input.KEY_RIGHT) && keyTime.triggering()  == true && current.positionX < 6 && checkRight(false) == true){
			lift(current, blockMatrix);
			current.positionX++;
			push(current, blockMatrix);
			keyTime.start();
		}
		//Force right
		else if(input.isKeyDown(Input.KEY_RIGHT)&& keyTime.triggering()  == true && checkRight(true) == true){
			current.forceRight(blockMatrix);
			keyTime.start();
		}
		
		//Rotating the current block
		if(input.isKeyDown(Input.KEY_SPACE) && keyTime.triggering()  == true){
			current.rotate(blockMatrix);
			keyTime.start();
		}
		
		//Dropping the item, to make the game quicker
		if(input.isKeyDown(Input.KEY_DOWN) && keyTime.triggering() == true){
			timer.changeTimerTime(0.0001); //Changes the speed of the game, so the block will fall faster
			keyTime.start();
		}
		
		if(input.isKeyDown(Input.KEY_ESCAPE) && keyTime.triggering() == true){
			tetrisPause = true;
			keyTime.start();
		}
		
		checkFinishedLine(); //Checks if there are any lines that are full (also removes them and adds points)
		checkGameOver(); //Checks if the leftover shapes have reached the top, and exits to the menu
		
		//Movement
		if(timer.triggering() == true){
			if(18 < current.positionY || checkLine() == false){  //Spawning a new block, if the current has reached the bottom, or collided into some leftovers
			current = new Block(typeOfNextBlock); //The current block equals the next block
			typeOfNextBlock = getRandomNumber.nextInt(7); //The next block gets a new random value
			nextBlock = new Block(typeOfNextBlock); //The next block is being initialized
			timer.changeTimerTime(difficulty); //The timer sets back to normal (in case it was adjusted by the drop function)
			}
			else if(checkLine() == true){ //Moving the current block one unit down, if it didn't collide
				lift(current, blockMatrix);
				current.positionY += 1;
				push(current, blockMatrix);
			}
			timer.start(); //Starts the time for the next block movement
		}
		
		//Updating the matrix
		
		}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		
		
		if(tetrisPause == false && gameOver == false){ //Only display the items if it's not paused
			
			
		//BACKGROUND
		g.setColor(new Color(255,140,0));
		g.fillRect(gamePositionX,gamePositionY+22, 110, 230);
		g.setColor(new Color(255,255,255));
		
		//Displaying the main matrix
			for(int x=0; x < 10; x++){
				for(int y=0; y< 24; y++){
					if(blockMatrix[x][y] == true){
			g.fillRect(gamePositionX+ x*11, gamePositionY+y*11, 10, 10);
					}
				}
			}
			g.setColor(new Color(155,155,155));
			
			//GUI PANEL
			g.drawString("Points: "+points, guiPanelX, guiPanelY);
			g.drawString("Next shape:", guiPanelX, guiPanelY+40);
			//Showing the next block
			for(int x=0; x < 4; x++){
				for(int y=0; y< 4; y++){
					if(nextBlock.form[x][y] == true){
			g.fillRect(guiPanelX+30+ x*11, guiPanelY+50+y*11, 10, 10);
					}
				}
			}
			g.setColor(new Color(255,255,255));
			

			
		}
		else{
			
			//Drawing menu elements
			g.setColor(new Color(255,255,255));
			g.drawString("TETRIS", 310, 50);
		gameMenu.update(tetrisPause, gameOver);	//Updating the menu functions
		gameMenu.getGraphics(g);
		gameMenu.draw();
		timer.pause(); //Pausing the game
		
		//Writing out the points and if the game is over
		g.drawString("Your points: "+points, gameMenu.menuButtons[2].posX+30, gameMenu.menuButtons[2].posY+gameMenu.menuButtons[2].sizeY+100);
		if(goText == true){
			g.drawString("GAME OVER!", gameMenu.menuButtons[2].posX+30, gameMenu.menuButtons[2].posY+gameMenu.menuButtons[2].sizeY+50);
		}
		}
			
			
			

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Tetris")); //Initializing the game's name on the window
			appgc.setDisplayMode(640, 480, false); //Initializing the window size
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	//Custom methods
	
	//Checking if the line below the current shape is free
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
		byte forceOrNot =1; //This function has two modes, one for forced movements and one for normal movements. This is selected by the boolean argument
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
	
	//Checks if there are any full lines, then removes them and adds points
	public void checkFinishedLine(){
	int row = 0;
		for(int y=0; y<24; y++){
		boolean tempResult = true;
	for(int x=0; x<10; x++){
		if(blockMatrix[x][y] == false){
			tempResult = false;
		}
	}
	
	if(tempResult == true){
		row = y;
		points = points + 10; //Adding points
		boolean [][] blockMatrix2 = new boolean[10][24];
		for(int tmpx = 0; tmpx<10;tmpx++){
			for(int tmpy = 0; tmpy<24; tmpy++){
				blockMatrix2[tmpx][tmpy] = blockMatrix [tmpx][tmpy];
			}
		}
		
		for(int tempy = 0; tempy < row; tempy++){ //Moving all the lines (that were above the full row) down.
			for(int x = 0; x<10; x++){
			blockMatrix[x][tempy+1]= blockMatrix2[x][tempy];
		}
	}
		}
		
		
	}
	
	
	}
	
	//Checks if leftovers have reached to the top. If yes, the game is over
	public boolean checkGameOver(){
		boolean tempGameOver = false;
		if(current.positionY < 1 && checkLine() == false){
			tempGameOver = true;
			gameOver = true;
			goText = true;
		}
		return tempGameOver;
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
	
	
	

	
}