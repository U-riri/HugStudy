package com.example.simplediary.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.simplediary.entity.DailyRecordEntity;
import com.example.simplediary.form.DailyRecordForm;
import com.example.simplediary.service.DailyRecordService;

@Controller
public class DailyRecordController {

    @Autowired
    private DailyRecordService service;

    // ---------------------------------------------------------
    // 【開発者用】全件一覧画面（index.html）
    // アプリ開発初期に作成した基本 CRUD 一覧。
    // 最終的なユーザー向け一覧は /history に統一しているが、
    // データ確認・動作検証のために残している。
    // ---------------------------------------------------------
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("records", service.findAll());
        return "index";
    }

    // ---------------------------------------------------------
    // 【ユーザー向け】ダッシュボード画面
    // 今日の日記 + 一週間のまとめを表示するメイン画面。
    // ---------------------------------------------------------
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        LocalDate todayDate = LocalDate.now();

        // 今日の日記（存在しない場合は null）
        DailyRecordEntity today = service.findByRecordDate(todayDate).orElse(null);
        model.addAttribute("today", today);

        // 一週間分のデータ
        LocalDate start = todayDate.minusDays(6);
        LocalDate end = todayDate;
        List<DailyRecordEntity> weeklyRecords = service.findByRecordDateBetween(start, end);
        model.addAttribute("weeklyRecords", weeklyRecords);

        // 画面表示用の「〇月〇日〜〇月〇日」
        model.addAttribute("weekRange", service.getWeekRange(todayDate));

        return "dashboard";
    }

    // ---------------------------------------------------------
    // 新規登録（GET）
    // 空のフォームを表示するだけ。
    // ---------------------------------------------------------
    @GetMapping("/add")
    public String showAddForm(@ModelAttribute DailyRecordForm dailyRecordForm) {
        return "record/add";
    }

    // ---------------------------------------------------------
    // 新規登録（POST）
    // バリデーション → 保存 → ダッシュボードへ戻る。
    // ---------------------------------------------------------
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute DailyRecordForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "record/add";
        }

        // ★ Service に String → LocalDate の変換を任せる
        service.saveFromForm(form);

        return "redirect:/dashboard";
    }

    // ---------------------------------------------------------
    // 編集（GET）
    // ID で取得 → Form に詰め替えて画面へ。
    // 戻り先（dashboard or history）も保持する。
    // ---------------------------------------------------------
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,
                               @RequestParam(name = "from", required = false) String from,
                               Model model) {

        DailyRecordEntity entity = service.findById(id).orElse(null);
        if (entity == null) {
            return "redirect:/dashboard";
        }

        // 戻り先（history or dashboard）
        String backTo = "history".equals(from) ? "history" : "dashboard";
        model.addAttribute("backTo", backTo);

        // ★ Entity → Form（LocalDate → String）
        DailyRecordForm form = new DailyRecordForm();
        form.setId(entity.getId());
        form.setRecordDate(entity.getRecordDate().toString());
        form.setNews(entity.getNews());
        form.setGoodThing(entity.getGoodThing());
        form.setMood(entity.getMood());

        model.addAttribute("dailyRecordForm", form);
        return "record/edit";
    }

    // ---------------------------------------------------------
    // 編集（POST）
    // バリデーション → 更新 → 戻り先へリダイレクト。
    // ---------------------------------------------------------
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,
                       @RequestParam(name = "from", required = false) String from,
                       @Valid @ModelAttribute("dailyRecordForm") DailyRecordForm form,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("backTo", from);
            return "record/edit";
        }

        form.setId(id);

        // ★ Service に String → LocalDate の変換を任せる
        service.saveFromForm(form);

        return "history".equals(from) ? "redirect:/history" : "redirect:/dashboard";
    }

    // ---------------------------------------------------------
    // 削除
    // 戻り先（dashboard or history）に応じてリダイレクト。
    // ---------------------------------------------------------
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         @RequestParam(name = "from", required = false) String from) {

        service.deleteById(id);

        if ("dashboard".equals(from)) {
            return "redirect:/dashboard";
        }

        return "redirect:/history";
    }

    // ---------------------------------------------------------
    // 【ユーザー向け】履歴一覧
    // 過去の全データを日付降順で表示。
    // ---------------------------------------------------------
    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("records", service.findAll());
        return "record/history";
    }
}
