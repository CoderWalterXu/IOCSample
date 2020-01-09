package com.xlh.study.iocsample.bean;


import androidx.annotation.NonNull;

public class ItemBean {

    String index;
    String content;

    public ItemBean(String index, String content) {
        this.index = index;
        this.content = content;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "index='" + index + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
