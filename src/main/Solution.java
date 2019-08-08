package main;

import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        int[] resultados = new int[2];
        int[] scoreResult = new int[scores.length];
        int maiorResultado = 0;
        int menorResultado = 0;

        if(scores.length == 1){
            resultados[0] = maiorResultado;
            resultados[1] = menorResultado;
        }else {
            scoreResult[0] = scores[0];
            scoreResult[1] = scores[0];
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] > scoreResult[0]) {
                    scoreResult[0] = scores[i];
                    maiorResultado++;
                } else if (scores[i] < scoreResult[1]) {
                    scoreResult[1] = scores[i];
                    menorResultado++;
                }

            }
        }
        resultados[0] = maiorResultado;
        resultados[1] = menorResultado;

        return resultados;
    }


    public static void main(String[] args) {

        int[] scores = new int[1];
        scores[0] = 10;


        int[] result = breakingRecords(scores);

        System.out.println(result[0]+" "+result[1]);

    }
}