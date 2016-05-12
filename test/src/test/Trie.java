package test;

import java.util.*;

public class Trie {
	private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!cur.subtree.containsKey(curChar)) {
                cur.subtree.put(curChar, new TrieNode());
            }
            cur = cur.subtree.get(curChar);
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!cur.subtree.containsKey(curChar)) {
                return false;
            }
            cur = cur.subtree.get(curChar);
        }
        System.out.println(cur.subtree.size());
        if (cur.subtree.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char curChar = prefix.charAt(i);
            if (!cur.subtree.containsKey(curChar)) {
                return false;
            }
            cur = cur.subtree.get(curChar);
        }
        return true;
    }
}

class TrieNode {
    // Initialize your data structure here.
    public Map<Character, TrieNode> subtree;
    public TrieNode() {
        subtree = new HashMap<Character, TrieNode>();
    }
}
