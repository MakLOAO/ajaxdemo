package com.mkl.xstream;

import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.List;

public class TestXStream {

    // 不使用别名，父标签为<list>
    // 对应的标签分别是<com.mkl.xstream.Province>, <com.mkl.xstream.City>
    @Test
    public void testXStream() {

        XStreamUtils xStreamUtils = new XStreamUtils();
        List<Province> provinceList = xStreamUtils.getProvinceList();

        XStream xStream = new XStream();
        String s = xStream.toXML(provinceList);
        System.out.println(s);
    }

    // 使用别名
    // 希望<list>为<china>, <com.mkl.xstream.Province>为<province>, <com.mkl.xstream.City>为<city>
    @Test
    public void testXStreamAlias() {

        XStreamUtils xStreamUtils = new XStreamUtils();
        List<Province> provinceList = xStreamUtils.getProvinceList();

        XStream xStream = new XStream();
        xStream.alias("china", List.class);
        xStream.alias("province", Province.class);
        xStream.alias("city", City.class);
        String s = xStream.toXML(provinceList);
        System.out.println(s);
    }

    // 默认bean的属性会生成子标签，现在希望对应bean的属性可以生成标签的属性
    @Test
    public void testXStreamAttr() {

        XStreamUtils xStreamUtils = new XStreamUtils();
        List<Province> provinceList = xStreamUtils.getProvinceList();

        XStream xStream = new XStream();
        xStream.alias("china", List.class);
        xStream.alias("province", Province.class);
        xStream.alias("city", City.class);
        // 把Province类型的name属性，生成<province>元素的属性
        xStream.useAttributeFor(Province.class, "name");
        String s = xStream.toXML(provinceList);
        System.out.println(s);
    }

    // 把没有用的<cities>属性也删除
    @Test
    public void testXStreamDeleteCollection() {

        XStreamUtils xStreamUtils = new XStreamUtils();
        List<Province> provinceList = xStreamUtils.getProvinceList();

        XStream xStream = new XStream();
        xStream.alias("china", List.class);
        xStream.alias("province", Province.class);
        xStream.alias("city", City.class);
        // 把Province类型的name属性，生成<province>元素的属性
        xStream.useAttributeFor(Province.class, "name");
        // 去除<cities>这样的Collection类型的属性
        xStream.addImplicitCollection(Province.class, "cities");
        String s = xStream.toXML(provinceList);
        System.out.println(s);
    }

    // 把不想要的javabean属性也去除
    @Test
    public void testXStreamDeleteAttr() {
        XStreamUtils xStreamUtils = new XStreamUtils();
        List<Province> provinceList = xStreamUtils.getProvinceList();

        XStream xStream = new XStream();
        xStream.alias("china", List.class);
        xStream.alias("province", Province.class);
        xStream.alias("city", City.class);
        // 把Province类型的name属性，生成<province>元素的属性
        xStream.useAttributeFor(Province.class, "name");
        // 去除<cities>这样的Collection类型的属性
        xStream.addImplicitCollection(Province.class, "cities");
        // 让City类的description属性不生成标签
        xStream.omitField(City.class, "description");
        String s = xStream.toXML(provinceList);
        System.out.println(s);
    }
}
