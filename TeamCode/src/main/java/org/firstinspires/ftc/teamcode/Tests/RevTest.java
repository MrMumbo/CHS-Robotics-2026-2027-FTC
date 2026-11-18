package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Tests.TestHw.RevTestHw;

@TeleOp(name="RevTestMotor", group="Tests")
public class RevTest extends OpMode {
    RevTestHw hw = new RevTestHw();

    public void init()
    {
        hw.declareHardware(hardwareMap);
    }

    public void loop()
    {
        hw.motor.setPower(gamepad1.left_stick_y);
        hw.flywheel.setPower(gamepad1.right_bumper ? 0 : -1);
        hw.servo.setPower(gamepad1.right_stick_y);
    }
}
