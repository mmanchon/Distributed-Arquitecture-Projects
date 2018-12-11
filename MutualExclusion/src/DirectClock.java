
public class DirectClock {
    public int[] clock;
    int myId;

    public DirectClock(int numProc, int id){
        this.myId = id;
        this.clock = new int[numProc];
        for(int i= 0; i < numProc; i++)clock[i] = 0;
        clock[myId] = 1;
    }

    public int getValue(int i){
        return clock[i];
    }

    public void tick(){
        clock[myId]++;
    }

    public void sendAction(){
        //sentValue = clock[myId];
        tick();
    }

    public void recieveAction(int sender, int sentValue){
        clock[sender] = Math.max(this.clock[sender],sentValue);
        clock[this.myId] = Math.max(this.clock[this.myId],sentValue) + 1;
    }
}

