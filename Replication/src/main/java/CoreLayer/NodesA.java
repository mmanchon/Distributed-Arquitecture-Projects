package CoreLayer;

import Utilities.Network;

public class NodesA{
    public static void main(String args[]){
        Network network = new Network(Integer.parseInt(args[0]));

        network.setClientPort(Integer.parseInt(args[1]));

        network.setCorePorts(new int[]{
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        });

        if((args.length == 5)) {
            network.setLayer1Ports(new int[]{Integer.parseInt(args[4])});
        }

        ReplicationNodesA replication = new ReplicationNodesA(network);
        replication.startReplication();
    }
}
