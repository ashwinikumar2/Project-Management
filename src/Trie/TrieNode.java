package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
	
	TrieNode<T>[] child;
	
	char key;
	
	T value;
	boolean endOfWord;
	
	TrieNode<T> parent;
	int size;
	TrieNode(TrieNode<T> parent, char key)
	{
		child=new TrieNode[256];
		for(int i=0;i<child.length;i++)
		{
			child[i]=null;
		}
		this.parent=parent;
		this.key=key;
		value=null;
		endOfWord=false;
	}
	
	public TrieNode<T>[] getChild()
	{
		return child;
	}
	public char key()
	{
		return key;
	}
	
	public TrieNode<T> child(char c)
	{
		int x=(int)c;
		return child[x];
	}
	public TrieNode<T> getParent()
	{
		return parent;
	}
	public TrieNode<T> parent(char c1)
	{
		return parent;
	}
    @Override
    public T getValue() {
    	
        return value;
    }
    public boolean isEndOfWord()
    {
    	return endOfWord;
    }
    public String toString()
    {
    	return ("Character= "+key+" value= "+value);
    }
    

}