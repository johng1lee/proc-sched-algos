import java.util.Queue;
import java.util.LinkedList;




/*
1.OS calls HPFwithFCFS and hands it a list of sorted processes
2a.We sort all the processes into 4 different priority queues regardless of arrival time and we deal with this when we are trying to pull the next process out.
2b.We sort the processes into 4 different priority queues only if they are valid -- they have arrived.

P1 arrives at 0 with priority 2
P2 arrives at 1 with priority 1

At time = 0: P1 runs
time = 1: P1 runs till completion.




 */
public class HPFwithFCFS implements ProcessScheduler{
    private LinkedList<Process> processes;
    private Process nextProcess;
    private Queue<Process> availableProcesses1 = new LinkedList<Process>();
    private	Queue<Process> availableProcesses2 = new LinkedList<Process>();
    private	Queue<Process> availableProcesses3 = new LinkedList<Process>();
    private	Queue<Process> availableProcesses4 = new LinkedList<Process>();
    
    public HPFwithFCFS(LinkedList<Process> processes){
	this.processes = processes;

	nextProcess = null;
    }
    
    public Process getNextProcess(int quantum){
    	  for(Process p: processes){
      		if(p.getArrivalTime() > quantum-1 &&p.getArrivalTime() <= quantum && p.getPriority()==1){
      		    availableProcesses1.add(p);
      		}
      		else if(p.getArrivalTime() > quantum-1 &&p.getArrivalTime() <= quantum && p.getPriority()==2){
      		    availableProcesses2.add(p);
      		}
      		else if(p.getArrivalTime() > quantum-1 &&p.getArrivalTime() <= quantum && p.getPriority()==3){
      		    availableProcesses3.add(p);
      		}
      		else if(p.getArrivalTime() > quantum-1 &&p.getArrivalTime() <= quantum && p.getPriority()==4){
      		    availableProcesses4.add(p);
      		}
      		
      	    }
    	  //System.out.print(availableProcesses1);
    	  //System.out.print(availableProcesses2);
    	  
    	if(quantum!=0) {
    		nextProcess.updateTimeRemaining((float)1.0);
    		if(nextProcess.getTimeRemaining() <= 0 && availableProcesses1.contains(nextProcess)==true){
    			availableProcesses1.remove(nextProcess);
        	    System.out.print(nextProcess.getName()+"|");
        	    return nextProcess;
    		}
    		else if(nextProcess.getTimeRemaining() <= 0 && availableProcesses2.contains(nextProcess)==true){
    			availableProcesses2.remove(nextProcess);
        	    System.out.print(nextProcess.getName()+"|");
        	    return nextProcess;
    		}
    		else if(nextProcess.getTimeRemaining() <= 0 && availableProcesses3.contains(nextProcess)==true){
    			availableProcesses3.remove(nextProcess);
        	    System.out.print(nextProcess.getName()+"|");
        	    return nextProcess;
    		}
    		else if(nextProcess.getTimeRemaining() <= 0 && availableProcesses4.contains(nextProcess)==true){
    			availableProcesses4.remove(nextProcess);
        	    System.out.print(nextProcess.getName()+"|");
        	    return nextProcess;
    		}
    	}
    	//System.out.print(nextProcess);
    	if(incompleteProcessExists()){
    		System.out.print(nextProcess.getName()+"|");
    		return nextProcess;
    	}
    	else{
    	    
    	    

        	if(availableProcesses1.isEmpty()!=true){
        		
        		nextProcess= availableProcesses1.poll();
        		System.out.print(nextProcess.getName()+"|");
        		return nextProcess;
        		
        	}
        	else if(availableProcesses2.isEmpty()!=true){
        		nextProcess= availableProcesses2.poll();
        		System.out.print(nextProcess.getName()+"|");
        		return nextProcess;
        	}
        	else if(availableProcesses3.isEmpty()!=true){
        		nextProcess= availableProcesses3.poll();
        		System.out.print(nextProcess.getName()+"|");
        		return nextProcess;
        	}
        	else if(availableProcesses4.isEmpty()!=true){
        		nextProcess= availableProcesses4.poll();
        		System.out.print(nextProcess.getName()+"|");
        		return nextProcess;
        	}
        	else
        		System.out.print(nextProcess.getName()+"|");
        		return null;
            		
    	}
    	
    	
    }
    
    private boolean incompleteProcessExists(){
    	
    	for(Process p: processes){
    	    if(p.processIncomplete()){
    		nextProcess = p;
    		return true;
    	    }
    	}
    	return false;
        }
}
