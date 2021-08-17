package bit;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * @Author： xinjingjie
 * @Date：2021/6/24 11:10
 **/
public class BitSetDemo {
    public static void main(String args[]) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) bits1.set(i);
            if ((i % 5) != 0) bits2.set(i);
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

//         XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);

        Long a = 230L;
        Long b = 4L;
        a &= b;
        System.out.println(a);


        System.out.println();


        System.out.println("--------");

        BitSet b1 = new BitSet(98);
        b1.set(289);

        System.out.println(splitBitSet(b1, 0));
        System.out.println(splitBitSet(b1, 1));
        System.out.println(splitBitSet(b1, 2));
        System.out.println(splitBitSet(b1, 3));
        System.out.println(splitBitSet(b1, 4));
        System.out.println(splitBitSet(b1, 5));
        System.out.println("-----------BigInteger.or()-------------");

        BigInteger bi1= new BigInteger("1111111");
        BigInteger bi2= new BigInteger("2222222");
        System.out.println(bi1);
        System.out.println(bi2);
        System.out.println(bi1.or(bi2));

        System.out.println("-----------");
        //BitSet  以64的倍数来扩大长度
        BitSet bitSet = new BitSet();
        bitSet.set(9999);
        System.out.println(bitSet.toString());
        System.out.println(bitSet.size());

        //超过inuse中的长度则返回false
        System.out.println(bitSet.get(99999));





    }


    private static BigInteger splitBitSet(BitSet bitSet, int position) {
        BitSet newBitSet = new BitSet(50);
        for (int i = 0; i < 48; i++) {
            if (position == 0) {
                boolean b = bitSet.get(i + 2);
                newBitSet.set(i + 2, b);
            } else {
                boolean b = bitSet.get(48 * position + 2 + i);
                newBitSet.set(i, b);
            }
        }
        return bitSet2bigInt(newBitSet);
    }

    private static BigInteger bitSet2bigInt(BitSet bitSet) {
        byte[] bytes = new byte[bitSet.size() / 8];
        for (int i = 0; i < bitSet.size(); i++) {
            int index = bitSet.size() / 8 - i / 8 - 1;
            int offset = i % 8;
            bytes[index] |= (bitSet.get(i) ? 1 : 0) << offset;
        }
        return new BigInteger(bytes);
    }
}
