package compiladorinstructionlist;

import java.util.List;
import java.util.Map;

public class Interpreter {
    
    public static Map receiveLines(List<String> lineList, Map outputs) {
        
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = 0; j < lineList.get(i).length(); j++) {
                char character = lineList.get(i).charAt(j);
                System.out.println(character);
            }
        }
        
        return outputs;
    }
}
