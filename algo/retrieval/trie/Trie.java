package algo.retrieval.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import algo.rank.SortRanker;
import algo.retrieval.Pair;
import algo.retrieval.PairComparator;
import algo.retrieval.RetrievalDS;

public class Trie implements RetrievalDS{
    private TrieNode root = new TrieNode();
    private int startIndex = 0;
    private int retrivalLimit = 5;
    private int maxEditDistance = 2;
    private SortRanker<Pair> finder = new SortRanker<>();


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
        TrieNode prefNode = root.getPrefixNode(prefix, startIndex);
        List<Pair> allSuggestions = prefNode.getAllDescendentWords(prefix);
        List<Pair> topSuggestions = finder.getOrderedElements(allSuggestions, new PairComparator(), retrivalLimit);
        List<String> result = new ArrayList<>();
        for(Pair p : topSuggestions) {
            result.add(p.getValue());
        }
        return result;
    }

    @Override
    public List<String> autoCorrect(String word) {
        Integer[] currentRow = new Integer[word.length()+1];
        
        for(int i=0;i<=word.length();++i) currentRow[i]=i;

        List<Pair> allResults = new ArrayList<>();

        for(Map.Entry<Character,TrieNode> child : root.children.entrySet()){
            child.getValue().autoCorrectHelper(child.getKey(), word, currentRow, allResults, maxEditDistance);
        }

        List<Pair> topResults = finder.getOrderedElements(allResults, new PairComparator(), retrivalLimit);
        List<String> similar = new ArrayList<>();
        for(Pair p: topResults){
            similar.add(p.getValue());
        }

        return similar;        
    }
    
}
