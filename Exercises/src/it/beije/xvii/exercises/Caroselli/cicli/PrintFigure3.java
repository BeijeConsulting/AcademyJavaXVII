package cicli;

// Stampare a video la seguente figura:
//1      654321
//12      54321
//123      4321
//1234      321
//12345      21
//123456      1

public class PrintFigure3 {
    public void printFigure3() {

        int rows = 6;

        for (int i = 1; i <= rows; i++) {
            //sx part of figure
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }

            System.out.print(" ");

            //dx part of figure
            for (int j = rows - i; j >= 0; j--) {
                System.out.print(j);
            }

            System.out.println();
        }

    }
}
