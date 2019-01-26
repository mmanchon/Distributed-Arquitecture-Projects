package Utilities;
import java.io.IOException;
import java.net.*;

public class Network {

    private DatagramSocket mySocket;
    private InetAddress reciverHost;
    private int MAX_LEN = 100;
    private int[] corePorts;
    private int[] layer1Ports;
    private int[] layer2Ports;
    private int clientPort;
    private int myPort;

    /**
     * @param my_sender_port
     */
    public Network(int my_sender_port) {
        try {
            this.myPort = my_sender_port;
            this.mySocket = new DatagramSocket(my_sender_port);
            reciverHost = InetAddress.getLocalHost();

        } catch (SocketException e) {
            System.out.println("ERROR: sockets mal creados");
        } catch (UnknownHostException e) {
            System.out.println("ERROR: Unknown host");
        }
    }

    /**
     * @param message
     */
    public void sendMessage(int port, String message) {
        String messageBuffer = this.myPort + "&" + message;
        byte[] senderBuffer = messageBuffer.getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(senderBuffer, senderBuffer.length);
        datagramPacket.setAddress(reciverHost);

        datagramPacket.setPort(port);

        try {
            this.mySocket.send(datagramPacket);

        } catch (IOException e) {
            System.out.println("ERROR: No s'ha pogut enviar el packet!");
        }

    }

    public String reciveMessage() {
        byte[] reciverBuffer = new byte[MAX_LEN];
        DatagramPacket packetReciver = new DatagramPacket(reciverBuffer, MAX_LEN);

        try {
            this.mySocket.receive(packetReciver);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  new String(packetReciver.getData(), 0, packetReciver.getLength());
    }

    public void broadcastCoreLayer(String message){
        for (int port :
                this.corePorts) {
            sendMessage(port, message);
        }
    }

    public void broadcastLayer1(String message){
        for (int port :
                this.layer1Ports) {
            sendMessage(port, message);
        }
    }

    public void broadcastLayer2(String message){
        for (int port :
                this.layer2Ports) {
            sendMessage(port, message);
        }
    }

    public void setMySocket(int port) {

        try {
            this.mySocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public String portToKey(String port){
        switch (port){
            case "6660":
                return "Cliente";
            case "6661":
                return "A1";
            case "6662":
                return "A2";
            case "6663":
                return "A3";
            case "6664":
                return "B1";
            case "6665":
                return "B2";
            case "6666":
                return "C1";
            case "6667":
                return "C2";
        }
        return "";
    }
    public void closeSockets() {
        this.mySocket.close();
    }

    public DatagramSocket getMySocket() {
        return mySocket;
    }

    public InetAddress getReciverHost() {
        return reciverHost;
    }

    public void setReciverHost(InetAddress reciverHost) {
        this.reciverHost = reciverHost;
    }

    public void setCorePorts(int[] corePorts){
        this.corePorts = corePorts;
    }

    public void setLayer1Ports(int[] layer1Ports){
        this.layer1Ports = layer1Ports;
    }

    public void setLayer2Ports(int[] layer2Ports){
        this.layer2Ports = layer2Ports;
    }

    public int[] getCorePorts(){
        return this.corePorts;
    }

    public int[] getLayer1Ports(){
        return this.layer1Ports;
    }

    public int[] getLayer2Ports(){
        return this.layer2Ports;
    }

    public void setClientPort(int clientPort){
        this.clientPort = clientPort;
    }

    public int getClientPort(){return this.clientPort;}

    public int getMyPort(){
        return this.myPort;
    }
}