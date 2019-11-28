
package RedBlack;
import Trie.Person;
import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;
public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> 
{

	T key;
	RedBlackNode<T , E> Left,Right,Parent;
	List<E> list;
	String colour;
	
	RedBlackNode(T Key)
	{
		Parent=null;
		Left=null;
		Right=null;
		this.key=key;
		colour="red";
		list=new ArrayList<E>();
	}
	
	public T getKey()
	{
		return key;
	}
	public RedBlackNode<T, E> left()
	{
		return Left;
	}
	public RedBlackNode<T, E> right()
	{
		return Right;
	}
	public RedBlackNode<T, E> parent()
	{
		return Parent;
	}
	
	public String colour()
	{
		return colour;
	}
	
	
	public void add(E value)
	{
		list.add(value);
	}
	
    @Override
    public E getValue() 
    {
    	return list.get(0);
    }

    @Override
    public List<E> getValues() {
        return list;
    }
}
