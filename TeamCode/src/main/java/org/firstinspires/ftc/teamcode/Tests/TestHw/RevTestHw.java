package org.firstinspires.ftc.teamcode.Tests.TestHw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RevTestHw {
    public DcMotor motor;

    public void declareHardware(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
    }
}