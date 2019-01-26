package LWB;

import Utilities.Network;

public class MainRA {

    public static void main(String args[]){
        int[] arrayPorts = new int[]{7000, 7001};
        Network network = new Network(
                arrayPorts[Integer.parseInt(args[0])],
                arrayPorts
        );

        RAMutex ra = new RAMutex(Integer.parseInt(args[0]), network);
        ra.start();
    }
}
