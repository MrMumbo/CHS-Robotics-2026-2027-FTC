package org.firstinspires.ftc.teamcode.Hardware;

public enum Values {
    TX(0),
    TY(1),
    TZ(2),
    PITCH(3),
    ROLL(4),
    YAW(5),
    RANGE(6),
    BEARING(7),
    ELEVATION(8);

    public final int id;

    private Values(int id) {
        this.id = id;
    }
}
