//This class describes the blocks and their methods

package example;


public class Block {
public boolean [][] form = new boolean [4][4]; //Stores the shape of the block (represented in a 4 by 4 matrix
boolean [][] tempForm = form;
public short positionX = 3; //The vertical position of the block inside the main canvas
public int positionY = 0; //The horizontal position of the block inside the main canvas
public int typeOfBlock = 0; //Defines the shape of the block
public int rotationNumber = 0; //Sets the shape's rotation stage

Block(int tempType){
	rotationNumber = 0;
	typeOfBlock = tempType; //The type of the block will be selected by the main class
	
	//Clearing the shape
	for(int x = 0; x<4; x++){
		for(int y = 0; y<4; y++){
		form [x][y] = false;
	}
	}
	
	//Setting up the shape
	switch(tempType){
	case 0: //Block 0 - I shape
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		form [3][3] = true;
		break;
		
	case 1: //Block 1 - L shape
		form [0][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		
	case 2: //Block 2 - Reverse L shape
		form [2][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		
	case 3: //Block 3 - Square
		form [0][2] = true;
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		
	case 4: //Block 4 - S shape
		form [1][2] = true;
		form [2][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		
	case 5: //Block 5 - Z shape
		form [0][2] = true;
		form [1][2] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		
	case 6: //Block 6 - T shape
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;	
		break;
	}
}


//Rotating the form
void rotate(boolean tempMatrix[][]){

	
	SimpleSlickGame.lift(this, tempMatrix); //Removes this block from the main matrix
	rotationNumber++; //Changes the rotation number
	if(3 < rotationNumber){ 
		rotationNumber = 0; //Looping the rotation number
	}
	
	for(int x = 0; x<4; x++){ //Clearing the current shape
		for(int y = 0; y<4; y++){
		form [x][y] = false;
	}
	}
	
	//Adding the new, modified shape
	switch(typeOfBlock){
	
	case 0: //Block 0 - I shape - Done
	switch(rotationNumber){
		
		case 0:
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		form [3][3] = true;
		break;
		case 1:
		form [2][0] = true;
		form [2][1] = true;
		form [2][2] = true;
		form [2][3] = true;
		break;
		case 2:
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		form [3][3] = true;
		break;
		case 3:
		form [2][0] = true;
		form [2][1] = true;
		form [2][2] = true;
		form [2][3] = true;
		break;
		
	}
	break;
		
	case 1: //Block 1 - L shape - Done
	switch(rotationNumber){
		
		case 0:
		form [0][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		case 1:
		form [0][3] = true;
		form [1][1] = true;
		form [1][2] = true;
		form [1][3] = true;
		break;
		case 2:
		form [0][2] = true;
		form [1][2] = true;
		form [2][2] = true;
		form [2][3] = true;
		break;
		case 3:
		form [0][1] = true;
		form [0][2] = true;
		form [0][3] = true;
		form [1][1] = true;
		break;
	
	}
	break;
		
	case 2: //Block 2 - Reverse L shape - Done
	switch(rotationNumber){
		
		case 0:
		form [2][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		case 1:
		form [1][1] = true;
		form [2][1] = true;
		form [2][2] = true;
		form [2][3] = true;
		break;
		case 2:
		form [0][2] = true;
		form [1][2] = true;
		form [2][2] = true;
		form [0][3] = true;
		break;
		case 3:
		form [0][1] = true;
		form [0][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;

	}
	break;

	case 3: //Block 3 - Square - Done
	switch(rotationNumber){
		
		case 0:
		form [0][2] = true;
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		case 1:
		form [0][2] = true;
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		case 2:
		form [0][2] = true;
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		case 3:
		form [0][2] = true;
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
	
	}
	break;
		
	case 4: //Block 4 - S shape - Done
	switch(rotationNumber){
	
		case 0:
		form [1][2] = true;
		form [2][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		case 1:
		form [1][1] = true;
		form [1][2] = true;
		form [2][2] = true;
		form [2][3] = true;
		break;
		case 2:
		form [1][2] = true;
		form [2][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		break;
		case 3:
		form [1][1] = true;
		form [1][2] = true;
		form [2][2] = true;
		form [2][3] = true;
		break;
	
	}
	break;
		
	case 5: //Block 5 - Z shape - Done
	switch(rotationNumber){
		
		case 0:
		form [0][2] = true;
		form [1][2] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		case 1:
		form [2][1] = true;
		form [2][2] = true;
		form [1][2] = true;
		form [1][3] = true;
		break;
		case 2:
		form [0][2] = true;
		form [1][2] = true;
		form [1][3] = true;
		form [2][3] = true;
		break;
		case 3:
		form [2][1] = true;
		form [2][2] = true;
		form [1][2] = true;
		form [1][3] = true;
		break;
	
	}
	break;
		
	case 6: //Block 6 - T shape - Done
	switch(rotationNumber){
	
		case 0:
		form [1][2] = true;
		form [0][3] = true;
		form [1][3] = true;
		form [2][3] = true;	
		break;
		case 1:
		form [2][1] = true;
		form [2][2] = true;
		form [1][2] = true;
		form [2][3] = true;
		break;
		case 2:
		form [0][2] = true;
		form [1][2] = true;
		form [2][2] = true;
		form [1][3] = true;
		break;
		case 3:
		form [0][1] = true;
		form [0][2] = true;
		form [1][2] = true;
		form [0][3] = true;
		break;
	
	}
	break;
	}
	SimpleSlickGame.push(this, tempMatrix); //Inserting the shape back into the main matrix
}

//Force functions 
//- they are used if a block's shape (the 4 by 4 matrix) cannot go any further
//without exiting the main matrix, but there's empty space on the side of the shape
//so it can be transformed in order to fit

void forceLeft(boolean [][]tempBlockMatrix){
	SimpleSlickGame.lift(this, tempBlockMatrix); //Removes the current shape from the main matrix
	
	//Checking, if the left row of the shape is empty
	boolean rowFree = true;
	boolean [][] tempForm = form;
	for(int y = 0; y < 4; y++){
		if(form[0][y] == true){
		rowFree = false;
		}
		}
	
	//Moves the content to the left. The row on the right will be set as empty
	if(rowFree == true){
		for(int y = 0; y < 4; y++){
			form[3][y] = false;
			}
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 4; y++){
			form[x][y] = tempForm [x+1][y];
			}
		}
		
		}
	SimpleSlickGame.push(this, tempBlockMatrix); //Inserts the shape back into the main matrix
	}

void forceRight(boolean [][]tempBlockMatrix){ //NOT WORKING!
	SimpleSlickGame.lift(this, tempBlockMatrix); //Removes the current shape from the main matrix
	
	//Checking, if the left row of the shape is empty
	boolean rowFree = true;
	
	for(int y = 0; y < 4; y++){
		if(form[3][y] == true){
		rowFree = false;
		}
		}
	
	//Moves the content to the right. The row on the left will be set as empty
	if(rowFree == true){
		
			for(int y = 0; y < 4; y++){
			tempForm[0][y] = form [0][y];
			tempForm[1][y] = form [1][y];
			tempForm[2][y] = form [2][y];
			tempForm[3][y] = form [3][y];
			}
		
		for(int y = 0; y < 4; y++){
			form[0][y] = false;
			}
		
			for(int y = 0; y < 4; y++){
			form[3][y] = tempForm [2][y];
			form[2][y] = tempForm [1][y];
			form[1][y] = tempForm [0][y];
			}
		
		
		}
	SimpleSlickGame.push(this, tempBlockMatrix); //Inserts the shape back into the main matrix
	
	}
}
	



	

