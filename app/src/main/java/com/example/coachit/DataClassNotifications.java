package com.example.coachit;

public class DataClassNotifications {
    private String dataTitle;
    private String dataMessage;
    private String dataFrom;

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataMessage() {
        return dataMessage;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public DataClassNotifications(String dataTitle, String dataMessage, String dataFrom) {
        this.dataTitle = dataTitle;
        this.dataMessage = dataMessage;
        this.dataFrom = dataFrom;
    }

    public DataClassNotifications(){

    }
}
