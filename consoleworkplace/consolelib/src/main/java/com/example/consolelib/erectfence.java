package com.example.consolelib;

import java.util.ArrayList;
import java.util.List;

public class erectfence {
    public erectfence() {

    }

    public int[][] outerTrees(int[][] points) {
        List<Integer> ansl = new ArrayList<Integer>();
        for (int i = 0; i < points.length; i++) {
            System.out.printf("[%d %d]\n", points[i][0], points[i][1]);
            int j = (i + 1) % points.length;
            double angU = getang(points[i], points[j]), angL = angU;
            double nangU = getnang(points[i], points[j]), nangL = nangU;
            double rangU = getrang(points[i], points[j]), rangL = rangU;
            double nrangU = getnrang(points[i], points[j]), nrangL = nrangU;
            System.out.printf("%d %d\n", points[j][0], points[j][1]);
            System.out.printf("%.6f %.6f\n", angU, nangU);
            boolean inbound = true;
            j = (j + 1) % points.length;
            while( j != i && inbound) {
                double ang = getang(points[i], points[j]),
                        nang = getnang(points[i], points[j]),
                        rang = getrang(points[i], points[j]),
                        nrang = getnrang(points[i], points[j]);
                angL = Math.min(angL, ang);
                angU = Math.max(angU, ang);
                nangL = Math.min(nangL, nang);
                nangU = Math.max(nangU, nang);
                rangL = Math.min(rangL, rang);
                rangU = Math.max(rangU, rang);
                nrangL = Math.min(nrangL, nrang);
                nrangU = Math.max(nrangU, nrang);
                System.out.printf("%d %d\n", points[j][0], points[j][1]);
                System.out.printf("%.6f %.6f %.6f %.6f %.6f %.6f %.6f %.6f %.6f\n",
                        angU, angL, angU - angL,
                        nangU, nangL, nangU - nangL,
                        rangU, rangL, rangU - rangL);
                inbound = ((angU - angL) <= Math.PI) || ((nangU - nangL) <= Math.PI)
                || ((rangU - rangL) <= Math.PI) || ((nrangU - nrangL) <= Math.PI);
                // inbound = (angU - angL) <= Math.PI;
                // inbound = (nangU - nangL) <= Math.PI;
                // inbound = Math.sin(angU - angL) >= 0;
                j = (j + 1) % points.length;
            }
            if (inbound) ansl.add(i);
        }
        int ans[][] = new int[ansl.size()][2];
        int i = 0;
        while (ansl.size() > 0) {
            int p = ansl.remove(0);
            ans[i][0] = points[p][0];
            ans[i++][1] = points[p][1];
        }
        return ans;
    }

    private double getang(int p1[], int p2[]) {
        return Math.atan2(1.0 * (p2[1] - p1[1]), 1.0 * (p2[0] - p1[0]));
    }

    private double getnang(int p1[], int p2[]) {
        return Math.atan2(1.0 * (p2[1] - p1[1]), 1.0 * (p1[0] - p2[0]));
    }

    private double getrang(int p1[], int p2[]) {
        return Math.atan2(1.0 * (p2[0] - p1[0]), 1.0 * (p2[1] - p1[1]));
    }

    private double getnrang(int p1[], int p2[]) {
        return Math.atan2(1.0 * (p2[0] - p1[0]), 1.0 * (p1[1] - p2[1]));
    }
}
