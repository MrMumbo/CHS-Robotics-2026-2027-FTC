package org.firstinspires.ftc.teamcode.Hardware;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;
import java.util.List;

class WebcamConfig {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static VisionPortal buildVisionPortal(
            HardwareMap hardwareMap,
            VisionProcessor processor
    ){
        WebcamName webcam = hardwareMap.get(WebcamName.class, "ArduCam");

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(webcam);
        builder.setCameraResolution(new Size(WIDTH, HEIGHT));
        builder.setStreamFormat(VisionPortal.StreamFormat.MJPEG);
        builder.enableLiveView(true);

        if(processor != null){
            builder.addProcessor(processor);
        }

        return builder.build();
    }
}

public class Webcam {
    private AprilTagProcessor apriltag;
    private VisionPortal visionPortal;
    private Telemetry telemetry;
    public List<AprilTagDetection> detectedTags = new ArrayList<>();
    public void init(HardwareMap hwMap) {
        apriltag = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .setLensIntrinsics(
                        937.817075,  // fx
                        933.067987,  // fy
                        651.251824,  // cx
                        400.472133   // cy
                )
                .build();

        visionPortal = WebcamConfig.buildVisionPortal(
                hwMap,
                apriltag
        );
    }

    public void printAprilTagTelemetry(List<AprilTagDetection> currentDetections, Telemetry telemetry) {
        this.telemetry = telemetry;

        telemetry.addData("Total AprilTags Detected: ", currentDetections.size());

        for (AprilTagDetection detection : currentDetections) {
            AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;

            if (singleDet.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", singleDet.id, singleDet.metadata.name));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", singleDet.id));
            }
        }

        telemetry.update();
    }

    public List<Double> getValues(int specifiedID) {
        List<AprilTagDetection> currentDetections = apriltag.getDetections();

        double tx = 0;
        double ty = 0;
        double tz = 0;
        double pitch = 0;
        double roll = 0;
        double yaw = 0;
        double range = 0;
        double bearing = 0;
        double elevation = 0;

        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;

                if (singleDet.metadata != null && singleDet.id == specifiedID) {
                    tx = singleDet.ftcPose.x;
                    ty = singleDet.ftcPose.y;
                    tz = singleDet.ftcPose.z;
                    pitch = singleDet.ftcPose.pitch;
                    roll = singleDet.ftcPose.roll;
                    yaw = singleDet.ftcPose.yaw;
                    range = singleDet.ftcPose.range;
                    bearing = singleDet.ftcPose.bearing;
                    elevation = singleDet.ftcPose.elevation;
                }
            } else {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;

                if (clusterDet.metadata != null) {
                    tx = clusterDet.ftcPose.x;
                    ty = clusterDet.ftcPose.y;
                    tz = clusterDet.ftcPose.z;
                    pitch = clusterDet.ftcPose.pitch;
                    roll = clusterDet.ftcPose.roll;
                    yaw = clusterDet.ftcPose.yaw;
                    range = clusterDet.ftcPose.range;
                    bearing = clusterDet.ftcPose.bearing;
                    elevation = clusterDet.ftcPose.elevation;
                }
            }
        }

        List<Double> webcam = new ArrayList<>();

        webcam.add(tx);
        webcam.add(ty);
        webcam.add(tz);
        webcam.add(pitch);
        webcam.add(roll);
        webcam.add(yaw);
        webcam.add(range);
        webcam.add(bearing);
        webcam.add(elevation);

        return webcam;
    }

    public List<Double> getValues(int specifiedID, boolean provideTelemetry) {
        List<AprilTagDetection> currentDetections = apriltag.getDetections();

        double tx = 0;
        double ty = 0;
        double tz = 0;
        double pitch = 0;
        double roll = 0;
        double yaw = 0;
        double range = 0;
        double bearing = 0;
        double elevation = 0;

        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;

                if (singleDet.metadata != null && singleDet.id == specifiedID) {
                    tx = singleDet.ftcPose.x;
                    ty = singleDet.ftcPose.y;
                    tz = singleDet.ftcPose.z;
                    pitch = singleDet.ftcPose.pitch;
                    roll = singleDet.ftcPose.roll;
                    yaw = singleDet.ftcPose.yaw;
                    range = singleDet.ftcPose.range;
                    bearing = singleDet.ftcPose.bearing;
                    elevation = singleDet.ftcPose.elevation;
                }
            } else {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;

                if (clusterDet.metadata != null) {
                    tx = clusterDet.ftcPose.x;
                    ty = clusterDet.ftcPose.y;
                    tz = clusterDet.ftcPose.z;
                    pitch = clusterDet.ftcPose.pitch;
                    roll = clusterDet.ftcPose.roll;
                    yaw = clusterDet.ftcPose.yaw;
                    range = clusterDet.ftcPose.range;
                    bearing = clusterDet.ftcPose.bearing;
                    elevation = clusterDet.ftcPose.elevation;
                }
            }
        }

        List<Double> webcam = new ArrayList<>();

        webcam.add(tx);
        webcam.add(ty);
        webcam.add(tz);
        webcam.add(pitch);
        webcam.add(roll);
        webcam.add(yaw);
        webcam.add(range);
        webcam.add(bearing);
        webcam.add(elevation);

        return webcam;
    }

    public void stopCamera()
    {
        visionPortal.close();
    }
}
