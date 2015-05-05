package example;

import org.newdawn.slick.Graphics;

//NOT USED YET
public class Menu {
 String [] menuPoints = {"Resume Game", "New Game", "Settings", "Quit"};
 int activeMenu = 0; 
 int menuX;
 int menuY;
 Button [] menuButtons = new Button[menuPoints.length];
	Menu(Graphics tempG, boolean tempPause){
	for(int i = 0; i < menuButtons.length; i++){
		menuButtons[i] = new Button(menuY+(i*55), menuPoints[i]);
	}
	
}
	
	void update(boolean tempPause){
		if(tempPause == true){
			menuButtons[0].selectable = false;
		}
		for(int i = 0; i < menuButtons.length; i++){
			menuButtons[i].isSelected = false;
		}
		menuButtons[activeMenu].isSelected = true; 
	}
	
	void draw(){
		for(int i = 0; i < menuPoints.length; i++){
			//Buttons should come here
		}
	}
}
