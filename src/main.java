import objects.*;
import algorithms.*;
import utils.*;
import java.util.*;
import java.util.regex.*;
import java.util.Scanner;

class MainMenu{
    public static void main(String argc[]){
       int d=-1;
        try(Scanner sc = new Scanner(System.in)) {
            do {
                // выбираем метод ввода
                System.out.println("""
                        select the input method:\n
                                  1 - Console \n
                                  2 - Random \n
                                  3 - File \n
                                  0 - Exit\n 
                        """);
                if (!sc.hasNextInt()) {
                    System.out.println("Incorrect\n");
                    continue;
                }
                d = sc.nextInt();
                //создаем объект обработчика
                Manager m = new Manager();
                List<Object> toSort = new ArrayList<>();
                if (d == 0) {
                    System.out.println("Bye!");
                    break;
                } else if (d < 0 || d > 3) {
                    System.out.println("""
                            Incorrect. Try again 
                            """);
                } else {
                    //объект обработчика должен вернуть список для сортировки
                    toSort = m.processChoice(d);
                    if(toSort==null){
                        System.out.println("Bye!");
                        break;
                    }
                    //объект обработчика применяет сортировку
                    m.applyAlgos(toSort);
                    System.out.println("""
                                    Again?
                                    0 - EXIT
                                    1 - Again 
                            """);
                    if (!sc.hasNextInt()) {
                        break;
                    }
                    d = sc.nextInt();
                    if (d == 0) {
                        break;
                    }

                }

            } while (d != 0);
        }
    }
}
