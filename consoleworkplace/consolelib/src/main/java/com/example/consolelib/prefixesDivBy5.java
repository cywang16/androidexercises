package com.example.consolelib;

import java.util.ArrayList;
import java.util.List;

public class prefixesDivBy5 {
    public prefixesDivBy5(int input[]) {
        A = input;
    }

    public List<Boolean> prefixesDivBy5() {
        List<Boolean> ans = new ArrayList<Boolean>();
        int rem = A[0], pos = 1;
        ans.add(rem == 0);
        for (int i = 1; i < A.length; i++) {
            rem <<= 1;
            rem %= 5;
            rem += A[i];
            ans.add((rem % 5) == 0);
            /*
            if (A[i] == 0) {
                ans.add(ans.get(ans.size() - 1));
            } else {
                rem += pos;
                ans.add((rem % 5) == 0);
            }
            */
        }
        return ans;
    }

    private int A[];
}
