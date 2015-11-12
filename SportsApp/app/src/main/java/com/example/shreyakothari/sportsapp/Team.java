package com.example.shreyakothari.sportsapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
    private String m_Name;
    private int m_Ranking;
    private float m_Rating;
    private int m_Wins;
    private int m_Loss;

    public Team() {

    }

    public Team(Parcel p) {
        m_Name = p.readString();
        m_Ranking = p.readInt();
        m_Rating = p.readFloat();
        m_Loss = p.readInt();
        m_Wins = p.readInt();
    }

    public String getName() {
        return this.m_Name;
    }

    public void setName(String name) {
        this.m_Name = name;
    }

    public int getRanking() {
        return this.m_Ranking;
    }

    public void setRanking(int ranking) {
        this.m_Ranking = ranking;
    }

    public int getWin() {
        return this.m_Wins;
    }

    public void setWin(int wins) {
        this.m_Wins = wins;
    }

    public int getLoss() {
        return this.m_Loss;
    }

    public void setLoss(int loss) {
        this.m_Loss = loss;
    }

    public float getRating() {
        return this.m_Rating;
    }

    public void setRating(float f) {
        this.m_Rating = f;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flag) {
        // TODO Auto-generated method stub
        dest.writeString(m_Name);
        dest.writeInt(m_Ranking);
        dest.writeFloat(m_Rating);
        dest.writeInt(m_Loss);
        dest.writeInt(m_Wins);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }


        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
