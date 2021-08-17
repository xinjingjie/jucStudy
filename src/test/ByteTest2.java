package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/7/29 16:59
 **/
public class ByteTest2 {
    public static void main(String[] args) {

        String a="";
        Map<String,String> map =new HashMap<>();
        map.put("a",a);
        String a1 = map.get("a");
        a1=null;
        assert false;
        a1.getBytes();

        System.out.println((byte) 247);

        System.out.println(Arrays.toString(intToByteArray(251))); //-56

        byte[] bytes = {0, 0, 0, -17};
        System.out.println(byteArrayToInt(bytes)); //-56

//
//        byte[] bytes = {-17,-65,-67};
//        bytes
    }

    /**
     * int到byte[] 由高位到低位
     *
     * @param i 需要转换为byte数组的整行值。
     * @return byte数组
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * byte[]转int
     *
     * @param bytes 需要转换成int的数组
     * @return int值
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }
}
