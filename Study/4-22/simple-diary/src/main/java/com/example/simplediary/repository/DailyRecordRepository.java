package com.example.simplediary.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplediary.entity.DailyRecordEntity;

@Repository
public interface DailyRecordRepository extends JpaRepository<DailyRecordEntity, Integer> {

    // ---------------------------------------------------------
    // 今日の日記を 1 件取得（dashboard 用）
    // LocalDate に完全一致するデータを返す。
    // 見つからない場合は Optional.empty()。
    // ---------------------------------------------------------
    Optional<DailyRecordEntity> findByRecordDate(LocalDate date);

    // ---------------------------------------------------------
    // 一週間分の記録を取得（dashboard 用）
    // 指定した期間内のデータを日付昇順で返す。
    // 例：2024-01-01 〜 2024-01-07
    // ---------------------------------------------------------
    List<DailyRecordEntity> findByRecordDateBetweenOrderByRecordDateAsc(LocalDate start, LocalDate end);

    // ---------------------------------------------------------
    // 全件を日付の降順（新しい順）で取得
    // index.html（開発者用一覧）や history で使用。
    // ---------------------------------------------------------
    List<DailyRecordEntity> findAllByOrderByRecordDateDesc();
}
