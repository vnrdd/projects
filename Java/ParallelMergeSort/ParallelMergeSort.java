
import java.util.*;

public class ParallelMergeSort {
    public static class Threads extends Thread {
        private int start;
        private int finish;
        public Comparable[] subArray;

        public Threads(int a, int b){
            start = a;
            finish = b;
            subArray = new Comparable[b-a];
        }

        public int getStart(){
            return start;
        }

        public int getFinish(){
            return finish;
        }
        
        public void run(){
            mergeSort(subArray);
        }

        public Comparable[] getSubArray(){
            return subArray;
        }

        public void mergeSort(Comparable[] array) {
            if (array.length > 1) {
                Comparable[] left = leftHalf(array);
                Comparable[] right = rightHalf(array);

                mergeSort(left);
                mergeSort(right);

                merge(array, left, right);
            }
        }

        public Comparable[] leftHalf(Comparable[] array) {
            int size1 = array.length / 2;
            Comparable[] left = new Comparable[size1];
            for (int i = 0; i < size1; i++) {
                left[i] = array[i];
            }
            return left;
        }

        public Comparable[] rightHalf(Comparable[] array) {
            int size1 = array.length / 2;
            int size2 = array.length - size1;
            Comparable[] right = new Comparable[size2];
            for (int i = 0; i < size2; i++) {
                right[i] = array[i + size1];
            }
            return right;
        }

        public void merge(Comparable[] result, Comparable[] left, Comparable[] right) {
            int i1 = 0, i2 = 0;

            for (int i = 0; i < result.length; i++) {
                if (i2 >= right.length || (i1 < left.length && (left[i1].compareTo(right[i2])) <= 0)) {
                    result[i] = left[i1];
                    i1++;
                } else {
                    result[i] = right[i2];
                    i2++;
                }
            }
        }
    }

    public Comparable[] parallelMergeSort(Comparable[] toSort, int threadsCount){
         Threads[] threads = new Threads[threadsCount];
         splitArray(toSort, threads, threadsCount);
        for(int i = 0; i < threads.length; ++i)
            threads[i].start();

        for (int i = 0; i < threads.length; ++i) {
            if (threads[i] != null) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Comparable[] res = threads[0].getSubArray();
        for(int i = 1; i < threadsCount; ++i){
            res = finalMerge(res, threads[i].getSubArray());
        }

        return res;
    }

    public static void splitArray(Comparable[] toSplit, Threads[] threads, int parts) {
        int avgSize = toSplit.length / parts;
        int toFill = parts;
        int remain = toSplit.length;
        int toLeft = 0;
        int i = 0;
        while (toFill != 0) {
            threads[i] = new Threads(toLeft, toLeft+avgSize-1);
            toLeft += avgSize;
            --toFill;
            remain -= avgSize;
            if (toFill == 0)
                break;
            avgSize = remain / toFill;
            ++i;
        }

        int start = 0;
        for (int j = 0; j < threads.length; ++j) {
                threads[j].subArray = Arrays.copyOfRange(toSplit, threads[j].getStart(), threads[j].getFinish()+1);
    }
}

    public static Comparable[] finalMerge(Comparable[] a, Comparable[] b) {
        Comparable[] result = new Comparable[a.length + b.length];
        int i = 0, j = 0, r = 0;
        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) <= 0) {
                result[r] = a[i];
                ++i; ++r;
            } else {
                result[r] = b[j];
                ++j; ++r;
            }
            if (i == a.length) {
                while (j < b.length) {
                    result[r] = b[j];
                    ++r; ++j;
                }
            }
            if (j == b.length) {
                while (i < a.length) {
                    result[r] = a[i];
                    ++r; ++i;
                }
            }
        }
        return result;
    }    
}