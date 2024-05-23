package com.example.coachit;

public class DataClassTeams {

    private String dataTeamImage;
    private String dataTeamName;
    private String dataTeamCoach;

    public String getDataTeamImage() {return dataTeamImage;}

    public String getDataTeamName() {
        return dataTeamName;
    }

    public String getDataTeamCoach() {
        return dataTeamCoach;
    }

    public DataClassTeams(String dataTeamName, String dataTeamCoach, String imageURL) {
        this.dataTeamName = dataTeamName;
        this.dataTeamCoach = dataTeamCoach;
        this.dataTeamImage = imageURL;

    }

    public DataClassTeams() {

    }
}
