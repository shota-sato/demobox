# 学んだこと

| 項目       | `@RestController`              | `@GetMapping`                   |
| -------- | ------------------------------ | ------------------------------- |
| 付ける場所    | クラスの上                          | メソッドの上                          |
| 役割       | Web APIのクラスだとSpringに教える        | GETリクエストのURLを定義する               |
| 何をする？    | クラスをBean登録＋レスポンスをJSON/文字列で返す設定 | 指定URLとHTTPメソッドを紐付ける             |
| HTTPメソッド | 関係ない                           | GET専用                           |
| 単体で意味ある？ | メソッドが無いと意味なし                   | クラスがControllerでないと意味なし          |
| よく一緒に使う  | `@RequestMapping`              | `@PostMapping` `@PutMapping` など |


## @RestController の正体

実はこれの合体版
```
@Controller  → Webの制御クラス
@ResponseBody  → 戻り値をHTMLではなくレスポンス本文として返す
```

## @GetMapping の正体
このURLにGETで来たらこのメソッド実行
```
@RequestMapping(method = RequestMethod.GET)
```


