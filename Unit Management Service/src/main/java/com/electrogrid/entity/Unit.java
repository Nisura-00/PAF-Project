package com.electrogrid.entity;

import java.sql.Date;

/**
 * Created By Rashani
 * Date: 4/19/2022
 */

public class Unit {

    private int id;
    private int minUnitValue;
    private int maxUnitValue;
    private float unitPrice;
    private Date insertDate;
    private Date modifiedDate;

    public Unit() {
    }

    public Unit(int id, int minUnitValue, int maxValue, float unitPrice, Date insertDate, Date modifiedDate) {
        this.id = id;
        this.minUnitValue = minUnitValue;
        this.maxUnitValue = maxValue;
        this.unitPrice = unitPrice;
        this.insertDate = insertDate;
        this.modifiedDate = modifiedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinUnitValue() {
        return minUnitValue;
    }

    public void setMinUnitValue(int minUnitValue) {
        this.minUnitValue = minUnitValue;
    }

    public int getMaxUnitValue() {
        return maxUnitValue;
    }

    public void setMaxUnitValue(int maxUnitValue) {
        this.maxUnitValue = maxUnitValue;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
