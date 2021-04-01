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

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.arr[index] == null) {
                curr.arr[index] = new TrieNode();
            }
            curr = curr.arr[index];
        }
        curr.words.add(word);
    }

    public List<String> lookUp(String word) {
        TrieNode curr = root;
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

    public boolean startsWith(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.arr[index] == null) {
                return false;
            }
            curr = curr.arr[index];
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
            System.out.print("prefix: ");
            line = inputScanner.nextLine().toLowerCase();
            token = line.split(" ")[0];
            System.out.printf("result: %s\n", t.lookUp(token));
        }

    }
}
