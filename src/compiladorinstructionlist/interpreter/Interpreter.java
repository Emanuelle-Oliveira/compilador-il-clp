package compiladorinstructionlist.interpreter;

import compiladorinstructionlist.memoryvariable.MemoryVariable;
import compiladorinstructionlist.screen.InterfaceScreen;
import java.util.ArrayList;
import java.util.HashMap;
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
        validOperators.add("OR");
        validOperators.add("ORN");
    }
    
    public static Map receiveLines(List<String> lineList, Map<String, Boolean> inputs, Map<String, Boolean> outputs, Map<String, Boolean> memoryVariables) {
        
        char character = '-';
        Boolean spaceDetected = false;
        String operator = "";
        String variable = "";
        
        initializeValidOperators();
        
        memoryVariables.clear();
        
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
            
            outputs = executeInstruction(operator, variable, inputs, outputs, memoryVariables);
            
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
    
    public static boolean inputIsValid(String variable, Map<String, Boolean> inputs) {
        Boolean isValid = true;
        
        if (inputs.get(variable) == null) {
            isValid = false;
            System.out.println("Entrada inválida!");
        }
        return isValid;
    }
    
    public static boolean outputIsValid(String variable, Map<String, Boolean> outputs) {
        Boolean isValid = true;
        
        if (outputs.get(variable) == null) {
            isValid = false;
            System.out.println("Saída inválida!");
        }
        return isValid;
    }
    
    public static boolean memoryVariableIsValid(String variable, Map<String, Boolean> memoryVariables) {
        Boolean isValid = true;
        
        if (memoryVariables.get(variable) == null) {
            isValid = false;
            System.out.println("Variável de memória inválida!");
        }
        return isValid;
    }
    
    public static Map executeInstruction(String operator, String variable, Map<String, Boolean> inputs, Map<String, Boolean> outputs, Map<String, Boolean> memoryVariables) {
        
        if(operatorIsValid(operator) && (inputIsValid(variable, inputs) || outputIsValid(variable, outputs))) { 
        
            if(operator.equals("LD")){
                if(variable.charAt(0) == 'I'){
                    accumulator = inputs.get(variable);
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = outputs.get(variable);
                }
            }

            if(operator.equals("LDN")){
                if(variable.charAt(0) == 'I'){
                    accumulator = !(inputs.get(variable));
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = !(outputs.get(variable));
                }
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
                if(variable.charAt(0) == 'I'){
                    accumulator = (accumulator && inputs.get(variable));
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = (accumulator && outputs.get(variable));
                }
            }

            if(operator.equals("ANDN")){
                if(variable.charAt(0) == 'I'){
                    accumulator = (accumulator && !(inputs.get(variable)));
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = (accumulator && !(outputs.get(variable)));
                }
            }

            if(operator.equals("OR")){
                if(variable.charAt(0) == 'I'){
                    accumulator = (accumulator || inputs.get(variable));
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = (accumulator || outputs.get(variable));
                }
            }

            if(operator.equals("ORN")){
                if(variable.charAt(0) == 'I'){
                    accumulator = (accumulator || !(inputs.get(variable)));
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = (accumulator || !(outputs.get(variable)));
                }
            }

            System.out.println(accumulator);
            System.out.println(inputs);
            System.out.println(outputs); 
        } else if(operatorIsValid(operator) && !inputIsValid(variable, inputs) && !outputIsValid(variable, outputs)){
            if(operator.equals("ST") || operator.equals("STN")){
                if(memoryVariableIsValid(variable, memoryVariables)) {
                    if(operator.equals("ST")){
                        memoryVariables.put(variable, accumulator);
                    }

                    if(operator.equals("STN")){
                        memoryVariables.put(variable, !accumulator);
                    }
                } else {
                    if(operator.equals("ST")){
                        MemoryVariable memoryVariable = new MemoryVariable(variable, accumulator);
                        memoryVariables.put(memoryVariable.id, memoryVariable.currentValue);
                    }

                    if(operator.equals("STN")){
                        MemoryVariable memoryVariable = new MemoryVariable(variable, !accumulator);
                        memoryVariables.put(memoryVariable.id, memoryVariable.currentValue);
                    }
                }
                System.out.println(accumulator);
                System.out.println(inputs);
                System.out.println(outputs); 
                System.out.println(memoryVariables);
            } else {
                if(memoryVariableIsValid(variable, memoryVariables)) {
                    if(operator.equals("LD")){
                        accumulator = memoryVariables.get(variable);
                    }

                    if(operator.equals("LDN")){
                        accumulator = !(memoryVariables.get(variable));
                    }
                    
                    if(operator.equals("AND")){
                        accumulator = (accumulator && memoryVariables.get(variable));
                    }

                    if(operator.equals("ANDN")){
                        accumulator = (accumulator && !(memoryVariables.get(variable)));
                    }
                    
                    if(operator.equals("OR")){
                        accumulator = (accumulator || memoryVariables.get(variable));
                    }
                    
                    if(operator.equals("ORN")){
                        accumulator = (accumulator || !(memoryVariables.get(variable)));
                    }
                    
                    System.out.println(accumulator);
                    System.out.println(inputs);
                    System.out.println(outputs); 
                    System.out.println(memoryVariables);
                } else {
                    InterfaceScreen.showErrorMessage();
                }
            }
        } else {
            InterfaceScreen.showErrorMessage();
        }
        return outputs;
    }  
}
