import java.util.*;


public class OS{
    private LinkedList<Process> processes;
    private ArrayList<String> runTime;
    private int quantumCounter;
    //    private boolean unfinishedProcessExists;
    private int numProcesses;
    private float arrivalTime;
    private float expTotalRunTime;
    private int priority;
    private int processNumber = 1;
    private String name = "P";
    private Random rGenerator = new Random(1);
    public OS(int numProcesses){
	this.numProcesses = numProcesses;
	runTime = new ArrayList<String>();
	quantumCounter = 0;
	//	unfinishedProcessExists = false;
	processes = new LinkedList<Process>();
	generateNewProcesses();
    }
    public void setNumProcesses(int updatedNumProc){
	numProcesses = updatedNumProc;
    }

    public void generateNewProcesses(){
	for(int i=0; i<numProcesses; i++){
	    priority = rGenerator.nextInt(4)+1;
	    arrivalTime = rGenerator.nextFloat() * (float)99.0;
	    expTotalRunTime = Math.round(rGenerator.nextFloat() * (float)100) / (float)10.0;
	    Process proc = new Process(arrivalTime, expTotalRunTime, priority, name + Integer.toString(processNumber));
	    processNumber++;
	    processes.add(proc);
	}
	Collections.sort(processes, new Comparator<Process>(){
		public int compare(Process p1, Process p2){
		    return ((Float)p1.getArrivalTime()).compareTo(((Float)p2.getArrivalTime()));
		}
	    });
	printProcesses();
	processNumber = 1;
    }
    private void printProcesses(){
	for(Process p:processes){
	    System.out.println(p);
	}
	
    }
    public void runProcesses(ProcessScheduler pS){
	while (quantumCounter<100 && processes.size()>0){
	    executeProcesses(pS);
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
		executeProcesses(pS);
		quantumCounter++;
	    }
	}
	quantumCounter = 0; //reset quantum counter
    }
    
    private void executeProcesses(ProcessScheduler pS){
	Process nextProcessToRun= pS.getNextProcess();// ORIGINAL
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

    public boolean allProcessesCompleted(){ // ######################################## TODO:
	//CHECK IF ALL PROCESSES ARE COMPLETED
	return true; //STUB
    }

    public LinkedList<Process> getIncompleteProcesses(){ // ######################################## TODO:
	//FILTER THE PROCESSES FOR ONLY INCOMPLETE PROCESSES
	return new LinkedList<Process>(); //STUB
    }

    public void clearRunTime(){
	runTime = new ArrayList<String>();
    }

    public ArrayList<String> getRunTime(){
	return runTime;
    }
    public void clearProcesses(){
	processes = new LinkedList<Process>();
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

    //ONLY FOR TESTING MAIN METHOD BELOW
    public LinkedList<Process> getProcesses(){
	return processes;
    }

    public static void main(String[] args){
	OS test = new OS(5);
	//############### TEST: FCFS 5 PROCESSES
	System.out.println("###################### FCFS ################");
	test.runProcesses(new FCFS(test.getProcesses()));
	ArrayList<String> runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();


	/*


	//############### TEST: FCFS 20 PROCESSES
	test.setNumProcesses(20);
	test.generateNewProcesses();
	test.runProcesses(new FCFS(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();
	//############### TEST: SJF 5 PROCESSES
	System.out.println("############################ SJF ##############");
	test.setNumProcesses(5);
	test.generateNewProcesses();
	test.runProcesses(new SJF(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();
	//################ TEST: SJF 40 PROCESSES
	test.setNumProcesses(40);
	test.generateNewProcesses();
	test.runProcesses(new SJF(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();


	//################ TEST: SRF 5  PROCESSES
	System.out.println("############################ SRF ##############");
	test.setNumProcesses(5);
	test.generateNewProcesses();
	test.runProcesses(new SRF(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();
	//################ TEST: SRF 25 PROCESSES
	test.setNumProcesses(40);
	test.generateNewProcesses();
	test.runProcesses(new SRF(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();


	*/


	//################ TEST: RR 5  PROCESSES
	System.out.println("############################ RR ##############");
	test.setNumProcesses(5);
	test.generateNewProcesses();
	test.runProcesses(new RR(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();
	//################ TEST: RR 35  PROCESSES
	test.setNumProcesses(35);
	test.generateNewProcesses();
	test.runProcesses(new RR(test.getProcesses()));
	runTime = test.getRunTime();
	for (String timeSlice:runTime){
	    System.out.printf(timeSlice + "|");
	}
	test.clearRunTime();
	test.clearProcesses();
	System.out.println();

    }
}

