package algo.retrieval.trie;

import java.util.List;

import algo.retrieval.RetrievalDS;

public class Trie implements RetrievalDS{
    private TrieNode root = new TrieNode();
    private int startIndex = 0;



    @Override
    public boolean insert(String word) {
        return root.insert(word,startIndex);
    }

    @Override
    public boolean delete(String word) {
        return root.delete(word,startIndex);
    }

    @Override
    public boolean search(String word) {
        return root.search(word,startIndex);
    }

    @Override
    public List<String> autoSuggest(String prefix) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> autoCorrect(String word) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
