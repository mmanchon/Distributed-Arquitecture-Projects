import CercaParallelaSM.CercaParallelaSM;
import LinkedList.SearchLinkedList;
import CercaParallela.CercaParallela;
import MergeSort.*;

import java.util.Arrays;

public class Main {

    private static final int NUMBER = 50;
    private static final int MAX = 100;
    private static final int NUM_THREADS = 9;

    public static void main(String args[]){
        int array[] = new int[MAX];
        int result;
        long initTime = 0, finalTime = 0;

        switch(Integer.parseInt(args[0])){
            case 1:
                SearchLinkedList searchLinkedList = new SearchLinkedList(true, NUMBER);
                SearchLinkedList searchLinkedList2 = new SearchLinkedList(false, NUMBER);
                searchLinkedList.start();
                searchLinkedList2.start();
                try {
                    searchLinkedList.join();
                    searchLinkedList2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(searchLinkedList.getTimeRun() > searchLinkedList2.getTimeRun()){
                    System.out.println("El Thread Nº2 l'ha trobat!");
                }else{
                    System.out.println("El Thread Nº1 l'ha trobat!");

                }
                break;
            case 2:

                for(int i = 0; i < MAX; i++){
                    array[i] = i;
                }
                CercaParallela cercaParallela = new CercaParallela(array,NUMBER);

                result = cercaParallela.cercaParallela(NUMBER,array,NUM_THREADS);

                System.out.println("El metodo CercaParallela ha devuelto " + result);

                break;
            case 3:


                for(int i = 0; i < MAX; i++){
                   // System.out.print(" Indice: " + i + " Value: " + (MAX-i));
                    array[i] = i;
                }
                System.out.println();
                CercaParallelaSM cercaParallelaSM = new CercaParallelaSM(0,0);

                result = cercaParallelaSM.cercaParallelaSM(NUMBER,array,NUM_THREADS);

                System.out.println("El metodo CercaParallela ha devuelto " + result);
                break;

            case 4:
                for(int i = 0; i < MAX; i++){
                    array[i] = MAX-i;
                }
                System.out.println("Array: " + Arrays.toString(array));
                MergeSort mergeSort = new MergeSort(array);
                initTime = System.nanoTime();
                mergeSort.sortArray();
                finalTime = System.nanoTime();
                System.out.println("Ha tardado " + (finalTime-initTime));
                int arraySorted[] = mergeSort.getArray();
                System.out.println("Sorted Array: " + Arrays.toString(arraySorted));
                break;
            case 5:
                for(int i = 0; i < MAX; i++){
                    array[i] = MAX-i;
                }
                System.out.println("Array: " + Arrays.toString(array));
                MergeSortSequencial mergeSortSequencial = new MergeSortSequencial();
                initTime = System.nanoTime();
                mergeSortSequencial.sort(array,0,array.length-1);
                finalTime = System.nanoTime();
                System.out.println("Ha tardado " + (finalTime-initTime));
            default:
                System.out.println("Argument no valid!");
                break;
        }

    }

}
