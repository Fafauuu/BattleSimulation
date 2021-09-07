package service;

import exceptions.CantMoveObjectException;
import model.Actions;
import model.Request;
import model.objects.Unit;

import java.util.ArrayList;
import java.util.List;

public class ActionHandler {
    private final List<Request> requests;
    private final Engine engine;

    public ActionHandler(Engine engine) {
        this.requests = new ArrayList<>();
        this.engine = engine;
    }

    public void simulateTurn() {
        performActions();
        dropAllRequests();
    }

    public void performActions() {
        while (!requests.isEmpty()) {
            for (Request request : requests) {
                perform(request);
            }
            dropUnnecessaryRequests();
        }
    }

    public void perform(Request request) {
        if (request.getRequester().isAlive()) {
            Actions action = request.getAction();
            if (request.getFulfilAttempts() == request.getMaxFulfilAttempts()){
                action = request.getSecondaryAction();
            }
            switch (action) {
                case MOVE_UP:
                    tryToMove(request, -1, 0);
                    break;
                case MOVE_DOWN:
                    tryToMove(request, 1, 0);
                    break;
                case MOVE_LEFT:
                    tryToMove(request, 0, -1);
                    break;
                case MOVE_RIGHT:
                    tryToMove(request, 0, 1);
                    break;
                case ATTACK:
                    tryToAttack(request);
                    break;
            }
        } else request.addAttempt();
    }

    private void tryToMove(Request request, int XModifier, int YModifier) {
        try {
            engine.move(request.getRequester(), XModifier, YModifier);
            request.setFulfilled(true);
        } catch (CantMoveObjectException e) {
            request.addAttempt();
        }
    }

    private void tryToAttack(Request request) {
        try {
            engine.attack(request.getRequester());
            request.setFulfilled(true);
        } catch (Exception e) {
            e.printStackTrace();
            request.addAttempt();
        }
    }

    public void dropUnnecessaryRequests() {
        dropFulfilledRequests();
        dropUnfulfilledRequests();
    }

    public void dropFulfilledRequests() {
        requests.removeIf(request -> request.isFulfilled());
    }

    public void dropUnfulfilledRequests() {
        requests.removeIf(request -> request.getFulfilAttempts() > request.getMaxFulfilAttempts());
    }

    public void dropAllRequests() {
        requests.clear();
    }

    public void setRequest(Unit unit, Actions action) {
        requests.add(new Request(unit, action));
    }
    public void setRequest(Unit unit, Actions action, Actions secondAction) {
        requests.add(new Request(unit, action, secondAction));
    }


}
