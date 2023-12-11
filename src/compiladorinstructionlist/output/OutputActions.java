package compiladorinstructionlist.output;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortFactoryPJC;
import com.intelligt.modbus.jlibmodbus.serial.SerialUtils;
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
    
    // "Limpa" values do hash de saída
    public static Map setAllFalse(Map outputs) {
        outputs.put("Q1", false);
        outputs.put("Q2", false);
        outputs.put("Q3", false);
        outputs.put("Q4", false);
        outputs.put("Q5", false);
        outputs.put("Q6", false);
        outputs.put("Q7", false);
        outputs.put("Q8", false);
        
        return outputs;
    }
    
    // "Simula" escrita
    public static Map<String, Boolean> dummyWrite(Map outputs) {
        // Escreve os valores de cada saída no módulo
        // ...
        Integer valueWrite = convertValueWrite(outputs);
        System.out.println("Valor para escrever: " + valueWrite);
        
        return outputs;
    }
    
    // Escrita
    public static Map write(Map outputs) {   
        // Escreve os valores de cada saída no módulo
        SerialParameters sp = new SerialParameters();
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
        
        // Converte para inteiro
        Integer valueWrite = convertValueWrite(outputs);
        
        try {
            // Seta porta
            sp.setDevice("COM5");
            
            if (sp.getDevice() != null) {
                // Parâmetros de configuração
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
                sp.setDataBits(8);
                sp.setParity(SerialPort.Parity.NONE);
                sp.setStopBits(1);

                SerialUtils.setSerialPortFactory(new SerialPortFactoryPJC());

                ModbusMaster m = ModbusMasterFactory.createModbusMasterRTU(sp);
                m.connect();

                int writeAddress = 0x0070;
 
                try {
                    m.writeMultipleRegisters(1, writeAddress, new int[]{valueWrite, 0});
           
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        m.disconnect();
                    } catch (ModbusIOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return outputs;
    }
    
    // Converte para inteiro
    public static int convertValueWrite(Map<String, Boolean> outputs) {
        StringBuilder binaryString = new StringBuilder();

        for (int i = 8; i >= 1; i--) {
            binaryString.append(outputs.getOrDefault("Q" + i, false) ? '1' : '0');
        }

        // Converte a string binária para inteiro
        return Integer.parseInt(binaryString.toString(), 2);
    }
}
