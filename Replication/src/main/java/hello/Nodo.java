package hello;

import java.util.Map;

public class Nodo {

    private Greeting data[];

    public Nodo(Greeting data[]){
        this.data = data;
    }

    public Greeting[] getData(){
        return this.data;
    }

    public void setData(Greeting data[]){
        this.data = data;
    }
}
