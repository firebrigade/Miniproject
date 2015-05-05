package example;

//A timer class that should be able to trigger a signal when a given amount of time has passed.

public class Timer {
	double msSeconds; //The duration of the timer in milliseconds
	double startTime; //The time when the timer was started
	
	Timer(double tempSeconds){
		msSeconds = tempSeconds * 1000; //Initializing the time value of the timer
	}
	
	void start(){
		startTime = System.currentTimeMillis(); //Storing the start time
	}

	
	boolean triggering(){
		if(startTime+msSeconds <= System.currentTimeMillis()){ //If the current time equals the duration plus the start time, then the timer has finished
			return true;
		}
		else{
			return false;
		}
	}
	
	void changeTimerTime(double tempSeconds){ //Changing the timer's duration (used when dropping the block)
		msSeconds = tempSeconds * 1000;
	}
}
