package service;

import model.Side;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CsvSaveReader {
    private final UnitFactory unitFactory;

    public CsvSaveReader(UnitFactory unitFactory) {
        this.unitFactory = unitFactory;
        readSave();
    }

    public void readSave(){

        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("src/saves/save1.csv"));

            String line;

            while ((line = csvReader.readLine()) != null){
                String[] data = line.split(",");

                Class<?>[] paramTypes = {Side.class, int.class, int.class};
                String methodName = "create" + data[0];
                Side side = Side.valueOf(data[1]);
                int xCoordinate = Integer.parseInt(data[2]);
                int yCoordinate = Integer.parseInt(data[3]);

                Method method = unitFactory.getClass().getMethod(methodName, paramTypes);
                method.invoke(unitFactory, side, xCoordinate, yCoordinate);
            }

        }catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }

    }

}
