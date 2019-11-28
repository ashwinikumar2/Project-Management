package Trie;

import java.util.ArrayList;

public class Trie<T> implements TrieInterface {
	
	TrieNode<T> Root;
	int max;
	int behindmax;
	int noOfElements=0;
	
	public Trie()
	{
		Root=new TrieNode(null,' ');
		max=0;
		behindmax=0;
	}
	
    @Override
    public boolean delete(String word) 
    {
    	TrieNode<T> current=Root;
    	int breakindex=0;
    	
    	int i;
    	
    	for(i=0;i<word.length();i++)
    	{
    		int x=(int) word.charAt(i);
    		if((((int)current.child[x].key())!=((int)word.charAt(i))))
    		{
    			
    			breakindex=i;
    			return false;
    		}
    		else
    		{	
    			current=current.child[x];
    			breakindex=i;
    		}
    	}
    	

//    	System.out.println("current is "+current);
    	if(breakindex!=(word.length()-1))
    	{
//    		System.out.println((breakindex+1)+"th character in the trie does not match");
    		return false;
    	}
    	
    	else 							//breakindex is the last of the word
    	{
    		if(current.endOfWord==true)
    		{
    			
    			if(current.getValue()==null)				//IF NOT LEAF
    			{
    				
//    				System.out.println("last character Node for the word has non null child");
    			current.endOfWord=false;
    			current.value=null;
    			if(max==word.length())
				{
					max=behindmax;
				}
				return true;
    			}    			
    			else										//IF IT IS LEAF
    			{
//    				System.out.println("last character Node for the word has null child");
    				TrieNode<T> current1=current;
    				TrieNode<T> parent1=current.getParent();
    				int i2=word.length();
    				while(i2!=0)
    				{i2--;
//    					System.out.println("current1 is "+current1);
//    					System.out.println("parent1 is "+parent1);
	    				if(parent1.isEndOfWord()==true)					//parent is end of a word
	    				{
//	    					System.out.println("parent has endOfWord= "+parent1.isEndOfWord());
	    					current1.key=' ';
	    					current1.value=null;
	    					current1=parent1;
	    					if(max==word.length())
	    					{
	    						max=behindmax;
	    					}
	    					return true;
	    				}
	    				else
	    				{
	    					int x=0;
				    		for(int j1=0;j1<256;j1++)
		    				{if(parent1.child[j1]!=null&&parent1.child[j1].key()!=' ')
		    				{
		    					x++;
		    				}
		    				
		    					
		    				}
//				    		System.out.println("value of no. of elements in childa array of parent1 is "+x);
		    				if(x>1)											//parent has more than one child
		    				{
//		    					System.out.println("parent1 has more than one child");
		    					current1.key=' ';
		    					current1.value=null;
		    					current1=parent1;
		    					if(max==word.length())
		    					{
		    						max=behindmax;
		    					}
		    					return true;
		    				}
		    				else											//parent has only one child
		    				{
//		    					System.out.println("parent1 neither has endOfWord=true nor two childs");
		    					current1.key=' ';
		    					current1.value=null;
		    					current1=parent1;
		    					if(parent1.getParent()!=null)
		    					parent1=parent1.getParent();
//		    					System.out.println("current1 now becomes "+current1);
//		    					System.out.println("parent1 now becomes "+parent1);
		    					if(i2==0) {
		    					
		    					if(max==word.length())
		    					{
		    						max=behindmax;
		    					}
		    					return true;
		    					}
		    				}
	    				}
//	    				
    				}return true;
    			}
    		}
    		else
    			return false;
    		
    	}
    }

    @Override
    public TrieNode<T> search(String word) {

    	TrieNode<T> current=Root;
    	int breakindex=0;
    	int i;
    	for(i=0;i<word.length();i++)
    	{
    		int x=(int) word.charAt(i);
    		if(current.child[x]!=null)
    		{
	    		if((((int)current.child[x].key())!=((int)word.charAt(i))))
	    		{
	    			breakindex=i;
	    			return null;
	    		}
	    		
	    		else
	    		{	
	    			current=current.child[x];
	    			breakindex=i;
	    		}
    		}
    		else
    		{
    			return null;
    		}
    	}
    	


    	if(breakindex!=(word.length()-1))
    	{
    		
    		return null;
    	}
    	
    	else 
    	{
    		if(current.endOfWord==true)
    		{
//    			String x1=((Person)(current.getValue())).getValue();
//    			System.out.println("[Name: "+word+", Phone="+x1);
				return current;
    		}
    		else
    			return null;
    	}
    }

