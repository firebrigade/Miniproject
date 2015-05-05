package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

//NOT USED YET
public class Menu {
	public Input input = new Input(480); //Gets input from the user
 String [] menuPoints = {"Resume Game", "New Game", "Quit"};
 int activeMenu = 0;
 int menuY = 100;
 Graphics g;
 Timer keyTime = new Timer(0.5);
 Button [] menuButtons = new Button[menuPoints.length];
	Menu(){
		for(int i = 0; i < menuButtons.length; i++){
		menuButtons[i] = new Button(menuY+(i*55), menuPoints[i]);
		keyTime.start();
	}
	
}
	
	void update(boolean tempPause, boolean gameOver){
		if(tempPause == true){
			menuButtons[0].selectable = false;
		}
		else{
			menuButtons[0].selectable = true;
		}
		for(int i = 0; i < menuButtons.length; i++){
			menuButtons[i].isSelected = false;
		}
		menuButtons[activeMenu].isSelected = true; 
		if(input.isKeyDown(Input.KEY_DOWN) && keyTime.triggering() == true){
			activeMenu++;
			keyTime.start();
		}
		if(input.isKeyDown(Input.KEY_UP) && keyTime.triggering() == true){
			activeMenu--;
			keyTime.start();
		}
		if(activeMenu < 0){
			activeMenu = menuButtons.length-1;
		}
		if(activeMenu > menuButtons.length-1){
			activeMenu = 0;
		}
	}
	
	void draw(){
		for(int i = 0; i < menuButtons.length; i++){
			menuButtons[i].draw(g);
		}
		
	}
	
	void getGraphics(Graphics tempg){
		g = tempg;
	}
	
}
