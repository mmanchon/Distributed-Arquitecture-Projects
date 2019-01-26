package Client;

import Utilities.Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Transactions {

    private FileInputStream fis;

    private Integer [] array;
    private ArrayList<Integer[]> arrayOrder = new ArrayList<>();
    private  Network network;
    private boolean read_only = true;
    private final String fileName = "Transactions";
    private int layer = -1;

    public Transactions(Network network) {
        this.network = network;

        ClassLoader loader = getClass().getClassLoader();
        if (loader.getResource(fileName) == null) {
            System.out.println("Transactions does not exist.");
            System.exit(1);
        } else {
            File file = new File(loader.getResource(fileName).getFile());

            if (!(file.isFile() && file.canRead())) {
                System.out.println(file.getName() + " cannot be read from.");
                System.exit(1);
            }

            try {
                this.fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: Unable to create scanner");
            }
        }


    }

    public boolean nextTransaction() {
        try {

            char current;
            int eof;
            this.removeAllTransactions();

            if ((eof = fis.read()) != -1) {

                current = (char) eof;

                while (current != 'b') {
                    current = (char) fis.read();
                }

                this.extractLayer();
                this.extractTransactions();

                return true;
            }else{
                System.out.println("There are no more transactions!!");
                this.layer = -1;
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void extractLayer() throws IOException {
        char current;
        this.layer = 0;
        this.read_only = false;

        current = (char) fis.read();

        while (current != ',') {

            if (current == '0' || current == '1' || current == '2') {
                this.layer = current - '0';
                this.read_only = true;
            }

            current = (char) fis.read();
        }

    }

    private void extractTransactions() throws IOException {
        char current = (char) this.fis.read();

        while(current != 'c') {

            if(current == 'r'){
                this.readTransaction();
            }

            if(current == 'w'){
                this.writeTransaction();
            }

            current = (char) this.fis.read();
        }
    }

    private void removeAllTransactions(){
        this.arrayOrder.clear();
    }

    private void readTransaction() throws IOException {
        char current = (char) this.fis.read();
        int valor = 0;

        while(current < '0' || current > '9'){
            current = (char) this.fis.read();
        }


        while(current >= '0' && current <= '9'){
            valor = valor*10 + (current-'0');
            current = (char)this.fis.read();
        }

        this.array = new Integer[2];

        this.array[0] = Integer.MAX_VALUE;
        this.array[1] = valor;

        this.arrayOrder.add(this.array);


    }

    private void writeTransaction() throws IOException {
        char current = (char) this.fis.read();
        int valor = 0, key =0;
        while(current < '0' || current > '9'){
            current = (char) this.fis.read();
        }

        while(current >= '0' && current <= '9'){
            key = key*10 + (current-'0');
            current = (char)this.fis.read();
        }

        while(current < '0' || current > '9'){
            current = (char) this.fis.read();
        }

        while(current >= '0' && current <= '9'){
            valor = valor*10 + (current-'0');
            current = (char)this.fis.read();
        }

        this.array = new Integer[2];

        this.array[0] = key;
        this.array[1] = valor;

        this.arrayOrder.add(this.array);

    }

    public void printTransactions(){

        Integer writes[];
        System.out.println(this.read_only);
        System.out.println(this.layer);
        for (Object object:
             this.arrayOrder) {

            writes =  (Integer[])object;
            if(writes[0] == Integer.MAX_VALUE){
                System.out.println("Read key: " + writes[1]);
            }else {
                System.out.println("Write key:" + writes[0] + " value:" + writes[1]);
            }
        }
    }

    public int getLayer(){
        return this.layer;
    }


    public boolean getReadOnly(){ return this.read_only;}

    public void manageTransactions(){

        for (Integer[] transaction :
                this.arrayOrder) {
            switch (this.layer){
                case 0:
                    if(transaction[0] == Integer.MAX_VALUE){
                        this.doReadTransaction(transaction);
                    }else{
                        this.doWriteTransaction(transaction);
                    }
                    break;
                case 1:
                    this.doReadTransaction(transaction);
                    break;
                case 2:
                    this.doReadTransaction(transaction);
                    break;
            }
        }
    }

    private String sendTransaction(String message){
        int randomNum;
        int port;

        switch (this.layer){
            case 0:
                randomNum = ThreadLocalRandom.current().nextInt(0, this.network.getCorePorts().length);
                port = this.network.getCorePorts()[randomNum];
                this.network.sendMessage(port,message);
                break;
            case 1:
                randomNum = ThreadLocalRandom.current().nextInt(0, this.network.getLayer1Ports().length);
                port = this.network.getLayer1Ports()[randomNum];
                this.network.sendMessage(port,message);
                break;
            case 2:
                randomNum = ThreadLocalRandom.current().nextInt(0, this.network.getLayer2Ports().length);
                port = this.network.getLayer2Ports()[randomNum];
                this.network.sendMessage(port,message);
                break;
        }

        return this.network.reciveMessage();

    }



    private void doReadTransaction(Integer []transaction){
        String message;
        message = "read&" + transaction[1];
        this.parseMessage(this.sendTransaction(message));
    }

    private void doWriteTransaction(Integer []transaction){
        String message;
        message = "write&" + transaction[0] + "&" +transaction[1];
        this.parseMessage(this.sendTransaction(message));
    }

    private void parseMessage(String message){
        String[] parts = message.split("&");

        if(parts[1].equals("ACK")){
            System.out.println("Hemos hecho WRITE del nodo " + this.network.portToKey(parts[0]));
            System.out.println(parts[1]);
        }else{
            System.out.println("Hemos hecho READ del nodo "+ this.network.portToKey(parts[0]));
            if(parts[1].equals("null")){
                System.out.println("vacio");
            }else{
                System.out.println(parts[1]);
            }
        }
        System.out.println("---------------------");

    }
}
