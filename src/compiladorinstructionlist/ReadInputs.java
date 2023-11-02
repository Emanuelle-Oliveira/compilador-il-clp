package compiladorinstructionlist;

import java.util.HashMap;
import java.util.Map;

public class ReadInputs {
    public static Map read() {
        Map<String, Boolean> inputs = new HashMap<>();
        
        // Cria as 8 entradas
        Input I1 = new Input("I1", false);
        Input I2 = new Input("I2", false);
        Input I3 = new Input("I3", false);
        Input I4 = new Input("I4", false);
        Input I5 = new Input("I5", false);
        Input I6 = new Input("I6", false);
        Input I7 = new Input("I7", false);
        Input I8 = new Input("I8", false);
        
        // Lê os valores vindos do módulo
        // ...
        
        // Adiciona no hash
        inputs.put(I1.id, I1.currentValue);
        inputs.put(I2.id, I2.currentValue);
        inputs.put(I3.id, I3.currentValue);
        inputs.put(I4.id, I4.currentValue);
        inputs.put(I5.id, I5.currentValue);
        inputs.put(I6.id, I6.currentValue);
        inputs.put(I7.id, I7.currentValue);
        inputs.put(I8.id, I8.currentValue);
        
        return inputs;
    }
}
