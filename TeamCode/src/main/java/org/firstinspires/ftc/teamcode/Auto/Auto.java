package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@Autonomous(name="Basic Auto", group="Autonomous")
public class Auto extends LinearOpMode {

    Hardware hw = new Hardware();

    public void runOpMode() {
        waitForStart();
        hw.declareHardware(hardwareMap);

        hw.driveTime(0, 1, 0, 0.3F);
        sleep(3000);
        hw.driveTime(-1, 0.2F, 0, 0.8F);
        hw.driveTime(-0.85F, 1, 0, 1.05F);
        hw.driveTime(0, 0.7F, 0.4F, 0.9F);
        hw.driveTime(0, -.85F, 0, 0.45F);
    }
}
