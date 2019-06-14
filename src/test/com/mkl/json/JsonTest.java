package com.mkl.json;

import com.mkl.xstream.City;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    @Test
    public void jsonMapTest() {
        // map
//        JSONObject map = new JSONObject();
//        map.put("name", "zhangsan");
//        map.put("age", 23);
//        map.put("gender", "male");
//        String s = map.toString();
//        System.out.println(s);
        // 当已经有一个bean时，可以通过fromObject()方法转换
        City city = new City("guangzhou", "gz");
        JSONObject map = JSONObject.fromObject(city);
        String s = map.toString();
        System.out.println(s);
    }

    @Test
    public void jsonListTest() {
        // map
        City sz = new City("shenzhen", "sz");
        City gz = new City("guangzhou", "gz");
//        JSONArray list = new JSONArray();
//        list.add(gz);
//        list.add(sz);
//        System.out.println(list.toString());
        // 原来就有一个list的情况
        List<City> cityList = new ArrayList<>();
        cityList.add(gz);
        cityList.add(sz);
        JSONArray list = JSONArray.fromObject(cityList);
        System.out.println(list.toString());
    }
}
