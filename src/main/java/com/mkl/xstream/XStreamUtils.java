package com.mkl.xstream;

import java.util.ArrayList;
import java.util.List;

public class XStreamUtils {

    public List<Province> getProvinceList() {
        Province p1 = new Province();
        p1.setName("北京");
        p1.addCity(new City("东城区", "DongChengQu"));
        p1.addCity(new City("朝阳区", "ChaoYangQu"));

        Province p2 = new Province();
        p2.setName("广东");
        p2.addCity(new City("广州市", "GuangZhou"));
        p2.addCity(new City("深圳市", "ShenZhen"));

        List<Province> provinceList = new ArrayList<>();
        provinceList.add(p1);
        provinceList.add(p2);

        return provinceList;
    }
}
