package ProjectManagement;

public class Job implements Comparable<Job>,JobReport_  {

	String jobName;
	Project project;
	User user;
	public int time;
	String priority,runtime;
	public int aTime,cTime,waitingTime;
	Job(String jobName, Project project, User user, String runtime,int aTime)
	{
		this.jobName=jobName;
		this.project=project;
		this.user=user;
		this.runtime=runtime;
		priority=project.getPriority();
		this.aTime=aTime;
		cTime=0;
	}
	
	public int getWaitingTime()
	{
		return waitingTime;
	}
    public int compareTo(Job job) 
    {
    	if(jobName.compareTo(job.getJobname())!=0) {
        if(Integer.parseInt(priority)==Integer.parseInt(job.getPriority()))
        {
//        	System.out.println("priority is same for the two jobs");
        	if(time<job.getInsertionTime())
        		return 1;
        	else
        		return -1;
//        	return 0;
        }
        else if (Integer.parseInt(priority)-Integer.parseInt(job.getPriority())<0) {
        	
//        	System.out.println(jobName+" has higher priority than "+job.getJobname());
        	return -1;
        }
        else 
        {
//        	System.out.println(job.getJobname()+" has higher priority than "+jobName);
        	return 1;
    	
        }
    	}
    	else {
    		if(time<job.getInsertionTime())
        		return 1;
        	else if(time<job.getInsertionTime())
        		return -1;
        	else return 0;
    	}
    }
    
    public String getRuntime()
    {
    	return runtime;
    }
    
    public String getPriority()
    {
    	return priority;
    }
    
    public String getJobname()
    {
    	return jobName;
    }
    public Project getProject()
    {
    	return project;
    }
    public User getUser()
    {
    	return user;
    }
    public String getStatus()
    {
//    	if()
//    	return "REQUESTED";
//    	
//    	else
//    		return "COMPLETED";
return null;
    }
    public int getInsertionTime()
    {
    	return time;
    }
    
    public String toString()
    {
    	return ("Job= "+jobName+" "+project.getName()+" with priority "+priority);
    }
    public String user() 
	 { 
		return user.getName(); 
	 }
	 
	 public String project_name()  
	 { 
		 return project.getName();
	 }

	 public int budget()  
	 { 
		 return Integer.parseInt(project.getBudget());
	 }

	 public int arrival_time()  
	 { 
		 return aTime;
	 }

	 public int completion_time()
	 {
		 return (time+Integer.parseInt(project.getBudget())); 
	 }
    
}