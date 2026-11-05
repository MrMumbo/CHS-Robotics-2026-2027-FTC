package org.firstinspires.ftc.teamcode.Hardware;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Teleop.Drive;
import org.firstinspires.ftc.teamcode.Vision.LimeLight;

public class Hardware extends LimeLight {

    ElapsedTime Movetimer = new ElapsedTime();
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotorEx intake;
    public DcMotorEx flyWheel;
    public Servo indexer;
    public LimeLight limeLight = new LimeLight();
    public IMU imu;

    public void declareHardware(HardwareMap hwMap) {
        limeLight.initLL(hwMap);

        frontRight = hwMap.get(DcMotor.class, "frontRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        indexer = hwMap.get(Servo.class, "indexer");
        intake = hwMap.get(DcMotorEx.class, "intake");
        flyWheel = hwMap.get(DcMotorEx.class, "flyWheel");
        flyWheel.setZeroPowerBehavior(BRAKE);
        flyWheel.setMode(RUN_USING_ENCODER);
        backRight.setDirection(REVERSE);
        imu = hwMap.get(IMU.class, "imu");
        imu = hwMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    public void drive(float x, float y, float r) {
        frontRight.setPower(-x + y - r);
        frontLeft.setPower(-x - y - r);
        backRight.setPower(x + y - r);
        backLeft.setPower(-x + y + r);
    }

    public void shootOnKey(float gamepadControl, float MaxVel)
    {
        if (Math.abs(flyWheel.getVelocity()) > MaxVel)
        {
            flyWheel.setVelocity(0);
        }
        else
        {
            flyWheel.setVelocity(gamepadControl * MaxVel);
        }
    }

    public void autoShoot(float desiredVelocity, Telemetry telemetry)
    {
        intake.setVelocity(2800);
        indexer.setPosition(0.6);
        flyWheel.setVelocity(desiredVelocity);
        while ((Math.abs(flyWheel.getVelocity()) < desiredVelocity - 20) || (Math.abs(flyWheel.getVelocity()) > desiredVelocity + 20))
        {
            telemetry.addData("Velocity: ", flyWheel.getVelocity());
            telemetry.update();
            if (Math.abs(flyWheel.getVelocity()) > desiredVelocity)
            {
                flyWheel.setVelocity(0);
            }
            else
            {
                flyWheel.setVelocity(desiredVelocity);
            }
        }
        flyWheel.setVelocity(desiredVelocity);
        indexer.setPosition(0.3);
        sleep(3000);
        flyWheel.setVelocity(0);
        intake.setVelocity(0);
        indexer.setPosition(0.6);
    }

    public void intakeOnKey(float gamepadControl)
    {
        intake.setVelocity(gamepadControl * 2800);
    }

    public void liftBalls(int gamepadControl) {
        if (gamepadControl == 0){
            indexer.setPosition(0.3);
        }
        else {
            indexer.setPosition(0.6);
        }
    }

    public void driveTime(float x, float y, float r, float time) {
        Movetimer.reset();
        frontLeft.setZeroPowerBehavior(BRAKE);
        frontRight.setZeroPowerBehavior(BRAKE);
        backRight.setZeroPowerBehavior(BRAKE);
        backLeft.setZeroPowerBehavior(BRAKE);
        while (Movetimer.seconds() < time) {
            frontRight.setPower(-x + y - r);
            frontLeft.setPower(-x - y - r);
            backRight.setPower(x + y - r);
            backLeft.setPower(-x + y + r);
        }
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    public void driveFieldRel(float x, float y, float r) {
        float theta;
        float thetaRobot;
        float thetaDrive;
        float magnitude = (float) Math.hypot(y, x);

        thetaDrive = (float) Math.atan2(y,x);
        thetaRobot = (float) imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        theta = thetaDrive - thetaRobot;

        float uY = (float) Math.cos(theta) * magnitude;
        float uX = (float) Math.sin(theta) * magnitude;

        drive(uX, uY, r);
    }
}