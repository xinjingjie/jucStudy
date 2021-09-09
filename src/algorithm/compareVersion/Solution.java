package algorithm.compareVersion;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author： xinjingjie
 * @Date：2021/9/1 9:14
 **/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(compareVersion("1.1.1", "2.3.4"));
//        System.out.println(compareVersion("3.1.1", "2.3.4"));
//        System.out.println(compareVersion("2.1.1", "2.1.3"));
//        System.out.println(compareVersion("1.1.1", "2.3"));
//        System.out.println(compareVersion("1.1.1", "1.1.1"));
//        System.out.println(compareVersion("1.1.01", "1.1.001"));
//        System.out.println(compareVersion("1.1.01", "1.1.001"));
//        System.out.println(compareVersion("1.00.01", "1.01.001"));
        System.out.println(compareVersion("1.0.1", "1"));
//        System.out.println(compareVersion("1", "1.1"));
//        System.out.println(compareVersion("1.0", "1"));
        System.out.println(compareVersion("19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.00.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000",
                                          "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000"));

        System.out.println(Arrays.toString("xxx".split("#")));

        System.out.println(Integer.parseInt("00")==Integer.parseInt("0"));
    }

    public static int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        for (int i = 0; i < split1.length; i++) {
            if (split2.length - 1 < i) {
                for (int i1=i;i1 < split1.length; i1++){
                    if (Integer.parseInt(split1[i1])!=0) {
                        return 1;
                    }
                }
                return 0;
            }
            if (Integer.parseInt(split1[i]) > Integer.parseInt(split2[i])) {
                return 1;
            } else if (Integer.parseInt(split1[i]) < Integer.parseInt(split2[i])) {
                return -1;
            }
        }
        if (split2.length > split1.length) {
            for (int i = split2.length - 1; i >= split1.length; i--) {
                if (Integer.parseInt(split2[i])!=0) {
                    return -1;
                }
            }
        }
        return 0;
    }

}
