package com.example.s528772.assignment08;

import java.util.Date;

/**
 * Created by s528772 on 11/16/2017.
 */

public class OrderInfo {


    public String Person_Name;
    public String Cookie_Type;
    public int Boxes_Count;
    public int Total_Price;
    public String objectId;
    public Date created;
    public Date updated;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "Person_Name='" + Person_Name + '\'' +
                ", Cookie_Type='" + Cookie_Type + '\'' +
                ", Boxes_Count=" + Boxes_Count +
                ", Total_Price=" + Total_Price +
                ", objectId='" + objectId + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
