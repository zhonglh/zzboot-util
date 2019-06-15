package com.zzboot.util.base.sorts;

import java.util.Comparator;

/**
 * 升序 ， 从小到大排列
 * @author Administrator
 */
public class SortComparator<T> implements Comparator<T> {

    @Override
    public int compare(T obj1,T obj2){
        if(obj1 == null && obj2 == null) {
            return 0;
        }
        else if(obj1 == null) {
            return 1;
        }
        else if(obj2 == null) {
            return -1;
        }
        else {
            if(obj1 instanceof ISort && obj2 instanceof ISort){
                int s1 = ((ISort)obj1).getSort();
                int s2 = ((ISort)obj2).getSort();
                if(s1 > s2){
                    return 1;
                }else if(s1 < s2){
                    return -1;
                }else {
                    String s21 = ((ISort)obj1).getSort2();
                    String s22 = ((ISort)obj2).getSort2();
                    return  s21.compareTo(s22) ;
                }
            }else {
                return obj2.toString().compareTo(obj1.toString());
            }
        }


    }

}
