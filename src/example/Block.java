package example;


public class Block {
public boolean [][] form = new boolean [4][4];
public short positionX = 3;
public int positionY = 0;
public int typeOfBlock = 0;
public int rotationNumber = 0;

Block(int tempType){
	rotationNumber = 0;
	typeOfBlock = tempType;
	
	for(int x = 0; x<4; x++){
		for(int y = 0; y<4; y++){
		form [x][y] = false;
	}
	}
	
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

void rotate(){
	/*
	boolean [] [] tempForm = new boolean [4][4];
	for(int x = 0; x < 4; x++){
		for(int y = 0; y < 4; y++){
	tempForm[x][y] = form [x][y];
		}
		}
	for(int x = 0; x < 4; x++){

		for(int y = 0; y < 4; y++){
			form [x] [y] = tempForm[y] [x];
		}	
	*/
	rotationNumber++;
	if(3 < rotationNumber){
		rotationNumber = 0;
	}
	
	for(int x = 0; x<4; x++){
		for(int y = 0; y<4; y++){
		form [x][y] = false;
	}
	}
	
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
	
}
	
}


	

