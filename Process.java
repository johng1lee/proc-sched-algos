public class Process{
    private float arrivalTime;
    private float expTotalRunTime;
    private float timeRemaining;
    private int priority;
    private String name;

    public Process(float arrivalTime, float expTotalRunTime, int priority, String name){
	this.arrivalTime = arrivalTime;
	this.expTotalRunTime = expTotalRunTime;
	this.priority = priority;
	this.name = name;
	timeRemaining = expTotalRunTime;
    }

    public float getArrivalTime(){
	return arrivalTime;
    }
    public float getTotalRunTime(){
	return expTotalRunTime;
    }
    public float getTimeRemaining(){
	return timeRemaining;
    }
    public void updateTimeRemaining(float timeCompleted){
	timeRemaining-=timeCompleted;
    }
    public int getPriority(){
	return priority;
    }
    public String getName(){
	return name;
    }
    public boolean processIncomplete(){
	if(timeRemaining < expTotalRunTime && timeRemaining > 0){
	    return true;
	}
	return false;
    }
    public String toString(){
	String message = "Process: " + name + "\tArrived: " + Float.toString(arrivalTime) + "\tTotalRunTime: " + Float.toString(expTotalRunTime) + "\tTimeLeft: " + Float.toString(timeRemaining) + "\tPriority: " + Float.toString(priority);
	return message;
    }
}
