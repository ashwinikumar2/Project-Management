package ProjectManagement;

public class User implements Comparable<User>, UserReport_ {
	
	String name;
	public int consumedTime;
	public Job latestJob;
	User(String name)
	
	{
		this.name=name;
		consumedTime=0;
		latestJob=null;
	}

    @Override
    public int compareTo(User user) {
    	
        return 0;
    }
    
    public String getName()
    {
    	return name;
    	
    }
    public String toString()
    {
    	return ("[User= "+name+"]");
    	
    }
    public int getConsumedTime()
    {
    	return consumedTime;
    }
    public Job getLatestJob()
    {
    	return latestJob;
    }
}
