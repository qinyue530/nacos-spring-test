package com.nacos.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

public class AllHeatmap {


    private String id;
    private String step;
    private String numOfSteps;
    private String detailGroup;
    private String timeBucket;

    @Override
    public String toString() {
        return "allHeatmap{" +
                "id='" + id + '\'' +
                ", step='" + step + '\'' +
                ", numOfSteps='" + numOfSteps + '\'' +
                ", detailGroup='" + detailGroup + '\'' +
                ", timeBucket='" + timeBucket + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getNumOfSteps() {
        return numOfSteps;
    }

    public void setNumOfSteps(String numOfSteps) {
        this.numOfSteps = numOfSteps;
    }

    public String getDetailGroup() {
        return detailGroup;
    }

    public void setDetailGroup(String detailGroup) {
        this.detailGroup = detailGroup;
    }

    public String getTimeBucket() {
        return timeBucket;
    }

    public void setTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
    }
}
