package dictionary.helper.naive;

import java.util.List;

import algo.retrieval.naive.NaiveRetrieval;
import dictionary.helper.DictionaryHelper;

public class NaiveDictionaryHelper implements DictionaryHelper {
    private NaiveRetrieval dataSet = new NaiveRetrieval();

    @Override
    public boolean insert(String word){
        return dataSet.insert(word);
    }

    @Override
    public boolean delete(String word){
        return dataSet.delete(word);
    }

    @Override
    public boolean search(String word){
        return dataSet.search(word);
    }

    @Override
    public List<String> autoSuggest(String prefix){
        return dataSet.autoSuggest(prefix);
    }

    @Override
    public List<String> autoCorrect(String word){
        return dataSet.autoCorrect(word);
    }

}
