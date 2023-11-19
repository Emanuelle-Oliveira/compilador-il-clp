package compiladorinstructionlist.memoryvariable;

public class MemoryVariable {
    public String id;
    public  Boolean currentValue;

    public MemoryVariable(String id, Boolean currentValue) {
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
        return "MemoryVariable{" + "id=" + id + ", currentValue=" + currentValue + '}';
    }
}
