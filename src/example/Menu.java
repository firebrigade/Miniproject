package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Menu {
	public Input input = new Input(480); //Gets input from the user
 String [] menuPoints = {"Resume Game", "New Game", "Quit"}; //Sets the menu points
 int activeMenu = 1; //Sets the number of the active menu
 int menuY = 100; //Sets the vertical position of the menu
 Graphics g;
 Timer keyTime = new Timer(0.5); //Protection against multiple triggering at one press
 Button [] menuButtons = new Button[menuPoints.length];
	Menu(){ //The default constructor creates a button for each point
		for(int i = 0; i < menuButtons.length; i++){
		menuButtons[i] = new Button(menuY+(i*55), menuPoints[i]);
		keyTime.start();
	}
	
}
	
	void update(boolean tempPause, boolean gameOver){ //Updates if the game is paused or is over (then the resume of the game is blocked)
		if(gameOver == true){
			menuButtons[0].selectable = false;
		}
		else{
			menuButtons[0].selectable = true;
		}
		for(int i = 0; i < menuButtons.length; i++){
			menuButtons[i].isSelected = false;
		}
		menuButtons[activeMenu].isSelected = true; 
		//The active menu can be selected by using the up/down arrows
		if(input.isKeyDown(Input.KEY_DOWN) && keyTime.triggering() == true){
			activeMenu++;
			keyTime.start();
		}
		if(input.isKeyDown(Input.KEY_UP) && keyTime.triggering() == true){
			activeMenu--;
			keyTime.start();
		}
		
		//Looping the menu selection
		if(activeMenu < 0){
			activeMenu = menuButtons.length-1;
		}
		if(activeMenu > menuButtons.length-1){
			activeMenu = 0;
		}
	}
	
	//Drawing the buttons
	void draw(){
		for(int i = 0; i < menuButtons.length; i++){
			menuButtons[i].draw(g);
		}
		
	}
	
	//Getting the graphics component from the main class, so it doesn't have to be imported again
	void getGraphics(Graphics tempg){
		g = tempg;
	}
	
}
