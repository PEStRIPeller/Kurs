package sample.classes;

import java.util.Comparator;

public class MemoryBlock {
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    private int start;
    private int end;
    private int id;

    public static Comparator<MemoryBlock> byEnd = (o1, o2) -> o2.start - o1.end;

    public static Comparator<MemoryBlock> byAsc = (o1, o2) -> o1.start > o2.start ? 1 : o1.start < o2.start ? -1 : 0;

    public MemoryBlock(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}
