package org.firstinspires.ftc.teamcode.Tests;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Tests.TestHw.TestBoardHw;

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

        hw.motor.setVelocity(100 * hw.limeLight.getTagValues(32).getTx());

        telemetry.addData("Tx: ", hw.limeLight.getTagValues(30).getTx());
        telemetry.addData("Ty: ", hw.limeLight.getTagValues(30).getTy());
        telemetry.update();
    }

    public void stop()
    {
        hw.limeLight.stopCamera();
    }
}
