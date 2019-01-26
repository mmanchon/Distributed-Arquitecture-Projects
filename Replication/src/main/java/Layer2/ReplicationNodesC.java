package Layer2;

import Utilities.Log;
import Utilities.Network;

import java.util.HashMap;
import java.util.Map;

public class ReplicationNodesC {

    private Network network;
    private String message;
    private Map<String,String> data = new HashMap<>();
    private Log log;

    ReplicationNodesC(Network network){
        this.network = network;
        this.log = new Log(this.network.portToKey(String.valueOf(this.network.getMyPort())));

    }

    public void startReplication(){
        while(true) {
            this.message = this.network.reciveMessage();
            this.parseMessage();
        }
    }

    private void parseMessage(){
        String[] parts = message.split("&");
        System.out.println("MESSAGE " + this.message);
        if(parts[0].equals("6665")){
            this.isALayer1Message(parts);
            this.printData();
            this.sendMessageToServer();
        }else if(parts[0].equals("6660")){
            //miramos si tenemos valor
            this.message = data.getOrDefault(parts[2],"null");
            this.log.writeLog(this.network.portToKey(String.valueOf(this.network.getMyPort()))+": he recibido un READ del nodo "+this.network.portToKey(parts[0])+" con la key : "+parts[2]);
            //respondemos al cliente con la respuesta
            this.network.sendMessage(this.network.getClientPort(),message);
        }
    }

    private void isALayer1Message(String parts[]){
        for (int i = 1; i < parts.length;i++){
            this.data.put(parts[i],parts[i+1]);
            this.log.writeLog(this.network.portToKey(String.valueOf(this.network.getMyPort()))+": he recibido un WRITE del nodo "+this.network.portToKey(parts[0])+" con la key : "+parts[i]+ "y el value: "+parts[i+1]);
            i++;
        }
    }

    private void printData(){
        for (String key :
                data.keySet()) {
            System.out.println("KEY: "+ key + " Valor: " + data.get(key));
        }
    }

    private void sendMessageToServer(){
        String message = "";

        for (String key :
                this.data.keySet()) {
            message = message + key + "&" + this.data.get(key) + "&";
        }

        this.network.sendMessage(6659,message);
    }

}
