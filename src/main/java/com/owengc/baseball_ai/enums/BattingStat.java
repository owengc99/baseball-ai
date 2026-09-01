package com.owengc.baseball_ai.enums;

public enum BattingStat {
    HR("hr"),
    H("h"),
    RBI("rbi"),
    R("r"),
    SB("sb"),
    DOUBLES("doubles"),
    TRIPLES("triples"),
    BB("bb"),
    SO("so");

    private final String column;

    BattingStat(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