    @Override
    public TrieNode<T> startsWith(String prefix) {

    	TrieNode<T> current=Root;
    	int breakindex=0;
    	int i;
    	int x1=(int) prefix.charAt(0);
    	if((Root.child[x1])==null)
    	{
    		return null;
    	}
    	else {
    	for(i=0;i<prefix.length();i++)
    	{
    		int x=(int) prefix.charAt(i);
    		if((((int)current.child[x].key())!=((int)prefix.charAt(i))))
    		{
    			
    			breakindex=i;
    			return null;
    		}
    		else
    		{	
    			current=current.child[x];
    			breakindex=i;
    		}
    	}
    	
    	
    	 
        return current;
    	}
    }
    public void words(TrieNode<T>[] c)
    {
    	for(int i=0;i<c.length;i++)
    	{
    		if(c[i]!=null) 
    		{
	    		if((c[i].getValue())!=null)
	    		{
	    			System.out.println((Person)(c[i].getValue()));
	    		}
	    		else
	    		{
	        		words(c[i].getChild());
	    		}
    		}
    	}
    	
    }
    @Override
    public void printTrie(TrieNode trieNode) {
    	if(trieNode.getValue()==null)
    	{
    	TrieNode<T>[] c=trieNode.getChild();
    	for(int i=0;i<c.length;i++)
    	{
    		if(c[i]!=null) 
    		{
	    		if(c[i].getValue()!=null)
	    		{
	    			System.out.println(((Person)(c[i].getValue())).toString());
	    		}
	    		else
	    		{
	        		words(c[i].getChild());
	    		}
    		}
    	}
    	
    	}
    	else
    		System.out.println(((Person)(trieNode.getValue())).toString());
    }

