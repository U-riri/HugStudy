package com.example.simplediary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplediary.entity.DailyRecordEntity;
import com.example.simplediary.form.DailyRecordForm;
import com.example.simplediary.repository.DailyRecordRepository;

@Service
public class DailyRecordService {

    @Autowired
    private DailyRecordRepository repository;

    // ---------------------------------------------------------
    // 【開発者用】全件取得（index.html で使用）
    // 日付の新しい順に並べて返す。
    // ---------------------------------------------------------
    public List<DailyRecordEntity> findAll() {
        return repository.findAllByOrderByRecordDateDesc();
    }

    // ---------------------------------------------------------
    // ID で 1 件取得（編集画面で使用）
    // 見つからない場合は Optional.empty() を返す。
    // ---------------------------------------------------------
    public Optional<DailyRecordEntity> findById(Integer id) {
        return repository.findById(id);
    }

    // ---------------------------------------------------------
    // 今日の日記を取得（dashboard 用）
    // 今日の日付に一致するデータを 1 件返す。
    // ---------------------------------------------------------
    public Optional<DailyRecordEntity> findByRecordDate(LocalDate date) {
        return repository.findByRecordDate(date);
    }

    // ---------------------------------------------------------
    // 一週間分のデータ取得（dashboard 用）
    // 期間内のデータを日付昇順で返す。
    // ---------------------------------------------------------
    public List<DailyRecordEntity> findByRecordDateBetween(LocalDate start, LocalDate end) {
        return repository.findByRecordDateBetweenOrderByRecordDateAsc(start, end);
    }

    // ---------------------------------------------------------
    // ★ Form → Entity 変換 + 保存
    //   ・フォームの recordDate は String のため LocalDate に変換
    //   ・新規/編集の両方に対応（ID が null なら新規）
    // ---------------------------------------------------------
    public void saveFromForm(DailyRecordForm form) {

        DailyRecordEntity entity = new DailyRecordEntity();

        // 編集時は ID をセット（新規時は null のまま）
        if (form.getId() != null) {
            entity.setId(form.getId());
        }

        // ★ String → LocalDate に変換（フォームは文字列で受け取るため）
        entity.setRecordDate(LocalDate.parse(form.getRecordDate()));

        entity.setNews(form.getNews());
        entity.setGoodThing(form.getGoodThing());
        entity.setMood(form.getMood());

        repository.save(entity);
    }

    // ---------------------------------------------------------
    // 削除（ID 指定）
    // ---------------------------------------------------------
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    // ---------------------------------------------------------
    // 一週間の範囲を文字列で返す（画面表示用）
    // 例）2024-01-01 〜 2024-01-07
    // ---------------------------------------------------------
    public String getWeekRange(LocalDate today) {
        LocalDate start = today.minusDays(6);
        return start + " 〜 " + today;
    }
}
