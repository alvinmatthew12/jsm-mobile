package com.example.jsm_project.Container;

public class BL_Shipper {
    public String bl_no;
    public int id;

    public BL_Shipper(int id, String bl_no) {
        this.id = id;
        this.bl_no = bl_no;
    }

    public String getBl_no() {
        return bl_no;
    }

    public void setBl_no(String bl_no) {
        this.bl_no = bl_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
