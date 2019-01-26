package HW;

import HW.HeavyWeight;

import java.io.IOException;

public class Main {

    public static void main(String []args){

        HeavyWeight heavyWeight = new HeavyWeight(true);

        try {

            if(Integer.parseInt(args[0]) == 6665){
                heavyWeight.heavyWeight1(6665,6666);

            }else{
                heavyWeight.heavyWeight2(6666,6665);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
