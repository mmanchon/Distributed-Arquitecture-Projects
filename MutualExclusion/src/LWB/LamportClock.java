package LWB;

public class LamportClock {
    int c;
    public LamportClock(){
        c = 1;
    }
    public  int getValue(){
        return c;
    }
    public void tick(){
        c = c + 1;
    }
    public void sendAction(){
        //include c in message
        c = c + 1;
    }
    public void reciveAction(int src, int sentValue){
        c = Math.max(c,sentValue)+1;
    }
}
