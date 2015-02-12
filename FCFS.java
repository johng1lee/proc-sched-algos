import java.util.*;

public class FCFS{
    private LinkedList<Process> processes;
    private int quantumCounter;
    private ArrayList<String> runTime;
    public FCFS(LinkedList<Process> processes){
	this.processes = processes;
	quantumCounter = 0;
	runTime = new ArrayList<String>();
    }
    public ArrayList<String> getRunTime(){
	return runTime;
    }
    public void runProcesses(){
	while (quantumCounter<100 && processes.size()>0){
	    executeProcesses();
	    quantumCounter++;
	}
	if(quantumCounter < 100){
	    while(quantumCounter < 100){
		runTime.add("NA");
		quantumCounter++;
	    }
	}
	if(!allProcessesCompleted()){ // ############## This part has not been checked or tested. This section extends the current runTime array to record all processes to completion after 99 quanta
	    processes = getIncompleteProcesses();
	    while (processes.size()>0){
		executeProcesses();
		quantumCounter++;
	    }
	}
    }
    private void executeProcesses(){
	Process nextProcessToRun= getNextProcess();
	if(nextProcessToRun == null){
	    System.out.println("No Process Available");
	    runTime.add("NA");
	    return;
	}
	System.out.println(nextProcessToRun); 
	runTime.add(nextProcessToRun.getName());
	nextProcessToRun.updateTimeRemaining((float)1.0);
	if(nextProcessToRun.getTimeRemaining() <= 0){
	    processes.remove(nextProcessToRun);
	    System.out.println("Removed: "+nextProcessToRun.getName()); 
	}
    }
    public Process getNextProcess(){
	if(processes.peek().getArrivalTime() > quantumCounter){
	    return null;
	}
	return processes.peek();
    }

    public boolean allProcessesCompleted(){
	for(Process p:processes){
	    if(p.processIncomplete()){
		return false;
	    }	 
	}
	return true;
    }

    public LinkedList<Process> getIncompleteProcesses(){
	LinkedList<Process> incompleteProcesses = new LinkedList<Process>();
	for(Process p:processes){
	    if(p.processIncomplete()){
		incompleteProcesses.add(p);
	    }
	}
	return incompleteProcesses;
    }
    public int getAverageTurnaroundTime(){ // ######################################## TODO:
	return 0;
    }

    public int getAverageWaitingTime(){ // ######################################## TODO:
	return 0;
    }

    public int getAverageResponseTime(){ // ######################################## TODO:
	return 0;
    }

    public int getThroughput(){ // ######################################## TODO:
	return 0;
    }

}
