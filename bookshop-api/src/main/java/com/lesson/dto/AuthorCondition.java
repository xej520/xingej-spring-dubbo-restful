
package com.lesson.dto;

/**
 * 
 * @author erjun 2017年12月28日 上午6:19:45
 */
public class AuthorCondition {

    private String name;

    private Integer age;

    private Integer ageTo;

    private Sex sex;

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

}
