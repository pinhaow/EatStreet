package com.example.EatStreet.model;

import com.example.EatStreet.R;

public class order {

    private String storeName, pick_up,terminal;

//    String user_contact[] = {"11", "22", "33"};
//    String driver_contact[] = {"111","222","333"};
//    String driver_name[] = {"aaaa","bbbb","cccc"};
//    String arrive_time[] = {"111","222","333"};


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPick_up() {
        return pick_up;
    }

    public void setPick_up(String pick_up) {
        this.pick_up = pick_up;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
