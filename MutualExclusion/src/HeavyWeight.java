import java.net.*;
import java.io.*;


public class HeavyWeight extends Thread {

    private boolean type;
    final int MAX_LEN = 100;

    HeavyWeight(boolean type) {
        this.type = type;
    }

    public void run() {
        try {
            if (this.type) {
                heavyWeight1(6665, 6666);
            } else {
                heavyWeight2(6666, 6665);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }


    private void heavyWeight1(int my_port, int reciver_port) throws IOException {
        //creem el socket per establir la connexió amb l'altre heavy weight
        DatagramSocket mySocket = new DatagramSocket(my_port);

        int[] arrayPorts = new int[]{6667,6668,6669};

        Lamport lamport;

        for(int i=0; i<arrayPorts.length; i++){
            lamport = new Lamport(i, new Network(arrayPorts[i], arrayPorts));
            lamport.start();
        }

        //El missatge que enviarem a l'altre node el passem a bytes
        String message = "TOKEN";
        byte[] senderBuffer = message.getBytes();
        byte[] reciverBuffer = new byte[MAX_LEN];

        //Agafem la direcció com a localhost
        InetAddress reciverHost = InetAddress.getLocalHost();

        //Creem el packet a enviar pel socket
        DatagramPacket packetSender = new DatagramPacket(senderBuffer, senderBuffer.length, reciverHost, reciver_port);
        DatagramPacket packetReciver = new DatagramPacket(reciverBuffer, MAX_LEN);

        while (true) {



            mySocket.receive(packetReciver);

            System.out.println("HW1 tiene el token");

            //Enviem el packet
            for(int i = 0; i < arrayPorts.length; i++){
                packetSender.setPort(arrayPorts[i]);
                mySocket.send(packetSender);
            }

            for (int i=0; i < arrayPorts.length;i++){
                mySocket.receive(packetReciver);
            }

            //Enviem al HW2
            packetSender.setPort(reciver_port);
            mySocket.send(packetSender);

        }

    }


    private void heavyWeight2(int my_port, int reciver_port) throws IOException {
        //creem el socket per establir la connexió amb l'altre heavy weight
        DatagramSocket mySocket = new DatagramSocket(my_port);
       // DatagramSocket reciverSocket;// = new DatagramSocket(my_reciver_port);

        //El missatge que enviarem a l'altre node. El passem a bytes
        String message = "TOKEN";
        byte[] senderBuffer = message.getBytes();
        byte[] reciverBuffer = new byte[MAX_LEN];

        //Agafem la direcció com a localhost
        InetAddress reciverHost = InetAddress.getLocalHost();

        //Creem el packet a enviar pel socket
        DatagramPacket packetSender = new DatagramPacket(senderBuffer, senderBuffer.length, reciverHost, reciver_port);
        DatagramPacket packetReciver = new DatagramPacket(reciverBuffer, MAX_LEN);

        while (true) {

            mySocket.send(packetSender);

            //Enviem el packet
            mySocket.receive(packetReciver);

            System.out.println("HW2 tiene el token");

        }
    }
}
