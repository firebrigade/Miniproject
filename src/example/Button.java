package example;



import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;

public class Button {
	
	public Input input = new Input(480); //Gets input from the user
	int sizeX = 200;   //size of the button
	int sizeY = 50;
	int posX = 240;    // position of button 
	int posY;
	Color buttonColor = new Color(0,200,100);  //three different colors to the buttons - buttonColor,2,3
	Color buttonColor2 = new Color(0,255,50);   // these are also the different states of the game, normal, selected, inactive
	Color buttonColor3 = new Color(150,150,150);
	String label;
	boolean selectable = true;
	boolean isSelected = false;
	
	Button(int tempPosY, String tempLabel){
		posY = tempPosY;
		label = tempLabel;
		
	}
	
	void draw(Graphics g){
		
			if(selectable == true && isSelected == false){   //the default color
				g.setColor(buttonColor);
			}
			else if(selectable == true && isSelected == true){
				g.setColor(buttonColor2);
			}
			else if(selectable == false){
				g.setColor(buttonColor3);
			}
				
				g.fillRect(posX, posY, sizeX, sizeY); // 
				g.setColor(new Color(255,255,255));
				g.drawString(label, posX+10, posY+10);
			
	}
	
	boolean isClicked(){
		if(isSelected == true && selectable == true && input.isKeyDown(Input.KEY_ENTER)){
			return true;
		}
		else{
		return false;
		}
		
	}
	

}

