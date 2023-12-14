package compiladorinstructionlist.interpreter;

import compiladorinstructionlist.memoryvariable.MemoryVariable;
import compiladorinstructionlist.screen.InterfaceScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Classe que interpreta as intruções
public class Interpreter {
    
    // Cria variáveis
    static Boolean accumulator;
    static List<String> validOperators = new ArrayList<String>();
    
    // Define operadores válidos
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
    
    // Recebe linhas vindas da tela e separa operador e variável
    public static Map receiveLines(List<String> lineList, Map<String, Boolean> inputs, Map<String, Boolean> outputs, Map<String, Boolean> memoryVariables) {
        
        // Variáveis auxiliares
        char character = '-';
        Boolean spaceDetected = false;
        String operator = "";
        String variable = "";
        Boolean justEmptyLines = true;
        
        initializeValidOperators();
        
        // Limpa hash de variáveis de memória e saídas
        memoryVariables.clear();
        
        // Limpa acumulador
        accumulator = null;
        
        // Itera lista de linhas
        for (int i = 0; i < lineList.size(); i++) {
            Integer currentLine = i + 1;
            System.out.println("\n-> Linha: " + currentLine.toString());
            
            // Ignora linhas vazias
            if(!lineList.get(i).isBlank()) {
                justEmptyLines = false;
                // Itera caracteres da linha
                for (int j = 0; j < lineList.get(i).length(); j++) {
                    character = lineList.get(i).charAt(j);

                    if (character != ' ' && character != '\n' && character != '\t' && !spaceDetected) {
                        operator = operator + character;
                    } 

                    if((character == ' ' || character == '\t') && !operator.equals("")) {
                        spaceDetected = true;
                    }

                    if (character != ' ' && character != '\n' && character != '\t' && spaceDetected) {
                        variable = variable + character;
                    }
                }

                System.out.println("Operador: " + operator);
                System.out.println("Variável: " + variable);

                outputs = executeInstruction(operator, variable, inputs, outputs, memoryVariables);
            }
            
            spaceDetected = false;
            operator = "";
            variable = "";
        }
        
        if(justEmptyLines) {
            InterfaceScreen.showErrorMessage("Insira as intruções para o CLP!");
        }
        
        return outputs;
    }
    
    // Verifica se operador é válido
    public static boolean operatorIsValid(String operator) {
        Boolean isValid = false;
        for(int i = 0; i < validOperators.size(); i++){
            if(!isValid && validOperators.get(i).equals(operator)){
                isValid = true;
                System.out.println("Operador válido!");
            } 
        }
        return isValid;
    }
    
    // Verifica se entrada é válido
    public static boolean inputIsValid(String variable, Map<String, Boolean> inputs) {
        Boolean isValid = true;
        
        if (inputs.get(variable) == null) {
            isValid = false;
            System.out.println("Não é uma entrada válida!");
        }
        return isValid;
    }
    
    // Verifica se saída é válida
    public static boolean outputIsValid(String variable, Map<String, Boolean> outputs) {
        Boolean isValid = true;
        
        if (outputs.get(variable) == null) {
            isValid = false;
            System.out.println("Não é uma saída válida!");
        }
        return isValid;
    }
    
    // Verifica se variável de memória é válida
    public static boolean memoryVariableIsValid(String variable, Map<String, Boolean> memoryVariables) {
        Boolean isValid = true;
        
        if (memoryVariables.get(variable) == null) {
            isValid = false;
            System.out.println("Não é uma memória válida!");
        }
        return isValid;
    }
    
