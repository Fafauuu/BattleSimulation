package service;

import model.Actions;
import model.objects.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class PathFindingService {

    public List<Actions> setFirstAndSecondDirection(Unit unit) {
        Actions firstDirection;
        Actions secondDirection;

        int XDifference = checkXDifference(unit, unit.getTarget());
        int YDifference = checkYDifference(unit, unit.getTarget());

        boolean XDifferenceGreaterThatY = Math.abs(XDifference) > Math.abs(YDifference);
        boolean XDifferenceGreaterOrEqualToY = Math.abs(XDifference) >= Math.abs(YDifference);


        if (XDifference <= 0 && YDifference > 0) {
            if (XDifferenceGreaterThatY) {
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_RIGHT;
            } else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_UP;
            }
        } else if (XDifference > 0 && YDifference >= 0) {
            if (XDifferenceGreaterOrEqualToY) {
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_RIGHT;
            } else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_DOWN;
            }
        } else if (XDifference >= 0 && YDifference < 0) {
            if (XDifferenceGreaterThatY) {
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_LEFT;
            } else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_DOWN;
            }
        } else {
            if (XDifferenceGreaterOrEqualToY) {
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_LEFT;
            } else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_UP;
            }
        }
        return new ArrayList<>(List.of(firstDirection, secondDirection));
    }

    private int checkXDifference(Unit unit, Unit target) {
        int unitXCoordinate = unit.getXCoordinate();
        int targetXCoordinate = target.getXCoordinate();

        return targetXCoordinate - unitXCoordinate;
    }

    private int checkYDifference(Unit unit, Unit target) {
        int unitYCoordinate = unit.getYCoordinate();
        int targetYCoordinate = target.getYCoordinate();

        return targetYCoordinate - unitYCoordinate;
    }
}
