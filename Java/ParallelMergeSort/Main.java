import java.util.*;

public class Main {

    public static void randFill(Integer[] src) {
        Random rand = new Random();
        for(int i = 0; i < src.length; ++i)
            src[i] = rand.nextInt(15);
    }


    public static void main(String[] args){
        Integer[] array = new Integer[1000000];
        ParallelMergeSort p = new ParallelMergeSort();
        randFill(array);


        long start = System.currentTimeMillis();
        p.parallelMergeSort(array, 4);
        long finish = System.currentTimeMillis();
        System.out.println("4 THREADS MERGE SORT TIME = " + (finish-start) + " ms");


        start = System.currentTimeMillis();
        Arrays.sort(array);
        finish = System.currentTimeMillis();
        System.out.println("DEFAULT MERGE SORT TIME = " + (finish-start) + " ms");
       /* start = System.currentTimeMillis();
        p.parallelMergeSort(array, 4);
        finish = System.currentTimeMillis();
        System.out.println("4 THREAD MERGE SORT TIME = " + (finish - start) + " ms");

        start = System.currentTimeMillis();
        p.parallelMergeSort(array, 8);
        finish = System.currentTimeMillis();
        System.out.println("8 THREAD MERGE SORT TIME = " + (finish - start) + " ms");*/
    }
}
