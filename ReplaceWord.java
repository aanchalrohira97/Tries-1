/*
sentence length is longer -> that means optimize search
create a dictionary
add words to it, and with every word traverse the dictionary wherever there is isEnd, replace the word there

tc: O(n * L + m * L), where n is the number of words in the dictionary,
 m is the number of words in the sentence, and L is the average length of the words.
sc: O(C + S), where C is the total number of characters in the dictionary and S is the length of the sentence.
*/

class Solution {

  public String replaceWords(List<String> dictionary, String sentence) {
    //establish root
    TrieNode root = new TrieNode('-');
    //add words to dictionary
    for (String word : dictionary) {
      insert(root, word);
    }

    StringBuilder sb = new StringBuilder();
    String[] words = sentence.split(" ");
    //split the sentence and now search root for each word
    for (String word : words) {
      sb.append(getRoot(root, word));
      sb.append(" ");
    }

    int length = sb.length();
    sb.setLength(length - 1);
    return new String(sb);
  }

  public void insert(TrieNode node, String word) {
    TrieNode temp = node;
    for (int i = 0; i < word.length(); i++) {
      if (temp.map.containsKey(word.charAt(i))) {
        temp = temp.map.get(word.charAt(i));
      } else {
        temp.map.put(word.charAt(i), new TrieNode(word.charAt(i)));
        temp = temp.map.get(word.charAt(i));
      }
    }
    temp.isWord = true;
  }

  public String getRoot(TrieNode root, String word) {
    TrieNode temp = root;
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < word.length(); i++) {
      if (temp.map.containsKey(word.charAt(i))) {
        temp = temp.map.get(word.charAt(i));
        sb.append(word.charAt(i));
        if (temp.isWord) {
          return new String(sb);
        }
      } else {
        break;
      }
    }

    return word;
  }

  class TrieNode {

    char c;
    HashMap<Character, TrieNode> map;
    boolean isWord;

    TrieNode(char c) {
      this.c = c;
      map = new HashMap<>();
      isWord = false;
    }
  }
}
