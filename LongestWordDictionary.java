/*
The intuition here is to sort the array to get all the words that form the present word 
should be before it in the array. While inserting check if all the characters of the word
 are present except the last character. This we are checking in the new count. We should 
 even checkthe case where even if newcount = 1 the previous word should also be formed with 
 adding only one character. For this we are using isValid field

 // list is NOT SORTED
 // to form the longest word, the smaller words in the list should exist 

 // build a trie, marking isEnd
 // how will i find the longest word? -> first check if isEnd is present for all words in the string
 // traverse the entire Trie to find which word exists, is a gap is there, then return empty? 
 // Trie + DFS + Backtrack and Trie + BFS -> incorrect and exhaustive search 

// soln1: we are using isValid and isWord -> TC: O(nlogn) SC: O(n)
// soln2: HashSet -> TC: O(n log n)  SC: O(n)

*/

class Solution {

  public String longestWord(String[] words) {
    Arrays.sort(words);
    String retWord = "";

    TrieNode root = new TrieNode('-');
    root.isValid = true;

    for (String word : words) {
      if (insert(root, word)) {
        if (word.length() > retWord.length()) {
          retWord = word;
        }
      }
    }

    return retWord;
  }

  public boolean insert(TrieNode root, String word) {
    TrieNode temp = root;
    TrieNode prev = root;
    int newCount = 0;

    for (int i = 0; i < word.length(); i++) {
      if (temp.arr[word.charAt(i) - 'a'] != null) {
        prev = temp;

        temp = temp.arr[word.charAt(i) - 'a'];
      } else {
        newCount++;

        prev = temp;

        temp.arr[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));

        temp = temp.arr[word.charAt(i) - 'a'];
      }
    }

    temp.isWord = true;

    if (newCount == 1) {
      if (prev.isValid) {
        temp.isValid = true;

        return true;
      }

      return false;
    }

    return false;
  }
}

class TrieNode {

  char c;

  TrieNode[] arr;

  boolean isWord;

  boolean isValid;

  TrieNode(char c) {
    this.c = c;

    arr = new TrieNode[26];

    isWord = false;

    isValid = false;
  }
}

class Solution {

  public String longestWord(String[] words) {
    HashSet<String> h = new HashSet<String>();
    String result = new String();
    Arrays.sort(words);
    for (String w : words) h.add(w);
    for (String w : words) {
      int len = w.length();
      while (len > 1) {
        if (!h.contains(w.substring(0, len - 1))) break;
        len--;
      }
      if (len == 1 && w.length() > result.length()) {
        result = w;
      }
    }
    return result;
  }
}
