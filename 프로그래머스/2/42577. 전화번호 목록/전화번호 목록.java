// 트라이 느낌
// 1,000,000  * 20 *2

import java.util.*;

class Solution {
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Trie t = new Trie();
        for (String s : phone_book) t.insert(s);
        
        for (String s : phone_book) {   
            if (t.isPrefix(s)) {        
                answer = false;
                break; 
            }
        }
        
        return answer;
    }
    
    class Trie {
        
        TrieNode root;
        
        Trie() {
            this.root = new TrieNode();
        }
        
        void insert(String s) {
            TrieNode cur = this.root;
            
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                cur.child.putIfAbsent(c, new TrieNode());
                cur = cur.child.get(c);
            }
            
            cur.isEnd = true;
        }

        boolean isPrefix(String s) {
            TrieNode cur = this.root;
            
            for (int i = 0; i < s.length()-1; i++) {
                char c = s.charAt(i);    
                if (cur.isEnd) return true;
                cur = cur.child.get(c);
            }
            
            return cur.isEnd;
        }
    }
    
    class TrieNode {
        Map<Character, TrieNode> child;
        boolean isEnd;
        
        TrieNode() {
            this.child = new HashMap();
            this.isEnd = false;
        }
    }
}