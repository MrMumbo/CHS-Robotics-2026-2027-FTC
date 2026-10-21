package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Tests.TestHw.LimeLightHw;

@TeleOp(name = "LimeLightTest", group = "Teleop")
public class LimeLightTest extends OpMode {
    public LimeLightHw hw = new LimeLightHw();

    public void init()
    {
        hw.declareHardware(hardwareMap);

        hw.limeLight.startCamera();
    }

    public void loop()
    {
        hw.limeLight.updateVision();

        telemetry.addData("Tag ID 30 y pose = ", hw.limeLight.getAprilTagPoseY(30));
    }

    public void stop()
    {
        hw.limeLight.stopCamera();
    }
}