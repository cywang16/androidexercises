package com.example.consolelib;

public class FriendsPairing {
    public FriendsPairing() {}

    public int CountPairing(int N) {
        int pairs[] = new int[N + 1];
        pairs[0] = 1;
        pairs[1] = 1;
        for (int n = 2; n <= N; n++) {
            // pairs[n] = (pairs[n - 1] +
            //         ((n - 1) * pairs[n - 2]) % mod) % mod;
            int i = 0;
            while (i < n - 1) {
                pairs[n] = (pairs[n] + pairs[n - 2]) % mod;
                i++;
            }
            pairs[n] = (pairs[n] + pairs[n - 1]) % mod;
        }
        return pairs[N];
    }

    private static final int mod = 1000000007;
}
