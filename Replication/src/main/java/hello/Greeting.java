package hello;

import java.util.Map;

public class Greeting {

    private String key;
    private Map<String,String> value;


    public Greeting(String content, Map<String,String> value) {
        this.key = content;
        this.value = value;
    }

    public String getContent() {
        return key;
    }

    public Map<String,String> getValue(){return this.value;}
}
