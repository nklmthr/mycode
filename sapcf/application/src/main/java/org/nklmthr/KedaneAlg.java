package org.nklmthr;

public class KedaneAlg {
    public static void main(String[] args) {
        solve(9, 14, new int[]{1, 2, 4, 4, 5, 5, 5, 7, 5});
    }

    public static int solve(int A, int B, int[] C) {
        int max_ending_here = 0;
        int max_so_far = 0;
        for (int i = 0; i < A; i++) {
            max_ending_here += C[i];
            if (max_ending_here <= B && max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
            }
            if (max_ending_here > B) {
                max_ending_here = 0;
            }
            System.out.println("i="+i+", C[i]="+C[i]+",max_ending_here="+max_ending_here+",max_so_far="+max_so_far);
        }
        return max_so_far;
    }
}
