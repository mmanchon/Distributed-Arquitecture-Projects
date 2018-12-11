import java.io.IOException;
import java.net.*;

public class Network {

    private DatagramSocket mySocket;
    private DatagramPacket senderPacket;
    private DatagramPacket reciverPacket;
    private InetAddress reciverHost;
    private int otherPorts[];
    final int MAX_LEN = 100;

    /**
     * @param my_sender_port
     * @param otherPorts
     */
    public Network(int my_sender_port, int otherPorts[]) {
        try {

            this.mySocket = new DatagramSocket(my_sender_port);
            reciverHost = InetAddress.getLocalHost();
            this.otherPorts = otherPorts;

        } catch (SocketException e) {
            System.out.println("ERROR: sockets mal creados");
        } catch (UnknownHostException e) {
            System.out.println("ERROR: Unknown host");
        }
    }

    /**
     * @param message
     */
    public void sentMessage(int port, String message, int clk) {
        byte[] senderBuffer = message.getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(senderBuffer, senderBuffer.length);
        datagramPacket.setAddress(reciverHost);

        datagramPacket.setPort(port);
        try {
            this.mySocket.send(datagramPacket);
        } catch (IOException e) {
            System.out.println("ERROR: No s'ha pogut enviar el packet!");
        }

    }

    public void broadcastMSG(String message, int clk) {
        for (int port :
                this.otherPorts) {
            this.sentMessage(port, message, clk);
        }
    }

    public void reciveMessage() {
        byte[] reciverBuffer = new byte[MAX_LEN];
        DatagramPacket packetReciver = new DatagramPacket(reciverBuffer, MAX_LEN);
        try {
            this.mySocket.receive(packetReciver);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setTimeOut(int num) {
        try {
            this.mySocket.setSoTimeout(num);
        } catch (SocketException e) {
            System.out.println("ERROR: No he pogut assignar timeout");
        }
    }

    public void closeSockets() {
        this.mySocket.close();
    }

    public DatagramSocket getMySocket() {
        return mySocket;
    }

    public void setMySocket(int port) {

        try {
            this.mySocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public DatagramPacket getSenderPacket() {
        return senderPacket;
    }

    public void setSenderPacket(DatagramPacket senderPacket) {
        this.senderPacket = senderPacket;
    }

    public DatagramPacket getReciverPacket() {
        return reciverPacket;
    }

    public void setReciverPacket(DatagramPacket reciverPacket) {
        this.reciverPacket = reciverPacket;
    }

    public InetAddress getReciverHost() {
        return reciverHost;
    }

    public void setReciverHost(InetAddress reciverHost) {
        this.reciverHost = reciverHost;
    }

    public int[] getOtherPorts() {
        return otherPorts;
    }

    public void setOtherPorts(int[] otherPorts) {
        this.otherPorts = otherPorts;
    }
}
