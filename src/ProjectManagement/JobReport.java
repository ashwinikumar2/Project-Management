package ProjectManagement;

public class JobReport implements JobReport_ {
	String jobName;
	Project project;
	User user;
	public int arrivalTime, completionTime;
	String priority,runtime;
	JobReport(String jobName, Project project, User user, String runtime)
	{
		this.jobName=jobName;
		this.project=project;
		this.user=user;
		this.runtime=runtime;
		priority=project.getPriority();
		
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
		 return arrivalTime;
	 }

	 public int completion_time()
	 {
		 return completionTime; 
	 }
}
