package LinkedList;

import java.util.LinkedList;

public class SearchLinkedList extends Thread {


    private LinkedList linkedList = new LinkedList();
    private static final int MAX = 100;
    private boolean opcio;
    private int num;
    private long timeRun = 0;

    public SearchLinkedList(boolean opcio, int num) {
        this.opcio = opcio;
        this.num = num;
        this.fillLinkedList();
    }

    public void run() {
        int aux;
        long initTime = 0, finalTime = 0;

        if (num >= 100 || num < 0) {
            System.out.println("ERROR! Nombre invalid!");
        } else {

            if (opcio) {
                initTime = System.nanoTime();
                for (int i = 0; i < MAX; i++) {
                    aux = (int) linkedList.get(i);

                    if (aux == num) {
                        break;
                    }
                }
                finalTime = System.nanoTime();
            } else {
                initTime = System.nanoTime();
                for (int i = MAX - 1; i >= 0; i--) {
                    aux = (int) linkedList.get(i);

                    if (aux == num) {
                        break;
                    }
                }
                finalTime = System.nanoTime();
            }
            this.timeRun = finalTime-initTime;

        }
    }

    private void fillLinkedList() {
        for (int i = 0; i < MAX; i++) {
            linkedList.add(i);
        }

    }

    public long getTimeRun(){
        return this.timeRun;
    }


}
