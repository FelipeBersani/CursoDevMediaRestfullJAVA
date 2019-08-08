package main;

import java.util.List;

public class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        int calculo1 = 0;
        int calculo2 = 0;
        // Write your code here
        for(int i=0; i<=2; i++){
            for(int j=2; i>=0; j--){
                if(i+j==2){
                    calculo1+=arr.get(i).get(j);
                }else if(i==j){
                    calculo2+=arr.get(i).get(j);
                }
            }
        }
        System.out.println(calculo1);
        System.out.println(calculo2);
        System.out.println(calculo1-calculo2);
        return calculo1-calculo2;
    }

}
