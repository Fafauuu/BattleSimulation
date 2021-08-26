package model;

public class Request {
    private final Unit requester;
    private int fulfilAttempts;
    private final int maxFulfilAttempts;
    private final Actions action;
    private boolean fulfilled;

    public Request(Unit requester, Actions action) {
        this.fulfilAttempts = 0;
        this.action = action;
        this.fulfilled = false;;
        this.requester = requester;

        if (action == Actions.ATTACK){
            maxFulfilAttempts = 1;
        }
        else {
            maxFulfilAttempts = 4;
        }
    }

    public void addAttempt(){
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
