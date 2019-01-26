package hello;

import Utilities.Network;
import java.util.HashMap;
import java.util.Map;


public class UpdateInfo {

    private Network network;
    private String message;
    public static Map<String,Map<String,String>> data = new HashMap<>();
    public static String key;
    public static final Object lock = new Object();

    static boolean newInfo = false;
    UpdateInfo(Network network){
        this.network = network;
    }

    public void startListening(){
        while(true) {
           message = this.network.reciveMessage();
            this.parseMessage();
            newInfo = true;
        }
    }

    private void parseMessage(){
        String[] parts = message.split("&");
        System.out.println("MESSAGE " + this.message);
        this.portToKey(parts[0]);
        this.extractData(parts);
        //this.printData();
    }

    private void portToKey(String port){
        switch (port){
            case "6661":
                key= "A1";
                break;
            case "6662":
                key = "A2";
                break;
            case "6663":
                key = "A3";
                break;
            case "6664":
                key = "B1";
                break;
            case "6665":
                key = "B2";
                break;
            case "6666":
                key = "C1";
                break;
            case "6667":
                key = "C2";
                break;
        }

    }

    private void extractData(String parts[]){
        Map<String,String> node;
        synchronized (lock) {
            if (data.get(key) == null) {
                node = new HashMap<>();
            } else {
                node = data.get(key);
            }


            for (int i = 1; i < parts.length-1;i++){
                node.put(parts[i],parts[i+1]);
                i++;
            }

            data.put(key, node);
        }
    }

    private void printData(){
        for (String node :
                data.keySet()){
            System.out.println("Para la llave "+ node);

            for (String clau :
                    data.get(node).keySet()) {
                System.out.println("Para la key: "+ clau +" valor: " + data.get(node).get(clau));
            }

        }
    }




}
