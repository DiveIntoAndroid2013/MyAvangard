package ru.omsu.diveintoandroid.myavangard.services.inner.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProtocolResponse extends BaseResponse {

    public static class Team {

        public static class Shots {
            @SerializedName("g")
            public int goals;

            @SerializedName("sog")
            public int shotsOnGoals;

            @SerializedName("g_pct")
            public float goalsPercent;
        }

        public static class PowerGoals {
            @SerializedName("GF54")
            public int in54;

            @SerializedName("GF53")
            public int in53;

            @SerializedName("GF43")
            public int in43;

            public int sum() {
                return in54 + in53 + in43;
            }

        }

        @SerializedName("Id")
        public long id;

        @SerializedName("Goals")
        public PowerGoals powerGoals;

        @SerializedName("Shots")
        public Shots shots;
    }

    @SerializedName("Team")
    public List<Team> teams;

}