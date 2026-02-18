# 起動方法

```
curl -X GET http://localhost:5000/hello

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Page</title>
</head>
<body>
    <h1>Hello, Spring Boot!</h1>
    <span>Hello, Thymeleaf!</span>
</body>
</html>
PS C:\Users\PC_User>

```

# 学んだこと

| 項目         | `@Controller`       | `@RestController`             |
| ---------- | ------------------- | ----------------------------- |
| 主な用途       | Web画面（HTML）         | Web API（JSON / 文字列）           |
| 戻り値の扱い     | View名として解釈される       | そのままレスポンス本文になる                |
| JSONを返すには？ | `@ResponseBody` が必要 | 不要（自動で付いている）                  |
| 内部構造       | ただの Controller      | `@Controller + @ResponseBody` |
| よく使う場面     | Thymeleaf / JSP     | REST API / SPAバックエンド          |
| 例          | return "index";     | return "Hello";               |


## ModelとDI（依存性注入）
```
@GetMapping("/hello")
public String hello(Model model)
```
コード内ではどこにもmodelというオブジェクトをインスタンス化していないしどこからも受け取っていない  
Spring が引数を見て「Model が必要だな」と判断しModel オブジェクトを生成し  
それを渡してメソッド実行  
Spring MVC が昔から持ってる機能  

### 他に自動注入されるもの
| 書き方                          | 何が入る？       |
| ---------------------------- | ----------- |
| `Model model`                | 画面に渡すデータ入れ物 |
| `HttpServletRequest request` | リクエスト情報     |
| `@RequestParam String name`  | URLパラメータ    |
| `@PathVariable Long id`      | URLの一部      |
| `@RequestBody User user`     | JSONデータ     |
| `Principal principal`        | ログインユーザー    |

## addAttribute()
Modelにデータを保存するメソッド 
```
model.addAttribute("message", "Hello, Thymeleaf!");
```
message という名前で"Hello, Thymeleaf!" を保存
実は中身は Map
Map<String, Object> model;

## MVC
| 役割         | 何をする？ |
| ---------- | ----- |
| Model      | データ   |
| View       | 画面    |
| Controller | つなぐ人  |

```
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "Hello"; // templates/Hello.htmlを表示
    }

}


ブラウザ → /hello にアクセス
        ↓
Controller 実行
        ↓
Model にデータ入れる
        ↓
return "Hello"
        ↓
Spring が View(HTMLテンプレート)であるtemplates/Hello.html を探す
        ↓
Model を HTML に渡す
        ↓
HTML が完成してブラウザへ
```


## Thymeleaf
```
<span th:text="${message}">Hello</span>
```
| 部分         | 意味                 |
| ---------- | ------------------ |
| span       | 小さな表示用の箱           |
| th:text    | Thymeleafの命令       |
| ${message} | Modelの値            |
| Hello      | デフォルト表示（テンプレート確認用） |

```
<html xmlns:th="http://www.thymeleaf.org">
```
| 部分                                                   | 意味                |
| ---------------------------------------------------- | ----------------- |
| xmlns                                                | XML namespace の宣言 |
| th                                                   | これから使う接頭語         |
| [http://www.thymeleaf.org](http://www.thymeleaf.org) | Thymeleafの名前空間    |

「th: というプレフィックスは Thymeleaf のものですよ」と宣言


