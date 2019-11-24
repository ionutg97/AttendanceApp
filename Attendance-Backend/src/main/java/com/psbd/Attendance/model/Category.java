package com.psbd.Attendance.model;

public enum Category {
    COURS("cours"),
    LABORATORY("laboratory"),
    SEMINARY("seminary");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public static Category getInstance(String value) {
        if (value == null)
            return null;

        switch (value.toLowerCase()) {
            case "seminary":
                return Category.SEMINARY;
            case "laboratory":
                return Category.LABORATORY;
            default:
                return Category.COURS;
        }
    }

    public String toString() {
        return this.value;
    }
}
