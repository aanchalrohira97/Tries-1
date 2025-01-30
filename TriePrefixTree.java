// Trie consists of TrieNodes and a flag to check for the end of the word

// Insert - O(n)
// Search - O(n)
// startswith - O(n)

// n: length of the word
// works on leetcode

// first establish the treenode

class TrieNode {

  //boolean
  boolean isEnd;
  TrieNode[] children;

  //constructor
  public TrieNode() {
    children = new TrieNode[26];
  }
}

class Trie {

  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode cursor = root;

    //iterate over the array
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (cursor.children[c - 'a'] == null) {
        cursor.children[c - 'a'] = new TrieNode();
      }
      cursor = cursor.children[c - 'a'];
    }
    cursor.isEnd = true;
  }

  public boolean search(String word) {
    TrieNode cursor = root;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (cursor.children[c - 'a'] == null) {
        return false;
      }
      cursor = cursor.children[c - 'a'];
    }

    if (cursor.isEnd) {
      return true;
    }
    return false;
  }

  public boolean startsWith(String prefix) {
    TrieNode cursor = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (cursor.children[c - 'a'] == null) {
        return false;
      }
      cursor = cursor.children[c - 'a'];
    }

    return true;
  }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
