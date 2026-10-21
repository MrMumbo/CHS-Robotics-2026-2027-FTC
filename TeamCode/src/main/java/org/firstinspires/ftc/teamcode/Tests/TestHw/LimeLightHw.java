package org.firstinspires.ftc.teamcode.Tests.TestHw;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Vision.LimeLight;

public class LimeLightHw extends LimeLight{
    public LimeLight limeLight = new LimeLight();

    public void declareHardware(HardwareMap hwMap) {
        limeLight.initLL(hwMap);
    }
}