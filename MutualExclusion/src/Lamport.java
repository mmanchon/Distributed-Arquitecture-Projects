public class Lamport extends Thread{
    private DirectClock v;
    private int[] q; //request queue
    final int N = 3;
    private int myId = 0;
    private Network network;

    public Lamport(int id, Network network){
        this.myId = id;
        v = new DirectClock(this.N, this.myId);
        this.q = new int[N];
        for(int j =0 ; j<N;j++){
            this.q[j] = Integer.MAX_VALUE;
        }
        this.network = network;
    }

    public void run(){
        this.network.reciveMessage();
        System.out.println("LW"+this.myId+" ha recibido mensaje");
    }

    public synchronized void requestCS(){
        v.tick();
        q[this.myId] = v.getValue(this.myId);
        network.broadcastMSG("request",q[myId]);
        while(!okeyCS()){
           myWait();
        }
    }

    public synchronized void releaseCS(){
        q[myId] = Integer.MAX_VALUE;
        network.broadcastMSG("release",v.getValue(myId));
    }

    boolean okeyCS(){
        for (int j = 0; j < N; j++){
            if(isGreater(q[myId],myId,q[j],j)) {
                return false;
            }
            if (isGreater(q[myId],myId,v.getValue(j),j)){
                return false;
            }
        }
        return true;
    }

    boolean isGreater(int entry1, int pid1, int entry2, int pid2){
        if(entry2 == Integer.MAX_VALUE) return  false;
        return ((entry1 > entry2) || ((entry1 == entry2) && (pid1 > pid2)));
    }

    public synchronized void handleMsg(/*Msg*/String m, int src, String tag){
        int timeStamp = 0;//m.getMessageInt();
        v.recieveAction(src,timeStamp);
        if(tag.equals("request")){
            this.q[src] = timeStamp;
            network.sentMessage(src,"ack",v.getValue(this.myId));
        }else if(tag.equals("release")){
            this.q[src] = Integer.MAX_VALUE;
        }
        //notify();
    }

    public void myWait(){

    }
}
