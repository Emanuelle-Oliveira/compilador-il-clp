package compiladorinstructionlist.screen;

import compiladorinstructionlist.interpreter.Interpreter;
import compiladorinstructionlist.output.OutputActions;
import compiladorinstructionlist.input.InputActions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

public class InterfaceScreen extends javax.swing.JFrame {
    
    Map<String, Boolean> inputs;
    Map<String, Boolean> outputs;
    
    public InterfaceScreen() {
        initComponents();
        
        inputs = new HashMap<>();
        outputs = new HashMap<>();
        
        inputs = InputActions.create(inputs);
        System.out.println(inputs);
        
        outputs = OutputActions.create(outputs);
        System.out.println(outputs);
 
        updateScreen();
        
        //Início: laço de execução (com tempo de varredura)
        
        inputs = InputActions.read(inputs);
        System.out.println(inputs);
        
        // Executa as instruções
        
        outputs = OutputActions.write(outputs);
        System.out.println(outputs);
        
        updateScreen();
        
        // Fim: laço de execução
    }
    
    public void updateScreen() {
        jl_showInputs.setText(inputs.toString());
        jl_showOutputs.setText(outputs.toString());
    }
    
    public static void showErrorMessage() {
        JOptionPane.showMessageDialog(null, "Sintaxe incorreta!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jta_writeInstructions = new javax.swing.JTextArea();
        jl_showInputs = new javax.swing.JLabel();
        jl_showOutputs = new javax.swing.JLabel();
        jb_run = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jta_writeInstructions.setColumns(20);
        jta_writeInstructions.setRows(5);
        jScrollPane1.setViewportView(jta_writeInstructions);

        jl_showInputs.setText("Entradas");

        jl_showOutputs.setText("Saídas");

        jb_run.setText("Executar");
        jb_run.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_runMouseClicked(evt);
            }
        });
        jb_run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_runActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_run)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jl_showInputs, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(jl_showOutputs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_showInputs, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jl_showOutputs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jb_run)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_runActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jb_runActionPerformed

    private void jb_runMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_runMouseClicked
        System.out.println("Botão clicado");

        updateScreen();
        
        List<String> lineList = new ArrayList<String>();
        lineList = saveLines(lineList);
        
        outputs = Interpreter.receiveLines(lineList, inputs, outputs);
        updateScreen();
    }//GEN-LAST:event_jb_runMouseClicked

    private List<String> saveLines(List<String> lineList) {
        int quant = jta_writeInstructions.getLineCount();
        
        for(int i = 0; i < quant; i++){
            try {
                Integer start = jta_writeInstructions.getLineStartOffset(i);
                Integer end = jta_writeInstructions.getLineEndOffset(i);
                String line = jta_writeInstructions.getText(start, end - start);
                lineList.add(line);
            } catch (BadLocationException ex) {
                Logger.getLogger(InterfaceScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println(lineList);
        return lineList;
    }
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_run;
    private javax.swing.JLabel jl_showInputs;
    private javax.swing.JLabel jl_showOutputs;
    private javax.swing.JTextArea jta_writeInstructions;
    // End of variables declaration//GEN-END:variables
}
