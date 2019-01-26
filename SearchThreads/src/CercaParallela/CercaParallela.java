package CercaParallela;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CercaParallela extends Thread {

    private int num;
    private int[] array;
    private static CyclicBarrier cyclicBarrier;
    private int searchResult = 0;
    private long timeRun = 0;
    private boolean encontrado = false;

    public CercaParallela(int array[], int num) {
        this.array = array;
        this.num = num;
    }

    public int cercaParallela(int aBuscar, int[] Array, int numThreads) {
        long min = Long.MAX_VALUE;
        String thread = "";
        this.array = Array.clone();
        this.num = aBuscar;

        this.cyclicBarrier = new CyclicBarrier(numThreads);

        CercaParallela threads[] = new CercaParallela[numThreads];

        int result;
        int divisio = this.array.length / numThreads;
        int mod = this.array.length % numThreads;
        int aux = 0;
        for (int i = 0; i < numThreads-1; i++) {
            threads[i] = new CercaParallela(Arrays.copyOfRange(array, aux, aux + divisio), aBuscar);
            threads[i].start();
            aux += divisio;
        }
        threads[numThreads-1] = new CercaParallela(Arrays.copyOfRange(array, aux, aux + divisio+mod), aBuscar);
        threads[numThreads-1].start();

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
                //System.out.println("Thread: "+i+" MIN: "+ min + " time :"+threads[i].getTimeRun());
                if(threads[i].getTimeRun() < min && threads[i].getEncontrado()){
                    min = threads[i].getTimeRun();
                    thread = String.valueOf(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!(thread.equals(""))) {
            System.out.println("El thread " + thread + " ha encontrado el numero y ha tardado "+min);
            return 0;
        }
        return -1;
    }

    public void run() {

        long initTime = 0, finalTime = 0;

        try {
            CercaParallela.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e2) {
            e2.printStackTrace();
        }

        initTime = System.nanoTime();
        for(int i = 0; i < this.array.length; i++){
            if(this.array[i] == this.num){
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
