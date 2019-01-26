package Layer2;

import Utilities.Network;

public class NodesC {

    public static void main(String args[]){
        Network network = new Network(Integer.parseInt(args[0]));

        network.setClientPort(Integer.parseInt(args[1]));

        network.setLayer1Ports(new int[]{
                Integer.parseInt(args[2])
        });

        ReplicationNodesC replication = new ReplicationNodesC(network);
        replication.startReplication();
    }
}
