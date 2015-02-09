import java.util.*;

public class HPF{
    private LinkedList<Process> processes;
    private int counter;
    private Process nextProcess;
    public HPF(LinkedList<Process> processes){
	this.processes = processes;
	counter = 0; // keeps track of quantum
	nextProcess = null;
    }
}
