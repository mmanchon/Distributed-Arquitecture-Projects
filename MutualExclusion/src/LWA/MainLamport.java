package LWA;

import Utilities.Network;

public class MainLamport {
    public static void main(String args[]){

        int[] arrayPorts = new int[]{6667, 6668, 6669};

        Network network = new Network(
                arrayPorts[Integer.parseInt(args[0])],
                arrayPorts
        );

        Lamport lamport = new Lamport(Integer.parseInt(args[0]), network);
        lamport.start();
    }
}
