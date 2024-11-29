package utils;
import java.io.*;
import java.util.*;

//класс для работы с файловой системой и файлами-тестами
public class FileProcess{
    private FileReader reader;
    private Scanner scanner;
    private String fn; //файлнэйм
    Type type = null;   //нужный тип
    int size; //нужный размер

    //конструктор по умолчанию
    public FileProcess(){
        type=Type.HUMAN;
        fn = "files/for_human.txt";
    }
    //конструктор для работы с файловой системой
    public FileProcess(String filename){

        fn = filename;
    }
    //конструктор для набора в случае выбора рандома
    public FileProcess(Type t, int sz){
        switch(t){
            case ANIMAL->fn = "files/for_animal.txt";
            case BARREL->fn = "files/for_barrel.txt";
            case HUMAN->fn = "files/for_human.txt";
        }
        type = t;
        size=sz;
    }
    //набирает из базы строки рандомно
    public List<Object> returnRandom(){
        List<Object> result = readFile();
        if(result.size()>size){
            int i=0;
            while(result.size()>size){
                result.remove(i++);
            }
        }else if(result.size()<size){
            int i=0;
            while(result.size()<size){
                result.add(result.get(i++));
            }
        }
        Collections.shuffle(result);
        return result;
    }

    public List<Object> readFile(){
        List<Object> result = new ArrayList<>();
        try{
            reader = new FileReader(fn);
            scanner = new Scanner(reader);
            
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                BuildNewObject builder= new BuildNewObject();
                if(s.matches("([\\w]+) ([\\w]+) (true|false)")){
                    if(type==null){
                        type = Type.ANIMAL;
                        result.add(builder.create(type, s));
                    }else{
                        if(type == Type.ANIMAL)
                            result.add(builder.create(type, s));
                        else{
                            System.out.println("Error type");
                            break;
                        }
                    }
                }else if(s.matches("([\\d]+) ([\\w]+) (MAN|WOMAN)")){
                    if(type==null){
                        type = Type.HUMAN;
                        result.add(builder.create(type, s));
                    }else{
                        if(type == Type.HUMAN)result.add(builder.create(type, s));
                        else{
                            System.out.println("Error type");
                            break;
                        }
                    }
                }else if(s.matches("([\\w]+) ([\\w]+) ([\\d]+)")){
                    if(type==null){
                        type = Type.BARREL;
                        result.add(builder.create(type, s));
                    }else{
                        if(type==Type.BARREL)result.add(builder.create(type, s));
                        else{
                            System.out.println("Error type");
                            break;
                        }
                    }
                }else{
                    System.out.println("Error type");
                    break;
                }
                
            }
            reader.close();
            scanner.close();
        }catch(FileNotFoundException e){
            System.out.println("Incorrect filename");
            result =null;
        }catch(IOException e){
            System.out.println("Scanner Error");
            result =null;
        }
        return result;
    }

}
 