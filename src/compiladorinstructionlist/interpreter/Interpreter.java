package compiladorinstructionlist.interpreter;

import compiladorinstructionlist.screen.InterfaceScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Interpreter {
    
    static Boolean accumulator;
    static List<String> validOperators = new ArrayList<String>();
    
    public static void initializeValidOperators() {
        validOperators.add("LD");
        validOperators.add("LDN");
        validOperators.add("ST");
        validOperators.add("STN");
        validOperators.add("AND");
        validOperators.add("ANDN");
        validOperators.add("ORD");
        validOperators.add("ORN");
    }
    
    public static Map receiveLines(List<String> lineList, Map<String, Boolean> inputs, Map<String, Boolean> outputs) {
        
        char character = '-';
        Boolean spaceDetected = false;
        String operator = "";
        String variable = "";
        
        initializeValidOperators();
        
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
            
            outputs = executeInstruction(operator, variable, inputs, outputs);
            
            spaceDetected = false;
            operator = "";
            variable = "";
        }
        return outputs;
    }
    
    public static boolean operatorIsValid(String operator) {
        Boolean isValid = false;
        for(int i = 0; i < validOperators.size(); i++){
            if(!isValid && validOperators.get(i).equals(operator)){
                isValid = true;
            } 
        }
        return isValid;
    }
    
    public static Map executeInstruction(String operator, String variable, Map<String, Boolean> inputs, Map<String, Boolean> outputs) {
        
        if(operatorIsValid(operator)) { 
        
            if(operator.equals("LD")){
                accumulator = inputs.get(variable);
            }

            if(operator.equals("LDN")){
                accumulator = !(inputs.get(variable));
            }

            if(operator.equals("ST")){
                if(variable.charAt(0) == 'I'){
                    inputs.put(variable, accumulator);
                }

                if(variable.charAt(0) == 'Q'){
                    outputs.put(variable, accumulator);
                }
            }

            if(operator.equals("STN")){
                if(variable.charAt(0) == 'I'){
                    inputs.put(variable, !accumulator);
                }

                if(variable.charAt(0) == 'Q'){
                    outputs.put(variable, !accumulator);
                }
            }

            if(operator.equals("AND")){
                accumulator = (accumulator && inputs.get(variable));
            }

            if(operator.equals("ANDN")){
                accumulator = (accumulator && !(inputs.get(variable)));
            }

            if(operator.equals("OR")){
                accumulator = (accumulator || inputs.get(variable));
            }

            if(operator.equals("ORN")){
                accumulator = (accumulator || !(inputs.get(variable)));
            }

            System.out.println(accumulator);
            System.out.println(inputs);
            System.out.println(outputs); 
        } else {
            InterfaceScreen.showErrorMessage();
        }
        return outputs;
    }  
}
