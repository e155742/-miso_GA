package main;

/**
 * 具の組み合わせ
 */
public class PopularCombi {
    private int point;
    private String[] name = new String[2];

    public PopularCombi(String name1, String name2, int point) {
        name[0] = name1;
        name[1] = name2;
        this.point = point;
    }

    public String getName(int index) {
        return name[index];
    }
    
    public int getPoint() {
        return point;
    }
}
