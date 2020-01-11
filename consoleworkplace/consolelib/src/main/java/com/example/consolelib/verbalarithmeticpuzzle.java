package com.example.consolelib;

public class verbalarithmeticpuzzle {
    public verbalarithmeticpuzzle(String[] ws, String r) {
        words = ws;
        result = r;
        assigned = new int[26];
    }

    public boolean isSolvable(String[] words, String result) {
        int assigned[] = new int[26];
        for (int i = 0; i < 26; i++) {
            assigned[i] = -1;
        }
        boolean taken[] = new boolean[10];
        return checksolution(words, result, assigned, taken, 0, 1);
    }

    public boolean isSolvable() {
        // int assigned[] = new int[26];
        for (int i = 0; i < 26; i++) {
            assigned[i] = -1;
        }
        boolean taken[] = new boolean[10];
        return checksolution(words, result, assigned, taken, 0, 1);
    }

    public void result() {
        for (String w: words) {
            printw(w);
        }
        printw(result);
    }

    public void printw(String s) {
        for (char c: s.toCharArray()) {
            System.out.printf("%d", assigned[c - 'A']);
        }
        System.out.printf("\n");
    }

    private String[] words;
    private String result;
    int assigned[];

    private boolean checksolution(String words[], String result,
                                  int assigned[], boolean taken[],
                                  int carry, int pos) {
        int sum = carry;
        for (int i = 0; i < words.length; i++) {
            int cp = words[i].length() - pos;
            if (cp < 0) continue;
            int ch = words[i].charAt(cp) - 'A', d = assigned[ch];
            if (d < 0) {
                for (int j = 0; j < 10; j++) {
                    if (cp == 0) {
                        if (j == 0) continue;
                    }
                    if (!taken[j]) {
                        taken[j] = true;
                        assigned[ch] = j;
                        if (checksolution(words, result, assigned, taken,
                                carry, pos)) return true;
                        taken[j] = false;
                        assigned[ch] = -1;
                    }
                }
                return false;
            }
            sum += d;
        }
        int sr = sum % 10, sc = sum / 10;
        int rp = result.length() - pos;
        if (rp < 0) return true;
        int rc = result.charAt(rp) - 'A';
        int r = assigned[rc];
        if (r >= 0) {
            if (r != sr) return false;
            return checksolution(words, result, assigned, taken,
                    sc, pos + 1);
        } else {
            if (taken[sr]) return false;
            if (rp == 0 && sr == 0) return false;
            taken[sr] = true;
            assigned[rc] = sr;
            if (checksolution(words, result, assigned, taken, sc, pos + 1)) {
                return true;
            }
            taken[sr] = false;
            assigned[rc] = -1;
            return false;
        }
    }
}
