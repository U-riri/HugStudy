package com.example.simplediary.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 画面入力用のフォームクラス。
 * Entity とは別に用意することで、
 * ・バリデーション（必須チェック・文字数制限）
 * ・String での日付受け取り
 * ・画面と Entity の分離
 * を実現している。
 */
public class DailyRecordForm {

    // ---------------------------------------------------------
    // ID（編集時のみ使用）
    // 新規登録では null のまま。
    // ---------------------------------------------------------
    private Integer id;

    // ---------------------------------------------------------
    // 記録日（String で受け取る）
    // ・input[type=text] で扱いやすいように String にしている
    // ・Service 層で LocalDate に変換して保存する
    // ---------------------------------------------------------
    @NotBlank(message = "日付を入力してください")
    private String recordDate;

    // ---------------------------------------------------------
    // 今日のニュース（20文字以内）
    // ---------------------------------------------------------
    @NotBlank(message = "今日のニュースを入力してください")
    @Size(max = 20, message = "ニュースは20文字以内で入力してください")
    private String news;

    // ---------------------------------------------------------
    // 良かったこと・できたこと（20文字以内）
    // ---------------------------------------------------------
    @NotBlank(message = "今日のいいこと・できたことを入力してください")
    @Size(max = 20, message = "いいことは20文字以内で入力してください")
    private String goodThing;

    // ---------------------------------------------------------
    // 気分（happy / neutral / sad）
    // セレクトボックスで選択。
    // ---------------------------------------------------------
    @NotBlank(message = "今日の気分を選択してください")
    private String mood;

    // ===== Getter / Setter =====

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRecordDate() { return recordDate; }
    public void setRecordDate(String recordDate) { this.recordDate = recordDate; }

    public String getNews() { return news; }
    public void setNews(String news) { this.news = news; }

    public String getGoodThing() { return goodThing; }
    public void setGoodThing(String goodThing) { this.goodThing = goodThing; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }
}
