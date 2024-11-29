package algorithms;

import java.util.List;

public interface SortStrategy {
    public <T extends  Comparable<T>> void sort(List<T> list);
}