import java.util.*;

public class wordbreak {
/* scroll
 * down for example and explaination
 */
    public static boolean wordBreak(String A, List<String> B) {
        TrieNode root = new TrieNode();
        for (String word : B) {
            insert(root, word);
        }
        Boolean[] memo = new Boolean[A.length()];
        return wb(A, root, 0, memo);
    }

    public static boolean wb(String A, TrieNode root, int start, Boolean[] memo) {
        int n = A.length();
        if (start == n) {
            return true; // We have successfully segmented the string.
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int i = start + 1; i <= n; i++) {
            String fstpart = A.substring(start, i);
            if (search(fstpart, root) && wb(A, root, i, memo)) {
                memo[start] = true;
                return true;
            }
        }

        memo[start] = false;
        return false;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean eow;

        TrieNode() {
            children = new TrieNode[26];
            eow = false;
        }
    }

    public static void insert(TrieNode root, String key) {
        TrieNode temp = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                temp.children[idx] = new TrieNode();
            }
            temp = temp.children[idx];
        }
        temp.eow = true;
    }

    public static boolean search(String key, TrieNode root) {
        TrieNode temp = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                return false;
            }
            temp = temp.children[idx];
        }
        return temp.eow;
    }

    public static void main(String[] args) {

        // Create a list of words
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("apple");
        wordList.add("apply");
        wordList.add("samsung");
        wordList.add("tv");
        wordList.add("i");
        wordList.add("like");

        // Input string
        String inputString = "ilike";

        // Call the wordBreak function
        boolean result = wordBreak(inputString, wordList);

        // Output the result
        System.out.println("Can '" + inputString + "' be segmented into words? " + result);
    }

}
// dry run
/*
 * Role of start:
 * 
 * The start parameter in the wb function represents the starting index in the
 * string A from which we are currently trying to break it into words. It keeps
 * track of our progress while searching for valid word breaks.
 * 
 * Role of memo (memoization):
 * 
 * The memo array is used to store the results of subproblems. It helps us avoid
 * redundant computations by remembering whether we can successfully segment a
 * substring of A starting from a specific index start. It's an array of Boolean
 * values where each element memo[i] represents whether we can segment the
 * substring starting at index i.
 * 
 * Now, let's illustrate their roles with an example:
 * 
 * Suppose we have the string A = "applepen" and the list of words B = ["apple",
 * "pen"]. We want to determine if we can break the string A into words from the
 * list B.
 * 
 * Initialization: We call wb(A, root, 0, memo) with start = 0. The memo array
 * is initially empty.
 * 
 * First Iteration at start = 0:
 * 
 * Inside the wb function, we start iterating through different substrings of A
 * that start at index start = 0. We check if each substring is a valid word by
 * looking it up in the Trie (search function).
 * 
 * First iteration: fstpart = "a". It's not a valid word.
 * 
 * Second iteration: fstpart = "ap". It's not a valid word.
 * 
 * Third iteration: fstpart = "app". It's not a valid word.
 * 
 * Fourth iteration: fstpart = "appl". It's not a valid word.
 * 
 * Fifth iteration: fstpart = "apple". It's a valid word.
 * 
 * At this point, we've found a valid word break starting from start = 0. We
 * update memo[0] to true to remember this result.
 * 
 * Recursive Call at start = 5:
 * 
 * Now, we make a recursive call to wb with start updated to the position after
 * "apple," which is start = 5 (the index of 'p' in "applepen").
 * 
 * Inside the recursive call, we start searching for valid word breaks starting
 * at start = 5. However, before we start the search, we check memo[5]. Since
 * memo[5] is null, indicating that we haven't computed this result before, we
 * proceed with the search.
 * 
 * This recursive call eventually leads to another valid word break at start = 5
 * with the word "pen."
 * 
 * We update memo[5] to true to remember this result.
 * 
 * Final Result:
 * 
 * Back in the original call to wb at start = 0, we've explored all possible
 * word breaks starting from index 0.
 * 
 * The memo array now contains the results for various starting indices. In this
 * case, memo[0] and memo[5] are both true, indicating that valid word breaks
 * exist starting from these positions.
 */
