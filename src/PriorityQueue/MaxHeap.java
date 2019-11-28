package PriorityQueue;
import ProjectManagement.Job;
import java.util.ArrayList;
public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {

	public ArrayList<T> heap;
	int s;
	
	public MaxHeap() {
		heap = new ArrayList<T>();
		s=0;
	}

    @Override
    public void insert(T element) {
    	s++;
//    		System.out.println(heap.size());
//        System.out.println(((Job)element).getJobname());
        
    	
    	heap.add(element);
    	
    	if(element instanceof Student)
    	{
    		((Student)(heap.get(heap.size()-1))).time=s;
    	}
    	else if(element instanceof Job)
    	{
    		((Job)(heap.get(heap.size()-1))).time=s;
    	}
    	int i=heap.size()-1;
    	int parentIndex;
    	if(((i-1)/2)>=0)
    	 parentIndex=(i-1)/2;
    	
    	else 
    	{
    		parentIndex=0;
    	}
    	if(heap.size()>1) 
    	{
//    		System.out.println("Parent intially is "+heap.get(parentIndex)+" with parentindex= "+parentIndex);
//    		System.out.println("Child initially is "+heap.get(i)+" with index as "+i);
//    		System.out.println((heap.get(parentIndex)).compareTo(heap.get(i)));
//    		System.out.println("priority of parent = "+((Job)(heap.get(parentIndex))).getPriority());
//    		System.out.println("priority of child = "+((Job)(heap.get(i))).getPriority());
    		
    	while((heap.get(parentIndex)).compareTo(heap.get(i))<0  && i!=0)
    	{
//    		System.out.println("parent is less than child");
//    		System.out.println("Parent is "+heap.get(parentIndex)+" with parentindex= "+parentIndex);
//    		System.out.println("Child is "+heap.get(i)+" with index as "+i);
//    		System.out.println("parent has less priority in than child");
//    		if(heap.get(parentIndex)==heap.get(i))
//    		{
//    			break;
//    		}
    		T h = heap.get(parentIndex);
    		heap.set(parentIndex, heap.get(i));
    		
//    		heap[parentIndex]=heap[i];
    		heap.set(i, h);
    		
//    		heap[i]=h;
    		
    		i=parentIndex;
    		
    		if(((i-1)/2)>=0 ) 
    		{
    	    	 parentIndex=(i-1)/2;
    		}
	    	else 
	    	{
    	    		parentIndex=i;
	    		break;
	    	}
    		
    		
//    		System.out.println("Parent becomes "+heap.get(parentIndex)+" with parentindex= "+parentIndex);
//    		System.out.println("Child becomes "+heap.get(i)+" with index as "+i);
    	}
//    	for(int i1=0;i1<heap.size();i1++)
//		{
//			System.out.println(i1+"th element of the heap array is "+heap.get(i1));
//			
//		}
    	}
    }
//    public Student extractS(int index)
//    {
//    	int i=index;
//    	
//    	Student student=(Student)heap.get(i);
//    	Student x=student;
//    	while (true)
//    	{
//    	if(2*i+2<heap.size())
//    	{
//	    	if(student.compareTo((Student)heap.get(2*i+1))==0 && student.compareTo((Student)heap.get(2*i+1))==0)
//	    	{
//	    		if((x.getInsertionTime()>((Student)(heap.get(2*i+1))).getInsertionTime()))
//    			{
//    				x=(Student)(heap.get(2*i+1));
//    			}
//	    		if((x.getInsertionTime()>((Student)(heap.get(2*i+2))).getInsertionTime()))
//	    		{
//	    			x=(Student)(heap.get(2*i+2));
//	    		}
//	    	}
//    	}
//    	
//    	else if(2*i+1<heap.size())
//    	{
//    		if(student.compareTo((Student)heap.get(2*i+1))==0)
//    		{
//    			if((x.getInsertionTime()>((Student)(heap.get(2*i+1))).getInsertionTime()))
//    			{
//    				x=(Student)(heap.get(2*i+1));
//    				break;
//    			}
//    			
//    		}
//    	}
//    	}
//    	return null;
//    }
    public ArrayList<T> getList()
    {
    	return heap;
    }
    @Override
    public T extractMax() 
    {
    	
    	if(heap.size()!=0)
    	{
//    		T obj1=heap.get(0);
    		int index=0;
//    		if(heap.get(0) instanceof Job) 
//    		{
//				for(int i=0;i<heap.size();i++)
//				{
//					if(((Job)(heap.get(i))).getPriority().compareTo(((Job)heap.get(index)).getPriority())==0)
//						
//					{
//						if(((Job)heap.get(i)).getInsertionTime()<((Job)heap.get(index)).getInsertionTime())
//						{
////							T obj2=heap.get(i);
////							heap.set(i, heap.get(0));
////							heap.set(0, obj2);
//							index=i;
////							index1=i;
//							
//						}
//					}
//				}
//				
//				}
				
				
    				for(int i=0;i<heap.size();i++)
    				{
    					if(heap.get(i).compareTo(heap.get(index))>0)
    						{
//    						System.out.println(heap.get(i)+" has same priority & has less insertion time as that of "+heap.get(index));
//    							T obj2=heap.get(i);
//    							heap.set(i, heap.get(0));
//    							heap.set(0, obj2);
    							index=i;
//    							index1=i;
    						}
    					
    				}
    				
    			
				T obj=heap.get(index);
				heap.remove(index);
				return obj;
    		
    	}
    	else
    	{
//    		System.out.println("//////////////////////////////////////");
			return null;
    	}
    	

    }
    }