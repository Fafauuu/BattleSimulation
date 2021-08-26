package service;

import model.BattleField;

public class PrintingFieldServiceConsoleImpl implements PrintingFieldService{
    private final BattleField battleField;

    public PrintingFieldServiceConsoleImpl(BattleField battleField) {
        this.battleField = battleField;
    }

    @Override
    public void print() {
        System.out.println(battleField.toString());
    }
}
