package compiladorinstructionlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompiladorInstructionList {

    public static void main(String[] args) {
        Map<String, Boolean> inputs = new HashMap<>();
        
        inputs = ReadInputs.read();
        
         System.out.println(inputs);
        
        Map<String, Boolean> outputs = new HashMap<>();
    }
    
}
