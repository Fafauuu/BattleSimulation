package service;

import model.BattleField;

public class PrintingFieldServiceConsoleImpl implements PrintingFieldService {
    private final BattleField battleField;

    public PrintingFieldServiceConsoleImpl(BattleField battleField) {
        this.battleField = battleField;
    }

    @Override
    public void print() {
        StringBuilder field = new StringBuilder();

        for (int x = 0; x < battleField.getFieldSize(); x++) {
            for (int y = 0; y < battleField.getFieldSize(); y++) {
                field.append(String.format("%-17s", battleField.getSingleField(x, y).toString()));
            }
            field.append("\n");
        }
        System.out.println(field);
    }
}
