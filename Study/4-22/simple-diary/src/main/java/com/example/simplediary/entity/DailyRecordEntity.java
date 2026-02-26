package com.example.simplediary.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "daily_record")  // ★ この Entity が対応するテーブル名
public class DailyRecordEntity {

    // ---------------------------------------------------------
    // 主キー（自動採番）
    // 日記 1 件を一意に識別する ID。
    // ---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // ---------------------------------------------------------
    // 記録日（LocalDate）
    // 1 日につき 1 件の記録を想定。
    // ---------------------------------------------------------
    @Column(name = "record_date")
    private LocalDate recordDate;

    // ---------------------------------------------------------
    // 今日のニュース（任意入力）
    // ---------------------------------------------------------
    @Column(name = "news")
    private String news;

    // ---------------------------------------------------------
    // 良かったこと・できたこと（任意入力）
    // ---------------------------------------------------------
    @Column(name = "good_thing")
    private String goodThing;

    // ---------------------------------------------------------
    // 気分（happy / neutral / sad）
    // セレクトボックスで選択。
    // ---------------------------------------------------------
    @Column(name = "mood")
    private String mood;

    // ===== Getter / Setter =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getGoodThing() {
        return goodThing;
    }

    public void setGoodThing(String goodThing) {
        this.goodThing = goodThing;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
