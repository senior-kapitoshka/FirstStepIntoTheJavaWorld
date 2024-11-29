package objects;

import java.io.Serializable;
import java.util.Objects;

public final class Human implements Comparable<Human>, Serializable{
    private String surname;
    private Gender gender;
    private int age;

    private Human(HumanBuilder builder) {
        this.surname = builder.surname;
        this.gender = builder.gender;
        this.age = builder.age;
    }

    public static HumanBuilder builder(){
        return new HumanBuilder();
    } 

    public static class HumanBuilder{
        private String surname;
        private Gender gender;
        private int age;

        public HumanBuilder surname (String surname) {
            this.surname = surname;
            return this;
        }
     
        public HumanBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }
        
        public HumanBuilder age(int age) {
            this.age = age;
            return this;
        }
    
        public Human build(){
            return new Human(this);  
        } 
    }

    @Override
    public int compareTo(Human other) {

        int surnameComp = String.CASE_INSENSITIVE_ORDER.compare(this.surname, other.surname);
        if (surnameComp != 0) {
            return surnameComp;
        }
        int ageComp = Integer.compare(this.age, other.age);
        if (ageComp != 0) {
            return ageComp;
        }
        return this.gender.equals(other.gender)? 0: -1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, gender, age);
    }

    @Override
    public String toString() {
        return "Human {" +
                " surname " + surname +
                ", gender " + gender +
                ", age " + age +
                "}";
    }
}
