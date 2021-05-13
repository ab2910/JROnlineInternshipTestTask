package com.game.entity;

import com.game.controller.PlayerOrder;

import java.util.Date;

public class Parameters {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Date after;
    private Date before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;
    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;

    public Parameters() {
    }

    public Parameters(String name, String title, Race race, Profession profession,
                      Long after, Long before, Boolean banned,
                      Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel,
                      PlayerOrder order, Integer pageNumber, Integer pageSize) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.after = new Date(after);
        this.before = new Date(before);
        this.banned = banned;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.order = order;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getAfter() {
        return after;
    }

    public void setAfter(Long after) {
        this.after = new Date(after);
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Long before) {
        this.before = new Date(before);
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return String.format("Parameters{|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|}",
                name != null ? "name='" + name + '\'' : "",
                title != null ? "title='" + title + '\'' : "",
                race != null ? "race=" + race : "",
                profession != null ? "profession=" + profession : "",
                after != null ? "after=" + after : "",
                before != null ? "before=" + before : "",
                banned != null ? "banned=" + banned : "",
                minExperience != null ? "minExperience=" + minExperience : "",
                maxExperience != null ? "maxExperience=" + maxExperience : "",
                minLevel != null ? "minLevel=" + minLevel : "",
                maxLevel != null ? "maxLevel=" + maxLevel : "",
                order != null ? "order=" + order : "",
                pageNumber != null ? "pageNumber=" + pageNumber : "",
                pageSize != null ? "pageSize=" + pageSize : ""
        ).replaceAll("\\|+", "|").replaceAll("\\|", " ");
    }
}