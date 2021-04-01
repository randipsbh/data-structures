import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Trie {

    private class TrieNode {
        public TrieNode[] arr;
        public List<String> words;

        public TrieNode() {
            arr = new TrieNode[26];
            words = new LinkedList<>();
        }
    }

    private TrieNode prefixRoot;
    private TrieNode suffixRoot;

    public Trie() {
        prefixRoot = new TrieNode();
        suffixRoot = new TrieNode();
    }

    public void insert(String word) {
        insertHelper(prefixRoot, word, false);
        insertHelper(suffixRoot, (new StringBuilder(word)).reverse().toString(), true);

    }

    private void insertHelper(TrieNode curr, String word, boolean isReverse) {
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.arr[index] == null) {
                curr.arr[index] = new TrieNode();
            }
            curr = curr.arr[index];
        }
        curr.words.add(isReverse ? (new StringBuilder(word)).reverse().toString() : word);
    }

    public List<String> prefixLookUp(String prefix) {
        return lookUp(prefixRoot, prefix);
    }

    public List<String> suffixLookUp(String suffix) {
        return lookUp(suffixRoot, (new StringBuilder(suffix)).reverse().toString());
    }

    private List<String> lookUp(TrieNode curr, String word) {
        System.out.printf("looking up: %s\n", word);
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.arr[index] == null) {
                return Collections.emptyList();
            }
            curr = curr.arr[index];
        }
        List<String> list = new LinkedList<>();
        lookUpHelper(curr, list);
        return list;
    }

    private void lookUpHelper(TrieNode curr, List<String> list) {
        if (curr == null) {
            return;
        }
        list.addAll(curr.words);
        for (int i = 0; i < curr.arr.length; i++) {
            lookUpHelper(curr.arr[i], list);
        }
    }

    private static boolean isAlphabetic(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Trie t = new Trie();
        File file = new File("/home/randip/Downloads/words.txt");
        Scanner fileScanner = new Scanner(file);
        String line = "", token = "";
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            token = line.split(" ")[0].toLowerCase();
            if (token.length() > 0) {
                if (isAlphabetic(token)) {
                    t.insert(token);
                }
            }
        }

        Scanner inputScanner = new Scanner(System.in);
        while (!token.startsWith("0")) {
            System.out.print("prefix/suffix: ");
            line = inputScanner.nextLine().toLowerCase();
            token = line.split(" ")[0];
            System.out.printf("prefix result: %s\n", t.prefixLookUp(token));
            System.out.printf("suffix result: %s\n", t.suffixLookUp(token));
        }

    }
}
