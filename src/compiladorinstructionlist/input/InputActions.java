package compiladorinstructionlist.input;

import java.util.Map;
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortFactoryJSSC;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortFactoryPJC;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortFactoryRXTX;
import com.intelligt.modbus.jlibmodbus.serial.SerialUtils;
import jssc.SerialPortList;

// Classe para as ações relacionadas com as entradas
public class InputActions {
    
    final static private int slaveId = 1;
    
    public static Map create(Map inputs) {
        // Cria as 8 entradas
        Input I1 = new Input("I1", false);
        Input I2 = new Input("I2", false);
        Input I3 = new Input("I3", false);
        Input I4 = new Input("I4", false);
        Input I5 = new Input("I5", false);
        Input I6 = new Input("I6", false);
        Input I7 = new Input("I7", false);
        Input I8 = new Input("I8", false);
        
        // Adiciona no hash
        inputs.put(I1.id, I1.currentValue);
        inputs.put(I2.id, I2.currentValue);
        inputs.put(I3.id, I3.currentValue);
        inputs.put(I4.id, I4.currentValue);
        inputs.put(I5.id, I5.currentValue);
        inputs.put(I6.id, I6.currentValue);
        inputs.put(I7.id, I7.currentValue);
        inputs.put(I8.id, I8.currentValue);
        
        return inputs;
    }
    
    // "Simula" leitura
    public static Map<String, Boolean> dummyRead(Map inputs) {
        // Lê os valores de cada entrada vindos do módulo
        boolean[] arrayBoolean = convertValueRead(49);
        System.out.println("[Dummy] Valor lido do módulo: 33");
        
        //for (int i = 0; i < 8; i++) {
        //    System.out.println(arrayBoolean[i]);
        //}
        
        // Atualiza no hash
        inputs.put("I1", arrayBoolean[7]);
        inputs.put("I2", arrayBoolean[6]);
        inputs.put("I3", arrayBoolean[5]);
        inputs.put("I4", arrayBoolean[4]);
        inputs.put("I5", arrayBoolean[3]);
        inputs.put("I6", arrayBoolean[2]);
        inputs.put("I7", arrayBoolean[1]);
        inputs.put("I8", arrayBoolean[0]);
        
        return inputs;
    }
       
    // Leitura
    public static Map read(Map inputs) {
        // Lê os valores de cada entrada vindos do módulo
        SerialParameters sp = new SerialParameters();
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
        
        Integer valueRead = 0;
        
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

                int slaveId = 0x01;
                int offset = 0x00C0;
                int quantity = 2;
 
                try {
                    int[] registerValues = m.readHoldingRegisters(slaveId, offset, quantity);

                    // Impressão dos valores lidos
                    System.out.println("Valores dos Registradores:");
                    for (int value : registerValues) {
                        System.out.println("Endereço: " + offset + ", Valor: " + value);
                    }
                    
                    valueRead = registerValues[0];
                    System.out.println("Valor lido do módulo: " + valueRead);
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
        
        // Converte para boolean
        boolean[] arrayBoolean = convertValueRead(valueRead);
        
        // Atualiza no hash
        inputs.put("I1", arrayBoolean[7]);
        inputs.put("I2", arrayBoolean[6]);
        inputs.put("I3", arrayBoolean[5]);
        inputs.put("I4", arrayBoolean[4]);
        inputs.put("I5", arrayBoolean[3]);
        inputs.put("I6", arrayBoolean[2]);
        inputs.put("I7", arrayBoolean[1]);
        inputs.put("I8", arrayBoolean[0]);
        
        return inputs;
    }
    
    // Converte para boolean
    public static boolean[] convertValueRead(int value) {
        // Converte para binário de 8 bits
        String binary = String.format("%8s", Integer.toBinaryString(value)).replaceAll(" ", "0");

        // Extrair os 8 bits mais significativos
        String importantBits = binary.substring(0, 8);

        // Converter para array de booleanos
        boolean[] resultado = new boolean[8];
        for (int i = 0; i < 8; i++) {
            resultado[i] = importantBits.charAt(i) == '1';
        }
        
        return resultado;
    }
}
