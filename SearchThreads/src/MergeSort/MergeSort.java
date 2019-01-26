package MergeSort;

import java.util.Arrays;

public class MergeSort extends Thread {

    private int array[];
    private int left, right;

    public MergeSort(int array[]){
        this.array = array;
        this.left = 0;
        this.right = this.array.length-1;
    }

    public int[] getArray(){
        return this.array;
    }

    public void sortArray(){
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        int mid = 0;
       // System.out.println("Este es el " + this.getName() + " left " + this.left + " right " + this.right + " " + Arrays.toString(this.array));
        if(this.left < this.right){

            mid = (this.left+this.right-1) / 2;

            MergeSort thread1 = new MergeSort(Arrays.copyOfRange(this.array,this.left,mid+1));
            MergeSort thread2 = new MergeSort(Arrays.copyOfRange(this.array,mid+1,this.right+1));

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int array1[] = thread1.getArray();
            int array2[] = thread2.getArray();

           /* System.out.println("----------------------");
            System.out.println("El " + thread1.getName() + " devuelve el array " + Arrays.toString(array1)+"\n"+"El " + thread2.getName() + " devuelve el array " + Arrays.toString(array2));
            System.out.println("----------------------");*/
            int k = 0, j = 0, i = 0;

            while (k < array1.length && j < array2.length){
                if(array1[k] < array2[j]){
                    this.array[i] = array1[k];
                    k++;
                }else if(array2[j] <= array1[k]){
                    this.array[i] = array2[j];
                    j++;
                }
                i++;
            }

            while(k < array1.length){
                this.array[i] = array1[k];
                k++;
                i++;
            }

            while(j < array2.length){
                this.array[i] = array2[j];
                j++;
                i++;
            }
        }
    }
}
