package ru.silversource.protocols;


import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersResponse;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.util.SerialParameters;

public class DeltaPLCModbusExample {

    public static void main(String[] args) {
        SerialParameters parameters = new SerialParameters();
        parameters.setPortName("COM5");
        parameters.setBaudRate(9600);
        parameters.setDatabits(7);
        parameters.setStopbits(1);
        parameters.setParity("Even");

        try {
            SerialConnection connection = new SerialConnection(parameters);
            ModbusSerialMaster master = new ModbusSerialMaster(parameters);
            connection.open();

            int slaveId = 1; // Адрес устройства Delta PLC
            int registerId = 100; // Адрес регистра для чтения

            ReadInputRegistersRequest request = new ReadInputRegistersRequest(registerId, 10);
            request.setUnitID(slaveId);

            InputRegister[] response = master.readInputRegisters(1,100); ///.send(request);;

            if (response != null) {
                InputRegister value = response[0];
                System.out.println("Прочитано значение из регистра: " + value);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

