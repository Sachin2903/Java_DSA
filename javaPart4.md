#   Trie Data Structure ( Prefix tree / retrievel tree )
       
       A
     /   \
    L     M
   / \
  L   S
       \
        O

k-ary tree
> Node can have more than one or two child node 
words[]="the","a","there","their","any","ther"

>> use trie when search a word , prefix or strign 

[alt text](./assests/trie.png)

```java
import java.util.*;
public class JavaBasic {
    static class Node{
        Node children[]=new Node[26];
        boolean eow=false;
        Node(){
             for(int i=0;i<26;i++){
                children[i]=null;
             }
        }
    }
    public static Node root =new Node();
    public static void main(String[] args) {
    }
}
```

## Insert in trie O(L) largest word length
words[] ="the" "a" "there" "their" "any" "thee"

```java
 public static void insert(String word){
        Node curr=root;
        for(int i =0;i<word.length();i++){
            int index=(int)(word.charAt(i)-'a');
            if(curr.children[index]==null){
                 curr.children[index]=new Node();
            }
            curr=curr.children[index];
        }
        curr.eow=true;
 }
 ```

## Search in trie O(L)

![alt text](./assests/searchTrie.png)

key ="thee" key ="thor"

```java
 public static boolean search(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = (int) (word.charAt(i) - 'a');
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.eow;
    }
```

## Word Break Problem

given an input string and a dictionary of words, find out if the input string can be broken into a space-sperated sequence of dictionary words.

words[]=(i,like,sam,samsung,mobile,ice)
key="ilikesamsung"

output : true

```java O(n or key lenght )
public static boolean wordBreak(String key) {
        if(key.length()==0){
            return true;
        }

        for (int i = 1; i <= key.length(); i++) {
            if(search( key.substring(0, i))&&wordBreak(key.substring(i))){
                return false;
            } 
        }
        return false;
    }
``` 

## Prefix Problem
FInd Shortest unique prefix for every word in a given list.
assume no word is prefix of another.

arr[]={"zebra","dog","duck","dove"}
ans={"z","dog","du","dov"}

* in class node add frequency =1 option , while creating trie if a node already exist in children increase frequency and continue 
* set freq of root -1 so it not stuck at root
* create a search that stop when node have freq =1 that give shortest prefix

```java
static class Node {
        Node children[] = new Node[26];
        boolean eow = false;
        int freq;
        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            freq=1;
        }
    }

    public static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = (int) (word.charAt(i) - 'a');
            if (curr.children[index] == null) {
                curr.children[index] = new Node();

            }else{
                curr.children[index].freq++;
            }

            curr = curr.children[index];
        }
        curr.eow = true;
    }

public static void findPrefix(Node root, String ans) {
        if(root==null){
            return;
        }
        if(root.freq==1){
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                findPrefix(root.children[i], ans + (char) (i + 'a'));
            }
        }
    }
```

## StartsWith Problem O(L prefix string length)
Create a function boolean startsWith (String prefix) for a trie. Return true if there is a previouly inserted string word that has the prefix, and false otherwise.

words[] = {"apple","app","mongo","man","woman"}
prefix="app" output:true
prefix="moon" output:false

```java
    public static boolean startWith(String prefix){
        Node curr=root;
        for(int i=0;i<prefix.length();i++){
            int index = (int) (prefix.charAt(i) - 'a');
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }
```

## Count Unique Substrings

Given a string of length n of lowercase alphabet characters, we need to count total number of distinct substrings of this string.

str="ababa"
ans=10

```java

  public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countNodes(root.children[i]);
            }
        }
        return count+1 ;
    }


String str = "ababa";
        for (int i = 0; i < str.length(); i++) {
            String suffix = str.substring(i);
            insert(suffix);
        }
```

## LOngest WOrd with all orefixes
FInd the longest string in words such that every prefix of it is also in words
words=["a","banana","app","appl","ap","apply","apple"]

ans="apple  


```java
  public static String ans = "";

public static void longestWOrd(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow) {
                char ch = (char) (i + 'a');
                temp.append(ch);
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWOrd(root.children[i], temp);
                temp.deleteCharAt(temp.length());
            }
        }
    }

        String words[] = { "a", "banana", "app", "appl", "ap", "apply", "applr" };
        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }longestWOrd(root,new StringBuilder(""));
        System.out.println(ans);
```


# Graph













