package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 染色体
 */
public class Chromosome {
    private int chromosomeLength; // 使用する遺伝子(具)の数
    private int[] gene;
    private List<Ingredient> guList;
    private List<PopularCombi> combiList;

    /**
     * @param chromosomeLength
     *            使用する遺伝子(具)の数
     * @param geneNum
     *            選択できる全ての遺伝子(具)の数
     * @param gu
     *            具のリスト
     */
    public Chromosome(int chromosomeLength, int geneNum, List<Ingredient> gu, List<PopularCombi> combi) {
        this.chromosomeLength = chromosomeLength;
        gene = new int[chromosomeLength];
        this.guList = gu;
        this.combiList = combi;

        // ランダムな遺伝子生成
        List<Integer> randNum = new ArrayList<Integer>(geneNum);
        for (int i = 0; i < geneNum; i++) {
            randNum.add(i);
        }
        Collections.shuffle(randNum);
        for (int i = 0; i < chromosomeLength; i++) {
            gene[i] = randNum.get(i);
        }

    }

    public int getGene(int index) {
        return gene[index];
    }

    public void setGene(int index, int value) {
        gene[index] = value;
    }

    public void showGu() {
        for (int i = 0; i < chromosomeLength; i++) {
            String name = guList.get(gene[i]).getName();
            System.out.print("[" + String.format("%2s", gene[i]) + "]" + name);
            for (int j = name.length(); j < 5; j++) {
                System.out.print('　');   
            }
            System.out.println(" " + String.format("%3s", guList.get(gene[i]).getPoint()) + "p");
        }
    }

    /**
     * @return 遺伝子の数
     */
    public int getLength() {
        return chromosomeLength;
    }

    /**
     * @return 具単品の人気度の合計値
     */
    public int getBasicPoint() {
        int point = 0;
        for (int i = 0; i < gene.length; i++) {
            point += guList.get(gene[i]).getPoint();
        }
        return point;
    }

    /**
     * @return 具の組み合わせ人気度の合計値
     */
    public int getCombiPoint() {
        int point = 0;
        for (int j = 0; j < combiList.size(); j++) {
            int flag = 0;
            for (int i = 0; i < gene.length; i++) {
                String name = guList.get(gene[i]).getName();
                String combiName1 = combiList.get(j).getName(0);
                String combiName2 = combiList.get(j).getName(1);
                if (name.equals(combiName1) || name.equals(combiName2)) {
                    flag++;
                }
                if (flag == 2) {
                    point += combiList.get(j).getPoint();
                    break;
                }
            }
        }
        return point;
    }

    public int getPoint() {
        return getBasicPoint() + getCombiPoint();
    }

    /**
     * Chromosomeを普通に代入したら参照渡しになるし、わざわざCloneableのclone使うのもだるいから
     */
    public void clone(Chromosome chromosome) {
        for (int i = 0; i < gene.length; i++) {
            gene[i] = chromosome.getGene(i);   
        }
    }

    public String toString() {
        return getPoint() + "";
    }

}
