package example;

//A timer class that should be able to trigger a signal when a given amount of time has passed.

public class Timer {
	long msSeconds;
	long startTime;
	
	Timer(int tempSeconds){
		msSeconds = tempSeconds * 1000; //Initializing the time value of the timer
	}
	
	void start(){
		startTime = System.currentTimeMillis();
	}

	
	boolean triggering(){
		if(startTime+msSeconds <= System.currentTimeMillis()){
			return true;
		}
		else{
			return false;
		}
	}
}