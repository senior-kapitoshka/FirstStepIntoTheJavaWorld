package utils;
import objects.*;
import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

// класс для универсального создания объекта из строки
public class BuildNewObject{
    private static String rgx = "([\\d|\\w]+) ([\\d|\\w]+) ([\\d|\\w]+)";
    Pattern p=Pattern.compile(rgx);

    public Object create(Type t, String s){
        Matcher m=p.matcher(s);
        if(t == Type.HUMAN){
           if(!s.matches("([\\d]+) ([\\w]+) (MAN|WOMAN)")) return null;
            while(m.find()){
            return new Human.HumanBuilder().
                age(Integer.parseInt(m.group(1))).
                gender(Gender.valueOf(m.group(3))).
                surname(m.group(2)).
                build();
            }
        }else if(t == Type.BARREL ){
            if(!s.matches("([\\w]+) ([\\w]+) ([\\d]+)")) return null;
            while(m.find()){
            return new Barrel.BarrelBuilder()
                        .storedMaterial(m.group(1))
                        .barrelMaterial(m.group(2))
                        .volume(Integer.parseInt(m.group(3)))
                        .build();
                    }

        }else if(t == Type.ANIMAL ){
            if(!s.matches("([\\w]+) ([\\w]+) (true|false)")) return null;
            while(m.find()){
                return new Animal
                    .AnimalBuilder()
                    .breed(m.group(1))
                    .color(m.group(2))
                    .isHair(Boolean.valueOf(m.group(3)))
                    .build();
            }
        }
        return null;
    }
    
}