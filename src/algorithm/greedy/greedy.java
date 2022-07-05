package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * 贪心算法：集合覆盖
 * */
public class greedy {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入哈希
        HashSet<String> set1 = new HashSet<>();
        //放入地区
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        broadcasts.put("k1", set1);
        broadcasts.put("k2", set2);
        broadcasts.put("k3", set3);
        broadcasts.put("k4", set4);
        broadcasts.put("k5", set5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        for (String key : broadcasts.keySet()) {
            HashSet<String> valSet = broadcasts.get(key);
            allAreas.addAll(valSet);
        }
//        System.out.println(allAreas);
        //存储电台集合
        ArrayList<String> selects = new ArrayList<>();
        //保存遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        String maxKey;
        while (allAreas.size() != 0) {
            maxKey=null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);//求出集合交集,付给tempSet
                if (tempSet.size() > 0 &&( maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey=key;
                }
            }
            if (maxKey!=null){
                //添加当前maxKey
                selects.add(maxKey);
                //移除地区
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("结果: "+selects);
    }
}
