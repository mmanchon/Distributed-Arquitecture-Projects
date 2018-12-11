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

    public CercaParallela(int array[], int num) {
        this.array = array;
        this.num = num;
    }

    public int cercaParallela(int aBuscar, int[] Array, int numThreads) {
        this.array = Array.clone();
        this.num = aBuscar;

        this.cyclicBarrier = new CyclicBarrier(numThreads);

        CercaParallela threads[] = new CercaParallela[numThreads];

        int result;
        int divisio = this.array.length / numThreads;
        int aux = 0;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CercaParallela(Arrays.copyOfRange(array, aux, aux + divisio), aBuscar);
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

        int index = -1, number = 0;

        try {
            CercaParallela.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e2) {
            e2.printStackTrace();
        }

        for(int i = 0; i < this.array.length; i++){
            if(this.array[i] == this.num){
                index = i;
                break;
            }
        }

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(Thread.currentThread().getName());

        while(m.find()) {
            number = Integer.parseInt(m.group());
        }

        if(index < 0){
            this.searchResult = -1;
        }else{
            this.searchResult = (this.array.length*(number-1)) + index;
        }
    }

    private int getSearchResult() {
        return this.searchResult;
    }
}
