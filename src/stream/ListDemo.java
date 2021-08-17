package stream;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author： xinjingjie
 * @Date：2021/5/28 9:10
 **/
public class ListDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();
        new Random().ints().limit(2000).forEach(numbers ::add);
        Integer[] integers1=numbers.toArray(new Integer[0]);
        Integer[] integers2=numbers.toArray(new Integer[0]);

        //可见返回的是不同的
        System.out.println(integers1==integers2);

        System.out.println(Arrays.toString(integers1));
        System.out.println(numbers);


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("排序前"+Arrays.toString(integers1));
        List<Integer> newInts = new ArrayList<>();
        int[]  beforeInt = Arrays.stream(integers2).mapToInt(Integer::valueOf).toArray();
        Instant s1 =Instant.now();
//        Arrays.stream(integers1).sorted().filter(value -> value != 0).forEach(newInts ::add);
        int[]  tempInt =Arrays.stream(beforeInt).sorted().toArray();;
        Instant e1 =Instant.now();
        System.out.println("耗时"+Duration.between(s1,e1).toMillis()+"ms");
        System.out.println("排序后"+Arrays.toString(tempInt));


        System.out.println();
        System.out.println();
        System.out.println();

        int[]  tempInts = Arrays.stream(integers2).mapToInt(Integer::valueOf).toArray();
        System.out.println("排序前"+Arrays.toString(tempInts));
        Instant s2 =Instant.now();
//        int[]  tempInts = Arrays.stream(integers2).filter(integer -> integer != 0).mapToInt(Integer::valueOf).toArray();
        quickSort(tempInts,0,integers2.length-1);
        Instant e2 =Instant.now();
        System.out.println("耗时"+Duration.between(s2,e2).toMillis()+"ms");
        System.out.println("排序后"+Arrays.toString(tempInts));

        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("排序后"+numbers);
        Instant s3 =Instant.now();
//        List<Integer> collect = numbers.stream().sorted().filter(integer -> integer != 0).collect(Collectors.toList());
        List<Integer> collect = numbers.stream().sorted().collect(Collectors.toList());
        Instant e3 =Instant.now();
        System.out.println("耗时"+Duration.between(s3,e3).toMillis()+"ms");
        System.out.println("排序后"+collect);





    }
    /*
    快速排序
     */
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];
        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

}
