import java.util.*;

public class HPFwithRR2
{
    private LinkedList<Process> processes;
    private int quantumCounter;
    private ArrayList<String> runTime;
    private Process nextProcess;
    private LinkedList<Process> priority1;
    private LinkedList<Process> priority2;
    private LinkedList<Process> priority3;
    private LinkedList<Process> priority4;
    private final int quantumCounterLimit;
    
    public HPFwithRR2(LinkedList<Process> processes)
    {
    	this.processes = processes;
    	quantumCounter = 0;
	runTime = new ArrayList<String>();
	nextProcess = null;
    	priority1 = new LinkedList<Process>();
    	priority2 = new LinkedList<Process>();
    	priority3 = new LinkedList<Process>();
    	priority4 = new LinkedList<Process>();
	quantumCounterLimit = 100;
    }
    private void sortProcessesByPriority(){
	for(Process p:processes){
	    if(p.getPriority() == 1){
		priority1.add(p);
	    }
	    else if(p.getPriority() == 2){
		priority2.add(p);
	    }
	    else if(p.getPriority() == 3){
		priority3.add(p);
	    }
	    else{
		priority4.add(p);
	    }
	}
    }
    public void runProcesses(){
	sortProcessesByPriority();
	while (quantumCounter<quantumCounterLimit && (priority1.size()>0 || priority2.size()>0 || priority3.size()>0 || priority4.size()>0)){
	    executeProcesses();
	    quantumCounter++;
	}
	if(quantumCounter < quantumCounterLimit){
	    while(quantumCounter < quantumCounterLimit){
		runTime.add("NA");
		quantumCounter++;
	    }
	}
	if(!allProcessesCompleted()){ // ############## This part has not been checked or tested. This section extends the current runTime array to record all processes to completion after 99 quanta
	    getIncompleteProcesses();
	    while (priority1.size()>0 || priority2.size()>0 || priority3.size()>0 || priority4.size()>0){
		executeProcesses();
		quantumCounter++;
	    }
	}
    }
    private void executeProcesses(){
	Process nextProcessToRun = getNextProcess();
	if(nextProcessToRun == null){
	    System.out.println("No Process Available");
	    runTime.add("NA");
	    return;
	}
	System.out.println(nextProcessToRun); 
	runTime.add(nextProcessToRun.getName());
	nextProcessToRun.updateTimeRemaining((float)1.0);
	if(nextProcessToRun.getTimeRemaining() <= 0){
	    priority1.remove(nextProcessToRun);
	    priority2.remove(nextProcessToRun);
	    priority3.remove(nextProcessToRun);
	    priority4.remove(nextProcessToRun);
	    System.out.println("Removed: "+nextProcessToRun.getName()); 
	}
    }
    
    public Process getNextProcess(){
	Process returningThis;
	if(priority1.size()>0){
	    returningThis = cycle(priority1);
	    if(returningThis != null){
		return returningThis;
	    }

	}
	if(priority2.size()>0){
	    returningThis = cycle(priority2);
	    if(returningThis != null){
		return returningThis;
	    }
	}
	if(priority3.size()>0){
	    returningThis = cycle(priority3);
	    if(returningThis != null){
		return returningThis;
	    }
	}
	if(priority4.size()>0){
	    returningThis = cycle(priority4);
	    if(returningThis != null){
		return returningThis;
	    }

	}
	return null;
    }

    private Process cycle(LinkedList<Process> priorityProcesses){
	ListIterator processesIter = priorityProcesses.listIterator();
	Process tempProcess = null;
	nextProcess = null;
	LinkedList<Process> holdingList = new LinkedList<Process>();
	while(processesIter.hasNext()){
	    tempProcess = (Process)processesIter.next();
	    if(tempProcess.getArrivalTime() <= quantumCounter){
		nextProcess = tempProcess;
		processesIter.remove();
		break;
	    }
	    else{
		holdingList.add(tempProcess);
		processesIter.remove();
	    }
	}
	processesIter = holdingList.listIterator();
	while(processesIter.hasNext()){
	    priorityProcesses.add((Process)processesIter.next());
	}
	if(nextProcess!=null){
	    priorityProcesses.add(nextProcess);
	}
	return nextProcess;

    }
    private boolean priorityProcessIsComplete(LinkedList<Process> priorityProcess){
	for(Process p:processes){
	    if(p.processIncomplete()){
		return false;
	    }	 
	}
	return true;
    }
    public boolean allProcessesCompleted(){
	return priorityProcessIsComplete(priority1) && priorityProcessIsComplete(priority2) && priorityProcessIsComplete(priority3) && priorityProcessIsComplete(priority4);
    }

    private void extractIncompleteProcesses(LinkedList<Process> priorityProcess){
	LinkedList<Process> incompleteProcesses = new LinkedList<Process>();
	ListIterator iter = priorityProcess.listIterator();
	Process evalP;
	while(iter.hasNext()){
	    evalP = (Process)iter.next();
	    if(!evalP.processIncomplete()){
		iter.remove();
	    }
	}
    }

    public void getIncompleteProcesses(){
	extractIncompleteProcesses(priority1);
	extractIncompleteProcesses(priority2);
	extractIncompleteProcesses(priority3);
	extractIncompleteProcesses(priority4);
	
    }
    
    public ArrayList<String> getRunTime(){
	return runTime;
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
