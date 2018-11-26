package jingl.wang.java.exectorsPool;


import java.io.IOException;

/**
 * @Date : 2018/11/5
 * @Author : 汪京陆(Ben)[wangjinglu@souche.com]
 * @CopyRight : DataTeam @ SouChe Inc
 * @Desc :
 */
public class Main {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(Integer.toBinaryString(SHARED_SHIFT));
        System.out.println(Integer.toBinaryString(SHARED_UNIT));
        System.out.println(Integer.toBinaryString(MAX_COUNT));
        System.out.println(Integer.toBinaryString(EXCLUSIVE_MASK));

    }
}
