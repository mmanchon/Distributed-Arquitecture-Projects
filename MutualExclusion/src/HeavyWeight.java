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
        DatagramSocket senderSocket; //= new DatagramSocket(my_port);
        DatagramSocket reciverSocket;// = new DatagramSocket(my_reciver_port);

        Lamport lamport1 = new Lamport(0, new Network(6667, new int[]{6668, 6669}));
        Lamport lamport2 = new Lamport(1, new Network(6668, new int[]{6667, 6669}));
        Lamport lamport3 = new Lamport(2, new Network(6669, new int[]{6667, 6668}));

        lamport1.start();
        lamport2.start();
        lamport3.start();

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



            reciverSocket = new DatagramSocket(my_port);
            reciverSocket.receive(packetReciver);
            reciverSocket.close();

            System.out.println("HeavyWeight1 tiene el token");

            //Enviem el packet
            senderSocket = new DatagramSocket(my_port);

            //Enviem al LW0
            packetSender.setPort(6667);
            senderSocket.send(packetSender);

            //Enviem al LW1
            packetSender.setPort(6668);
            senderSocket.send(packetSender);

            //Enviem al LW2
            packetSender.setPort(6669);
            senderSocket.send(packetSender);

            //Enviem al HW2
            packetSender.setPort(reciver_port);
            senderSocket.send(packetSender);

            //Tanquem el socket
            senderSocket.close();

            /*try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

        /*senderSocket.close();
        reciverSocket.close();
*/
    }


    private void heavyWeight2(int my_port, int reciver_port) throws IOException {
        //creem el socket per establir la connexió amb l'altre heavy weight
        DatagramSocket senderSocket; //= new DatagramSocket(my_sender_port);
        DatagramSocket reciverSocket;// = new DatagramSocket(my_reciver_port);

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

            senderSocket = new DatagramSocket(my_port);
            senderSocket.send(packetSender);
            senderSocket.close();

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Enviem el packet
            reciverSocket = new DatagramSocket(my_port);
            reciverSocket.receive(packetReciver);
            reciverSocket.close();

            System.out.println("HeavyWeight2 tiene el token");

            /*try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
