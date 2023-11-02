package compiladorinstructionlist;

import java.util.HashMap;
import java.util.Map;

public class CompiladorInstructionList {

    public static void main(String[] args) {
        /*Map<String, Boolean> inputs = new HashMap<>();
        Map<String, Boolean> outputs = new HashMap<>();
        
        inputs = InputActions.create(inputs);
        System.out.println(inputs);
        
        outputs = OutputActions.create(outputs);
        System.out.println(outputs);*/
        
        InterfaceScreen screen = new InterfaceScreen();
        screen.setVisible(true);
        screen.setLocationRelativeTo(null);
        
        // Início: laço de execução (com tempo de varredura)
        
        /*inputs = InputActions.read(inputs);
        System.out.println(inputs);
        
        // Executa as instruções
        
        outputs = OutputActions.write(outputs);
        System.out.println(outputs);*/
        
        // Fim: laço de execução
    }
    
}
