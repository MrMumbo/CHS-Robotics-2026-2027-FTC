package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class TestBoardHw {
    public DcMotorEx motor;
    public NormalizedColorSensor colorSensor;
    public Servo servo;

    public LimeLight limeLight = new LimeLight();

    public void declareHardware(HardwareMap hwMap) {
        limeLight.init(hwMap);
        colorSensor = hwMap.get(NormalizedColorSensor.class, "colorSensor");

        motor = hwMap.get(DcMotorEx.class, "motor");
        servo = hwMap.get(Servo.class, "servo");
    }
}