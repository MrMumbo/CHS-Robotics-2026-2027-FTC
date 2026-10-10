package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.GoBildaHw;
@TeleOp(name="GoBildaTest", group="Teleop")

public class GoBildaTest extends OpMode {
    GoBildaHw hw = new GoBildaHw();

    public void init()
    {
        hw.declareHardware(hardwareMap);
    }

    public void loop()
    {
        hw.shoot(gamepad1.left_stick_y);

        hw.intake(gamepad1.right_stick_y);
    }
}
