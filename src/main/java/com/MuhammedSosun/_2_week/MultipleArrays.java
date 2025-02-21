package com.MuhammedSosun._2_week;

public class MultipleArrays {
    public static void main(String[] args) {
        int[][] matris = new int[3][3];
        matris[0][0]=1;
        matris[0][1]=2;
        matris[0][2]=3;
        matris[1][0]=4;
        matris[1][1]=5;
        matris[1][2]=6;
        matris[2][0]=7;
        matris[2][1]=8;
        matris[2][2]=9;
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                System.out.print(matris[i][j] + " ");
                
            }
            System.out.println();
            
        }



    }
}
