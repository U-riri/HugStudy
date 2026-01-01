/**
* Task2-8 : 課題内容
*
* 本課題では、演算子の使い方を学んでいきましょう。
* 問①から問⑦まであります。
* 指定された数値、式、変数名を守って記述して下さい。
*
* 問③,⑤については、コメントを入れて下さい。
* 問⑦については、変数名を使って出力して下さい。
*
*/
public class Task2_8 {




    public static void main(String[] args) {




    // ① 5 と 16 を加算した値を代入する、変数 plus を作成して下さい。
    int plus = 5 + 16;



    // ② 27 から 7 を減算した値を代入する、変数 minus を作成して下さい。
    int minus = 27 - 7;



    // ③下記の処理について、何をしているのかコメントを記入して下さい。
    // [10と2を乗算した値を代入する、変数multiplyを作成する]
    int multiply = 10 * 2;




    // ④ 20 を 6 で割った余りを代入する、変数 remi を作成して下さい。
    int remi = 20 % 6;



    // ⑤下記の処理について、何をしているのかコメントを記入して下さい。
    // [Stringクラスの変数townを宣言、値「中央」で初期化]
    // [Stringクラスの変数lineを宣言、値「線」で初期化]
    String town = "中央";
    String line = "線";




    // ⑥ ⑤で作成した変数を連結させた値を代入する変数 train を作成して下さい。
    String train = town + line;



    // ⑦ 変数plus, minus, multiply, remi, train をそれぞれ出力して下さい。
    // 変数plus
    System.out.println ( plus );

    // 変数minus
    System.out.println ( minus );

    // 変数multiply
    System.out.println ( multiply );

    // 変数remi
    System.out.println ( remi );

    // 変数train
    System.out.println ( train );


    }
}