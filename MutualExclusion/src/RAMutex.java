import java.net.DatagramPacket;
import java.util.LinkedList;

public class RAMutex extends Thread {

    private int myts;
    private LamportClock c = new LamportClock();
    private LinkedList pendingQ = new LinkedList();
    private int numOkay = 0;

    private Network network;
    private int myId;

    public RAMutex(int myId, Network network) {
        this.myts = Integer.MAX_VALUE;
        this.network = network;
        this.myId = myId;
    }

    public void run() {
        String message = "";
        DatagramPacket datagramPacket;

        while (true) {

            while (!(message.equals("TOKEN"))) {
                datagramPacket = this.network.reciveMessage();
                message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            }

            this.requestCS();

            for (int i = 0; i < 10; i++) {

                System.out.println("Soc el procés lightweight B" + (this.myId + 1));

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            this.releaseCS();
            this.network.sendTokenMessage(6666);
            message = "";
        }
    }

    public synchronized void requestCS() {
        c.tick();
        myts = c.getValue();
        this.network.broadcastMSG("request", myts);
        numOkay = 0;
        while (numOkay < this.network.getOtherPorts().length - 1) {
            this.myWait();
        }
    }

    public synchronized void releaseCS() {
        myts = Integer.MAX_VALUE;
        while (!pendingQ.isEmpty()) {
            int src = (int) this.pendingQ.removeFirst();
            this.network.sentMessage(src, "okay", c.getValue());
        }
    }

    public synchronized void handleMsg(int timeStamp, int src, String tag) {

        int idSrc = this.network.getPosition(src);

        c.reciveAction(idSrc, timeStamp);

        if (tag.equals("request")) {
            if ((this.myts == Integer.MAX_VALUE)
                    || (timeStamp < this.myts)
                    || ((timeStamp == this.myts) && (idSrc < this.myId))) {

                this.network.sentMessage(src, "okay", c.getValue());
            } else {
                this.pendingQ.add(src);
            }
        } else if (tag.equals("okay")) {
            this.numOkay++;
            if (numOkay == this.network.getOtherPorts().length - 1) {
                //no se que hacer aquí
            }
        }
    }

    public void myWait() {
        DatagramPacket datagramPacket;

        datagramPacket = this.network.reciveMessage();

        if (datagramPacket != null) {

            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            String[] parts = message.split("&");

            int portSender = Integer.valueOf(parts[0]);
            int timeStamp = Integer.valueOf(parts[2]);

            this.handleMsg(timeStamp, portSender, parts[1]);

        }
    }
}
