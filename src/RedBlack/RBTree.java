package RedBlack;
import Trie.Person;
import java.util.List;

public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	RedBlackNode<T, E> Root;
	int size;
	public RBTree()
	{
		Root=new RedBlackNode<T,E>(null);
	
		size=0;
	}
	
	public void balance(RedBlackNode<T, E> c)
	{
		RedBlackNode<T,E > current=c;
		
		while(current!=Root && current.parent()!=Root && current.parent()!=null )
		{
//			System.out.println("current is "+current.getKey());
//			System.out.println("current's parent is not null");
			if((current.parent().colour()).compareTo(current.colour())==0 && (current.colour()).compareTo("red")==0)
			{
//				System.out.println("current's parent is also red");
				if(current.parent().parent()!=null)
				{
//					System.out.println("current's grandparent exists");
					if(current.parent().parent().left()!=null)
					{
						
						if(current.parent().parent().left()==current.parent())			//LL,RL TYPE
						{
//							System.out.println("current's parent is left child of its parent");			//LL,LR
							if(current.parent().parent().right()!=null)
							{
//								System.out.println("grandparent has right child");
								if((current.parent().parent().right().colour()).compareTo("red")==0)
								{
//									System.out.println("uncle of current is red");
									current.Parent.colour="black";
									current.Parent.Parent.colour="red";
									current.Parent.Parent.Right.colour="black";
								}
								else
								{
//									System.out.println("uncle of current is black");
									if(current.parent().left()!=null)
									{
										if(current.parent().left()==current)		//LL 
										{
//											System.out.println("current is left child of its parent");
											RotateLL(current.parent().parent(), current.parent(), current);
											
										}
										else										//LR
										{
											RotateLR(current.parent().parent(), current.parent(), current);
											
										}
									}
									else													//LR
									{
//										System.out.println("current's parent has only right child which is itself current");
										
										RotateLR(current.parent().parent(), current.parent(), current);	
									}
									
									
								}
							}
							
							else
							{
//								System.out.println("grandparent has only left child ");
								
									if(current.parent().left()==current)		//LL 
									{
//										System.out.println("current is left child of its parent");
										RotateLL(current.parent().parent(), current.parent(), current);
										
									}
									else										//LR
									{
										RotateLR(current.parent().parent(), current.parent(), current);
										
									}
								
								
							}
						}
						else if(current.parent().parent().right()!=null)					//LR,RR TYPE
						{
//							System.out.println("current's parent is right child of its parent");
							
							if(current.parent().parent().left().colour().compareTo("red")==0)
							{
//								System.out.println("current has left uncle which is red");
							current.Parent.colour="black";
							current.Parent.Parent.colour="red";
							current.Parent.Parent.Left.colour="black";
							}
							else
							{
//								System.out.println("current has left uncle which is black");
								
								if(current.parent().left()==current)		//RL 
								{
//									System.out.println("current is left child of its parent");
									RotateRL(current.parent().parent(), current.parent(), current);
									
								}
								else										//RR
								{
									RotateRR(current.parent().parent(), current.parent(), current);
									
								}
								
							}
						}
					}
					else 												//RR,RL TYPE
					{
//						System.out.println("current's grandparent has only right child which is current's parent");
						if(current.parent().left()==current)		//RL 
						{
//							System.out.println("current is left child of its parent");
							RotateRL(current.parent().parent(), current.parent(), current);
							
						}
						else										//RR
						{
							RotateRR(current.parent().parent(), current.parent(), current);
							
						}
					}
				}
				else
				current.Parent.colour="black";
//				System.out.println("finally current is "+current.getKey()+ " "+((Person)(current.getValue())).getValue()+" "+current.colour());
				
			}
			if(current!=Root)
			current=current.parent();	
		}
	}
	
	public RedBlackNode<T,E > RotateLL(RedBlackNode<T,E> a, RedBlackNode<T, E> b,RedBlackNode<T,E> c )
	{
		if(a.parent()!=null) 
		{
		RedBlackNode<T, E> parentOfA=a.parent();
		T key1= b.getKey();
		
		RedBlackNode<T,E > B=new RedBlackNode<T,E>(key1);
		B.list=b.list;
		B.Parent=b.parent();
		B.colour=b.colour();
		
		if(b.left()!=null)
		B.Left=b.left();
		
		if(b.right()!=null)
		B.Right=b.right();
		
		b.Parent=parentOfA;
		if(a.parent().left()!=null)
		{
			if(a.parent().left()==a)
				parentOfA.Left=b;
			else
				parentOfA.Right=b;
		}
		
		
		else
			parentOfA.Right=b;
		
		b.Parent=parentOfA;
		RedBlackNode<T,E> Br;
		
		if(B.right()!=null) 
		{
		Br=B.right();
		a.Left=Br;
		}
		else
			a.Left=null;
		
		b.Right=a;
		a.Parent=b;
		
		b.colour="red";
		a.colour="black";
		c.colour="black";
							
		}
		
		else
		{
			T key1=(T) b.getKey();
			
			RedBlackNode<T,E > B=new RedBlackNode<T,E>(key1);
			B.list=b.list;
			B.Parent=b.parent();
			
			if(b.left()!=null)
				B.Left=b.left();
			
			if(b.right()!=null)
				B.Right=b.right();
			
			b.Parent=null;
			b.colour="black";
			if(B.right()!=null)
			{
			RedBlackNode<T,E > Br=B.right();
			a.Left=Br;
			
			}
			else
				a.Left=null;
			b.Right=a;
			a.Parent=b;
			
			
			a.colour="black";
			c.colour="black";
			
			Root=b;
		}
		
		return b;
	}
	
	public RedBlackNode<T,E> RotateLR(RedBlackNode<T,E > a, RedBlackNode<T,E > b, RedBlackNode<T,E> c)
	{
//		System.out.println("root of subtree was "+a.getKey()+" with coulour as "+a.colour());
//		System.out.println("left of subtree was "+a.left().getKey()+" with coulour as "+a.left().colour());
//		System.out.println("right of left of root of subtree was "+a.left().right().getKey()+" with coulour as "+a.left().right().colour());
		RedBlackNode<T,E> A=new RedBlackNode<T,E>(a.getKey());
		A.list=a.getValues();
		if(a.parent()!=null)
		A.Parent=a.parent();
		
		A.colour=a.colour();
		
		if(a.left()!=null)
		A.Left=a.left();
		if(a.right()!=null)
		A.Right=a.right();
		
		
		
		RedBlackNode<T,E> C=new RedBlackNode<T,E>(c.getKey());
		C.list=c.getValues();
		if(c.parent()!=null)
		C.Parent=c.parent();
		C.colour=c.colour();
		
		if(c.left()!=null)
		C.Left=c.left();
		if(c.right()!=null)
		C.Right=c.right();
		
		
		
		RedBlackNode<T,E> B=new RedBlackNode<T,E>(b.getKey());
		B.list=b.getValues();
		if(b.parent()!=null)
		B.Parent=b.parent();
		B.colour=b.colour();
		
		if(b.left()!=null)
		B.Left=b.left();
		if(b.right()!=null)
		B.Right=b.right();
		
		
		if(a.parent()!=null)
		{
			
				
				if(a.parent().left()!=null)
				{
					if(a.parent().left()==a)
					{
					a.Parent.Left=c;
					}
					else
					{
						a.Parent.Right=c;
					}
				}
				else {
					a.Parent.Right=c;
				}
				c.Parent=A.parent();
				c.Right=a;
				a.Parent=c;
				if(C.right()!=null)
				a.Left=C.right();
				else
					a.Left=null;
				if(A.right()!=null)
				a.Right=A.right();
				c.Left=b;
				b.Parent=c;
				if(C.left()!=null)
				b.Right=C.left();
				else
					b.Right=null;
				if(B.left()!=null)
				b.Left=B.left();
				c.colour="red";
				a.colour="black";
				b.colour="black";
				
			
		}
		else
		{
			
			c.Parent=null;
			c.Right=a;
			a.Parent=c;
			if(C.right()!=null)
			a.Left=C.right();
			else
				a.Left=null;
			if(A.right()!=null)
			a.Right=A.right();
			c.Left=b;
			b.Parent=c;
			if(B.left()!=null)
				b.Left=B.left();
			if(C.left()!=null)
			b.Right=C.left();
			else
				b.Right=null;
			
			c.colour="black";
            
			a.colour="black";
			b.colour="black";
			
			Root=c;
			
		}
		
		
//		System.out.println("root of subtree now becomes "+c.getKey()+" with coulour as "+c.colour());
//		System.out.println("left of subtree now becomes "+c.left().getKey()+" with coulour as "+c.left().colour());
//		System.out.println("right of subtree now becomes "+c.right().getKey()+" with coulour as "+c.right().colour());
		return c;
	}
	
	public RedBlackNode<T,E > RotateRR(RedBlackNode<T,E> a, RedBlackNode<T,E > b, RedBlackNode<T,E> c)
	{
//		System.out.println("root of subtree was "+a.getKey()+" with coulour as "+a.colour());
//		System.out.println("right of subtree was "+a.right().getKey()+" with coulour as "+a.right().colour());
//		System.out.println("right of right of root of subtree was "+a.right().right().getKey()+" with coulour as "+a.right().right().colour());
		
		if(a.parent()!=null) 
		{
		RedBlackNode<T, E> parentOfA=a.parent();
		T key1=b.getKey();
		
		RedBlackNode<T,E > B=new RedBlackNode<T,E>(key1);
		B.key=key1;
		B.list=b.list;
		B.Parent=b.parent();
		B.colour=b.colour();
		
		if(b.left()!=null)
		B.Left=b.left();
		
		if(b.right()!=null)
		B.Right=b.right();
		
		b.Parent=parentOfA;
		if(a.parent().left()!=null)
		{
			if(a.parent().left()==a)
				parentOfA.Left=b;
			else
				parentOfA.Right=b;
		}
		
		
		else
			parentOfA.Right=b;
			
		b.Parent=parentOfA;
		RedBlackNode<T,E> Bl;
		
		if(B.left()!=null) 
		{
			Bl=B.left();
			a.Right=Bl;
		}
		else
			a.Right=null;
		b.Left=a;
		a.Parent=b;
		
		b.colour="red";
		a.colour="black";
		c.colour="black";
							
		}
		
		else
		{
			T key1=b.getKey();
			
			RedBlackNode<T,E > B=new RedBlackNode<T,E>(key1);
			B.list=b.list;
			B.key=key1;
			B.Parent=b.parent();
			
			if(b.left()!=null)
				B.Left=b.left();
			
			if(b.right()!=null)
				B.Right=b.right();
			
			b.Parent=null;
			b.colour="black";
			if(B.left()!=null)
			{
			RedBlackNode<T,E > Bl=B.left();
			a.Right=Bl;
			
			}
			else
				a.Right=null;
			b.Left=a;
			a.Parent=b;
			
			
			a.colour="black";
			c.colour="black";
			
			Root=b;
		}
//		System.out.println("root of subtree now becomes "+b.getKey()+" "+((Person)(b.getValue())).getValue()+" "+b.colour());
//		System.out.println("left of subtree now becomes "+b.left().getKey()+" "+((Person)(b.left().getValue())).getValue()+" "+b.left().colour());
//		System.out.println("right of subtree now becomes "+b.right().getKey()+" "+((Person)(b.right().getValue())).getValue()+" "+b.right().colour());
		
		return b;
		
	}
	
	public RedBlackNode<T,E > RotateRL(RedBlackNode<T,E > a, RedBlackNode<T,E > b, RedBlackNode<T,E > c)
	{
//		System.out.println("root of subtree was "+a.getKey()+" with coulour as "+a.colour());
//		System.out.println("right of subtree was "+a.right().getKey()+" with coulour as "+a.right().colour());
//		System.out.println("left of right of root of subtree was "+a.right().left().getKey()+" with coulour as "+a.right().left().colour());
		RedBlackNode<T,E> A=new RedBlackNode<T,E>(a.getKey());
		A.list=a.getValues();
		if(a.parent()!=null)
		A.Parent=a.parent();
		
		A.colour=a.colour();
		
		if(a.left()!=null)
		A.Left=a.left();
		if(a.right()!=null)
		A.Right=a.right();
		
		
		
		RedBlackNode<T,E> C=new RedBlackNode<T,E>(c.getKey());
		C.list=c.getValues();
		if(c.parent()!=null)
		C.Parent=c.parent();
		C.colour=c.colour();
		
		if(c.left()!=null)
		C.Left=c.left();
		if(c.right()!=null)
		C.Right=c.right();
		
		
		
		RedBlackNode<T,E> B=new RedBlackNode<T,E>(b.getKey());
		B.list=b.getValues();
		if(b.parent()!=null)
		B.Parent=b.parent();
		B.colour=b.colour();
		
		if(b.left()!=null)
		B.Left=b.left();
		if(b.right()!=null)
		B.Right=b.right();
		
		
		if(a.parent()!=null)
		{
			
				
				if(a.parent().left()!=null)
				{
					if(a.parent().left()==a)
					{
					a.Parent.Left=c;
					}
					else
					{
						a.Parent.Right=c;
					}
				}
				else {
					a.Parent.Right=c;
				}
				c.Parent=A.parent();
				c.Left=a;
				a.Parent=c;
				if(C.right()!=null)
				b.Left=C.right();
				else
					b.Left=null;
				if(A.left()!=null)
				a.Left=A.left();
				c.Right=b;
				b.Parent=c;
				if(C.left()!=null)
				a.Right=C.left();
				else
					a.Right=null;
				if(B.right()!=null)
				b.Right=B.right();
				c.colour="red";
				a.colour="black";
				b.colour="black";
				
			
		}
		else
		{
			
			c.Parent=null;
			c.Left=a;
			a.Parent=c;
			if(C.left()!=null)
			a.Right=C.left();
			else
				b.Left=null;
			if(A.left()!=null)
			a.Left=A.left();
			c.Right=b;
			b.Parent=c;
			if(C.right()!=null)
			b.Left=C.right();
			else
				a.Right=null;
			if(B.right()!=null)
			b.Right=B.right();
			c.colour="black";
            
			a.colour="black";
			b.colour="black";
			
			Root=c;
			
		}
		
		
//		System.out.println("root of subtree now becomes "+c.getKey()+" with coulour as "+c.colour());
//		System.out.println("left of subtree now becomes "+c.left().getKey()+" with coulour as "+c.left().colour());
//		System.out.println("right of subtree now becomes "+c.right().getKey()+" with coulour as "+c.right().colour());
		return c;
	}
	
    @Override
    public void insert(T key, E value) 
    {
    	RedBlackNode<T,E > current=Root;

		RedBlackNode<T,E > insertedNode;
    	
    	if(size==0)
    	{
    		Root.key=key;
    		insertedNode=Root;
    		Root.add(value);
    		size++;
    		Root.colour="black";
    	}
    	else 
    	{
    		RedBlackNode<T,E > n=new RedBlackNode<T,E>(key);
//    	    System.out.println("value is "+((Person)value).getName());
    	    if(value==null)
    		System.out.println("value is null");
    	    n.list.add(value);
    		
    		while(true) 
    		{
//    			System.out.println("current node is "+current.getKey()+" with value as "+((Person)(current.getValue())).getValue()+" and its colour is "+current.colour);
//    			if(key==current.key)
	    		if(((String)key).compareTo((String)current.key)==0)
	    		{
//	    			System.out.println("entered where key is equal to the current node's key");
//	    			System.out.println("value"+((Person)value) .getValue()+"is added to the list of the current node ");
	    			if(!(current.list.contains(value)))
	    			current.add(value);
	    			
	    			insertedNode=current;
	    			break;
	    		}
	    		
	    		else if(((String)key).compareTo((String)current.key)<0)
	    		{
//	    			System.out.println("key is less than current node's key");
	    			if(current.left()==null)
	    			{
//	    				System.out.println("new node is added at the left of the current node");
	    				current.Left=new RedBlackNode<T,E>(key);
	    				current.Left.key=key;
	    				current.Left.add(value);
	    				E value3=current.left().getValue();
	    				
	    				current.Left.Parent=current;
	    				
	    				insertedNode=current.left();
	    				size++;
	    				
	    				if((current.colour()).compareTo("red")==0 )							//current is never root
	    				{
//	    					System.out.println("current's colour is red and node is inserted at left of the current");
	    					if(current.parent()!=null)
	    					{
		    					if(current.Parent.left()!=null)
		    					{
			    					if(current.Parent.left()==current)					//LL TYPE
			    					{
				    					if(current.Parent.right()!=null)
				    					{
				    						if((current.Parent.right().colour()).compareTo("red")==0)
				    						{
				    							if(current.Parent.Parent!=null)
				    							{
					    							current.Parent.Right.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="red";
				    							}
				    							else
				    							{
				    								current.Parent.Right.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="black";
				    							}
				    						}
				    						else
				    						{
				    							RedBlackNode<T,E> x=RotateLL(current.Parent ,current, current.Left);
				    							
				    							balance(x);
				    						}	
				    					}
				    					else
				    					{
				    						RedBlackNode<T,E> x=RotateLL(current.Parent ,current, current.Left);
				    						balance(x);
				    					}
			    					}
		    					}
		    					else if(current.Parent.right()!=null)
		    					{
			    					if(current.Parent.right()==current)							//RL type
			    					{
			    						if(current.Parent.left()!=null)
			    						{
			    							if((current.Parent.left().colour()).compareTo("red")==0)
			    							{
			    								if(current.Parent.Parent!=null)
				    							{
					    							current.Parent.Left.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="red";
				    							}
				    							else
				    							{
				    								current.Parent.Left.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="black";
				    							}
			    								
			    							}
			    								
			    							else
			    							{
			    								RedBlackNode<T,E> x=RotateRL(current.Parent, current, current.Left);
			    								balance(x);
			    							}
			    							
			    						}
			    						
			    						else
			    						{
			    							RedBlackNode<T,E> x=RotateRL(current.Parent, current, current.Left);
			    							balance(x);
			    						}
			    					}
		    					}
	    					}
	    				}
	    				
	    				break;
	    			}
	    			else
	    			{
//	    				System.out.println("Simply traversed left");
	    				current=current.left();
	    			}
	    		}
	    		
	    		else if(((String)key).compareTo((String)current.key)>0)
	    		{
//	    			System.out.println("key is greater than current node's key");
	    			if(current.right()==null)
	    			{
	    				
	    				current.Right=n;
	    				current.Right.key=key;
	    				current.Right.Parent=current;
	    				insertedNode=current.right();
	    				size++;
	    				int l=(current.right().getValues()).size();
//	    				System.out.println("right of current is null and node is inserted as "+current.right().getKey()+" with values size as "+ l +"with its colour as "+current.right().colour());
	    				if((current.colour()).compareTo("red")==0 )
	    				{
//	    					System.out.println("current's colour is red and node is inserted at right of the current");
	    					if(current.parent()!=null)
	    					{
//	    						System.out.println("current's parent is not null");
		    					if(current.Parent.right()!=null)
		    					{
//		    						System.out.println("current's parent has non null right child");
			    					if(current.Parent.right()==current)					//RR TYPE
			    					{
//			    						System.out.println("current is right child of its parent");
			    						if(current.Parent.left()!=null)
				    					{
			    							
				    						if((current.Parent.left().colour()).compareTo("red")==0)
				    						{
//				    							System.out.println("left uncle of inserted node is red");
				    							if(current.Parent.Parent!=null)
				    							{
					    							current.Parent.Left.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="red";
				    							}
				    							else
				    							{
				    								current.Parent.Left.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="black";
				    							}
				    						}
				    						else
				    						{
//				    							System.out.println("left uncle of inserted node is black");
				    							RedBlackNode<T,E> x=RotateRR(current.Parent ,current, current.Left);		    							
				    							balance(x);
				    						}	
				    					}
			    						
			    						else
				    					{
//			    							System.out.println("current's parent does not have left child");
			    							RedBlackNode<T,E> x=RotateRR(current.Parent ,current, current.Right);
			    							balance(x);
				    					}
			    					}	
			    				}
		    					else if(current.Parent.left()!=null)		 										
		    					{
//		    						System.out.println("current's parent has non-null left child ");
		    						if(current.Parent.left()==current)							//LR Type
			    					{
//		    							System.out.println("current is left child of its parent");
			    						if(current.Parent.right()!=null)
			    						{
			    							
			    							if((current.Parent.right().colour()).compareTo("red")==0)
			    							{
//			    								System.out.println("inserted node has right uncle with colour as red");
			    								if(current.Parent.Parent!=null)
				    							{
					    							current.Parent.Right.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="red";
				    							}
				    							else
				    							{
				    								current.Parent.Right.colour="black";
					    							current.colour="black";
					    							current.Parent.colour="black";
				    							}
			    								
			    							}
			    								
			    							else
			    							{
//			    								System.out.println("inserted node has right uncle with colour as black");
			    								RedBlackNode<T,E> x=RotateLR(current.Parent, current, current.Right);
			    								balance(x);
			    							}
			    							
			    						}
			    						
			    						else
			    						{
//			    							System.out.println("current's parent does not have right child");
			    							RedBlackNode<T,E> x=RotateLR(current.Parent, current, current.Right);
			    							balance(x);
			    						}
			    					}
		    					}
	    					}
	    				}
	    				break;
	    			}
	    			else
	    			{
//	    				System.out.println("Simply traversed right");
	    				current=current.right();
	    			}
	    		}
	    		
	    		else {
//	    			System.out.println("affffffff");
	    		}
	    		
    		}
    		
    		
    		
//    		if(insertedNode.parent().colour()=="red")
//    		{
//    			if(insertedNode.parent().left()==insertedNode)
//    			
//    		}
//    		
    	}
    	

//		System.out.println("root of rbtree is "+(String)(Root.getKey())+" with value as "+((Person)(Root.getValue())).getValue()+" and its colour is "+((String)(Root.colour())));
		

//		if(Root.left()!=null) 
//		{
////			System.out.println("left of root is "+Root.left().getKey()+" with value as "+((Person)(Root.left().getValue())).getValue()+" and colour as "+Root.left().colour());
//			if(Root.left().left()!=null)
//			{
//				System.out.println("left of left of root is "+Root.left().left().getKey()+" "+((Person)(Root.left().left().getValue())).getValue()+" "+Root.left().left().colour());
//			}
//			if(Root.left().right()!=null)
//			{
//				System.out.println("right of left of root is "+Root.left().right().getKey()+" "+((Person)(Root.left().right().getValue())).getValue()+" "+Root.left().right().colour());
//			}
//			
//		}	
//		if(Root.right()!=null)
//		{
//			System.out.println("Right of root is "+Root.right().getKey()+" "+((Person)(Root.right().getValue())).getValue()+" "+Root.right().colour());
//			if(Root.right().left()!=null)
//			{
//				System.out.println("left of Right of root is "+Root.right().left().getKey()+" "+((Person)(Root.right().left().getValue())).getValue()+" "+Root.right().left().colour());
//				if(Root.right().left().left()!=null)
//				{
//					System.out.println("left of left of Right of root is "+Root.right().left().left().getKey()+" "+((Person)(Root.right().left().left().getValue())).getValue()+" "+Root.right().left().left().colour());
//				}
//				if(Root.right().left().right()!=null)
//				{
//					System.out.println("right of left of Right of root is "+Root.right().left().right().getKey()+" "+((Person)(Root.right().left().right().getValue())).getValue()+" "+Root.right().left().right().colour());
//				}
//			}
//			if(Root.right().right()!=null)
//			{
//				System.out.println("right of Right of root is "+Root.right().right().getKey()+" "+((Person)(Root.right().right().getValue())).getValue()+" "+Root.right().right().colour());
//				if(Root.right().right().left()!=null)
//				{
//					System.out.println("left of right of Right of root is "+Root.right().right().left().getKey()+" "+((Person)(Root.right().right().left().getValue())).getValue()+" "+Root.right().right().left().colour());
//					
//				}
//				if(Root.right().right().right()!=null)
//				{
//					System.out.println("right of right of Right of root is "+Root.right().right().right().getKey()+" "+((Person)(Root.right().right().right().getValue())).getValue()+" "+Root.right().right().right().colour());
//					if(Root.right().right().right().left()!=null)
//					{
//						System.out.println("left of right of right of Right of root is "+Root.right().right().right().left().getKey()+" "+((Person)(Root.right().right().right().left().getValue())).getValue()+" "+Root.right().right().right().left().colour());
//					}
//					if(Root.right().right().right().right()!=null)
//					{
//						
//						System.out.println("right of right of right of Right of root is "+Root.right().right().right().left().getKey()+" "+((Person)(Root.right().right().right().left().getValue())).getValue()+" "+Root.right().right().right().left().colour());
//					}
//				}
//			
//			}
//		
//		}
//			System.out.println("");
		
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	RedBlackNode<T,E> current=Root;
    	
    	while(true)
    	{
    		if((current.getKey()).compareTo(key)==0)
    		{
    			return current; 
    			
    			
    		}
    		else if((current.getKey()).compareTo(key)<0)
    		{
    			if(current.right()!=null)
    			current=current.right();
    			else
    				return null;
    		}
    		else if((current.getKey()).compareTo(key)>0)
    		{
    			if(current.left()!=null)
        			current=current.left();
        			else
        				return null;
    		}
    		
    		
    	}
        
    }
}