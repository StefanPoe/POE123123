package com.example.coachit;

public class DataClass {
    private String dataImage;
    private String dataName;
    private String dataSurname;
    private String dataNickname;
    private String dataAge;
    private String dataSex;
    private String dataCity;
    private String dataTeam;
    private String dataCoach;
    private String dataPosition;
    private String dataHeight;
    private String dataWeight;
    private String dataDomhand;
    private String dataWingspan;
    private String dataVertical;
    private String dataContact1;
    private String dataContact2;




    public String getDataImage() {return dataImage;}

    public String getDataName() {
        return dataName;
    }

    public String getDataSurname() {
        return dataSurname;
    }

    public String getDataNickname() {
        return dataNickname;
    }

    public String getDataAge() {
        return dataAge;
    }

    public String getDataSex() {return dataSex;}

    public String getDataCity() {
        return dataCity;
    }

    public String getDataTeam() {
        return dataTeam;
    }

    public String getDataCoach() {
        return dataCoach;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getDataHeight() {
        return dataHeight;
    }

    public String getDataWeight() {
        return dataWeight;
    }

    public String getDataDomhand() {
        return dataDomhand;
    }

    public String getDataWingspan() {
        return dataWingspan;
    }

    public String getDataVertical() {
        return dataVertical;
    }

    public String getDataContact1() {
        return dataContact1;
    }

    public String getDataContact2() {
        return dataContact2;
    }








    public DataClass(String dataName, String dataSurname, String dataNickname, String dataAge, String dataCity, String dataCoach, String dataSex, String dataDomhand, String dataContact1, String dataContact2, String dataHeight, String dataWeight, String dataPosition, String dataTeam, String dataVertical, String dataWingspan, String imageURL) {
        this.dataName = dataName;
        this.dataSurname = dataSurname;
        this.dataNickname = dataNickname;
        this.dataAge = dataAge;
        this.dataCity = dataCity;
        this.dataCoach = dataCoach;
        this.dataSex = dataSex;
        this.dataDomhand = dataDomhand;
        this.dataContact1 = dataContact1;
        this.dataContact2 = dataContact2;
        this.dataHeight = dataHeight;
        this.dataWeight = dataWeight;
        this.dataPosition = dataPosition;
        this.dataTeam = dataTeam;
        this.dataVertical = dataVertical;
        this.dataWingspan = dataWingspan;
        this.dataImage = imageURL;
    }

    public DataClass(){

    }
}
