package org.fallenreaper.core.util;

import com.sun.javafx.scene.traversal.Direction;
import jdk.jshell.execution.Util;
import org.joml.Vector3f;

import javax.swing.text.Position;
import java.util.EnumSet;
import java.util.List;

public class Vec3 {

    public static final Vec3 ZERO = new Vec3(0.0D, 0.0D, 0.0D);
    public final double x;
    public final double y;
    public final double z;

    public static Vec3 fromRGB24(int pPacked) {
        double d0 = (double)(pPacked >> 16 & 255) / 255.0D;
        double d1 = (double)(pPacked >> 8 & 255) / 255.0D;
        double d2 = (double)(pPacked & 255) / 255.0D;
        return new Vec3(d0, d1, d2);
    }


    public Vec3(double pX, double pY, double pZ) {
        this.x = pX;
        this.y = pY;
        this.z = pZ;
    }

    public Vec3(Vector3f pFloatVector) {
        this((double)pFloatVector.x(), (double)pFloatVector.y(), (double)pFloatVector.z());
    }

    /**
     * Returns a new vector with the result of the specified vector minus this.
     */
    public Vec3 vectorTo(Vec3 pVec) {
        return new Vec3(pVec.x - this.x, pVec.y - this.y, pVec.z - this.z);
    }

    /**
     * Normalizes the vector to a length of 1 (except if it is the zero vector)
     */
    public Vec3 normalize() {
        double d0 = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return d0 < 1.0E-4D ? ZERO : new Vec3(this.x / d0, this.y / d0, this.z / d0);
    }

    public double dot(Vec3 pVec) {
        return this.x * pVec.x + this.y * pVec.y + this.z * pVec.z;
    }

    /**
     * Returns a new vector with the result of this vector x the specified vector.
     */
    public Vec3 cross(Vec3 pVec) {
        return new Vec3(this.y * pVec.z - this.z * pVec.y, this.z * pVec.x - this.x * pVec.z, this.x * pVec.y - this.y * pVec.x);
    }

    public Vec3 subtract(Vec3 pVec) {
        return this.subtract(pVec.x, pVec.y, pVec.z);
    }

    public Vec3 subtract(double pX, double pY, double pZ) {
        return this.add(-pX, -pY, -pZ);
    }

    public Vec3 add(Vec3 pVec) {
        return this.add(pVec.x, pVec.y, pVec.z);
    }

    /**
     * Adds the specified x,y,z vector components to this vector and returns the resulting vector. Does not change this
     * vector.
     */
    public Vec3 add(double pX, double pY, double pZ) {
        return new Vec3(this.x + pX, this.y + pY, this.z + pZ);
    }



    /**
     * Euclidean distance between this and the specified vector, returned as double.
     */
    public double distanceTo(Vec3 pVec) {
        double d0 = pVec.x - this.x;
        double d1 = pVec.y - this.y;
        double d2 = pVec.z - this.z;
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    /**
     * The square of the Euclidean distance between this and the specified vector.
     */
    public double distanceToSqr(Vec3 pVec) {
        double d0 = pVec.x - this.x;
        double d1 = pVec.y - this.y;
        double d2 = pVec.z - this.z;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public double distanceToSqr(double pX, double pY, double pZ) {
        double d0 = pX - this.x;
        double d1 = pY - this.y;
        double d2 = pZ - this.z;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public Vec3 scale(double pFactor) {
        return this.multiply(pFactor, pFactor, pFactor);
    }

    public Vec3 reverse() {
        return this.scale(-1.0D);
    }

    public Vec3 multiply(Vec3 pVec) {
        return this.multiply(pVec.x, pVec.y, pVec.z);
    }

    public Vec3 multiply(double pFactorX, double pFactorY, double pFactorZ) {
        return new Vec3(this.x * pFactorX, this.y * pFactorY, this.z * pFactorZ);
    }

    /**
     * Returns the length of the vector.
     */
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double lengthSqr() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double horizontalDistance() {
        return Math.sqrt(this.x * this.x + this.z * this.z);
    }

    public double horizontalDistanceSqr() {
        return this.x * this.x + this.z * this.z;
    }

    public boolean equals(Object pOther) {
        if (this == pOther) {
            return true;
        } else if (!(pOther instanceof Vec3)) {
            return false;
        } else {
            Vec3 vec3 = (Vec3)pOther;
            if (Double.compare(vec3.x, this.x) != 0) {
                return false;
            } else if (Double.compare(vec3.y, this.y) != 0) {
                return false;
            } else {
                return Double.compare(vec3.z, this.z) == 0;
            }
        }
    }

    public int hashCode() {
        long j = Double.doubleToLongBits(this.x);
        int i = (int)(j ^ j >>> 32);
        j = Double.doubleToLongBits(this.y);
        i = 31 * i + (int)(j ^ j >>> 32);
        j = Double.doubleToLongBits(this.z);
        return 31 * i + (int)(j ^ j >>> 32);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }


    public Vec3 lerp(Vec3 pTo, double pDelta) {
        return new Vec3(Mth.lerp(pDelta, this.x, pTo.x), Mth.lerp(pDelta, this.y, pTo.y), Mth.lerp(pDelta, this.z, pTo.z));
    }

    public Vec3 xRot(float pPitch) {
        float f = (float) Math.cos(pPitch);
        float f1 = (float) Math.sin(pPitch);
        double d0 = this.x;
        double d1 = this.y * (double)f + this.z * (double)f1;
        double d2 = this.z * (double)f - this.y * (double)f1;
        return new Vec3(d0, d1, d2);
    }

    public Vec3 yRot(float pYaw) {
        float f = (float) Math.cos(pYaw);
        float f1 = (float) Math.sin(pYaw);
        double d0 = this.x * (double)f + this.z * (double)f1;
        double d1 = this.y;
        double d2 = this.z * (double)f - this.x * (double)f1;
        return new Vec3(d0, d1, d2);
    }

    public Vec3 zRot(float pRoll) {
        float f = (float) Math.cos(pRoll);
        float f1 = (float) Math.sin(pRoll);
        double d0 = this.x * (double)f + this.y * (double)f1;
        double d1 = this.y * (double)f - this.x * (double)f1;
        double d2 = this.z;
        return new Vec3(d0, d1, d2);
    }



    public final double x() {
        return this.x;
    }

    public final double y() {
        return this.y;
    }

    public final double z() {
        return this.z;
    }
}