    @Override
    public boolean insert(String word, Object value) {
    	int size=word.length();
    	if(max<=size) {
    		behindmax=max;
    		max=size;
    		
    	}	
//    	System.out.println("Number of characters in string is "+size);
    	TrieNode<T> behindCurrent=Root;
    	TrieNode<T> current=Root;
    	

	    	int indexOfInitial=(int) word.charAt(0);
	    	if(Root.child[indexOfInitial]==null)
	    	{
//	    		System.out.println("Root has array which does has null node at index of first character of given word");
	    		for(int j=0;j<word.length();j++)
	    		{
//	    			System.out.println("character under consideration is "+word.charAt(j));
//	    			System.out.println("current was "+current.key());
//    	    		System.out.println("behindCurrent was "+behindCurrent.key());
	    			int indexOfJthChar=(int) word.charAt(j);
	    			if(j==0)
	    			{
	    				Root.child[indexOfJthChar]=new TrieNode(Root, word.charAt(0));
	    	    		noOfElements++;
//	    	    		System.out.println("Given character is "+(j+1)+"th character of the given word");
	    	    		if(word.length()==1) {
	    	    			if(Root.child[indexOfJthChar].isEndOfWord()==true)
	    						return false;
	    		    		Root.child[indexOfJthChar].endOfWord=true;
//	    		    		String x3="[Name: "+word+", Phone="+((Person)value).getValue();
	    		    		Root.child[indexOfJthChar].value=(T)value;
//	    		    		System.out.println("first character of given word i.e "+Root.child[indexOfJthChar]+" isEndOfWord="+Root.child[indexOfJthChar].isEndOfWord());
	    		    		return true;
	    	    		}
	    		    	else {
	    		    			Root.child[indexOfJthChar].endOfWord=false;
//	    		    			System.out.println("first character of given word i.e "+Root.child[indexOfJthChar]+" isEndOfWord=FALSE");
	    		    	}
	    	    		Root.child[indexOfJthChar].parent=null;
	    	    		
	    	    		behindCurrent=Root.child[indexOfJthChar];
	    	    		current=Root.child[indexOfJthChar];
//	    	    		System.out.println("current now becomes "+current.key());
//	    	    		System.out.println("behindCurrent now becomes "+behindCurrent.key());
	    	    		
	    			}
	    		
		    		else if(j!=(word.length()-1))							//NOT THE LAST CHARACTER
		    		{
			    		current.child[indexOfJthChar]=new TrieNode(current, word.charAt(j));
			    		current.child[indexOfJthChar].endOfWord=false;
			    		if(Root.child[indexOfJthChar]!=null)
			    		Root.child[indexOfJthChar].parent=current;
//			    		System.out.println("ELEMENT INSERTED IS "+ current.child[indexOfJthChar].key()+" as a child of "+current.key()+" with value as "+current.child[indexOfJthChar].getValue()+" with isEndOfWord= "+current.child[indexOfJthChar].isEndOfWord());
			    		
			    		
			    		behindCurrent=current;
			    		current=current.child[indexOfJthChar];
//			    		System.out.println("current now becomes "+current.key());
//	    	    		System.out.println("behindCurrent now becomes "+behindCurrent.key());
		    			
		    		}
	    			
		    		else													//THE LAST CHARACTER
			    	{
		    			if(current.child[indexOfJthChar]!=null){
		    			if(current.child[indexOfJthChar].isEndOfWord()==true)
    						return false;}
			    		current.child[indexOfJthChar]=new TrieNode(current, word.charAt(j));
			    		current.child[indexOfJthChar].endOfWord=true;
			    		current.child[indexOfJthChar].parent=current;
			    		current.child[indexOfJthChar].value=(T) value;
//			    		System.out.println("value is added at the end of the word");
//			    		System.out.println("value inseted at last node is "+(String)value);
//			    		System.out.println("ELEMENT INSERTED IS "+ current.child[indexOfJthChar].key()+" with value as "+current.child[indexOfJthChar].getValue()+" with isEndOfWord= "+current.child[indexOfJthChar].isEndOfWord()+" as a child of "+current.key());
			    		behindCurrent=current;
			    		current=current.child[indexOfJthChar];
//			    		System.out.println("current now becomes "+current.key());
//	    	    		System.out.println("behindCurrent now becomes "+behindCurrent.key());
	    	    		return true;
			    	}
	    			
//	    		System.out.println("");
	    		}
	    	}
	    	else
	    	{
	    		for(int j=0;j<word.length();j++)
	    		{
//	    			System.out.println("character under consideration is "+word.charAt(j));
//	    			System.out.println("current was "+current.key());
//    	    		System.out.println("behindCurrent was "+behindCurrent.key());
	    			int indexOfJthChar=(int) word.charAt(j);
//	    			System.out.println("Character is "+word.charAt(j)+" and current is "+current.key());
//	    			if(j == word.length()-1) 
//	    				current.value=(T)value;
	    			if(current.child[indexOfJthChar]==null)
	    			{
	    				current.child[indexOfJthChar]=new TrieNode<T>(current, word.charAt(j));
	    				current.child[indexOfJthChar].parent=current; 
//			    		System.out.println("ELEMENT INSERTED IS "+current.child[indexOfJthChar].key()+" with value as "+current.child[indexOfJthChar].getValue() +" as a child of "+current.child[indexOfJthChar].getParent());
	    				int x1=0;
			    		for(int j1=0;j1<256;j1++)
	    				{if(current.child[j1]!=null)
	    				{
	    					x1++;
	    				}
	    				
	    					
	    				}
//			    		System.out.println("value of no. of elements in childa array of current is "+x1);
	    				
	    				if(j==(word.length()-1))
	    				{
	    					if(current.child[indexOfJthChar].isEndOfWord()==true)
	    						return false;
			    		current.child[indexOfJthChar].endOfWord=true;
			    		current.child[indexOfJthChar].value=(T)value;
//			    		System.out.println("");
//			    		System.out.println((j+1)+"th character of given word is inserted i.e "+current.child[indexOfJthChar]+ " "+current.child[indexOfJthChar].value+" isEndOfWord="+current.child[indexOfJthChar].isEndOfWord());
			    		return true;
//			    		System.out.println("value is added at the end of the word");
			    		
	    				}
			    		else {
//			    			System.out.println("");
			    			current.child[indexOfJthChar].endOfWord=false;
			    			
			    			
//			    			System.out.println((j+1)+"th character of given word is inserted i.e "+current.child[indexOfJthChar]+ " "+current.child[indexOfJthChar].value+" isEndOfWord="+current.child[indexOfJthChar].isEndOfWord());
			    		}
	    				
			    		behindCurrent=current;
			    		current=current.child[indexOfJthChar];
//			    		System.out.println("current now becomes "+current.key());
//	    	    		System.out.println("behindCurrent now becomes "+behindCurrent.key());
	    			}
	    			
	    			else 
	    			{
		    				if(j==word.length()-1)
		    				{
		    					current.child[indexOfJthChar].parent=current;
		    					if(current.child[indexOfJthChar].isEndOfWord()==true)
		    						return false;
		    					
					    		current.child[indexOfJthChar].endOfWord=true;
					    		current.child[indexOfJthChar].value=(T)value;
//					    		System.out.println((j+1)+"th character of given word is inserted i.e "+current.child[indexOfJthChar]+ " "+current.child[indexOfJthChar].value+" isEndOfWord="+current.child[indexOfJthChar].isEndOfWord());
//					    		System.out.println("");
					    		return true;
		    				}
		    				current.child[indexOfJthChar].parent=current;
		    				behindCurrent=current;
				    		current=current.child[indexOfJthChar];
//				    		System.out.println("current now becomes "+current.key());
//		    	    		System.out.println("behindCurrent now becomes "+behindCurrent.key());
		    			
	    			}
	    		}	
	    		
//	    		System.out.println("");
	    	}
	    	
//    	}
    	return true;
    }

