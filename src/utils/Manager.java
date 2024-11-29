package utils;
import java.util.*;

import algorithms.*;
import objects.*;

// обработчик для применения сортировки, бинарного поиска, выбор способа ввода
public class Manager{
    private static Scanner sc = new Scanner(System.in);

    public static void applyAlgos(List<Object> toSort){
        if(toSort == null){
            System.out.println("Error\n");
        }else{
            System.out.println("\nBefore sorting: ");
            for(Object o: toSort)System.out.println(o);
            System.out.println("""
                       
                       Select sorting strategy: 
                       
                                0 - recursive quickSort\n
                                1 - non-recursive insertionSort\n
                                default - non-recursive insertionSort\n
                        """);

            SortContext context;
            int st = sc.nextInt();
            switch(st){
                case 0-> context =new SortContext(new QuickSortAlgo());
                case 1-> context =new SortContext(new InsertionSortAlgo());
                default->context =new SortContext(new InsertionSortAlgo());
            }

            if(toSort.get(0) instanceof Human) context.sort((List<Human>)(Object) toSort);
            else if(toSort.get(0) instanceof Barrel) context.sort((List<Barrel>)(Object) toSort);
            else if(toSort.get(0) instanceof Animal) context.sort((List<Animal>)(Object) toSort);

            System.out.println("\nAfter sorting: ");
            for(Object o: toSort)System.out.println(o);

                Type t=Type.HUMAN;
                if(toSort.get(0) instanceof Human){
                    t=Type.HUMAN;
                }else if(toSort.get(0) instanceof Animal){
                    t=Type.ANIMAL;
                }else if(toSort.get(0) instanceof Barrel){
                    t=Type.BARREL;
                }
                Object toFind=null;
                do {
                    sc.nextLine();
                    System.out.println("""
                           
                           Enter the type-key to find:
                               in format: 
                                    for ANIMAL -> ["Breed(word) Color(word) does it have hair: true/false(word)"]
                                    for BARREL -> ["Filler(word) Material(word) Volume(integer)"]
                                    for HUMAN ->  ["Age(integer) Surname(word) Gender: WOMAN/MAN(word)"]
                           
                           ->   or type q for exit  <-;
                            """);
                    String s = sc.nextLine();
                    if(s.equals("q")) return;
                    else{

                        toFind = new BuildNewObject().create(t, s);
                        if(toFind != null) break;
                        else{
                            System.out.println("""
                                    try again.
                                    press Enter.
                            """);
                        }
                    }

                }while(true);
                BinarySearchAlgo bs = new BinarySearchAlgo();
                int id = -1;
                switch (t) {
                    case ANIMAL -> id = bs.findByKey((List<Animal>) (Object) toSort, (Animal) (Object) toFind);
                    case HUMAN -> id = bs.findByKey((List<Human>) (Object) toSort, (Human) (Object) toFind);
                    case BARREL -> id = bs.findByKey((List<Barrel>) (Object) toSort, (Barrel) (Object) toFind);
                }
                System.out.println(id == -1 ? "not found" : "key on index " + id);

        }
    }
    //обработать изначальный выбор способа ввода
    public List<Object> processChoice(int d){
        List<Object> toSort=new ArrayList<>();
        if(d==1 || d==2){
            int type =-1;
            do{
                System.out.println("""
                       Types :\n
                                0 - Animal\n
                                1 - Barrel\n
                                2 - Human
                        """);
                type = sc.nextInt();
                if(type>=0 && type<=2) break;
            }while(true);

            Type t = type==0?
                    Type.ANIMAL:
                    type==1?
                    Type.BARREL:
                    Type.HUMAN;
            int size=-1;
            do{

                System.out.println("Size? (in range [2:100])");

                size=sc.nextInt();
                if(size>1 && size<101) break;
            }while(true);

            if(d==1){
                BuildNewObject objB=new BuildNewObject();
                String str;
                sc.nextLine();
                for(int i=0;i<size;){
                    System.out.println("enter a string in the format: ");
                    switch(t){
                        case ANIMAL -> System.out.println("for ANIMAL ->  [\"Breed(word) Color(word) does it have hair: true/false(word)\"]");
                        case BARREL -> System.out.println("for BARREL ->  [\"Filler(word) Material(word) Volume(integer)\"]");
                        case HUMAN -> System.out.println("for HUMAN ->  [\"Age(integer) Surname(word) Gender: WOMAN/MAN(word)\"]");
                    }
                    System.out.println("\n ->  or type q to exit  <-");
                    str=sc.nextLine();
                    if(str.equals("q")) return null;
                    Object newObject = objB.create(t,str);
                    if(str.matches("([\\d|\\w]+) ([\\d|\\w]+) ([\\d|\\w]+)") && newObject!=null){
                        toSort.add(newObject);
                        ++i;
                    }else{
                        System.out.println("""
                                                    Incorrect string, try one more time 
                                           """);
                    }
                }
            }else{
                toSort=process(t, size);
            }
        }else if(d==3){
            String filename= null;
            do{
                if(filename == null)System.out.println("enter filename (the path must end with .txt): ");
                filename = sc.nextLine();
                if(filename.matches("[\\w|/|_|\\d]+\\.txt$")){
                    toSort = process(filename);
                    if(toSort == null){
                        System.out.println("""
                                        try again.\n
                                        
                                        enter filename (the path must end with .txt):  
                                       """);
                    }else{
                        break;
                    }
                }else{
                    System.out.println("""
                                        try again.\n
                                        
                                        enter filename (the path must end with .txt):  
                                       """);
                }
            }while(true);


        }else return null;

        return toSort;
    }
    //возвращает список набранный рандомно из базы тестов
    private static List<Object> process(Type t, int sz){
        List<Object> result = new ArrayList<>();
        FileProcess proc = new FileProcess(t,sz);
        result = proc.returnRandom();
        return result;
    }
    //обрабатывает файл по введенному пути
    //возвращает список объектов, созданных из строк файла
    private static List<Object> process(String filename){
        List<Object> result = new ArrayList<>();
        if(filename.isEmpty() ){
            FileProcess proc= new FileProcess();
            result = proc.readFile();
        }else{
            FileProcess proc = new FileProcess(filename);
            result = proc.readFile();
        }
        return result;
    }

}