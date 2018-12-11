public class Main {

    public static void main(String []args){
        HeavyWeight heavyWeight = new HeavyWeight(true);
        HeavyWeight heavyWeight1 = new HeavyWeight(false);

        heavyWeight.start();
        heavyWeight1.start();
    }
}
