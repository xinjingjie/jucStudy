package algorithm.skyLine;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author： xinjingjie
 * @Date：2021/7/13 10:20
 * <p>
 * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 **/
public class SkyLineDemo {
    //最高值左右横坐标
    static List<Pair<Integer, Integer>> leftRightValue = new LinkedList<>();

    static List<Integer> needCompare = new ArrayList<>();

    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings2 = {{0, 2, 10}, {2, 4, 15}, {4, 8, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings3 = {{0, 2, 3}, {2, 5, 3}};
        int[][] buildings4 = {{0, 1, 3}};
        int[][] buildings5 = {{0,2147483647,2147483647}};
        int[][] buildings6 = {{0,10,10}};
        System.out.println(getSkyline(buildings6));
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {


        //队列
        LinkedList<Integer> sets = new LinkedList<>();
        //横坐标所有的顶层
        Map<Integer, List<Integer>> eachHeight = new HashMap<>();
        //横坐标对应的最高值
        Map<Integer, Integer> eachHighestMap = new HashMap<>();
        //大楼集群左右横坐标
        List<Pair<Integer, Integer>> bigBuilding = new ArrayList<>();

        //每个集群对应的最高值
        List<Integer> eachHighest = new LinkedList<>();

        List<List<Integer>> res = new ArrayList<>();

        int temp = 0;

        for (int[] building : buildings) {
            //找出集群左右位置
            if (sets.size() > 2 && building[0] > temp) {
                Pair<Integer,Integer> a = new Pair(sets.pollFirst(), sets.pollLast());
                bigBuilding.add(a);
                sets.clear();
                sets.addLast(building[0]);

            } else {
                sets.addLast(building[0]);
            }

            sets.addLast(building[1]);

            temp = building[1];

            needCompare.add(building[0]);
            needCompare.add(building[1]);


            for (int z = building[0]; z <= building[1]; z++) {
                List<Integer> list = eachHeight.get(z);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(building[2]);

                if (z < building[1]) {
                    eachHeight.put(z, list);
                }
                int value = eachHighestMap.get(z) == null ? -1 : eachHighestMap.get(z);
                eachHighestMap.put(z, building[2] > value ? building[2] : eachHighestMap.get(z));
            }
        }
        //最后加一次
        if (sets.size() >= 2) {
            Pair a = new Pair(sets.pollFirst(), sets.pollLast());
            bigBuilding.add(a);
        }



        //找出最高值和左右两边的位置
        for (Pair<Integer, Integer> p : bigBuilding) {
            int left = p.getKey();
            int right = p.getKey();

            int max = eachHighestMap.get(p.getKey());
            //这里需要优化
            eachHighest.add(max);

            for (int j = p.getKey(); j <= p.getValue(); j++) {
                if (max < eachHighestMap.get(j)) {
                    Integer integer = eachHighestMap.get(j);
                    max = integer;
                    eachHighest.remove(eachHighest.size() - 1);
                    eachHighest.add(integer);
                    left = j;
                    right = j;
                } else if (max == eachHighestMap.get(j)) {
                    right = j;
                }

            }
            leftRightValue.add(new Pair<>(left, right));
        }

        for (int i = 0; i < bigBuilding.size(); i++) {
            int max = eachHighest.get(i);
            int left = getLeft(i, max);
            int right = getRight(i, max);
            Pair<Integer, Integer> p = bigBuilding.get(i);
            for (int j = p.getKey(); j <= left; j++) {
                //从左到右 如果前一个和当前的不一样
                if (!eachHighestMap.get(j).equals(eachHighestMap.get(j - 1))) {
                    Integer integer = eachHeight.get(j) == null ? 0 : eachHeight.get(j).get(eachHeight.get(j).size() - 1);
                    List<Integer> t = new ArrayList<>();
                    t.add(j);
                    t.add(integer);
                    res.add(t);
                }
            }

            for (int j = right; j <= p.getValue(); j++) {
                //从右到左  后一个和当前的不一样
                if (!eachHighestMap.get(j).equals(eachHighestMap.get(j + 1))) {
                    Integer integer = eachHeight.get(j) == null ? 0 : eachHeight.get(j).get(eachHeight.get(j).size() - 1);
                    List<Integer> t = new ArrayList<>();
                    t.add(j);
                    t.add(integer);
                    res.add(t);
                }
            }

        }

        System.out.println(needCompare);
        System.out.println(eachHighestMap);
        System.out.println(leftRightValue);
        System.out.println(eachHighest);
        System.out.println(bigBuilding);
        System.out.println(eachHeight.keySet());
//        for (Integer integer : eachHeight.keySet()) {
//            System.out.println(integer + ":" + eachHeight.get(integer) + "----" + eachHighestMap.get(integer));
//        }


        return res;
    }

    public static int getLeft(int index, int max) {
        return leftRightValue.get(index).getKey();
    }

    public static int getRight(int index, int max) {
        return leftRightValue.get(index).getValue();
    }
}