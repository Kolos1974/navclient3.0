package services;

public interface StatusAndRemainingCallback extends StateChangeCallback {

    void remainingTime(long secs);

}
