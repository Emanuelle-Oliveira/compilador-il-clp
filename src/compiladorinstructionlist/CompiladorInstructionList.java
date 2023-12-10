package compiladorinstructionlist;

import compiladorinstructionlist.screen.InterfaceScreen;

// Classe que chama a tela
public class CompiladorInstructionList {

    public static void main(String[] args) {
        InterfaceScreen screen = new InterfaceScreen();
        screen.setVisible(true);
        screen.setLocationRelativeTo(null);
    } 
}
