package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Hardware.RevTestHw;

@TeleOp(name="RevTestMotor", group="Teleop")
public class RevTest extends OpMode {
    RevTestHw hw = new RevTestHw();

    public void init()
    {
        hw.declareHardware(hardwareMap);
    }

    public void loop()
    {
        hw.motor.setPower(gamepad1.left_stick_y);
    }
}
