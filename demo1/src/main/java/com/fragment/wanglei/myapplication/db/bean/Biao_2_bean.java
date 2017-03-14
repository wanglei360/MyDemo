package com.fragment.wanglei.myapplication.db.bean;

import java.util.Date;

/**
 * 创建者：wanglei
 * 时间：16/4/20  14:59
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Biao_2_bean {

    private Long id;
    /** Not-null value. */
    private String text;
    private String comment;
    private Date date;
    private Date date1;


    public Biao_2_bean(Long id, String text, String comment, Date date,Date date1) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
        this.date1 = date1;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
