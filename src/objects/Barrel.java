package objects;

import java.io.Serializable;
import java.util.Objects;

public final class Barrel implements Comparable<Barrel>, Serializable{
    private int volume;
    private String storedMaterial;
    private String barrelMaterial;

    private Barrel(BarrelBuilder builder) {
        super();
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.barrelMaterial = builder.barrelMaterial;
    }
    public static BarrelBuilder builder(){
        return new BarrelBuilder();
    } 

    public static class BarrelBuilder{
        private int volume;
        private String storedMaterial;
        private String barrelMaterial;

        public BarrelBuilder volume (int volume) {
            this.volume = volume;
            return this;
        }
     
        public BarrelBuilder storedMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }
        
        public BarrelBuilder barrelMaterial(String barrelMaterial) {
            this.barrelMaterial = barrelMaterial;
            return this;
        }
    
        public Barrel build(){
            return new Barrel(this);  
        } 
    }
    @Override
    public int compareTo(Barrel other) {

        int volumeComp = Integer.compare(this.volume, other.volume);
        if (volumeComp != 0) {
            return volumeComp;
        }
        int storedComp = String.CASE_INSENSITIVE_ORDER.compare(this.storedMaterial, other.storedMaterial);
        if (storedComp != 0) {
            return storedComp;
        }
        return String.CASE_INSENSITIVE_ORDER.compare(this.barrelMaterial, other.barrelMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, storedMaterial, barrelMaterial);
    }


    @Override
    public String toString() {
        return "Barrel {" +
                " volume " + volume +
                ", storedMaterial " + storedMaterial +
                ", barrelMaterial " + barrelMaterial +
                "}";
    }
}
