package compiladorinstructionlist.screen;

import compiladorinstructionlist.interpreter.Interpreter;
import compiladorinstructionlist.output.OutputActions;
import compiladorinstructionlist.input.InputActions;
import compiladorinstructionlist.uppercasedocumentfilter.UpperCaseDocumentFilter;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

// Classe da tela
public class InterfaceScreen extends javax.swing.JFrame {

    // Cria variáveis
    Map<String, Boolean> inputs;
    Map<String, Boolean> outputs;
    static Map<String, Boolean> memoryVariables = new HashMap<String, Boolean>();
    static Integer mode = 1;
    
    public InterfaceScreen() {
        // Configuraçõs da view
        initComponents();
        getContentPane().setBackground(Color.white);
        
        jb_run.setContentAreaFilled(false);
        jb_run.setOpaque(true);
        jb_run.setBackground(Color.green);
        
        jb_stop.setContentAreaFilled(false);
        jb_stop.setOpaque(true);
        jb_stop.setBackground(Color.red);
        
        jb_program.setContentAreaFilled(false);
        jb_program.setOpaque(true);
        jb_program.setBackground(Color.yellow);
        
        jta_memory_variables.setEditable(false);
        
        jta_writeInstructions.setEditable(true);
        // Transforma letras minúsculas em maiúsculas
        AbstractDocument doc = (AbstractDocument) jta_writeInstructions.getDocument();
        doc.setDocumentFilter(new UpperCaseDocumentFilter());
        // Evita linhas vazias
        jta_writeInstructions.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = jta_writeInstructions.getText();
                    int caretPosition = jta_writeInstructions.getCaretPosition();
                    int lineStart = text.lastIndexOf("\n", caretPosition - 1) + 1;

                    String line = text.substring(lineStart, caretPosition).trim();
                    if (line.isEmpty()) {
                        e.consume(); // Impede a quebra de linha se a linha atual estiver vazia ou tiver apenas espaços em branco
                    }
                }
            }
        });

        //Inicializa entradas e saídas
        inputs = new HashMap<>();
        outputs = new HashMap<>();
        
        inputs = InputActions.create(inputs);
        System.out.println("HashMap de entradas criado:" + inputs);
        
        outputs = OutputActions.create(outputs);
        System.out.println("HashMap de saídas criado:" + outputs);
 
        // Atualiza entradas e saídas na tela
        updateScreen();
    }
    
    // Define a cor dos valores ("true" e "false") das entradas e saídas na tela
    public void setColor(Boolean value, JLabel label) {
        if(value) {
            label.setForeground(Color.green);
        } else {
            label.setForeground(Color.red);
        }
    }
    
    // Atualiza entradas e saídas na tela
    public void updateScreen() {
        jl_input1_value.setText(inputs.get("I1").toString());
        setColor(inputs.get("I1"), jl_input1_value);
        
        jl_input2_value.setText(inputs.get("I2").toString());
        setColor(inputs.get("I2"), jl_input2_value);
        
        jl_input3_value.setText(inputs.get("I3").toString());
        setColor(inputs.get("I3"), jl_input3_value);
        
        jl_input4_value.setText(inputs.get("I4").toString());
        setColor(inputs.get("I4"), jl_input4_value);

        jl_input5_value.setText(inputs.get("I5").toString());
        setColor(inputs.get("I5"), jl_input5_value);
        
        jl_input6_value.setText(inputs.get("I6").toString());
        setColor(inputs.get("I6"), jl_input6_value);
        
        jl_input7_value.setText(inputs.get("I7").toString());
        setColor(inputs.get("I7"), jl_input7_value);
        
        jl_input8_value.setText(inputs.get("I8").toString());
        setColor(inputs.get("I8"), jl_input8_value);
        
        jl_output1_value.setText(outputs.get("Q1").toString());
        setColor(outputs.get("Q1"), jl_output1_value);
        
        jl_output2_value.setText(outputs.get("Q2").toString());
        setColor(outputs.get("Q2"), jl_output2_value);
        
        jl_output3_value.setText(outputs.get("Q3").toString());
        setColor(outputs.get("Q3"), jl_output3_value);
        
        jl_output4_value.setText(outputs.get("Q4").toString());
        setColor(outputs.get("Q4"), jl_output4_value);
        
        jl_output5_value.setText(outputs.get("Q5").toString());
        setColor(outputs.get("Q5"), jl_output5_value);
        
        jl_output6_value.setText(outputs.get("Q6").toString());
        setColor(outputs.get("Q6"), jl_output6_value);
        
        jl_output7_value.setText(outputs.get("Q7").toString());
        setColor(outputs.get("Q7"), jl_output7_value);
        
        jl_output8_value.setText(outputs.get("Q8").toString());
        setColor(outputs.get("Q8"), jl_output8_value);
    }
    
    // Atualiza o modo atual na tela
    public void updateMode() {
        if(mode == 1) {
            jl_mode_value.setText("Program");
            jta_writeInstructions.setEditable(true);
            
        } else if (mode == 2) {
            jl_mode_value.setText("Stop");
            jta_writeInstructions.setEditable(false);
        } else {
            jl_mode_value.setText("Run");
            jta_writeInstructions.setEditable(false);
        }
    }
    
    // Atualiza as variáveis de memória na tela
    public void updateMemoryVariables() {
        jta_memory_variables.setText("");

        String line = "";

        for (Map.Entry<String, Boolean> variable : memoryVariables.entrySet()) {
            line = variable.getKey() + " = " + variable.getValue() + "\n";
            jta_memory_variables.setText(jta_memory_variables.getText() + line);
        }
    }
    
    // Mostra mensagem de erro na tela
    public static void showErrorMessage(String message) {
        mode = 1;
        JOptionPane.showMessageDialog(null, message);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_inputs = new javax.swing.JLabel();
        jl_outputs = new javax.swing.JLabel();
        jb_run = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jb_stop = new javax.swing.JButton();
        jl_input1 = new javax.swing.JLabel();
        jl_input2 = new javax.swing.JLabel();
        jl_input3 = new javax.swing.JLabel();
        jl_input4 = new javax.swing.JLabel();
        jl_input5 = new javax.swing.JLabel();
        jl_input6 = new javax.swing.JLabel();
        jl_input7 = new javax.swing.JLabel();
        jl_input8 = new javax.swing.JLabel();
        jl_output7 = new javax.swing.JLabel();
        jl_output8 = new javax.swing.JLabel();
        jl_output1 = new javax.swing.JLabel();
        jl_output2 = new javax.swing.JLabel();
        jl_output3 = new javax.swing.JLabel();
        jl_output4 = new javax.swing.JLabel();
        jl_output5 = new javax.swing.JLabel();
        jl_output6 = new javax.swing.JLabel();
        jl_input7_value = new javax.swing.JLabel();
        jl_input8_value = new javax.swing.JLabel();
        jl_input1_value = new javax.swing.JLabel();
        jl_input2_value = new javax.swing.JLabel();
        jl_input3_value = new javax.swing.JLabel();
        jl_input4_value = new javax.swing.JLabel();
        jl_input5_value = new javax.swing.JLabel();
        jl_input6_value = new javax.swing.JLabel();
        jl_output7_value = new javax.swing.JLabel();
        jl_output8_value = new javax.swing.JLabel();
        jl_output1_value = new javax.swing.JLabel();
        jl_output2_value = new javax.swing.JLabel();
        jl_output3_value = new javax.swing.JLabel();
        jl_output4_value = new javax.swing.JLabel();
        jl_output5_value = new javax.swing.JLabel();
        jl_output6_value = new javax.swing.JLabel();
        jl_time = new javax.swing.JLabel();
        jt_time = new javax.swing.JTextField();
        jb_program = new javax.swing.JButton();
        jl_mode = new javax.swing.JLabel();
        jl_mode_value = new javax.swing.JLabel();
        jl_memorie_variables = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta_memory_variables = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jta_writeInstructions = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jl_inputs.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_inputs.setText("Entradas:");

        jl_outputs.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_outputs.setText("Saídas:");

        jb_run.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jb_run.setText("Run");
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

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel1.setText("Compilador: Lista de Instrução - CLP");

        jb_stop.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jb_stop.setText("Stop");
        jb_stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_stopMouseClicked(evt);
            }
        });
        jb_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_stopActionPerformed(evt);
            }
        });

        jl_input1.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input1.setText("I1 =");

        jl_input2.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input2.setText("I2 =");

        jl_input3.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input3.setText("I3 =");

        jl_input4.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input4.setText("I4 =");

        jl_input5.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input5.setText("I5 =");

        jl_input6.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input6.setText("I6 =");

        jl_input7.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input7.setText("I7 =");

        jl_input8.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_input8.setText("I8 =");

        jl_output7.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output7.setText("Q7 =");

        jl_output8.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output8.setText("Q8 =");

        jl_output1.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output1.setText("Q1 =");

        jl_output2.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output2.setText("Q2 =");

        jl_output3.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output3.setText("Q3 =");

        jl_output4.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output4.setText("Q4 =");

        jl_output5.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output5.setText("Q5 =");

        jl_output6.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_output6.setText("Q6 =");

        jl_input7_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input7_value.setText("false");

        jl_input8_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input8_value.setText("false");

        jl_input1_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input1_value.setText("false");

        jl_input2_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input2_value.setText("false");

        jl_input3_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input3_value.setText("false");

        jl_input4_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input4_value.setText("false");

        jl_input5_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input5_value.setText("false");

        jl_input6_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_input6_value.setText("false");

        jl_output7_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output7_value.setText("false");

        jl_output8_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output8_value.setText("false");

        jl_output1_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output1_value.setText("false");

        jl_output2_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output2_value.setText("false");

        jl_output3_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output3_value.setText("false");

        jl_output4_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output4_value.setText("false");

        jl_output5_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output5_value.setText("false");

        jl_output6_value.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_output6_value.setText("false");

        jl_time.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jl_time.setText("Tempo de Delay (em ms):");

        jt_time.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jt_time.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jt_time.setText("1000");
        jt_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jt_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_timeActionPerformed(evt);
            }
        });

        jb_program.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jb_program.setText("Program");
        jb_program.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_programMouseClicked(evt);
            }
        });
        jb_program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_programActionPerformed(evt);
            }
        });

        jl_mode.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jl_mode.setText("Modo atual:");

        jl_mode_value.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jl_mode_value.setText("Program");

        jl_memorie_variables.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jl_memorie_variables.setText("Variáveis de Memória:");

        jta_memory_variables.setColumns(20);
        jta_memory_variables.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jta_memory_variables.setRows(5);
        jta_memory_variables.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPane2.setViewportView(jta_memory_variables);

        jta_writeInstructions.setColumns(20);
        jta_writeInstructions.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jta_writeInstructions.setRows(5);
        jta_writeInstructions.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPane3.setViewportView(jta_writeInstructions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jb_run, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_program)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jl_mode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_mode_value))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_inputs, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jl_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_input8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jl_input1_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input2_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input3_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input4_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input5_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input6_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input7_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_input8_value, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_outputs, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jl_output1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jl_output8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jl_output1_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output2_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output3_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output4_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output5_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output6_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output7_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_output8_value, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jl_memorie_variables, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_time, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_run)
                            .addComponent(jb_stop)
                            .addComponent(jb_program)
                            .addComponent(jl_mode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_mode_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_inputs, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_outputs, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jl_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jl_output1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_output8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jl_input1_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input2_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input3_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input4_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input5_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input6_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input7_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jl_input8_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_output1_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output2_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output3_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output4_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output5_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output6_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output7_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_output8_value, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jl_memorie_variables, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Função que é executada quando o botão run é clicado
    private void jb_runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_runActionPerformed
        System.out.println("\nBotão run clicado!");
        mode = 3;
        
        // Verificando tempo de delay
        String stringTime = jt_time.getText();
        
        if(!stringTime.equals("")) {
            Integer time = 0;

            try {
                time = Integer. parseInt(stringTime);
            } catch(NumberFormatException e) {
                mode = 1;
                updateMode();
                showErrorMessage("Tempo de delay inválido! Insira um número inteiro.");
            }
            
            System.out.println("Tempo de delay: " + time + "\n");
        }
        
        // Salva linhas da área de texto
        List<String> lineList = new ArrayList<String>();
        lineList = saveLines(lineList);
        
        // Chama o interpretador que retorna as saídas
        outputs = Interpreter.receiveLines(lineList, inputs, outputs, memoryVariables);
        
        // Atualiza tela
        updateScreen();
        updateMode();
        updateMemoryVariables();
    }//GEN-LAST:event_jb_runActionPerformed

    // Salva linhas da área de texto e retorna lista de linhas
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
        
        System.out.println("Lista de linhas: " + lineList);
        return lineList;
    }
    
    private void jb_runMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_runMouseClicked

    }//GEN-LAST:event_jb_runMouseClicked

    private void jb_stopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_stopMouseClicked

    }//GEN-LAST:event_jb_stopMouseClicked
    
    // Função que é executada quando o botão stop é clicado
    private void jb_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_stopActionPerformed
        System.out.println("\nBotão stop clicado!");
        mode = 2;
        updateMode();
    }//GEN-LAST:event_jb_stopActionPerformed

    private void jt_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_timeActionPerformed

    }//GEN-LAST:event_jt_timeActionPerformed

    private void jb_programMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_programMouseClicked

    }//GEN-LAST:event_jb_programMouseClicked

    // Função que é executada quando o botão program é clicado
    private void jb_programActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_programActionPerformed
        System.out.println("\nBotão program clicado!");
        mode = 1;
        updateMode();
    }//GEN-LAST:event_jb_programActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jb_program;
    private javax.swing.JButton jb_run;
    private javax.swing.JButton jb_stop;
    private javax.swing.JLabel jl_input1;
    private javax.swing.JLabel jl_input1_value;
    private javax.swing.JLabel jl_input2;
    private javax.swing.JLabel jl_input2_value;
    private javax.swing.JLabel jl_input3;
    private javax.swing.JLabel jl_input3_value;
    private javax.swing.JLabel jl_input4;
    private javax.swing.JLabel jl_input4_value;
    private javax.swing.JLabel jl_input5;
    private javax.swing.JLabel jl_input5_value;
    private javax.swing.JLabel jl_input6;
    private javax.swing.JLabel jl_input6_value;
    private javax.swing.JLabel jl_input7;
    private javax.swing.JLabel jl_input7_value;
    private javax.swing.JLabel jl_input8;
    private javax.swing.JLabel jl_input8_value;
    private javax.swing.JLabel jl_inputs;
    private javax.swing.JLabel jl_memorie_variables;
    private javax.swing.JLabel jl_mode;
    private javax.swing.JLabel jl_mode_value;
    private javax.swing.JLabel jl_output1;
    private javax.swing.JLabel jl_output1_value;
    private javax.swing.JLabel jl_output2;
    private javax.swing.JLabel jl_output2_value;
    private javax.swing.JLabel jl_output3;
    private javax.swing.JLabel jl_output3_value;
    private javax.swing.JLabel jl_output4;
    private javax.swing.JLabel jl_output4_value;
    private javax.swing.JLabel jl_output5;
    private javax.swing.JLabel jl_output5_value;
    private javax.swing.JLabel jl_output6;
    private javax.swing.JLabel jl_output6_value;
    private javax.swing.JLabel jl_output7;
    private javax.swing.JLabel jl_output7_value;
    private javax.swing.JLabel jl_output8;
    private javax.swing.JLabel jl_output8_value;
    private javax.swing.JLabel jl_outputs;
    private javax.swing.JLabel jl_time;
    private javax.swing.JTextField jt_time;
    private javax.swing.JTextArea jta_memory_variables;
    private javax.swing.JTextArea jta_writeInstructions;
    // End of variables declaration//GEN-END:variables
}
