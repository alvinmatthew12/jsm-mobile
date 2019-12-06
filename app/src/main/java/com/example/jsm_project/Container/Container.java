package com.example.jsm_project.Container;

public class Container {
    public String containerNo, containerName, blID;
    public int id;

    public Container(int id, String containerNo, String customerName, String blID) {
        this.id = id;
        this.containerNo = containerNo;
        this.containerName = customerName;
        this.blID = blID;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getBlID() {
        return blID;
    }

    public void setBlID(String blID) {
        this.blID = blID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
