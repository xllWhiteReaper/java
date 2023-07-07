package programming_2.bnf_set_calculator.models;

public class Indexes {
    private int start;
    private int end;

    public Indexes(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }
}
