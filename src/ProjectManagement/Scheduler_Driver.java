package ProjectManagement;

import PriorityQueue.MaxHeap;
import PriorityQueue.Student;
import PriorityQueue.PriorityQueueDriverCode;
import RedBlack.RBTree;
import Trie.Trie;
import Trie.TrieDriverCode;
import Trie.TrieNode;
import Trie.Person;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
public class Scheduler_Driver{// extends Thread implements SchedulerInterface {
	
	static Trie< Project > projects=new Trie();
	static Trie<User> users=new Trie();
	static MaxHeap<Job> jobs=new MaxHeap();
	static ArrayList<Job> completedJobs=new ArrayList();
	static ArrayList<Job> incompletedJobs=new ArrayList();
    static int jobsDone=0;
    static ArrayList<Job> AllJobs=new ArrayList();
    static ArrayList<Job> f=new ArrayList();
    static ArrayList<Job> f1=new ArrayList();
    static ArrayList<Job> f2=new ArrayList();
//    static int aTime=0;
    static int x=0;
    static int x1=0;
    static int x2=0;
    static int gTime=0;
    static ArrayList<User> completedJobsUsers=new ArrayList();
    public static void main(String[] args) throws IOException {
//

//        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
//        File file;
//        if (args.length == 0) {
//            URL url = Scheduler_Driver.class.getResource("INP");
//            file = new File(url.getPath());
//        } else {
//            file = new File(args[0]);
//        }
//
//        scheduler_driver.execute(file);
//    }
//
//    public void execute(File commandFile) throws IOException {
//
    	File commandFile=new File("H:\\1 sem 2019-20\\col106\\Practice\\Assignment5\\src\\ProjectManagement\\INP");
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

                switch (cmd[0]) {
                    case "PROJECT":
                        handle_project(cmd);
                        break;
                    case "JOB":
                        handle_job(cmd);
                        break;
                    case "USER":
                        handle_user(cmd[1]);
                        break;
                    case "QUERY":
                        handle_query(cmd[1]);
                        break;
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                    	System.out.println("Top query");
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                    	System.out.println("Flush query");
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


            run_to_completion();
            print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }
   
//    @Override
    public static ArrayList<JobReport_> timed_report(String[] cmd) {
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }

//    @Override
    public static ArrayList<UserReport_> timed_top_consumer(int top) {
    	ArrayList<User> a=new ArrayList();
    	
    	for(int i=0;i<completedJobsUsers.size();i++)
    	{
//    		if(completedJobsUsers.get(i).getConsumedTime()>=top)
    		a.add(completedJobsUsers.get(i));
    	}
    	
    	for(int j=0;j<a.size();j++)
    	{
    		for(int j1=0;j1<a.size();j1++)
    		{
    			if((a.get(j)).getConsumedTime()<(a.get(j1)).getConsumedTime())
    			{
    			User u=a.get(j1);
    			a.set(j, a.get(j1));
    			a.set(j1, u);
    			}
    			else if(a.get(j).getLatestJob().completion_time()<a.get(j1).getLatestJob().completion_time())
    			{
    				User u=a.get(j1);
        			a.set(j, a.get(j1));
        			a.set(j1, u);
    			}
    		}
    	}
    	
    	ArrayList<UserReport_> a1=new ArrayList();
    	
    	for(int i1=0;i1<top;i1++)
    	{
    		if(i1==a1.size())
    			break;
    		a1.add(a.get(i1));
    	}
    	
        return a1;
    }



//    @Override
    public static void timed_flush(int waittime) {
    	ArrayList<Job> a=new ArrayList();
    	int gTime1=gTime;
//	    	for(int i2=0;i2<jobs.heap.size();i2++)
//	    	{
//	    		System.out.println(i2+"th element of the heap array is "+jobs.heap.get(i2));
//	    		
//	    	}
//    	System.out.println("value of gtime is "+gTime1);
    	for(int i1=0;i1<jobs.heap.size();i1++)
    	{
//    		System.out.println("value of gtime is "+gTime1);
//    		System.out.println(i1+"th element of heap array is "+jobs.getList().get(i1)+" with project budget "+jobs.getList().get(i1).getProject().getBudget());
//    		System.out.println("waiting time of "+jobs.getList().get(i1).getJobname()+" is "+(gTime1-jobs.heap.get(i1).arrival_time()));
    		
    		if((gTime1-jobs.heap.get(i1).arrival_time())>=waittime) 
    		{
//    			System.out.println("the job has waiting time greater than equal to the required value");
    		if(Integer.parseInt(jobs.heap.get(i1).getProject().getBudget())-Integer.parseInt(jobs.heap.get(i1).getRuntime())>=0)
    		{
//    			System.out.println("the job has sufficient budget to execute");
    			if(jobs.heap.size()>0) 
    	    	{	
//    	    		System.out.println("Size of heap >0");
    	    		Job obj1=jobs.heap.get(i1);
//    	    		if(!a.contains(obj1))
    	    		jobs.heap.get(i1).project.budget=Integer.toString(Integer.parseInt(jobs.heap.get(i1).getProject().getBudget())-Integer.parseInt(jobs.heap.get(i1).getRuntime()));
    	    		
    	    		gTime+=Integer.parseInt(obj1.getRuntime());
    	    		jobs.heap.get(i1).cTime=gTime;
    	    		
    	    		jobs.heap.set(i1, jobs.heap.get(jobs.heap.size()-1));
    	    		int index=i1;
//    	    		System.out.println("Element extracted is "+(Object)obj1);
//    	    		System.out.println("element set at its place is "+heap.get(0));
    	    		if(index<jobs.heap.size()-1) {
    	    		jobs.heap.remove(jobs.heap.size()-1);
    	    		int parentindex1;
    	    		int parentindex2;
    	    		
    	    		if((2*index+2)<=jobs.heap.size()-1)
	    			{parentindex2=(2*index)+2;
	    				parentindex1=2*index+1;
	    			}
	    			else if(2*index+1<jobs.heap.size())
	    			{
	    				parentindex2=index;
	    			parentindex1=index;
	    			}
	    			else
	    			{	parentindex1=index;
	    			parentindex2=index;
	    			}
//    	    		System.out.println("value of index= "+index);
//    	    		System.out.println("value of child1= "+parentindex1);
//    	    		System.out.println("value of child2= "+parentindex2);
//    	    		if(2*i1+2<jobs.heap.size())
//    	    		{
////    	    				System.out.println("Heap size is greater than 2");
//    	    			parentindex1=2*i1+1;
//    	        		parentindex2=2*i1+2;
//    	    		}
//    	    		else if(heap.size()==2)
//    	    		{
////    	    			System.out.println("Heap size is equal to 2");
//    	    			parentindex1=1;
//    	        		parentindex2=1;
//    	        		
//    	    		}
//    	    		else 
//    	    		{
////    	    			System.out.println("Heap size is 1");
//    	    			parentindex1=0;
//    	        		parentindex2=0;
//    	    		}
//    	    		System.out.println("heap size is "+heap.size()+" and value of 1child is "+parentindex1+" and value of 2child is "+parentindex2);
    	    		if(jobs.heap.size()>2)
    	    		{
//    	    			
    		    		while(jobs.heap.get(index).compareTo(jobs.heap.get(parentindex1))<=0 || jobs.heap.get(index).compareTo(jobs.heap.get(parentindex2))<=0 )
    		    		{
//    		    			System.out.println("parent has less priority than one of its child");
//    		    			System.out.println("present node is "+heap.get(index)+" with index "+index);
//    		    			System.out.println("Left child= "+heap.get(parentindex1)+" with index "+parentindex1);
//    		    			System.out.println("Right child= "+heap.get(parentindex2)+" with index "+parentindex2);
//    		    			if(heap.get(index) instanceof Student)
//    		    	    	{
//    		    				if((((Student)(heap.get(parentindex2))).getInsertionTime())==((Student) (heap.get(parentindex1))).getInsertionTime())
//    							{
//    								
//    							}
//    		    	    	}
    		    			if(jobs.heap.size()>2) {
    		    			if((2*index+2)<=jobs.heap.size()-1)
    		    			{parentindex2=(2*index)+2;
    		    				parentindex1=2*index+1;
    		    			}
    		    			else if(2*index+1<jobs.heap.size()-1)
    		    			{
    		    				parentindex2=index;
    		    			parentindex1=index;
    		    			}
    		    			else
    		    			{	parentindex1=index;
    		    			parentindex2=index;
    		    			}
    		    			}
    		    			else if(jobs.heap.size()==2)
    		    			{
    		    				index=0;
    		    				parentindex2=0;
    		    				parentindex1=1;
    		    			}
    		    			else if(jobs.heap.size()==1)
    		    			{
    		    				
    		    				index=0;
    		    				parentindex2=0;
    		    				parentindex1=0;
    		    			}
    		    				
    		    			
    		    			if(parentindex2!=index && jobs.heap.get(parentindex2).compareTo(jobs.heap.get(parentindex1))==0 && parentindex1!=parentindex2)
    		    			{
//    		    				System.out.println("left and right child have same priority");
    		    				
    		    		    		Job exs=jobs.heap.get(parentindex1);
    		    					Job s=jobs.heap.get(parentindex2);
    		    		    		
    		    					
    		    						if(exs.getInsertionTime()<s.getInsertionTime())
    		    						{
//    		    							System.out.println("left child has less insertion time");
    		    							Job obj=jobs.heap.get(index);
    		    							jobs.heap.set(index, jobs.heap.get(parentindex1));
    		    							jobs.heap.set(parentindex1, obj);
    		    		    				index=parentindex1;
    		    						}
    		    						else
    		    						{
//    		    							System.out.println("right child has less insertion time");
    		    							Job obj=jobs.heap.get(index);
    		    							jobs.heap.set(index, jobs.heap.get(parentindex2));
    		    							jobs.heap.set(parentindex2, obj);
    		    		    				index=parentindex2;
    		    						}
    		    				
    		    			}
    		    			
    		    			else if(jobs.heap.get(index).compareTo(jobs.heap.get(parentindex1))<=0 && jobs.heap.get(parentindex2).compareTo(jobs.heap.get(parentindex1))<0)
    		    			{
//    		    				System.out.println("Root has less priority than left child and left child has higher priority than right child");
    		    				Job obj=jobs.heap.get(index);
    		    				jobs.heap.set(index, jobs.heap.get(parentindex1));
    		    				jobs.heap.set(parentindex1, obj);
    		    				index=parentindex1;
//    		    				System.out.println("parentindex1 becomes "+parentindex1);
//    			    			System.out.println("parentindex2 becomes "+parentindex2);
    		    			}
    		    			else if(jobs.heap.get(index).compareTo(jobs.heap.get(parentindex2))<=0 && jobs.heap.get(parentindex2).compareTo(jobs.heap.get(parentindex1))>0)
    		    			{
//    		    				System.out.println("Root has less priority than right child and left child has less priority than right child");
    		    				Job obj=jobs.heap.get(index);
    		    				jobs.heap.set(index, jobs.heap.get(parentindex2));
    		    				jobs.heap.set(parentindex2, obj);
    		    				index=parentindex2;
    		    			}
    		    			else 
    		    			{
    		    				
    		    				break;
    		    			}
    		    			
    		    			if((2*index+2)<=jobs.heap.size()-1)
    		    			{parentindex2=(2*index)+2;
    		    				parentindex1=2*index+1;
    		    			}
    		    			else if(2*index+1<jobs.heap.size()-1)
    		    			{
    		    				parentindex2=index;
    		    			parentindex1=index;
    		    			}
    		    			else
    		    			{	parentindex1=index;
    		    			parentindex2=index;
    		    			}
    		    			
    		    			if(index>=jobs.heap.size())
    		    				break;
//    		    			if((2*index)+1<=heap.size()-1)
//    		    			parentindex2=(2*index)+2;
//    		    			else
//    		    				parentindex2=index;
//    		    			System.out.println("index becomes "+index);
//    		    			System.out.println("parentindex1 becomes "+parentindex1);
//    		    			System.out.println("parentindex2 becomes "+parentindex2);
    		    			
    		    		}
    	    		}
    	    		else if(jobs.heap.size()==2)
    	    		{
//    	    			System.out.println("size of heap becomes 2");
    	    			if(jobs.heap.get(0).compareTo(jobs.heap.get(1))<0)
    	    			{
//    	    				System.out.println("root has less priority than left child");
    	    				Job obj=jobs.heap.get(index);
    	    				jobs.heap.set(index, jobs.heap.get(parentindex1));
    	    				jobs.heap.set(parentindex1, obj);
    	    				index=parentindex1;
    	    			}    				
    	    			
    	    		}
    	    			i1--;
    	    		if(!a.contains(obj1))
    	    		a.add(obj1);
    	    		if(!completedJobs.contains(obj1)) {
    	    		completedJobs.add(obj1);x++;
    	    		f.add(completedJobs.get(completedJobs.size()-1));
//    	    		System.out.println("project budget now becomes "+jobs.getList().get(i1).getProject().getBudget());
    	    		}
    	    		
//    	    		if(a.size()>=1)
//    	    			System.out.println(a.get(a.size()-1).getJobname()+" added succesfully in the array returned");
    		
    	    		}
    	    		else
    	    		{	i1--;
    	    		if(jobs.heap.get(index)!=null)
    	    		jobs.heap.remove(index);
    	    			if(!a.contains(obj1))
        	    		a.add(obj1);
        	    		if(!completedJobs.contains(obj1)) 
        	    	{x++;
        	    		completedJobs.add(obj1);
        	    		f.add(completedJobs.get(completedJobs.size()-1));
    	    			break;
    	    			
    	    		}
    	    	}
    		}
    	}
//    	for(int i=0;i<AllJobs.size();i++)
//    	{
//    		if((gTime-AllJobs.get(i).arrival_time())>waittime)
//    		{
//    			a.add(AllJobs.get(i));
//    		}
//    	}
    	
    	
    	}
    	}
//    	System.out.println("size of a= "+a.size());
    	for(int j=0;j<a.size();j++)
    	{
    		for(int j1=j;j1<a.size();j1++)
    		{
    			if((a.get(j)).compareTo(a.get(j1))<0)
    			{
    				
    				Job job=a.get(j1);
//    				System.out.println("value of parent before swaping is "+a.get(j));
//    				System.out.println("value of child before swaping is "+a.get(j1));
    				a.set(j1, a.get(j));
    				a.set(j, job);
    				
//    				System.out.println("value of parent after swaping is "+a.get(j));
//    				System.out.println("value of child after swaping is "+a.get(j1));
    			}
    		}
    		
    	}
    	
//    	System.out.println("size of alljobs is "+AllJobs.size());
    	for(int k=0;k<a.size();k++)
    	{
    		System.out.println("Job{user='"+a.get(k).getUser().getName()+"', project='"+a.get(k).getProject().getName()+"', jobstatus=REQUESTED, execution_time="+a.get(k).getRuntime()+", end_time="+a.get(k).completion_time()+", priority="+a.get(k).getPriority()+", name='"+a.get(k).getJobname()+"'}");
//    		"Job{user='"+ completedJobs.get(i).getUser().getName()+"', project='"+completedJobs.get(i).getProject().getName()+"', jobstatus=COMPLETED, execution_time="+completedJobs.get(i).getRuntime()+", end_time="+gTime+completedJobs.get(i).getRuntime()+", name='"+completedJobs.get(i).getJobname()+"'}"
    	}
    	
    }
    

    private static ArrayList<JobReport_> handle_new_priority(String s) 
    {
    	System.out.println("Priority query");
    	ArrayList<JobReport_> a=new ArrayList();
    	
    	for(int j=0;j<incompletedJobs.size();j++)
    	{
    		
    		if(incompletedJobs.get(j).getPriority().compareTo(s)>=0)
    		{
    			a.add(incompletedJobs.get(j));
    			
    		}
    	}
    	for(int k=0;k<jobs.getList().size();k++)
    	{
    		if(jobs.getList().get(k).getPriority().compareTo(s)>=0)
    		{
    			a.add(jobs.getList().get(k));
    			
    		}
    	}
//    	for(int o=0;o<a.size();o++)
//    	{
//    		System.out.println(o+"th element is "+a.get(o));
//    		
//    	}
    	return a;
    }

    private static ArrayList<JobReport_> handle_new_projectuser(String[] cmd) {
    	System.out.println("Project User query");
    	ArrayList<JobReport_> a=new ArrayList();
    	for(int i=0;i<completedJobs.size();i++)
    	{
    		if((completedJobs.get(i).getProject().getName()).compareTo(cmd[1])==0 && (completedJobs.get(i).getUser().getName()).compareTo(cmd[2])==0)
    		{if(completedJobs.get(i).arrival_time()>=Integer.parseInt(cmd[3]) && completedJobs.get(i).arrival_time()<=Integer.parseInt(cmd[4]))
    		{
    			a.add(completedJobs.get(i));
    			
    		}}
    	}
    	ArrayList<JobReport_> b=new ArrayList();
    	for(int j=0;j<incompletedJobs.size();j++)
    	{
    		if((incompletedJobs.get(j).getProject().getName()).compareTo(cmd[1])==0 && (incompletedJobs.get(j).getUser().getName()).compareTo(cmd[2])==0)
    		{if(incompletedJobs.get(j).arrival_time()>=Integer.parseInt(cmd[3]) && incompletedJobs.get(j).arrival_time()<=Integer.parseInt(cmd[4]))
    		{
    			b.add(incompletedJobs.get(j));
    			
    		}}
    	}
    	for(int k=0;k<jobs.getList().size();k++)
    	{
    		
    			if((jobs.getList().get(k).getProject().getName()).compareTo(cmd[1])==0 && (jobs.getList().get(k).getUser().getName()).compareTo(cmd[2])==0)
    		{if(jobs.getList().get(k).arrival_time()>=Integer.parseInt(cmd[3]) && jobs.getList().get(k).arrival_time()<=Integer.parseInt(cmd[4]))
    		{
    			b.add(jobs.getList().get(k));
    			
    		}}
    	}
    	
    	
    	for(int l=0;l<b.size();l++)			//SORT
    	{
    		for(int l1=l;l1<b.size();l1++)
    		{
    			if(((Job)b.get(l)).compareTo((Job)b.get(l1))>0)
    			{
    				JobReport_ j= b.get(l);
    				b.set(l, b.get(l1));
    				b.set(l1, j);
    			}
    		}
    		
    	}
    	
    	for(int i1=0;i1<b.size();i1++)
    	{
    		a.add(b.get(i1));
    	}
    	
//    	for(int o=0;o<a.size();o++)
//    	{
//    		System.out.println(o+"th element is "+a.get(o));
//    		
//    	}
    	return a;
    	
    }

    private static ArrayList<JobReport_> handle_new_user(String[] cmd) {
        
    	System.out.println("User query");
    	ArrayList<JobReport_> a=new ArrayList();
    	for(int i=0;i<completedJobs.size();i++)
    	{
    		if((completedJobs.get(i).getUser().getName()).compareTo(cmd[1])==0)
    		
    		{if(completedJobs.get(i).arrival_time()>=Integer.parseInt(cmd[2]) && completedJobs.get(i).arrival_time()<=Integer.parseInt(cmd[3]))
    		{
    			a.add(completedJobs.get(i));
    			
    		}}
    	}
    	for(int j=0;j<incompletedJobs.size();j++)
    	{
    		
    			if((incompletedJobs.get(j).getUser().getName()).compareTo(cmd[1])==0)
    		{if(incompletedJobs.get(j).arrival_time()>=Integer.parseInt(cmd[2]) && incompletedJobs.get(j).arrival_time()<=Integer.parseInt(cmd[3]))
    		{
    			a.add(incompletedJobs.get(j));
    			
    		}}
    	}
    	for(int k=0;k<jobs.getList().size();k++)
    	{
    		if((jobs.getList().get(k).getUser().getName()).compareTo(cmd[1])==0)
    		{if(jobs.getList().get(k).arrival_time()>=Integer.parseInt(cmd[2]) && jobs.getList().get(k).arrival_time()<=Integer.parseInt(cmd[3]))
    		{
    			a.add(jobs.getList().get(k));
    			
    		}}
    	}
    	
//    	for(int o=0;o<a.size();o++)
//    	{
//    		System.out.println(o+"th element is "+a.get(o));
//    		
//    	}
    	return a;
        
    	
        
    }

    private static ArrayList<JobReport_> handle_new_project(String[] cmd) {
    	System.out.println("Project query");
    	ArrayList<JobReport_> a=new ArrayList();
    	for(int i=0;i<completedJobs.size();i++)
    	{
    		if((completedJobs.get(i).getProject().getName()).compareTo(cmd[1])==0)
    		{if(completedJobs.get(i).arrival_time()>=Integer.parseInt(cmd[2]) && completedJobs.get(i).arrival_time()<=Integer.parseInt(cmd[3]))
    		{
    			a.add(completedJobs.get(i));
    			
    		}}
    	}
    	for(int j=0;j<incompletedJobs.size();j++)
    	{
    		if((incompletedJobs.get(j).getProject().getName()).compareTo(cmd[1])==0)
    		{if(incompletedJobs.get(j).arrival_time()>=Integer.parseInt(cmd[2]) && incompletedJobs.get(j).arrival_time()<=Integer.parseInt(cmd[3]))
    		{
    			a.add(incompletedJobs.get(j));
    			
    		}}
    	}
    	for(int k=0;k<jobs.getList().size();k++)
    	{
    		
    			if((jobs.getList().get(k).getProject().getName()).compareTo(cmd[1])==0)
    		{if(jobs.getList().get(k).arrival_time()>=Integer.parseInt(cmd[2]) && jobs.getList().get(k).arrival_time()<=Integer.parseInt(cmd[3]))
    		{
    			a.add(jobs.getList().get(k));
    			
    		}}
    	}
    	
//    	for(int o=0;o<a.size();o++)
//    	{
//    		System.out.println(o+"th element is "+a.get(o));
//    		
//    	}
    	return a;
        
    }



    
    public static void schedule() {
            execute_a_job();
    }
    
    public static void timed_handle_job(String[] cmd)
    {
    	System.out.println("Creating job");
    	Project project;
    	if(projects.search(cmd[2])!=null)
	    {	
//    		System.out.println("The project exists with the given name");
    		project=projects.search(cmd[2]).getValue();
    		User user;
    		if(users.search(cmd[3])!=null)
    		{
//    			System.out.println("The user also exists with the given name");
    			user=users.search(cmd[3]).getValue();
    			Job job=new Job(cmd[1], project,user, cmd[4] ,gTime);
//    			job.aTime=gTime;
    			System.out.println("value of arrival time set to "+job.arrival_time());
    			AllJobs.add(job);
    			jobs.insert(job);
    			
//    			System.out.println("Job inserted is "+job.getJobname()+" "+job.getProject().getName()+" "+job.getUser().getName());
    		}
    		else
    		{
    			System.out.println("No such user exists: "+cmd[3]);
    		}
	    	
	    	
    	}
    	else
    	{
    		System.out.println("No such project exists. "+cmd[2]);
    		
    	}
    	
//    	for(int i=0;i<jobs.getList().size();i++)
//    	{
//    		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//    	}
    	
    	
    	
    }
    public static void timed_handle_project(String[] cmd)
    {
//System.out.println("Creating project");
    	
    	Project project=new Project(cmd[1], cmd[2], cmd[3]);
    	
    	projects.insert(cmd[1], project);
    	
//    	System.out.println("project inserted is "+projects.search(cmd[1]).getValue().getName());
    	
    }
    
    
    public static void timed_handle_user(String name)
    {
    	User user=new User(name);
//    	System.out.println("Creating user");
    	
    	users.insert(name, user);
    	
    }
    
    public static void timed_run_to_completion()
    {
    	while(jobs.getList().size()!=0)
        {
    	System.out.println("Running code");
    	int size= jobs.getList().size();
    	System.out.println("Remaining jobs: "+size);
    	
    	Job job=jobs.extractMax();
    	
    	Project project=job.getProject();
    	
    	
//    	System.out.println("value of runtime - budget= "+(Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime())));
    	if((Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime()))>=0)
    	{
//    		System.out.println("value of runtime - budget >=0");
    		System.out.println("Executing: "+job.getJobname()+" from: "+project.getName());
    		job.getProject().budget=Integer.toString(Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime()));
    		System.out.println("Project: "+project.getName()+" budget remaining: "+project.getBudget());
    		System.out.println("System execution completed");
    		jobsDone++;
//    		completedJobs.add(job);
//    		x1++;
//    		f1.add(completedJobs.get(completedJobs.size()-1));
//    		for(int i=0;i<jobs.getList().size();i++)
//        	{
//        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//        	}
    	}
    	else
    	{
//    		System.out.println("value of runtime - budget <0");
    		int p=0;
    		Job j=job;
    		Project project1=j.getProject();
    		while(p==0)
    		{
//    			System.out.println("j is "+j.getJobname()+" "+j.getProject().getName()+" "+j.getUser().getName());
//    			System.out.println("value of runtime - budget <0");
    			if((Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime()))<0)
    			{
    				System.out.println("Executing: "+j.getJobname()+" from: "+j.getProject().getName());
//    				System.out.println("Again value of runtime - budget= "+(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime())));
    				incompletedJobs.add(j);
    				System.out.println("Un-sufficient budget.");
    				if(jobs.getList().size()!=0) {
    				j=jobs.extractMax();
//    				System.out.println("Due to unsufficient budget another job "+j.getJobname()+" "+j.getProject().getName()+" was extracted from the max heap");
//    				for(int i=0;i<jobs.getList().size();i++)
//    	        	{
//    	        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//    	        	}
    				}
    				else{
    					p++;
    					System.out.println("System execution completed");
    					break;
    				}
    			}
    			else    			
    			{
//    				System.out.println("Finally value of runtime - budget becomes "+(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime())));
    				j.getProject().budget=Integer.toString(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime()));
    				System.out.println("Executing: "+j.getJobname()+" from: "+j.getProject().getName());
    				System.out.println("Project: "+j.getProject().getName()+" budget remaining: "+j.getProject().getBudget());
    				System.out.println("System execution completed");
    	    		jobsDone++;
//    	    		completedJobs.add(j);
//    	    		for(int i=0;i<jobs.getList().size();i++)
//    	        	{
//    	        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//    	        	}
    	    		p++;
    	    		break;
    			}
    		}
    	}
    	
        }
    }
    public static void run_to_completion() {
    	while(jobs.getList().size()!=0)
        {
    	System.out.println("Running code");
    	int size= jobs.getList().size();
    	System.out.println("Remaining jobs: "+size);
    	
    	Job job=jobs.extractMax();
    	
    	Project project=job.getProject();
    	
    	
//    	System.out.println("value of runtime - budget= "+(Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime())));
    	if((Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime()))>=0)
    	{
//    		System.out.println("value of runtime - budget >=0");
//    		System.out.println("Executing: "+job.getJobname()/*+" PRIORITY= "+job.getPriority()+" &insertion time= "+job.getInsertionTime()*/+" from: "+job.getProject().getName());
    		System.out.println("Executing: "+job.getJobname()+" from: "+job.getProject().getName());
    		job.getProject().budget=Integer.toString(Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime()));
    		System.out.println("Project: "+project.getName()+" budget remaining: "+project.getBudget());
    		System.out.println("System execution completed");
    		jobsDone++;
    		if(!completedJobs.contains(job)) {
    		completedJobs.add(job);
    		x1++;
    		f1.add(job);
    		}
//    		for(int i=0;i<jobs.getList().size();i++)
//        	{
//        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//        	}
    	}
    	else
    	{
//    		System.out.println("value of runtime - budget <0");
    		
    		Job j=job;
    		Project project1=j.getProject();
    		while(true)
    		{
//    			System.out.println("j is "+j.getJobname()+" "+j.getProject().getName()+" "+j.getUser().getName());
//    			System.out.println("value of runtime - budget <0");
    			if((Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime()))<0)
    			{
//    				System.out.println("Executing: "+j.getJobname()+" PRIORITY= "+j.getPriority()+" &insertion time= "+j.getInsertionTime()+" from: "+j.getProject().getName());
    				System.out.println("Executing: "+j.getJobname()+" from: "+j.getProject().getName());
//    				System.out.println("Again value of runtime - budget= "+(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime())));
    				incompletedJobs.add(j);
    				System.out.println("Un-sufficient budget.");
    				if(jobs.getList().size()!=0) {
    				j=jobs.extractMax();
//    				System.out.println("Due to unsufficient budget another job "+j.getJobname()+" "+j.getProject().getName()+" was extracted from the max heap");
//    				for(int i=0;i<jobs.getList().size();i++)
//    	        	{
//    	        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//    	        	}
    				}
    				else{
    					System.out.println("System execution completed");
    					break;
    				}
    			}
    			else    			
    			{
//    				System.out.println("Finally value of runtime - budget becomes "+(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime())));
    				j.getProject().budget=Integer.toString(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime()));
//    				System.out.println("Executing: "+j.getJobname()+" PRIORITY= "+j.getPriority()+" &insertion time= "+j.getInsertionTime()+" from: "+j.getProject().getName());
    				System.out.println("Executing: "+j.getJobname()+" from: "+j.getProject().getName());
    				System.out.println("Project: "+j.getProject().getName()+" budget remaining: "+j.getProject().getBudget());
    				System.out.println("System execution completed");
    	    		jobsDone++;
    	    		if(!completedJobs.contains(j)) {
    	    		completedJobs.add(j);
    	    		x1++;
    	    		f1.add(completedJobs.get(completedJobs.size()-1));
    	    		}
//    	    		for(int i=0;i<jobs.getList().size();i++)
//    	        	{
//    	        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//    	        	}
    	    		break;
    			}
    		}
    	}
    	
        }
    	
    }

    public static void print_stats() {
    	System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+completedJobs.size());
    	int x1=0;
    	
    	for(int i21=0;i21<completedJobs.size();i21++)
    	{
    		for(int i31=i21;i31<completedJobs.size();i31++)
    		{
    			if((completedJobs.get(i21)).compareTo(completedJobs.get(i31))<0) {
    			Job j=completedJobs.get(i21);
    			completedJobs.set(i21, completedJobs.get(i31));
    			completedJobs.set(i31, j);}
    		}
    		
    	}
    	for(int i=0;i<completedJobs.size();i++)
    	{
    		
//    		System.out.println("gTime= "+gTime);
//    		System.out.println("runtime= "+Integer.parseInt(completedJobs.get(i).getRuntime()));
    		int x=(x1+Integer.parseInt(completedJobs.get(i).getRuntime()));
    		System.out.println("Job{user='"+ completedJobs.get(i).getUser().getName()+"', project='"+completedJobs.get(i).getProject().getName()+"', jobstatus=COMPLETED, execution_time="+completedJobs.get(i).getRuntime()+", end_time="+x+", name='"+completedJobs.get(i).getJobname()+"'}");
    		x1+=Integer.parseInt(completedJobs.get(i).getRuntime());
    	}
    	System.out.println("------------------------");
    	
    	System.out.println("Unfinished jobs:");
    	
    	
    	
    	for(int i2=0;i2<incompletedJobs.size();i2++)
    	{
    		for(int i3=i2;i3<incompletedJobs.size();i3++)
    		{
    			if((incompletedJobs.get(i2)).compareTo(incompletedJobs.get(i3))<0) {
    			Job j=incompletedJobs.get(i2);
    			incompletedJobs.set(i2, incompletedJobs.get(i3));
    			incompletedJobs.set(i3, j);}
    		}
    		
    	}
    	
    	for(int i1=0;i1<incompletedJobs.size();i1++)
    	{
    		System.out.println("Job{user='"+ incompletedJobs.get(i1).getUser().getName()+"', project='"+incompletedJobs.get(i1).getProject().getName()+"', jobstatus=REQUESTED, execution_time="+incompletedJobs.get(i1).getRuntime()+", end_time=null, name='"+incompletedJobs.get(i1).getJobname()+"'}");
    		
    	}
    	System.out.println("Total unfinished jobs: "+ incompletedJobs.size());
    	System.out.println("--------------STATS DONE---------------");
