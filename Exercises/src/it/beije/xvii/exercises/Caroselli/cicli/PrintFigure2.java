package cicli;

//  Stampare a video la seguente figura:
//#
//##
//###
//####
//#####
//######

public class PrintFigure2 {
    public void printFigure2() {
        int rows = 6;
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }
}
