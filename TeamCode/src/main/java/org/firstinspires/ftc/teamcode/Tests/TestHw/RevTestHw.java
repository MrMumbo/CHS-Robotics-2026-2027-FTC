package org.firstinspires.ftc.teamcode.Tests.TestHw;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RevTestHw {
    public DcMotor motor;
    public CRServo servo;
    public DcMotor flywheel;

    public void declareHardware(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
        flywheel = hwMap.get(DcMotor.class, "flywheel");
        servo = hwMap.get(CRServo.class, "servo");
    }
}