    public void levelPrint(TrieNode<T> Root1,int level, ArrayList<Character> arr)
    {
//    	
    	TrieNode<T>[] a=null;
    	if(level==1)
    	{
    		if(Root1.getValue()==null)
        	{a=Root1.getChild();
    		for(int i=0;i<a.length;i++)
    		{
    			if(a[i]!=null && ((Character.toString(a[i].key())).compareTo((Character.toString(' ')))!=0))
    			{
    			arr.add(a[i].key());
//    			System.out.println("element added to array list is "+a[i].key()+" verified by "+arr.get(arr.size()-1));
    			}
    		}
        	}
    	}
    	else
    	{
	    	if(Root1.getChild()!=null)
	    	{
	    		
	    		TrieNode<T> current=null;
	    		a=Root1.getChild();
	    		
	    		for(int i=0;i<a.length;i++)
	    		{
	    			if(a[i]!=null)
	    			{
	    				current=a[i];
		    			if(current.getValue()==null)
		    			{
		    				
		    				levelPrint(current, level-1,arr);
		    			}
	    			}
	    		}
	    	}
	    	else
	    	{
	    		levelPrint(Root1, level-1,arr);
	    	}
    	}
    }
    @Override
    public void printLevel(int level) {
    	ArrayList<Character> arr=new ArrayList<Character>();
    	
    	levelPrint(Root, level, arr);
    	
    	int size = arr.size();  
        Character x = null;  
         for(int i1=0; i1 < size; i1++)
         {  
                 for(int j=1; j < (size-i1); j++)
                 {  
                	 if(arr.get(j-1) > arr.get(j))
                     {  
                		 x = arr.get(j-1);  
                         arr.set(j-1, arr.get(j));  
                         arr.set(j, x);  
                     }  
                          
                 }  
         }  
    	
    	
    	for(int i=0;i<arr.size();i++)
    	{
    		System.out.print(arr.get(i));
    		if(i!=arr.size()-1)
    			System.out.print(",");
    	}
    	System.out.println("");
    }

    @Override
    public void print() {
    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	
    	for(int i=1;i<=max;i++)
    	{
    		System.out.print("Level "+ i + ": "); 
    		printLevel(i);
    	}
    	System.out.println("Level "+(max+1)+": ");
    	System.out.println("-------------");
    	
    	

    }
}

