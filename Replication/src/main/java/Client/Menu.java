package Client;

import Utilities.Network;

import java.util.Scanner;

public class Menu {

    private Transactions transactions;

    final String MENU = "Quina acció vols realitzar?\n1.Parser next transaction\n2.Enviar transaction\n3.Exit";

    public Menu(Transactions transactions){
        this.transactions = transactions;
    }

    public int showMenu(){
        int option;

        Scanner in = new Scanner(System.in);
        System.out.println(MENU);

        option = in.nextInt();

        return option;
    }

    public void manageOption(int option){
        while(option != 3) {

            option = this.showMenu();

            switch (option) {
                case 1:
                    if(transactions.nextTransaction()) {
                        transactions.printTransactions();
                    }
                    break;
                case 2:
                    if(transactions.getLayer() == -1){
                        System.out.println("No has recopilado ninguna transacción");
                    }else{
                        transactions.manageTransactions();
                    }
                    break;
                case 3:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Error: Opción incorrecta");
                    break;
            }
        }
    }
}
