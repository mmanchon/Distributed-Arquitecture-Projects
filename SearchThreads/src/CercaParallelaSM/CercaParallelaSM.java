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
    private long timeRun = 0;
    private boolean encontrado = false;

    public CercaParallelaSM(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int cercaParallelaSM(int aBuscar, int[] Array, int numThreads) {
        long min = Long.MAX_VALUE;
        String thread = "";

        CercaParallelaSM.array = Array;
        CercaParallelaSM.num = aBuscar;

        CercaParallelaSM.cyclicBarrier = new CyclicBarrier(numThreads);

        CercaParallelaSM threads[] = new CercaParallelaSM[numThreads];

        int result;
        int divisio = CercaParallelaSM.array.length / numThreads;
        int mod = CercaParallelaSM.array.length % numThreads;
        int aux = 0;

        for (int i = 0; i < numThreads-1; i++) {
            threads[i] = new CercaParallelaSM(aux, aux + divisio);
            threads[i].start();
            aux += divisio;
        }
        threads[numThreads-1] = new CercaParallelaSM(aux, aux + divisio+mod);
        threads[numThreads-1].start();

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
                if(threads[i].getTimeRun() < min && threads[i].getEncontrado()){
                    min = threads[i].getTimeRun();
                    thread = String.valueOf(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!(thread.equals(""))) {
            System.out.println("El thread " + thread + " ha encontrado el numero y ha tardado "+ min);
            return 0;
        }
        return -1;
    }

    public void run() {
        long initTime = 0, finalTime = 0;

        this.searchResult = -1;
        try {

            CercaParallelaSM.cyclicBarrier.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e2) {
            e2.printStackTrace();
        }
        initTime = System.nanoTime();
        for(int i = this.a; i < this.b; i++){

            if(CercaParallelaSM.array[i] == CercaParallelaSM.num){
                this.searchResult = i;
                this.encontrado = true;
                break;
            }
        }
        finalTime = System.nanoTime();
        this.timeRun = finalTime-initTime;
    }

    private int getSearchResult() {
        return this.searchResult;
    }

    public long getTimeRun(){
        return this.timeRun;
    }

    public boolean getEncontrado(){
        return this.encontrado;
    }
}
