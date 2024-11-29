package algorithms;

import java.util.*;

public class InsertionSortAlgo implements SortStrategy{
    @Override
    public <T extends  Comparable<T>> void sort(List<T> list){
        insertionSort(list);
    }

    private <T extends  Comparable<T>> void insertionSort(List<T> list) {
        int i, x = 0;
        T key;
        for ( i = 1; i<list.size(); ++i){
            key= list.get(i);
            x = i - 1; 
            while (x >= 0 && list.get(x).compareTo(key) > 0){
                list.set( x+1, list.get(x));
                x--;
            }
            list.set( x+1, key );
        }
    }
}