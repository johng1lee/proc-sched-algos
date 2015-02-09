import java.util.*;

public class FCFS implements ProcessScheduler{
    private LinkedList<Process> processes;
    private int counter;
    public FCFS(LinkedList<Process> processes){
	this.processes = processes;
	counter = 0;
    }
    public Process getNextProcess(){
	if(processes.peek().getArrivalTime() > counter){
	    counter++;
	    return null;
	}
	counter++;
	return processes.peek();
    }
}
