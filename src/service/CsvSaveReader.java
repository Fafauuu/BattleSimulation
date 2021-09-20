package service;

import model.Side;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CsvSaveReader {
    private File file;
    private final UnitFactory unitFactory;
    private final UnitDatabase unitDatabase;

    public CsvSaveReader(UnitFactory unitFactory, UnitDatabase unitDatabase) {
        this.unitFactory = unitFactory;
        this.unitDatabase = unitDatabase;
        unitDatabase.removeAllUnits();
        loadGame();
        readFile();
    }

    private void loadGame() {
        JFileChooser fileChooser = new JFileChooser("src/saves");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    public void readFile(){
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(file));

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
