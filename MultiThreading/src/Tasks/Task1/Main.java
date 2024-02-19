package Tasks.Task1;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Print thread1 = new Print("|");
        //Print thread2 = new Print("-");

        PrintThread thread1 = new PrintThread("|");
        PrintThread thread2 = new PrintThread("-");

        thread1.start();
        thread2.start();

    }
}