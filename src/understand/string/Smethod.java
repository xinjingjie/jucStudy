package understand.string;

/**
 * @Author： xinjingjie
 * @Date：2021/9/8 15:13
 **/
public class Smethod {
    public static void main(String[] args) {
        char[] chars = "2".toCharArray();
        for (char c:chars){
            System.out.println((char)c);
        }
        String s1="a2bc123";  //50
        String s2="ab";   //98
        System.out.println(s1.compareTo(s2));
    }
}
