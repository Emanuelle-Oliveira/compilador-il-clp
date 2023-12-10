package compiladorinstructionlist.output;

// Classe saída
public class Output {
    String id;
    Boolean currentValue;

    public Output(String id, Boolean currentValue) {
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
        return "Output{" + "id=" + id + ", currentValue=" + currentValue + '}';
    }
}
