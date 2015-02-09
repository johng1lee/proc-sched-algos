import java.util.*;

public class SRF implements ProcessScheduler{
    private LinkedList<Process> processes;
    private int counter;
    private Process nextProcess;
    public SRF(LinkedList<Process> processes){
	this.processes = processes;
	counter = 0;
	nextProcess = null;
    }
    public Process getNextProcess(){
	ArrayList<Process> availableProcesses = new ArrayList<Process>();
	for(Process p: processes){
	    if(p.getArrivalTime() <= counter){
		availableProcesses.add(p);
	    }
	}
	Collections.sort(availableProcesses, new Comparator<Process>(){
		public int compare(Process p1, Process p2){
		    return ((Float)p1.getTimeRemaining()).compareTo(((Float)p2.getTimeRemaining()));
		}
	    });
	counter++;
	if(availableProcesses.size() == 0){
	    return null;
	}
	else{
	    return availableProcesses.get(0);
	}

    }
    public void resetCounter(){
	counter = 0;
    }
}
