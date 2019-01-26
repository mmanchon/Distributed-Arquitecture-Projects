package Layer1;

import Utilities.Network;

import java.util.HashMap;
import java.util.Map;

public class NodesB {

    private static Map<String,String> data = new HashMap<>();


    public static void main(String args[]){

        Network network = new Network(Integer.parseInt(args[0]));

        network.setClientPort(Integer.parseInt(args[1]));

        network.setCorePorts(new int[]{Integer.parseInt(args[2])});

        if((args.length == 5)) {
            network.setLayer2Ports(new int[]{
                    Integer.parseInt(args[3]),
                    Integer.parseInt(args[4]),
            });

            TimerThread timer = new TimerThread(network, data);
            timer.start();
        }

        ReplicationNodesB replication = new ReplicationNodesB(network,data);
        replication.startReplication();


    }
}
