package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Classes.Hardware;

@TeleOp(name="Color Sensor", group="Teleop")
public class colorSensorDrive extends OpMode {
    Hardware hw = new Hardware();
    double distanceINCH;
    public void init() {
        hw.declareHardware(hardwareMap);
    }

    public void loop() {
        NormalizedRGBA colors = hw.colorSensor.getNormalizedColors();

        telemetry.addData("Distance: ", distanceINCH = ((DistanceSensor) hw.colorSensor).getDistance(DistanceUnit.INCH));
        telemetry.addData("Red: ", colors.red);
        telemetry.addData("Green: ", colors.green);
        telemetry.addData("Blue: ", colors.blue);
        telemetry.addData("Alpha: ", colors.alpha);
        if (colors.red > colors.green && colors.red > colors.blue) {
            telemetry.addLine("Red");
        }
        if (colors.blue > colors.green && colors.blue > colors.red) {
            telemetry.addLine("Blue");
        }
        telemetry.update();
    }
}