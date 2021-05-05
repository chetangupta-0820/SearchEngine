package algo.retrieval.naive;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algo.retrieval.RetrievalDS;

public class NaiveRetrieval implements RetrievalDS{
    private Set<String> dictionarySet = new HashSet<>();
    
    public boolean insert(String word) {
        return dictionarySet.add(word);
    }

    public boolean search(String word) {
        return dictionarySet.contains(word);
    }

    public boolean delete(String word) {
        return dictionarySet.remove(word);
    }

    public List<String> autoSuggest(String prefix) {
        throw new UnsupportedOperationException();
    }

    public List<String> autoCorrect(String word) {
        throw new UnsupportedOperationException();
    }
}