    // Executa instruções
    public static Map executeInstruction(String operator, String variable, Map<String, Boolean> inputs, Map<String, Boolean> outputs, Map<String, Boolean> memoryVariables) {
        
        // Caso operador seja válido e tenhamos como variável uma entrada ou uma saída
        if(operatorIsValid(operator) && (inputIsValid(variable, inputs) || outputIsValid(variable, outputs))) { 
        
            // Carrega entrada ou saída para o acumulador
            if(operator.equals("LD")){
                if(variable.charAt(0) == 'I'){
                    accumulator = inputs.get(variable);
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = outputs.get(variable);
                }
            }

            // Carrega entrada ou saída negada para o acumulador
            if(operator.equals("LDN")){
                if(variable.charAt(0) == 'I'){
                    accumulator = !(inputs.get(variable));
                }
                
                if(variable.charAt(0) == 'Q'){
                    accumulator = !(outputs.get(variable));
                }
            }
            
            // Verifica se o valor do acumulador não é nulo
            if(accumulator != null) {
                if (operator.equals("ST") || operator.equals("STN")) {
                    if(outputIsValid(variable, outputs)) {
                        // Carrega o valor do acumulador para a variável (saída)
                        if(operator.equals("ST")){
                            if(variable.charAt(0) == 'Q'){
                                outputs.put(variable, accumulator);
                            }
                        }

                        // Carrega o valor do acumulador negado para a variável (saída)
                        if(operator.equals("STN")){
                            if(variable.charAt(0) == 'Q'){
                                outputs.put(variable, !accumulator);
                            }
                        }
                    } else {
                        InterfaceScreen.showErrorMessage("Entradas não podem ser modificadas, portanto, operadores ST e STN não são válidos para entradas!");
                    }
                }

                // Faz operação AND entre o acumulador e a variável (entrada ou saída) e salva no acumulador
                if(operator.equals("AND")){
                    if(variable.charAt(0) == 'I'){
                        accumulator = (accumulator && inputs.get(variable));
                    }

                    if(variable.charAt(0) == 'Q'){
                        accumulator = (accumulator && outputs.get(variable));
                    }
                }

                // Faz operação AND entre o acumulador e a variável (entrada ou saída) negada e salva no acumulador
                if(operator.equals("ANDN")){
                    if(variable.charAt(0) == 'I'){
                        accumulator = (accumulator && !(inputs.get(variable)));
                    }

                    if(variable.charAt(0) == 'Q'){
                        accumulator = (accumulator && !(outputs.get(variable)));
                    }
                }

                // Faz operação OR entre o acumulador e a variável (entrada ou saída) e salva no acumulador
                if(operator.equals("OR")){
                    if(variable.charAt(0) == 'I'){
                        accumulator = (accumulator || inputs.get(variable));
                    }

                    if(variable.charAt(0) == 'Q'){
                        accumulator = (accumulator || outputs.get(variable));
                    }
                }

                // Faz operação OR entre o acumulador e a variável (entrada ou saída) negada e salva no acumulador
                if(operator.equals("ORN")){
                    if(variable.charAt(0) == 'I'){
                        accumulator = (accumulator || !(inputs.get(variable)));
                    }

                    if(variable.charAt(0) == 'Q'){
                        accumulator = (accumulator || !(outputs.get(variable)));
                    }
                }

                System.out.println("Acumulador: " + accumulator);
                System.out.println("Entradas: " + inputs);
                System.out.println("Saídas: " + outputs);
            } else {
                InterfaceScreen.showErrorMessage("Acumulador vazio! Carregue inicialmente a variável desejada para o acumulador com as funções LD ou LDN!");
            }
            
        // Caso operador seja válido e tenhamos como variável uma memória
        } else if(operatorIsValid(operator) && !inputIsValid(variable, inputs) && !outputIsValid(variable, outputs)){
            // Para operações de carregamento (onde variável de memória são criadas)
            if(operator.equals("ST") || operator.equals("STN")){
                // Se memória já existe, só atualiza no hash
                if(memoryVariableIsValid(variable, memoryVariables)) {
                    if(operator.equals("ST")){
                        memoryVariables.put(variable, accumulator);
                    }

                    if(operator.equals("STN")){
                        memoryVariables.put(variable, !accumulator);
                    }
                // Se memória não existe, ela é criada e e guardada no hash
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
                System.out.println("Acumulador: " + accumulator);
                System.out.println("Entradas: " + inputs);
                System.out.println("Saídas: " + outputs);
                System.out.println("Variáveis de memória: " +memoryVariables);
            // Outras operações
            } else {
                // Memória precisa existir
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
                    InterfaceScreen.showErrorMessage("Sintaxe incorreta! Variável " + variable + " não existe!");
                }
            }
        } else {
            InterfaceScreen.showErrorMessage("Sintaxe incorreta! Operador " + operator + " não existe!");
        }
        return outputs;
    }  
}
