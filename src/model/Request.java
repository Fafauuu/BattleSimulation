package model;

import model.objects.units.Unit;

public class Request {
    private final Unit requester;
    private int fulfilAttempts;
    private final int maxFulfilAttempts;
    private final Actions action;
    private final Actions secondaryAction;
    private boolean fulfilled;

    public Request(Unit requester, Actions action) {
        this.fulfilAttempts = 0;
        this.action = action;
        this.secondaryAction = null;
        this.fulfilled = false;
        this.requester = requester;
        maxFulfilAttempts = setMaxFulfilAttempts(action);
    }

    public Request(Unit requester, Actions action, Actions secondaryAction) {
        this.fulfilAttempts = 0;
        this.action = action;
        this.secondaryAction = secondaryAction;
        this.fulfilled = false;
        this.requester = requester;
        maxFulfilAttempts = setMaxFulfilAttempts(action);
    }

    private int setMaxFulfilAttempts(Actions action) {
        final int maxFulfilAttempts;
        if (action == Actions.ATTACK) {
            maxFulfilAttempts = 1;
        } else {
            maxFulfilAttempts = 4;
        }
        return maxFulfilAttempts;
    }


    public void addAttempt() {
        fulfilAttempts++;
    }

    public int getFulfilAttempts() {
        return fulfilAttempts;
    }

    public int getMaxFulfilAttempts() {
        return maxFulfilAttempts;
    }

    public Actions getAction() {
        return action;
    }

    public Actions getSecondaryAction() {
        return secondaryAction;
    }

    public Unit getRequester() {
        return requester;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
