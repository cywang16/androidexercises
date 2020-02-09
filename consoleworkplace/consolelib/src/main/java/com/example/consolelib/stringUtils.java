package com.example.consolelib;

import java.util.Random;

public class stringUtils {
    public static String create(int len) {
        Random rnd = new Random();
        char buf[] = new char[len];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (char)('A' + rnd.nextInt(26));
        }
        return new String(buf);
    }

    public static int LCS(String a, String b) {
        return LcsHelp(a.toCharArray(), b.toCharArray(), 0, 0);
    }

    private static int LcsHelp(char a[], char b[], int i, int j) {
        if (i >= a.length || j >= b.length) {
            return 0;
        }
        if (a[i] == b[j]) {
            return 1 + LcsHelp(a, b, i + 1, j + 1);
        } else {
            return Math.max(LcsHelp(a, b, i + 1, j), LcsHelp(a, b, i, j + 1));
        }
    }

    public static String getLCS(String a, String b) {
        return getLcsHelp(a.toCharArray(), b.toCharArray(), 0, 0);
    }

    private static String getLcsHelp(char a[], char b[], int i, int j) {
        if (i >= a.length || j >= b.length) {
            return "";
        }
        if (a[i] == b[j]) {
            return a[i] + getLcsHelp(a, b, i + 1, j + 1);
        } else {
            String a1 = getLcsHelp(a, b, i, j + 1),
                    b1 = getLcsHelp(a, b, i + 1, j);
            if (a1.length() >= b1.length()) {
                return a1;
            } else {
                return b1;
            }
        }
    }

    public static String getLCSD(String a, String b) {
        String lcs[][] = new String[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) {
                    lcs[i][j] = "";
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + a.charAt(i - 1);
                } else {
                    lcs[i][j] = lcs[i - 1][j].length() >= lcs[i][j - 1].length()?
                            lcs[i - 1][j]: lcs[i][j - 1];
                }
            }
        }
        return lcs[a.length()][b.length()];
    }
}
