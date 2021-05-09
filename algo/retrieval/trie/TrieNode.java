package algo.retrieval.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algo.retrieval.Pair;

public class TrieNode {
    private boolean isEndOfWord;
    private int accessFrequency;
    private int pref;
    Map<Character,TrieNode> children = new HashMap<>();

    TrieNode(){
        isEndOfWord=false;
        pref=0;
        accessFrequency=0;
    }

    boolean insert(String word,int index){
        //Base Case
        if(index==word.length()){
            if(isEndOfWord)
                return false;
            else{
                pref++;
                isEndOfWord=true;
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

}
