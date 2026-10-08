package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class RevTestHw {
    public DcMotor motor;

    public void declareHardware(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
    }
}