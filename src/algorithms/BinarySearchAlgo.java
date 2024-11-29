package algorithms;
import java.util.*;

public class BinarySearchAlgo {
    //возвращает индекс ключа либо -1
     
    public static <T extends Comparable<T>> int findByKey(List<T> sortedList, T key) {
        int left = 0;
        int right = sortedList.size() - 1;
        int result = -1;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            int cmp = key.compareTo(sortedList.get(mid));
            if (cmp == 0) {
                result = mid;
                break;
            }else if (cmp > 0){
                left = mid+1;
            }else if( cmp < 0 ){
                right = mid-1;
            }
        }
        return result;
    }
}