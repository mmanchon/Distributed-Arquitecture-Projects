package Client;

import Utilities.Network;

public class Client {

    public static void main(String args[]){

        Network network = new Network(Integer.parseInt(args[0]));
        network.setCorePorts(new int[]{
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        });

        network.setLayer1Ports(new int[]{
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5])
        });

        network.setLayer2Ports(new int[]{
                Integer.parseInt(args[6]),
                Integer.parseInt(args[7])
        });

        Transactions transactions = new Transactions(network);

        Menu menu = new Menu(transactions);

        menu.manageOption(0);
    }
}
