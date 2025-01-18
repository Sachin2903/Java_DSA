import java.util.*;

public class JavaBasic {
    static class Node {
        Node children[] = new Node[26];
        boolean eow = false;
        int freq;

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            freq = 1;
        }
    }

    public static Node root = new Node();

    public static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = (int) (word.charAt(i) - 'a');
            if (curr.children[index] == null) {
                curr.children[index] = new Node();

            } else {
                curr.children[index].freq++;
            }

            curr = curr.children[index];
        }
        curr.eow = true;
    }

    public static void findPrefix(Node root, String ans) {
        if (root == null) {
            return;
        }
        if (root.freq == 1) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                findPrefix(root.children[i], ans + (char) (i + 'a'));
            }
        }
    }

    public static boolean startWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = (int) (prefix.charAt(i) - 'a');
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }

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

    public static boolean wordBreak(String key) {
        if (key.length() == 0) {
            return true;
        }

        for (int i = 1; i <= key.length(); i++) {
            if (search(key.substring(0, i)) && wordBreak(key.substring(i))) {
                return false;
            }
        }
        return false;
    }

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
        return count + 1;
    }

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

    public static void main(String[] args) {
        String words[] = { "a", "banana", "app", "appl", "ap", "apply", "applr" };
        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }longestWOrd(root,new StringBuilder(""));
        System.out.println(ans);

    }
}
