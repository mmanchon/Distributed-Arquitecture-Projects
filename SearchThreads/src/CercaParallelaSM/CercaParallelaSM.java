package CercaParallelaSM;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CercaParallelaSM extends Thread {

    private static int num;
    private static int[] array;
    private int a,b =0;
    private static CyclicBarrier cyclicBarrier;
    private int searchResult = 0;

    public CercaParallelaSM(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int cercaParallelaSM(int aBuscar, int[] Array, int numThreads) {
        CercaParallelaSM.array = Array;
        CercaParallelaSM.num = aBuscar;

        CercaParallelaSM.cyclicBarrier = new CyclicBarrier(numThreads);

        CercaParallelaSM threads[] = new CercaParallelaSM[numThreads];

        int result;
        int divisio = CercaParallelaSM.array.length / numThreads;
        int aux = 0;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CercaParallelaSM(aux, aux + divisio);
            threads[i].start();
            aux += divisio;
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
                result = threads[i].getSearchResult();

                if (result > 0) {
                    return result;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    public void run() {

        this.searchResult = -1;
        try {

            CercaParallelaSM.cyclicBarrier.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e2) {
            e2.printStackTrace();
        }

        for(int i = this.a; i < this.b; i++){

            if(CercaParallelaSM.array[i] == CercaParallelaSM.num){
                this.searchResult = i;
                break;
            }
        }
    }

    private int getSearchResult() {
        return this.searchResult;
    }
}
