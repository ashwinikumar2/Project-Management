package ProjectManagement;


public class Project {

	String name;
	String budget;
	String priority;
	
	Project(String name, String priority,String budget)
	{
		this.name=name;
		this.budget=budget;
		this.priority=priority;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getBudget()
	{
		return budget;
	}
	
	public String getPriority()
	{
		return priority;
	}
}
