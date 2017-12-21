package com.study.spring.test.data;

import java.util.List;

public class TestData {

    private String name;
    private Integer type;
    private List<SubData> subDatas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<SubData> getSubDatas() {
        return subDatas;
    }

    public void setSubDatas(List<SubData> subDatas) {
        this.subDatas = subDatas;
    }
}
