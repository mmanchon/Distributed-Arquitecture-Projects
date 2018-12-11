package LinkedList;

import java.util.LinkedList;

public class SearchLinkedList extends Thread {


    private LinkedList linkedList = new LinkedList();
    private static final int MAX = 100;
    private boolean opcio;
    private int num;

    public SearchLinkedList(boolean opcio, int num) {
        this.opcio = opcio;
        this.num = num;
        this.fillLinkedList();
    }

    public void run() {
        int aux;
        if (num >= 100 || num < 0) {
            System.out.println("ERROR! Nombre invalid!");
        } else {

            if (opcio) {

                for (int i = 0; i < MAX; i++) {
                    aux = (int) linkedList.get(i);

                    if (aux == num) {
                        System.out.println("El Thread Nº1 l'ha trobat!");
                        break;
                    }
                }
            } else {

                for (int i = MAX - 1; i > 0; i--) {
                    aux = (int) linkedList.get(i);

                    if (aux == num) {
                        System.out.println("El Thread Nº2 l'ha trobat!");
                        break;
                    }
                }
            }
        }
    }

    private void fillLinkedList() {
        for (int i = 0; i < MAX; i++) {
            linkedList.add(i);
        }

    }


}
