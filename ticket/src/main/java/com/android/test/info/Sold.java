package com.android.test.info;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Lee on 2017/10/27 0027.
 * 已售座位信息表
 */


@Entity
public class Sold {
    @Id(autoincrement =true)
    private Long id;
    private String filmName;
    private String hall;
    private String date;

    @Generated(hash = 1507864730)
    public Sold(Long id, String filmName, String hall, String date, String time,
            String seat) {
        this.id = id;
        this.filmName = filmName;
        this.hall = hall;
        this.date = date;
        this.time = time;
        this.seat = seat;
    }

    @Generated(hash = 326176276)
    public Sold() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    private String time;
    private String seat;


}
