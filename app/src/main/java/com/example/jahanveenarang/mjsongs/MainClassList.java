package com.example.jahanveenarang.mjsongs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainClassList {


    @SerializedName("resultCount")
    private int resultcount;

    public int getResultcount()
    {
        return resultcount;
    }

    public void setResultcount(int resultcount) {

        this.resultcount = resultcount;
    }


    @SerializedName("results")
    private List<ModalClass> results;

    public List<ModalClass> getResults()
    {
        return results;
    }

    public void setResults(List<ModalClass> results)
    {
        this.results = results;
    }

}
