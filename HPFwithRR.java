import java.util.*;

public class HPFwithRR implements ProcessScheduler
{
    private LinkedList<Process> processes;
    private int quantumCount;
    private LinkedList<Process> priority1;
    private LinkedList<Process> priority2;
    private LinkedList<Process> priority3;
    private LinkedList<Process> priority4;
    
    
    public HPFwithRR(LinkedList<Process> processes)
    {
    	this.processes = processes;
    	quantumCount = 0;
    	priority1 = new LinkedList<Process>();
    	priority2 = new LinkedList<Process>();
    	priority3 = new LinkedList<Process>();
    	priority4 = new LinkedList<Process>();
    }
    
    public Process getNextProcess(int quantum)
    {
    	for(Process p: processes)
    	{
    		if(p.getPriority()==1){
    			priority1.add(p);
    		}
    		else if(p.getPriority()==2){
    			priority2.add(p);
    		}
    		else if(p.getPriority()==3){
    			priority3.add(p);
    		}
    		else 
    			priority4.add(p);
    	}
    	
    	ArrayList<Process> availableProcesses = new ArrayList<Process>();
    	quantumCount = 0;
    	
    	while(quantumCount <= 100)
    	{   
    		//LinkedList<Process> holder = new LinkedList<Process>();
    		if (!priority1.isEmpty())
    		{
    			for (Process ch1: priority1)
    			{
    				if(ch1.getArrivalTime() <= quantumCount && ch1.getTimeRemaining() > 0)
    				{
    					availableProcesses.add(ch1);
    					
    				}
    			}
    		}    		
    		
    		if (!priority2.isEmpty())
    		{
    			for (Process ch2: priority2)
    			{
    				if(ch2.getArrivalTime() <= quantumCount && ch2.getTimeRemaining() > 0)
    				{
    					availableProcesses.add(ch2);
    					
    				}
    			}
    			
    		}
    		
    		if (!priority3.isEmpty())
    		{
    			for (Process ch3: priority3)
    			{
    				if(ch3.getArrivalTime() <= quantumCount && ch3.getTimeRemaining() > 0)
    				{
    					availableProcesses.add(ch3);
    					
    				}
    			}
    		
    		}
    		
    		if (!priority4.isEmpty())
    		{
    			for (Process ch4: priority4)
    			{
    				if(ch4.getArrivalTime() <= quantumCount && ch4.getTimeRemaining() > 0)
    				{
    					availableProcesses.add(ch4);
    					
    				}
    			}
    			
    		}
    		quantumCount++;
    	}
    	
    	if(availableProcesses.size() == 0)
		{
			return null;
		}
		else
		{
			return availableProcesses.get(0);
		}
    	
    }    
}
