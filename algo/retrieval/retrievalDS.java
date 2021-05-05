package algo.retrieval;

import java.util.List;

public interface RetrievalDS {
    public boolean insert(String word);

    public boolean delete(String word);

    public boolean search(String word);

    public List<String> autoSuggest(String prefix);

    public List<String> autoCorrect(String word);
}
