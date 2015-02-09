import java.util.*;

public class RR implements ProcessScheduler{
    private LinkedList<Process> processes;
    private LinkedList<Process> roundRobinProcesses;
    private int counter;
    private Process nextProcess;
    private HashMap<Process, Integer> processesInQueue;
    public RR(LinkedList<Process> processes){
	this.processes = processes;
	counter = 0; // keeps track of quantum
	nextProcess = null;
	roundRobinProcesses = new LinkedList<Process>();
	processesInQueue = new HashMap<Process, Integer>();
    }
    public Process getNextProcess(){
	System.out.println("ROUND ROBIN PROCESSES");
	System.out.println(roundRobinProcesses);
	System.out.println("\n\n");
	removeCompletedProcesses();
	for(Process p:processes){
	    if(p.getArrivalTime() <= counter && !processesInQueue.containsKey(p)){
		roundRobinProcesses.addFirst(p);
		processesInQueue.put(p,1);// If P2 arrives after P1 but before P1 quantum ends, P2 will be allowed to go first.
	    }
	}
	if(roundRobinProcesses.size() == 0){
	    counter++;
	    return null;
	}
	else{
	    nextProcess = roundRobinProcesses.remove();
	    roundRobinProcesses.add(nextProcess);
	}
	counter++;
	return nextProcess;
    }
    private void removeCompletedProcesses(){
	LinkedList<Process> temporaryList = new LinkedList<Process>();
	for(Process p:roundRobinProcesses){
	    if(p.getTimeRemaining() > 0){
		temporaryList.add(p);
	    }
	}
	roundRobinProcesses = temporaryList;
    }
    public int getCounter(){
	return counter;
    }
    public void updateCounter(int timeElapsed){
	counter = timeElapsed;
    }
}
