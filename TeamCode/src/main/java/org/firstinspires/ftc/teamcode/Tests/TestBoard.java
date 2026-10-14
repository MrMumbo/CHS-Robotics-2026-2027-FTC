package org.firstinspires.ftc.teamcode.Tests;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Hardware.TestBoardHw;
import org.firstinspires.ftc.teamcode.Hardware.Values;
@TeleOp(name="Test Board", group="Teleop")
public class TestBoard extends OpMode {
    TestBoardHw hw = new TestBoardHw();


    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
    }

    public void loop()
    {
        NormalizedRGBA colors = hw.colorSensor.getNormalizedColors();

        hw.motor.setVelocity(100 * hw.limeLight.getValues().getTx());

        telemetry.addData("Tx: ", hw.limeLight.getValues().getTx());
        telemetry.addData("Ty: ", hw.limeLight.getValues().getTy());
        telemetry.addData("Velocity: ", hw.motor.getVelocity());
        telemetry.update();
    }

    public void stop()
    {
        hw.limeLight.stopCamera();
    }
}
