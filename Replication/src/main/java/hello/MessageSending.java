package hello;

public class MessageSending implements Runnable{
    private GreetingController listener;

    public MessageSending(GreetingController listener) {
        this.listener = listener;

    }

    @Override
    public void run() {
        while (true) {
            if(UpdateInfo.newInfo) {
                UpdateInfo.newInfo = false;
                listener.fireGreeting();
            }
        }
    }
}
