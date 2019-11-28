package PriorityQueue;

public class Student implements Comparable<Student> {
    public String name;
    public Integer marks;
    public int time; 
    public Student(String trim, int parseInt) {
    	name=trim;
    	marks=parseInt;
    	time=-1;
    }
    
    @Override
    public int compareTo(Student student) {
    	if(marks==student.getMarks())
    	{
    		if(time<student.getInsertionTime())
    		{
    			return 1;
    		}
    		else if(time>student.getInsertionTime())
    		{
    			return -1;
    		}
    		else
    			return 0;
    	}
    	
    	else if(marks>student.getMarks())
    		return 1;
    	
    	else
    		return -1;
    }

    public String getName() {
        return name;
    }
    
    public int getMarks()
    {
    	return marks;
    }
    
    public int getInsertionTime()
    {
    	return time;
    }
    
    public String toString()
    {
    	return ("Student{name='"+name+"', marks="+marks+"}");
    }
}