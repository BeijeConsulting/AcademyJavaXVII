package stringhe;

import com.sun.media.jfxmediaimpl.HostUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RockPaperScissors {

    //  Scrivere un programma che chieda agli utenti due stringhe in ingresso,
    //  le stringhe possono valere solo: “carta”, “forbice” o “sasso”. Il programma dovrà quindi effettuare
    //  i dovuti controlli e dichiarare il vincitore secondo le note regole della “morra cinese”
    //  forbice vince su carta, carta vince su sasso, sasso vince su forbice).
    public static void rockPaperScissors() {

        List<String> players = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            System.out.println("Inserisci rock per scegliere sasso, paper per scegliere carta o scissors per scegliere forbici, scelta numero : " + i);
            Scanner scanner = new Scanner(System.in);
            String selectedChoice = scanner.nextLine();
            players.add(selectedChoice);


            //merged switch
            switch (selectedChoice.toLowerCase()) {
                case "rock":
                case "paper":
                case "scissors":
                    players.set(i, selectedChoice.toLowerCase());
                    break;
                default:
                    System.out.println("Scelta non valida, riprova");
                    i--;
                    break;
            }
        }

        if (players.get(0).equals("rock") & players.get(1).equals("paper")) {
            System.out.println("Vince il giocatore 1!");
        }
        if (players.get(0).equals("paper") & players.get(1).equals("rock")) {
            System.out.println("Vince il giocatore 2!");
        }
        if (players.get(0).equals("rock") & players.get(1).equals("scissors")) {
            System.out.println("Vince il giocatore 1!");
        }
        if (players.get(0).equals("scissors") & players.get(1).equals("rock")) {
            System.out.println("Vince il giocatore 2!");
        }
        if (players.get(0).equals("paper") & players.get(1).equals("scissors")) {
            System.out.println("Vince il giocatore 2!");
        }
        if (players.get(0).equals("scissors") & players.get(1).equals("paper")) {
            System.out.println("Vince il giocatore 1!");
        }
        if (players.get(0).equals("rock") & players.get(1).equals("rock") || players.get(0).equals("scissors") & players.get(1).equals("scissors") || players.get(0).equals("paper") & players.get(1).equals("paper")) {
            System.out.println("Parità!");
        }

    }


}
