package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GoBildaHw {
    public DcMotor flyWheel;
    public DcMotor intake;

    public void declareHardware(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "intake");
        flyWheel = hwMap.get(DcMotor.class, "flyWheel");
    }

    public void shoot(float gamepad)
    {
        flyWheel.setPower(gamepad);
    }

    public void intake(float gamepad)
    {
        intake.setPower(gamepad);
    }
}
