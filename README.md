# compilador-il-clp

O projeto consiste em usar um sistema de leitura de 8 entradas e escrita de 8 saídas, utilizando como interface um módulo de entradas e saídas ModBus. 
O programa desenvolvido emula um CLP (Controlador Lógico Progamável), a programação desse CLP deve ser feita utilizando a linguagem de lista de instruções (Instruction List - IL).

**Modos de Operação do CLP:**
- Modo Programação (PROGRAM): Permite que o programa lógico seja alterado, não há leitura e nem escrita nas saídas físicas.
- Modo Parado (STOP): O sistema para o programa lógico criado pelo usuário.
- Modo Operação (RUN): O sistema roda o programa lógico criado pelo usuário.

**Interpretador desenvolvido:**
![image](https://github.com/Emanuelle-Oliveira/compilador-il-clp/assets/87440842/d4c6fecb-df9b-40fa-b0a4-cf6e74927ff1)

**Entradas e saídas disponíveis:**
- Entradas: I1 até I8
- Saídas: Q1 até Q8

**Operações disponíveis da lista de instruções:**
- LD: Carrega um valor para o acumulador.<br>
```Exemplo: LD I1```
- LDN: Carrega um valor negado para o acumulador.<br>
```Exemplo: LDN I1```
- ST: Armazena o conteúdo do acumulador no local especificado.<br>
```Exemplo: ST Q1```
- STN: Armazena o conteúdo do acumulador negado no local especificado.<br>
```Exemplo: STN Q1```
- AND: Função booleana AND entre o operando indicado e o valor do acumulador.<br>
```Exemplo: AND I2```
- ANDN: Função booleana AND entre o operando indicado negado e o valor do acumulador.<br>
```Exemplo: ANDN I2```
- OR: Função booleana OR entre o operando indicado e o valor do acumulador.<br>
```Exemplo: OR I3```
- ORN: Função booleana OR entre o operando indicado negado e o valor do acumulador.<br>
```Exemplo: ORN I3```

**Outras funcionalidades:**
- Criação de memórias booleanas.<br>
```Exemplo: ST M1```

**Tecnologias utilizadas:**
- Java Desktop
- Biblioteca jlibmodbus