//    	System.out.println("x= "+x);
//    	System.out.println("size of f= "+f.size());
//    	System.out.println("x1= "+x1);
//    	System.out.println("size of f1= "+f1.size());
//    	System.out.println("x2= "+x2);
//    	System.out.println("size of f2= "+f2.size());
    }

    public static void handle_add(String[] cmd) {
//    	System.out.println("ash");
    	
    	if(projects.search(cmd[1])!=null) {
//    		System.out.println("ash1");
    		System.out.println("ADDING Budget");
    		Project project=projects.search(cmd[1]).getValue();
    		
    		project.budget=Integer.toString(Integer.parseInt(project.getBudget())+Integer.parseInt(cmd[2]));
    		int x=incompletedJobs.size();
    		
    		for(int i=0;i<incompletedJobs.size();i++)
    		{
//    			System.out.println(i+"th element of the incompletedJobs is "+incompletedJobs.get(i) );
    			if((incompletedJobs.get(i).getProject().getName()).compareTo(cmd[1])==0)
    			{
    				if((Integer.parseInt(incompletedJobs.get(i).getProject().getBudget())-Integer.parseInt(incompletedJobs.get(i).getRuntime()))>=0)
    				{
// /*CAUTION*/   					incompletedJobs.get(i).aTime=gTime;
//    					System.out.println("job "+incompletedJobs.get(i).getJobname()+" "+incompletedJobs.get(i).getProject().getName()+" with budget of project= "+incompletedJobs.get(i).getProject().getBudget());
    					jobs.insert(incompletedJobs.get(i));
    					incompletedJobs.remove(i);
    					i--;
    					
    				}
    			}
    		}
    		
    		
    	}
    	else
    	{
//    		System.out.println("afafafsf");
    		System.out.println("No such project exists. "+cmd[1]);
    	}
    	
    }

    public static void handle_empty_line() {
       schedule();
       
       System.out.println("Running code");
   	int size= jobs.getList().size();
   	System.out.println("Remaining jobs: "+size);
   	
   	Job job=jobs.extractMax();
   	
   	if(job!=null) {
   	Project project=job.getProject();
   	
   	
//   	System.out.println("value of runtime - budget= "+(Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime())));
   	if((Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime()))>=0)
   	{
//   		if(!(AllJobs.contains(job)))
//   		AllJobs.add(job);
   		AllJobs.remove(job);
//   		System.out.println("value of runtime - budget >=0");
//   		System.out.println("Executing: "+job.getJobname()+" PRIORITY= "+job.getPriority()+" &insertion time= "+job.getInsertionTime()+" from: "+project.getName());
   		System.out.println("Executing: "+job.getJobname()+" from: "+project.getName());
   		job.getProject().budget=Integer.toString(Integer.parseInt(job.getProject().getBudget())-Integer.parseInt(job.getRuntime()));
   		System.out.println("Project: "+project.getName()+" budget remaining: "+project.getBudget());
   		System.out.println("Execution cycle completed");
   		jobsDone++;
   		job.cTime=gTime;
//   		job.waitingTime=job.completion_time()-job.arrival_time();
   				gTime+=Integer.parseInt(job.getRuntime());
   		job.user.consumedTime+=Integer.parseInt(job.getRuntime());
   		job.user.latestJob=job;
//   		if(!completedJobsUsers.contains(job.getUser()))
//   			completedJobsUsers.add(job.getUser());
   		if(!completedJobs.add(job)) {
 		completedJobs.add(job);
 		x2++;
 		f2.add(completedJobs.get(completedJobs.size()-1));}
//   		for(int i=0;i<jobs.getList().size();i++)
//       	{
//       		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//       	}
   	}
   	
   	else
   	{
//   		System.out.println("value of runtime - budget <0");
   		int p=0;
   		Job j=job;
   		Project project1=j.getProject();
   		while(p==0)
   		{
   			
   			AllJobs.remove(j);
//   			System.out.println("j is "+j.getJobname()+" "+j.getProject().getName()+" "+j.getUser().getName());
//   			System.out.println("value of runtime - budget <0");
   			if((Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime()))<0)
   			{
//   				if(!(AllJobs.contains(j)))
//   	   		   		AllJobs.add(j);
//   				System.out.println("Executing: "+j.getJobname()+" PRIORITY= "+j.getPriority()+" &insertion time= "+j.getInsertionTime()+" from: "+j.getProject().getName());
   				System.out.println("Executing: "+j.getJobname()+" from: "+j.getProject().getName());
//   				System.out.println("Again value of runtime - budget= "+(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime())));
//   				j.waitingTime=gTime-job.arrival_time();
   				incompletedJobs.add(j);
   				System.out.println("Un-sufficient budget.");
   				if(jobs.getList().size()!=0) {
   				j=jobs.extractMax();
//   				System.out.println("Due to unsufficient budget another job "+j.getJobname()+" "+j.getProject().getName()+" was extracted from the max heap");
//   				for(int i=0;i<jobs.getList().size();i++)
//   	        	{
//   	        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//   	        	}
   				}
   				else{
   					System.out.println("Execution cycle completed");
   					p++;
   					break;
   				}
   			}
   			else    			
   			{
   				
//   				System.out.println("Finally value of runtime - budget becomes "+(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime())));
   				j.getProject().budget=Integer.toString(Integer.parseInt(j.getProject().getBudget())-Integer.parseInt(j.getRuntime()));
//   				System.out.println("Executing: "+j.getJobname()+" PRIORITY= "+j.getPriority()+" &insertion time= "+j.getInsertionTime()+" from: "+j.getProject().getName());
   				System.out.println("Executing: "+j.getJobname()+" from: "+j.getProject().getName());
   				System.out.println("Project: "+j.getProject().getName()+" budget remaining: "+j.getProject().getBudget());
   	    		System.out.println("Execution cycle completed");
   	    		jobsDone++;
   	    		j.cTime=gTime;
//   	    		j.waitingTime=job.completion_time()-job.arrival_time();
   	    		gTime+=Integer.parseInt(j.getRuntime());
   	    		j.user.consumedTime+=Integer.parseInt(j.getRuntime());
   	    		j.user.latestJob=j;
///////////////////   	    		completedJobs.add(j);
   	    		if(!completedJobs.add(j)) {
   	    	 		completedJobs.add(j);
   	    	 		x2++;
   	    			f2.add(completedJobs.get(completedJobs.size()-1));}
//   	    		for(int i=0;i<jobs.getList().size();i++)
//   	        	{
//   	        		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//   	        	}
   	    		p++;
   	    		break;
   			}
   		}
   	}
   	}
   	else
   	{
   		System.out.println("Execution cycle completed");
   	}
    }


    public static void handle_query(String key) {
System.out.println("Querying");
    	
    	int indexComp=-1;
    	int indexInComp=-1;
    	int indexHeap=-1;
    	for(int i=0;i<completedJobs.size();i++)
    	{
    		if((completedJobs.get(i).getJobname()).compareTo(key)==0)
    		{
    			indexComp=i;
    			break;
    		}
    	}
    	if(indexComp!=-1)
    	{
    		System.out.println(key+": COMPLETED");
    	}
    	else
    	{
    		for(int i=0;i<incompletedJobs.size();i++)
        	{
        		if((incompletedJobs.get(i).getJobname()).compareTo(key)==0)
        		{
        			indexInComp=i;
        			break;
        		}
        	}
    		if(indexInComp!=-1)
        	{
        		System.out.println(key+": NOT FINISHED");
        	}
    		else
    		{
    			for(int i=0;i<jobs.getList().size();i++)
            	{
            		if((jobs.getList().get(i).getJobname()).compareTo(key)==0)
            		{
            			indexHeap=i;
            			break;
            		}
            	}
    			if(indexHeap!=-1)
    			{
    				System.out.println(key+": NOT FINISHED");
    			}
    			else
    			{
    				System.out.println(key+": NO SUCH JOB");
    			}
    		}
    	}
    }

    public static void handle_user(String name) {
    	User user=new User(name);
    	
    	System.out.println("Creating user");
    	
    	users.insert(name, user);
    	completedJobsUsers.add(user);
//    	System.out.println("user created: "+(users.search(name)).getValue());
    }

    public static void handle_job(String[] cmd) {
//    	System.out.println("Ashwini");
    	System.out.println("Creating job");
    	Project project;
    	if(projects.search(cmd[2])!=null)
	    {	
//    		System.out.println("ashwini1");
//    		System.out.println("The project exists with the given name");
    		project=projects.search(cmd[2]).getValue();
    		User user;
    		if(users.search(cmd[3])!=null)
    		{
//    			System.out.println("ashwini2");
//    			System.out.println("The user also exists with the given name");
    			user=users.search(cmd[3]).getValue();
//    			System.out.println("ashwini3");
    			Job job=new Job(cmd[1], project,user, cmd[4],gTime );
//    			System.out.println("value of arrival time is "+job.arrival_time());
//    			job.aTime=gTime;
//    			System.out.println("value of arrival time set to "+job.aTime);
    			AllJobs.add(job);
    			jobs.insert(job);
//    			System.out.println("ashwini4");
//    			System.out.println("Job inserted is "+job.getJobname()+" priority= "+job.getPriority()+" insertion time= "+job.getInsertionTime()+" "+job.getProject().getName()+" "+job.getUser().getName());
    		}
    		else
    		{
    			System.out.println("No such user exists: "+cmd[3]);
    		}
	    	
	    	
    	}
    	else
    	{
    		System.out.println("No such project exists. "+cmd[2]);
    		
    	}
    	
//    	for(int i=0;i<jobs.getList().size();i++)
//    	{
//    		System.out.println(i+"th element of the heap array is "+jobs.getList().get(i));
//    	}
//    	System.out.println("jobs size is "+jobs.getList().size());
//    	System.out.println("");
    }

    public static void handle_project (String[] cmd) {
    	System.out.println("Creating project");
    	
    	Project project=new Project(cmd[1], cmd[2], cmd[3]);
    	
    	projects.insert(cmd[1], project);
    	
//    	System.out.println("project inserted is "+projects.search(cmd[1]).getValue().getName());
    }

    public static void execute_a_job() {
    	
    	
    	
    }
}
