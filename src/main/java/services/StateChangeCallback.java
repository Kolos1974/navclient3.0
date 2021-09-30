package services;

public interface StateChangeCallback {

    public enum MessageType {
        INFO, ERROR, NETWORKERROR
    }

    void onSendingAttemp();

    void onSuccessfulAttemp();

    void onFailedAttemp();

    void info(MessageType type, String info);

}
