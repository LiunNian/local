package com.paper.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CollectionUtil {

    /**
     * 去List里面的重复数据
     * @param list List集合
     * @return
     */
    public static List getUniqList(List list){
       return new ArrayList(new HashSet(list));
    }
}
