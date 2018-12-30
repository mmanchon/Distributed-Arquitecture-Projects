import java.net.*;
import java.io.*;
import java.util.Random;


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
        Random r = new Random();
        int[] arrayPorts = new int[]{6667, 6668, 6669};
        int result;
        Lamport lamport;

        for (int i = 0; i < arrayPorts.length; i++) {
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

            //System.out.println("HW1 tiene el token");
            result = r.nextInt(arrayPorts.length);
            System.out.println("RANDOM " + result);
            //Enviem el packet
            for (int i = result; i < arrayPorts.length; i++) {
                packetSender.setPort(arrayPorts[i]);
                mySocket.send(packetSender);
            }

            for (int i = 0; i < result; i++) {
                packetSender.setPort(arrayPorts[i]);
                mySocket.send(packetSender);
            }

            for (int i = 0; i < arrayPorts.length; i++) {
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
        RAMutex raMutex;
        int[] arrayPorts = new int[]{7000, 7001,7002};

        for (int i = 0; i < arrayPorts.length; i++) {
            raMutex = new RAMutex(i, new Network(arrayPorts[i], arrayPorts));
            raMutex.start();
        }

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

            //System.out.println("HW2 tiene el token");

            for (int i = 0; i < arrayPorts.length; i++) {
                packetSender.setPort(arrayPorts[i]);
                mySocket.send(packetSender);
            }

            for (int i = 0; i < arrayPorts.length; i++) {
                mySocket.receive(packetReciver);
            }

            packetSender.setPort(reciver_port);

        }
    }
}
