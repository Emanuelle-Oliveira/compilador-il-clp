package compiladorinstructionlist.output;

import java.util.Map;

// Classe para as ações relacionadas com as saídas
public class OutputActions {
    
    public static Map create(Map outputs) {
        // Cria as 8 saídas
        Output Q1 = new Output("Q1", false);
        Output Q2 = new Output("Q2", false);
        Output Q3 = new Output("Q3", false);
        Output Q4 = new Output("Q4", false);
        Output Q5 = new Output("Q5", false);
        Output Q6 = new Output("Q6", false);
        Output Q7 = new Output("Q7", false);
        Output Q8 = new Output("Q8", false);
        
        // Adiciona no hash
        outputs.put(Q1.id, Q1.currentValue);
        outputs.put(Q2.id, Q2.currentValue);
        outputs.put(Q3.id, Q3.currentValue);
        outputs.put(Q4.id, Q4.currentValue);
        outputs.put(Q5.id, Q5.currentValue);
        outputs.put(Q6.id, Q6.currentValue);
        outputs.put(Q7.id, Q7.currentValue);
        outputs.put(Q8.id, Q8.currentValue);
        
        return outputs;
    }
    
    // "Simula" escrita
    public static Map<String, Boolean> dummyWrite(Map outputs) {
        // Atualiza no hash
//        outputs.put("Q1", true);
//        outputs.put("Q2", false);
//        outputs.put("Q3", false);
//        outputs.put("Q4", false);
//        outputs.put("Q5", true);
//        outputs.put("Q6", false);
//        outputs.put("Q7", false);
//        outputs.put("Q8", false);
        
        // Escreve os valores de cada saída no módulo
        // ...
        
        return outputs;
    }
    
    // Escrita
    public static Map write(Map outputs) {
        // Atualiza no hash
        outputs.put("Q1", true);
        outputs.put("Q2", false);
        outputs.put("Q3", false);
        outputs.put("Q4", false);
        outputs.put("Q5", true);
        outputs.put("Q6", false);
        outputs.put("Q7", false);
        outputs.put("Q8", false);
        
        // Escreve os valores de cada saída no módulo
        // ...
        
        return outputs;
    }
}
