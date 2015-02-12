import java.util.*;


public class OS{
    private LinkedList<Process> processes;
    private int numProcesses;
    private float arrivalTime;
    private float expTotalRunTime;
    private int priority;
    private int processNumber = 1;
    private String name = "P";
    private Random rGenerator = new Random(1);
    public OS(int numProcesses){
	this.numProcesses = numProcesses;
	processes = new LinkedList<Process>();
	generateNewProcesses();
    }
    public void setNumProcesses(int updatedNumProc){
	numProcesses = updatedNumProc;
    }

    public void generateNewProcesses(){
	for(int i=0; i<numProcesses; i++){
	    priority = rGenerator.nextInt(4)+1;
	    arrivalTime = rGenerator.nextFloat() * (float)9.0;//	    arrivalTime = rGenerator.nextFloat() * (float)99.0;
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
	System.out.println();
    }
    public LinkedList<Process> getProcesses(){
	return processes;
    }
    public static void main(String[] args){
	OS generator = new OS(5);

	FCFS fcfsTester = new FCFS(generator.getProcesses());
	fcfsTester.runProcesses();
	ArrayList<String> processes = fcfsTester.getRunTime();
	System.out.println(processes);

	OS generator2 = new OS(5);
	SJF sjfTester = new SJF(generator2.getProcesses());
	sjfTester.runProcesses();
	ArrayList<String> processes2 = sjfTester.getRunTime();
	System.out.println(processes2);

	OS generator3 = new OS(5);
	SRF srfTester = new SRF(generator3.getProcesses());
	srfTester.runProcesses();
	ArrayList<String> processes3 = srfTester.getRunTime();
	System.out.println(processes3);

	OS generator4 = new OS(5);
	RR rrTester = new RR(generator4.getProcesses());
	rrTester.runProcesses();
	ArrayList<String> processes4 = rrTester.getRunTime();
	System.out.println(processes4);

	// OS generator5 = new OS(5);
	// HPFwithFCFS hpffcfsTester = new HPFwithFCFS(generator5.getProcesses());
	// hpffcfsTester.getNextProcess();
	// ArrayList<String> processes5 = hpffcfsTester.getRunTime();
	// System.out.println(processes5);

	OS generator6 = new OS(5);
	HPFwithRR2 hpfrrTester = new HPFwithRR2(generator6.getProcesses());
	hpfrrTester.runProcesses();
	ArrayList<String> processes6 = hpfrrTester.getRunTime();
	System.out.println(processes6);

    }
}

