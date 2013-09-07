package ru.omsu.diveintoandroid.myavangard.services.inner.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetGamesResponse extends BaseResponse {

    public static class Game {
        @SerializedName("Id")
        public long id;

        @SerializedName("FirstTeam")
        public FirstTeam firstTeam;

        @SerializedName("SecondTeam")
        public SecondTeam secondTeam;

        @SerializedName("Date")
        public String date;

        @SerializedName("Status")
        public int status;
    }

    public static class FirstTeam {
        @SerializedName("FirstTeamId")
        public long id;

        @SerializedName("Name")
        public String name;

        @SerializedName("Scores")
        public String scores;

        @SerializedName("Logo")
        public String logo;
    }

    public static class SecondTeam {
        @SerializedName("SecondTeamId")
        public long id;

        @SerializedName("Name")
        public String name;

        @SerializedName("Scores")
        public String scores;

        @SerializedName("Logo")
        public String logo;
    }

    @SerializedName("Games")
    public List<Game> games;

}