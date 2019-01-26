package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Nodo greeting(){
        Nodo nodo;
        MessageSending messageSending = new MessageSending(this);
        new Thread(messageSending).start();
        int i = 0;

        synchronized (UpdateInfo.lock) {
            Greeting array[] = new Greeting[UpdateInfo.data.size()];

            for (String key :
                    UpdateInfo.data.keySet()) {
                array[i] = new Greeting(key, UpdateInfo.data.get(key));
                i++;
            }

            nodo = new Nodo(array);
        }
        return nodo;
    }

    public void fireGreeting() {
        int i =0;

        synchronized (UpdateInfo.lock) {
            Greeting array[] = new Greeting[UpdateInfo.data.size()];

            for (String key :
                    UpdateInfo.data.keySet()) {
                array[i] = new Greeting(key, UpdateInfo.data.get(key));
                i++;
            }

            this.template.convertAndSend("/topic/greetings", new Nodo(array));

        }
    }




}
