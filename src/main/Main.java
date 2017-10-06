package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static final int CHROMOSOME_NUM       = 30; // 染色体の数 5の倍数にすること
    static final int CHROMOSOME_LENGTH    = 5;  // 染色体内の遺伝子(具)の数
    static final int MAX_GENERATION       = 5;  // 繰り返す世代
    static final int MUTATION_PROBABILITY = 5;  // 突然変異する確率(%)

    static final List<Ingredient>   GU_LIST    = new ArrayList<Ingredient>();
    static final List<PopularCombi> COMBI_LIST = new ArrayList<PopularCombi>();

    /**
     * http://www.nihonmiso.com/tips/list_a/07.html
     */
    public static void createGuList() {
        GU_LIST.add(new Ingredient("豆腐",       339));
        GU_LIST.add(new Ingredient("わかめ",     241));
        GU_LIST.add(new Ingredient("油あげ",     211));
        GU_LIST.add(new Ingredient("長ねぎ",     172));
        GU_LIST.add(new Ingredient("じゃがいも", 160));
        GU_LIST.add(new Ingredient("大根",       136));
        GU_LIST.add(new Ingredient("玉ねぎ",     86 ));
        GU_LIST.add(new Ingredient("なめこ",     85 ));
        GU_LIST.add(new Ingredient("しじみ",     46 ));
        GU_LIST.add(new Ingredient("里いも",     40 ));
        GU_LIST.add(new Ingredient("あさり",     39 ));
        GU_LIST.add(new Ingredient("卵",         21 ));
        GU_LIST.add(new Ingredient("にんじん",   17 ));
        GU_LIST.add(new Ingredient("なす",       16 ));
        GU_LIST.add(new Ingredient("ほうれん草", 13 ));
        GU_LIST.add(new Ingredient("えのき",     12 ));
        GU_LIST.add(new Ingredient("もやし",     11 ));
        GU_LIST.add(new Ingredient("ふ",         11 ));
        GU_LIST.add(new Ingredient("豚肉",       11 ));
        GU_LIST.add(new Ingredient("白菜",       10 ));
    }

    /**
     * http://www.nihonmiso.com/tips/list_a/07.html
     */
    public static void createCombiList() {
        COMBI_LIST.add(new PopularCombi("豆腐",       "わかめ",     57));
        COMBI_LIST.add(new PopularCombi("わかめ",     "じゃがいも", 39));
        COMBI_LIST.add(new PopularCombi("豆腐",       "油あげ",     35));
        COMBI_LIST.add(new PopularCombi("豆腐",       "なめこ",     35));
        COMBI_LIST.add(new PopularCombi("豆腐",       "長ねぎ",     33));
        COMBI_LIST.add(new PopularCombi("油あげ",     "大根",       33));
        COMBI_LIST.add(new PopularCombi("じゃがいも", "玉ねぎ",     31));
        COMBI_LIST.add(new PopularCombi("油あげ",     "長ねぎ",     12));
    }

    public static void showGeneration(Chromosome[] chromosome) {
        for (int i = 0; i < CHROMOSOME_NUM; i++) {
            System.out.println("[" + i + "] " + chromosome[i].getPoint());
        }
    }

    public static void main(String args[]) {
        createGuList();
        createCombiList();

        // 初期集団の生成
        Chromosome[] chromosome = new Chromosome[CHROMOSOME_NUM];
        for (int i = 0; i < CHROMOSOME_NUM; i++) {
            chromosome[i] = new Chromosome(CHROMOSOME_LENGTH, GU_LIST.size(), GU_LIST, COMBI_LIST);
        }
        Arrays.sort(chromosome, (a, b) -> b.getPoint() - a.getPoint());
        System.out.println("===== 初期集団 =====");
        showGeneration(chromosome);

        // 世代交代
        GenerationalChange gc = new GenerationalChange(chromosome, GU_LIST.size(), MUTATION_PROBABILITY);
        for (int i = 0; i < MAX_GENERATION; i++) {
            gc.select();
            gc.crossLoop();
            gc.mutationLoop();
            Arrays.sort(chromosome, (a, b) -> b.getPoint() - a.getPoint());
            System.out.println("===== 第" + (i + 1) + "世代 =====");
            showGeneration(chromosome);
        }

        // 最良の具
        System.out.println("\nおいしいみそ汁");
        chromosome[0].showGu();
        System.out.println(chromosome[0].getPoint());
    }
}
