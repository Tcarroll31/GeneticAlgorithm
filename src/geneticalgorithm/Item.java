package geneticalgorithm;

public class Item {

    private final String name;
    private final double weight;
    private final int value;
    private boolean included;
    
    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    
    public Item(Item other){
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    // Getter for Weight
    public double getWeight() {
        return weight;
    }

    // Getter for the Value
    public int getValue() {
        return value;
    }

    // Getter for included
    public boolean isIncluded() {
        return included;
    }

    // Setter for included
    public void setIncluded(boolean included) {
        this.included = included;
    }

    /**
     * This toString method sets the format to be printed
     * @return
     */
    @Override
    public String toString(){
        return name + "(" + weight + " lbs, $" + value + ")";
    }

}
