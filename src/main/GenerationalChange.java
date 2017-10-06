package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerationalChange {
    private final int chromosomeNum; // 染色体の数
    private Chromosome[] chromosome;
    private List<Integer> changeGene;
    private int chromosomeLength;    // 染色体中の遺伝子の数
    private int geneNum;             // 全遺伝子の数
    private int crossNum;            // 交叉する遺伝子の数
    private int mutationProbability; // 突然変異する確率

    /**
     * @param chromosome
     *            染色体のインスタンスの配列
     * @param geneNum
     *            全遺伝子の数
     * @param mutationProbability
     *            突然変異する確率
     */
    public GenerationalChange(Chromosome[] chromosome, int geneNum, int mutationProbability) {
        this.chromosomeNum = chromosome.length;
        this.geneNum = geneNum;
        this.chromosome = chromosome;
        this.mutationProbability = mutationProbability;

        chromosomeLength = chromosome[0].getLength();
        changeGene = new ArrayList<Integer>(chromosomeLength);
        for (int i = 0; i < chromosomeLength; i++) {
            changeGene.add(i);   
        }

        crossNum = (int)Math.ceil(chromosome[0].getLength() / 2.0);
    }

    /**
     * 選択
     */
    public void select() {
        final int segment = chromosomeNum / 5;
        for (int i = 0; i < segment * 2; i++) {
            chromosome[i + segment * 3].clone(chromosome[i]);
        }
        Arrays.sort(chromosome, (a, b) -> b.getPoint() - a.getPoint());
    }

    /**
     * 交叉(1対のみ)
     */
    public void cross(int rnk) {
        // 親1と重複している親2の要素を親1と同じindexに入れ替える
        for (int i = 0; i < chromosomeLength; i++) {
            int parent1 = chromosome[rnk].getGene(i);
            for (int j = 0; j < chromosomeLength; j++) {
                int parent2 = chromosome[rnk + 1].getGene(j);
                if (parent1 == parent2) {
                    int temp = chromosome[rnk + 1].getGene(i);
                    chromosome[rnk + 1].setGene(i, parent2);
                    chromosome[rnk + 1].setGene(j, temp);
                    break;
                }
            }
        }
        // 交叉する
        Collections.shuffle(changeGene);
        for (int i = 0; i < crossNum; i++) {
            int n = changeGene.get(i);
            int parent1 = chromosome[rnk].getGene(n);
            int parent2 = chromosome[rnk + 1].getGene(n);
            if (parent1 != parent2) {
                chromosome[rnk].setGene(n, parent2);
                chromosome[rnk + 1].setGene(n, parent1);
            }
        }
    }

    /**
     * 交叉(全染色体)
     */
    public void crossLoop() {
        for (int i = 1; i < chromosomeNum - 1; i += 2) {
            cross(i);
        }
    }

    private Random random = new Random();
    private List<Integer> mutationGene = new ArrayList<Integer>();
    /**
     * 突然変異(一個)
     */
    public void mutation(int rnk) {
        // 確率を外したらガード
        int randomNum = random.nextInt(100);
        if (mutationProbability <= randomNum) { return; }

        int randomIndex = random.nextInt(chromosomeLength);
        mutationGene.clear();
        for (int i = 0; i < geneNum; i++) {
            mutationGene.add(i);
        }
        // 重複を削除
        for (int i = 0; i < chromosomeLength; i++) {
            int removeIndex = mutationGene.indexOf(chromosome[rnk].getGene(i));
            mutationGene.remove(removeIndex);
        }
        Collections.shuffle(mutationGene);
        chromosome[rnk].setGene(randomIndex, mutationGene.get(0));
    }

    /**
     * 突然変異(全染色体)
     */
    public void mutationLoop() {
        for (int i = 1; i < chromosomeNum; i++) {
            mutation(i);
        }
    }

}
