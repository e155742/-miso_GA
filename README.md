1 + 1 = みそスープ
====
20種類の具の中から最も優れた5種類の具を選ぶ  
[**みそ汁で一番好きな具は（５,０００人アンケート結果）**](http://www.nihonmiso.com/tips/list_a/07.html)

### 評価
ランキングのパーセントの合計をそのままスコアとする。  
単品のランキングだけでなく組み合わせのランキングも評価対象となる。

### 選択
ランキング方式  
下位40%を殺して上位40%を複製

### 交叉  
ランク付けした際、お隣の順位の染色体とランダムな3つの具を入れ替える。  
この際、交叉後に具が重複しないようにする(同じ具が2品以上にならないようにする)。

### 突然変異
5%の確率で具が変化する。これも変化後に具が重複しないようにする。

## 実行
miso.jarを実行すればいい。
```bash
$ make
$ java -jar miso.jar
```