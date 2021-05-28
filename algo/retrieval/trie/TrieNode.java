package algo.retrieval.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algo.retrieval.Pair;

public class TrieNode {
    private boolean isEndOfWord;
    private int accessFrequency;
    private int pref;
    private String word;
    Map<Character,TrieNode> children = new HashMap<>();

    TrieNode(){
        isEndOfWord=false;
        pref=0;
        accessFrequency=0;
        word="";
    }

    boolean insert(String word,int index){
        //Base Case
        if(index==word.length()){
            if(isEndOfWord)
                return false;
            else{
                pref++;
                isEndOfWord=true;
                this.word = word;
                return true;
            }
        }

        if(children.get(word.charAt(index))== null){
            children.put(word.charAt(index), new TrieNode());
        }
        
        if(children.get(word.charAt(index)).insert(word, index+1)){
            pref++;         
            return true;
        }
        
        return false;
    }

    boolean search(String word,int index){
        //Base Case
        if(index==word.length()){
            if(isEndOfWord){
                accessFrequency++;
            }

            return isEndOfWord;
        }

        if(children.get(word.charAt(index))== null){
            return false;
        }
        
        return children.get(word.charAt(index)).search(word, index+1);
    }

    boolean delete(String word,int index){
        //Base Case
        if(index==word.length()){
            if(isEndOfWord){
                pref--;
                isEndOfWord=false;
                this.word = "";
                return true;
            }

            return false;
        }

        if(children.get(word.charAt(index)) == null)
            return false;
        
        if(children.get(word.charAt(index)).delete(word, index+1)){
            pref--;
            if(pref==0){
                children.remove(word.charAt(index));
            }
            return true;
        }

        return false;
    }

    TrieNode getPrefixNode(String word,int index){
        //Base Case
        if(index==word.length())
            return this;

        if(children.get(word.charAt(index))== null)
            return null;
        
        return children.get(word.charAt(index)).getPrefixNode(word, index+1);
    }

    List<Pair> getAllDescendentWords(String prefix){
        List<Pair> descendents = new ArrayList<>();
        //Base Case
        if(isEndOfWord){
            descendents.add(new Pair(prefix, accessFrequency));
        }

        for(char c: children.keySet()){
            String newPrefix = prefix+c;
            descendents.addAll(children.get(c).getAllDescendentWords(newPrefix));
        }

        return descendents;
    }

    public void autoCorrectHelper(Character letter,String word, Integer[] previousRow,List<Pair> results,int maxEditDistance){
        int columnLength = word.length()+1;
        Integer[] currentRow = new Integer[columnLength];
        currentRow[0] = previousRow[0]+1;

        int insertCost,deleteCost,replaceCost;
        for(int i=1;i<columnLength;++i){
            insertCost = currentRow[i-1] +1;
            deleteCost = previousRow[i]+1;

            if(word.charAt(i-1) != letter){
                replaceCost=previousRow[i-1]+1;
            }
            else{
                replaceCost=previousRow[i-1];
            }
            currentRow[i] = Math.min(insertCost,Math.min(deleteCost,replaceCost));
        }

        if(currentRow[columnLength-1] <= maxEditDistance && this.isEndOfWord ==true)
            results.add(new Pair(this.word, -currentRow[columnLength-1]));

        if(Collections.min(Arrays.asList(currentRow)) <=maxEditDistance){
            for(Map.Entry<Character,TrieNode> child : this.children.entrySet()){
                child.getValue().autoCorrectHelper(child.getKey(), word, currentRow, results, maxEditDistance);
            }
        }

    }

}
