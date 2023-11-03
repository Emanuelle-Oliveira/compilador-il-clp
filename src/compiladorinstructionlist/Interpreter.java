package compiladorinstructionlist;

import java.util.List;
import java.util.Map;

public class Interpreter {
    
    public static Map receiveLines(List<String> lineList, Map outputs) {
        
        char character = '-';
        //char lastCharacter = '-';
        //Boolean lineIsCorrect = true;
        Boolean spaceDetected = false;
        //Boolean skipLineDetected = false;
        String operator = "";
        String variable = "";
        
        for (int i = 0; i < lineList.size(); i++) {
            for (int j = 0; j < lineList.get(i).length(); j++) {
                
                //lastCharacter = character;

                character = lineList.get(i).charAt(j);
                
                if (character != ' ' && character != '\n' && !spaceDetected) {
                    operator = operator + character;
                } 
                
                if(character == ' ') {
                    spaceDetected = true;
                }
                
                if (character != ' ' && character != '\n' && spaceDetected) {
                    variable = variable + character;
                }
            }
            System.out.println("Operador: " + operator);
            System.out.println("Variável: " + variable);
            
            spaceDetected = false;
            operator = "";
            variable = "";
        }
        return outputs;
    }
}
