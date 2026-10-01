package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Hardware.TestBoardHw;

@TeleOp(name="Test Board", group="Teleop")
public class TestBoard extends OpMode {
    TestBoardHw hw = new TestBoardHw();


    public void init() {
        hw.declareHardware(hardwareMap);

    }

    public void loop()
    {
        NormalizedRGBA colors = hw.colorSensor.getNormalizedColors();
        if (colors.blue > colors.red && colors.blue > colors.green)
        {
            hw.motor.setVelocity(-2800);
        } // L tyren
        else
        {
            hw.motor.setVelocity(0);
        }

        telemetry.addData("Servo Pos", hw.servo.getPosition());
        if (colors.red > colors.blue && colors.red > colors.green)
        {
            hw.servo.setPosition(1.0);
        }
        if (colors.green > colors.blue && colors.green > colors.red)
        {
            hw.servo.setPosition(1.0);
            hw.motor.setVelocity(-2800);
        }
        else
        {
            hw.servo.setPosition(0.0);

        }
//        if (colors.blue > colors.red && colors.blue > colors.green)
//        {
//            hw.motor.setVelocity(0);
//        }
//        else
//        {
//            hw.motor.setVelocity(hw.motor.getVelocity()+(gamepad1.left_stick_y*50));
//        }
        telemetry.addData("Blue: ", colors.blue);
        telemetry.addData("red: ", colors.red);
        telemetry.addData("green: ", colors.green);
        telemetry.addData("Velocity: ", hw.motor.getVelocity());
        telemetry.update();
    }
}
