package org.fallenreaper.core.util;

import org.joml.Vector3f;

public class Vec2 {
    public static final Vec2 ZERO = new Vec2(0.0D, 0.0D);
    public final double x;
    public final double y;


    public static Vec2 fromRGB24(int pPacked) {
        double d0 = (double)(pPacked >> 16 & 255) / 255.0D;
        double d1 = (double)(pPacked >> 8 & 255) / 255.0D;

        return new Vec2(d0, d1);
    }


    public Vec2(double pX, double pY) {
        this.x = pX;
        this.y = pY;
    }

    public Vec2(Vector3f pFloatVector) {
        this((double)pFloatVector.x(), (double)pFloatVector.y());
    }

    /**
     * Returns a new vector with the result of the specified vector minus this.
     */
    public Vec2 vectorTo(Vec2 pVec) {
        return new Vec2(pVec.x - this.x, pVec.y - this.y);
    }

    /**
     * Normalizes the vector to a length of 1 (except if it is the zero vector)
     */
    public Vec2 normalize() {
        double d0 = Math.sqrt(this.x * this.x + this.y * this.y );
        return d0 < 1.0E-4D ? ZERO : new Vec2(this.x / d0, this.y / d0);
    }

    public double dot(Vec2 pVec) {
        return this.x * pVec.x + this.y * pVec.y ;
    }



    public Vec2 subtract(Vec2 pVec) {
        return this.subtract(pVec.x, pVec.y);
    }

    public Vec2 subtract(double pX, double pY) {
        return this.add(-pX, -pY);
    }

    public Vec2 add(Vec2 pVec) {
        return this.add(pVec.x, pVec.y);
    }

    /**
     * Adds the specified x,y,z vector components to this vector and returns the resulting vector. Does not change this
     * vector.
     */
    public Vec2 add(double pX, double pY) {
        return new Vec2(this.x + pX, this.y + pY);
    }



    /**
     * Euclidean distance between this and the specified vector, returned as double.
     */
    public double distanceTo(Vec2 pVec) {
        double d0 = pVec.x - this.x;
        double d1 = pVec.y - this.y;

        return Math.sqrt(d0 * d0 + d1 * d1);
    }

    /**
     * The square of the Euclidean distance between this and the specified vector.
     */
    public double distanceToSqr(Vec2 pVec) {
        double d0 = pVec.x - this.x;
        double d1 = pVec.y - this.y;
        return d0 * d0 + d1 * d1 ;
    }

    public double distanceToSqr(double pX, double pY, double pZ) {
        double d0 = pX - this.x;
        double d1 = pY - this.y;

        return d0 * d0 + d1 * d1 ;
    }

    public Vec2 scale(double pFactor) {
        return this.multiply(pFactor, pFactor);
    }

    public Vec2 reverse() {
        return this.scale(-1.0D);
    }

    public Vec2 multiply(Vec2 pVec) {
        return this.multiply(pVec.x, pVec.y);
    }

    public Vec2 multiply(double pFactorX, double pFactorY) {
        return new Vec2(this.x * pFactorX, this.y * pFactorY);
    }

    /**
     * Returns the length of the vector.
     */
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y );
    }

    public double lengthSqr() {
        return this.x * this.x + this.y * this.y ;
    }

    public double horizontalDistance() {
        return Math.sqrt(this.x * this.x );
    }

    public double horizontalDistanceSqr() {
        return this.x * this.x ;
    }

    public boolean equals(Object pOther) {
        if (this == pOther) {
            return true;
        } else if (!(pOther instanceof Vec2)) {
            return false;
        } else {
            Vec2 Vec2 = (Vec2)pOther;
            if (Double.compare(Vec2.x, this.x) != 0) {
                return false;
            } else if (Double.compare(Vec2.y, this.y) != 0) {
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = Double.doubleToLongBits(this.x);
        int i = (int)(j ^ j >>> 32);
        j = Double.doubleToLongBits(this.y);
        i = 31 * i + (int)(j ^ j >>> 32);

        return 31 * i + (int)(j ^ j >>> 32);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }


    public Vec2 lerp(Vec2 pTo, double pDelta) {
        return new Vec2(Mth.lerp(pDelta, this.x, pTo.x), Mth.lerp(pDelta, this.y, pTo.y));
    }





    public final double x() {
        return this.x;
    }

    public final double y() {
        return this.y;
    }
    
}
