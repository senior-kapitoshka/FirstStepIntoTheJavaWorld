package objects;

import java.io.Serializable;
import java.util.Objects;

public final class Animal implements Comparable<Animal>, Serializable{
    private  String breed;
    private  String color;
    private  boolean isHair;

    private Animal(AnimalBuilder builder) {
        super();
        this.breed = builder.breed;
        this.color = builder.color;
        this.isHair = builder.isHair;
    }
    public static AnimalBuilder builder(){
        return new AnimalBuilder();
    } 

    public static class AnimalBuilder{
        private  String breed;
        private String color;
        private boolean isHair;

        public AnimalBuilder breed (String breed) {
            this.breed = breed;
            return this;
        }
     
        public AnimalBuilder color(String color) {
            this.color = color;
            return this;
        }
        
        public AnimalBuilder isHair(boolean isHair) {
            this.isHair = isHair;
            return this;
        }
    
        public Animal build(){
            return new Animal(this);  
        } 
    }

    @Override
    public int compareTo(Animal other) {

        int breedComp = String.CASE_INSENSITIVE_ORDER.compare(this.breed, other.breed);
        if (breedComp != 0) {
            return breedComp;
        }
        int colorComp = String.CASE_INSENSITIVE_ORDER.compare(this.color, other.color);
        if (colorComp != 0) {
            return colorComp;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, color, isHair);
    }

    @Override
    public String toString() {
        return "Animal {" +
                " breed " + breed +
                ", color " + color +
                ", isHair " + isHair +
                "}";
    }
}
