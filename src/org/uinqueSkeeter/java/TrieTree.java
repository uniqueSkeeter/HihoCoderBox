package org.uinqueSkeeter.java;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class TrieTree {

	private Node root;
	protected class Node{
		protected boolean wordFlag;
		protected int words;
		protected int prefixes;
		protected Node[] edges;
		Node(){
			wordFlag = false;
			words = 0;
			prefixes = 0;
			//26个字母由下标表示从0到25
			edges = new Node[26];
			for(int i=0;i<edges.length;i++){
				edges[i]=null;
			}
		}
	}
	public TrieTree(){
		root = new Node();
	}
	public List<String> listAllWords(){
		List<String> wordList = new ArrayList<String>();
		Node[] edges = root.edges;
		for(int i=0;i<edges.length;i++){
			if(edges[i] != null){
				String word = ""+(char)('a'+i);
				DFS_Words(wordList,edges[i],word);
			}
		}
		return wordList;
	}
	private void DFS_Words(List wordList,Node node,String word){
		if(node.wordFlag){
			wordList.add(word);
		}
		Node[] edges = node.edges;
		for(int i=0;i<edges.length;i++){
			if(edges[i]!=null){
					String newWord = word + (char)('a'+i);
					DFS_Words(wordList,edges[i],newWord);
			}
		}
	}
	public int countPrefixes(String prefix){
		return countPrefixes(root,prefix);
	}
	private int countPrefixes(Node node,String prefix){
		if(prefix.length() == 0){
			return node.prefixes;
		}
		char c = prefix.charAt(0);
		int index = c-'a';
		if(node.edges[index]==null){
			//没有这个前缀的单词
			return 0;
		}else{
			//在当前节点递归找剩下的前缀
			return countPrefixes(node.edges[index],prefix.substring(1));
		}
	}
	//有点问题
	public int countWords(String word){
		return countWords(root,word);
	}
	//有点问题
	private int countWords(Node node,String word){
		if(word.length() == 0){
			return node.words;
		}
		char c = word.charAt(0);
		int index = c-'a';
		if(node.edges[index]==null){
			//没有这个单词
			return 0;
		}else{
			//在当前节点递归找剩下的部分
			return countPrefixes(node.edges[index],word.substring(1));
		}
	}
	public void addWord(String word){
		addWord(root,word);
	}
	private void addWord(Node node,String word){
		if(word.length()==0){
			node.words++;
			node.wordFlag = true;
		}else{
			node.prefixes++;
			char c = word.charAt(0);
			int index = c-'a';
			if(node.edges[index]==null){
				node.edges[index] = new Node();
			}
			addWord(node.edges[index],word.substring(1));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrieTree trie = new TrieTree(); 
		/*
		trie.addWord("babaab"); 
		trie.addWord("babbbaaaa"); 
		trie.addWord("abba"); 
		trie.addWord("aaaaabaa"); 
		trie.addWord("babaababb"); 
		System.out.println(trie.root.prefixes); 
		System.out.println(trie.root.words); 
		
		List< String> list = trie.listAllWords(); 
		Iterator listiterator = list.listIterator(); 
		while(listiterator.hasNext()) 
		{ 
		    String s = (String)listiterator.next(); 
		    System.out.println(s); 
		} 
		
		int count = trie.countPrefixes("babb"); 
		int count1 = trie.countPrefixes("baabaaa"); 
		int count2 = trie.countPrefixes("bab");
		int count3 = trie.countPrefixes("bb"); 
		int count4 = trie.countPrefixes("bbabbaab"); 
		System.out.println("the count of babb prefixes:"+count); 
		System.out.println("the count of baabaaa prefixes:"+count1); 
		System.out.println("the count of bab prefixes:"+count2);
		System.out.println("the count of bb prefixes:"+count3);
		System.out.println("the count of bbabbaab prefixes:"+count4); 
		*/
		int a,b,count;
		String word,prefix;
		Scanner scan = new Scanner(System.in);
		a = scan.nextInt();
		while(a > 0){
			word = scan.next();
			trie.addWord(word);
			a--;
		}
		b = scan.nextInt();
		while(b > 0){
			prefix = scan.next();
		    count = trie.countPrefixes(prefix);
			System.out.println(count);
		}
		scan.close();
	}

}
