package compiladorinstructionlist.input;

// Classe entrada
public class Input {
    String id;
    Boolean currentValue;

    public Input(String id, Boolean currentValue) {
        this.id = id;
        this.currentValue = currentValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Boolean currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        return "Input{" + "id=" + id + ", currentValue=" + currentValue + '}';
    }    
}
