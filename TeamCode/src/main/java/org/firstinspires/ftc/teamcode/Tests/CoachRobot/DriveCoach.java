package org.firstinspires.ftc.teamcode.Tests.CoachRobot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Teleop.ButtonMapping;
import org.firstinspires.ftc.teamcode.Tests.CoachRobot.HwCoach.HardwareCoach;

@Disabled
@TeleOp(name="Drive Logic Coach Robot", group="Coach")
public class DriveCoach extends OpMode {
    HardwareCoach hw = new HardwareCoach();
    ButtonMappingCoach controller = new ButtonMappingCoach();

    @Override
    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
    }

    @Override
    public void loop() {
        controller.UpdateButtonMap(gamepad1, 2);

        hw.limeLight.updateVision();

        hw.drive(controller.axialMovementX, -controller.axialMovementY, controller.rotationalMovement);

        hw.shootOnKey(controller.shootKey);
        hw.intakeOnKey(controller.intakeKey);
        hw.intakeFlowersOnKey(controller.flowerGrabbingKey);

        hw.liftBalls(controller.flipperKey);

        telemetry.update();
    }
}
