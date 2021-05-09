package algo.rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortRanker<T> implements Ranker<T> {

    @Override
    public List<T> getOrderedElements(List<T> elements, Comparator<T> orderComparator, int num) {
        Collections.sort(elements, orderComparator);
        List<T> result = new ArrayList<>();
        for(int i=0;i<num && i<elements.size();i++) {
            result.add(elements.get(i));
        }
        return result;
    }
    
}
