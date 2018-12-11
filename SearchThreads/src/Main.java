import CercaParallelaSM.CercaParallelaSM;
import LinkedList.SearchLinkedList;
import CercaParallela.CercaParallela;
import MergeSort.MergeSort;

import java.util.Arrays;

public class Main {

    private static final int NUMBER = 49;
    private static final int MAX = 97;
    private static final int NUM_THREADS = 2;

    public static void main(String args[]){
        int array[] = new int[MAX];
        int result;

        switch(Integer.parseInt(args[0])){
            case 1:
                SearchLinkedList searchLinkedList = new SearchLinkedList(true, NUMBER);
                SearchLinkedList searchLinkedList2 = new SearchLinkedList(false, NUMBER);
                searchLinkedList.start();
                searchLinkedList2.start();
                break;
            case 2:

                for(int i = 0; i < MAX; i++){
                    System.out.print(" Indice: " + i + " Value: " + (MAX-i));
                    array[i] = MAX-i;
                }
                System.out.println();
                CercaParallela cercaParallela = new CercaParallela(array,NUMBER);

                result = cercaParallela.cercaParallela(NUMBER,array,NUM_THREADS);

                System.out.println("El metodo CercaParallela ha devuelto " + result);
                break;
            case 3:


                for(int i = 0; i < MAX; i++){
                    System.out.print(" Indice: " + i + " Value: " + (MAX-i));
                    array[i] = MAX-i;
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
                mergeSort.sortArray();
                int arraySorted[] = mergeSort.getArray();
                System.out.println("Sorted Array: " + Arrays.toString(arraySorted));
                break;
            default:
                System.out.println("Argument no valid!");
                break;
        }

    }

}
