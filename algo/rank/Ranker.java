package algo.rank;

import java.util.Comparator;
import java.util.List;

public interface Ranker<T> {
    public List<T> getOrderedElements(List<T> elements, Comparator<T> orderComparator, int num);
}
