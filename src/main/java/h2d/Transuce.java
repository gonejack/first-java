package h2d;

import static org.junit.Assert.assertEquals;

public class Transuce {
    public static void main(String[] args) {
        String yb = "3 1A5C 271 FF";
        String[] ybs = yb.split(" ");
        int sum = 0;
        for (int i = 1; i < ybs.length; i++) {
            sum += Transuce.h2D(ybs[i]);
        }

        assertEquals(7628, sum);
    }

    static int h2D(String s) {
        int i = 0, l = s.length(), n = 0;
        while (i < l) {
            int x = s.codePointAt(i);
            n = n << 4 | (x > '9' ? x - ('A' - 10) : x - '0');
            i += 1;
        }
        return n;
    }
}
