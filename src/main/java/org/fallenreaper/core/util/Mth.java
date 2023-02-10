package org.fallenreaper.core.util;


import java.util.Locale;
import java.util.UUID;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import jdk.jshell.execution.Util;
import org.apache.commons.lang3.math.NumberUtils;

public class Mth {
    private static final int BIG_ENOUGH_INT = 1024;
    private static final float BIG_ENOUGH_FLOAT = 1024.0F;
    private static final long UUID_VERSION = 61440L;
    private static final long UUID_VERSION_TYPE_4 = 16384L;
    private static final long UUID_VARIANT = -4611686018427387904L;
    private static final long UUID_VARIANT_2 = Long.MIN_VALUE;
    public static final float PI = (float)Math.PI;
    public static final float HALF_PI = ((float)Math.PI / 2F);
    public static final float TWO_PI = ((float)Math.PI * 2F);
    public static final float DEG_TO_RAD = ((float)Math.PI / 180F);
    public static final float RAD_TO_DEG = (180F / (float)Math.PI);
    public static final float EPSILON = 1.0E-5F;
    public static final float SQRT_OF_TWO = sqrt(2.0F);
    private static final float SIN_SCALE = 10430.378F;


    /**
     * Though it looks like an array, this is really more like a mapping. Key (index of this array) is the upper 5 bits
     * of the result of multiplying a 32-bit unsigned integer by the B(2, 5) De Bruijn sequence 0x077CB531. Value (value
     * stored in the array) is the unique index (from the right) of the leftmo
     */
    private static final int[] MULTIPLY_DE_BRUIJN_BIT_POSITION = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
    private static final double ONE_SIXTH = 0.16666666666666666D;
    private static final int FRAC_EXP = 8;
    private static final int LUT_SIZE = 257;
    private static final double FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
    private static final double[] ASIN_TAB = new double[257];
    private static final double[] COS_TAB = new double[257];


    public static float sqrt(float pValue) {
        return (float)Math.sqrt((double)pValue);
    }

    /**
     * Returns the greatest integer less than or equal to the float argument
     */
    public static int floor(float pValue) {
        int i = (int)pValue;
        return pValue < (float)i ? i - 1 : i;
    }

    /**
     * Returns {@code value} cast as an int, and no greater than {@code Integer.MAX_VALUE - 1024}
     */
    public static int fastFloor(double pValue) {
        return (int)(pValue + 1024.0D) - 1024;
    }

    /**
     * Returns the greatest integer less than or equal to the double argument
     */
    public static int floor(double pValue) {
        int i = (int)pValue;
        return pValue < (double)i ? i - 1 : i;
    }

    /**
     * Long version of floor()
     */
    public static long lfloor(double pValue) {
        long i = (long)pValue;
        return pValue < (double)i ? i - 1L : i;
    }

    public static int absFloor(double pValue) {
        return (int)(pValue >= 0.0D ? pValue : -pValue + 1.0D);
    }

    public static float abs(float pValue) {
        return Math.abs(pValue);
    }

    /**
     * Returns the unsigned value of an int.
     */
    public static int abs(int pValue) {
        return Math.abs(pValue);
    }

    public static int ceil(float pValue) {
        int i = (int)pValue;
        return pValue > (float)i ? i + 1 : i;
    }

    public static int ceil(double pValue) {
        int i = (int)pValue;
        return pValue > (double)i ? i + 1 : i;
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static byte clamp(byte pValue, byte pMin, byte pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static int clamp(int pValue, int pMin, int pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static long clamp(long pValue, long pMin, long pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static float clamp(float pValue, float pMin, float pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static double clamp(double pValue, double pMin, double pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    /**
     * Method for linear interpolation of doubles.
     * @param pStart Start value for the lerp.
     * @param pEnd End value for the lerp.
     * @param pDelta A value between 0 and 1 that indicates the percentage of the lerp. (0 will give the start value and
     * 1 will give the end value) If the value is not between 0 and 1, it is clamped.
     */
    public static double clampedLerp(double pStart, double pEnd, double pDelta) {
        if (pDelta < 0.0D) {
            return pStart;
        } else {
            return pDelta > 1.0D ? pEnd : lerp(pDelta, pStart, pEnd);
        }
    }

    /**
     * Method for linear interpolation of floats.
     * @param pStart Start value for the lerp.
     * @param pEnd End value for the lerp.
     * @param pDelta A value between 0 and 1 that indicates the percentage of the lerp. (0 will give the start value and
     * 1 will give the end value) If the value is not between 0 and 1, it is clamped.
     */
    public static float clampedLerp(float pStart, float pEnd, float pDelta) {
        if (pDelta < 0.0F) {
            return pStart;
        } else {
            return pDelta > 1.0F ? pEnd : lerp(pDelta, pStart, pEnd);
        }
    }

    /**
     * Maximum of the absolute value of two numbers.
     */
    public static double absMax(double pX, double pY) {
        if (pX < 0.0D) {
            pX = -pX;
        }

        if (pY < 0.0D) {
            pY = -pY;
        }

        return pX > pY ? pX : pY;
    }

    /**
     * Buckets an integer with specified bucket sizes.
     */
    public static int intFloorDiv(int pX, int pY) {
        return Math.floorDiv(pX, pY);
    }


    public static double average(long[] pValues) {
        long i = 0L;

        for(long j : pValues) {
            i += j;
        }

        return (double)i / (double)pValues.length;
    }

    public static boolean equal(float pX, float pY) {
        return Math.abs(pY - pX) < 1.0E-5F;
    }

    public static boolean equal(double pX, double pY) {
        return Math.abs(pY - pX) < (double)1.0E-5F;
    }

    public static int positiveModulo(int pX, int pY) {
        return Math.floorMod(pX, pY);
    }

    public static float positiveModulo(float pNumerator, float pDenominator) {
        return (pNumerator % pDenominator + pDenominator) % pDenominator;
    }

    public static double positiveModulo(double pNumerator, double pDenominator) {
        return (pNumerator % pDenominator + pDenominator) % pDenominator;
    }

    /**
     * Adjust the angle so that its value is in the range [-180;180)
     */
    public static int wrapDegrees(int pAngle) {
        int i = pAngle % 360;
        if (i >= 180) {
            i -= 360;
        }

        if (i < -180) {
            i += 360;
        }

        return i;
    }

    /**
     * The angle is reduced to an angle between -180 and +180 by mod, and a 360 check.
     */
    public static float wrapDegrees(float pValue) {
        float f = pValue % 360.0F;
        if (f >= 180.0F) {
            f -= 360.0F;
        }

        if (f < -180.0F) {
            f += 360.0F;
        }

        return f;
    }

    /**
     * The angle is reduced to an angle between -180 and +180 by mod, and a 360 check.
     */
    public static double wrapDegrees(double pValue) {
        double d0 = pValue % 360.0D;
        if (d0 >= 180.0D) {
            d0 -= 360.0D;
        }

        if (d0 < -180.0D) {
            d0 += 360.0D;
        }

        return d0;
    }

    /**
     * Gets the difference between two angles in degrees.
     */
    public static float degreesDifference(float pStart, float pEnd) {
        return wrapDegrees(pEnd - pStart);
    }

    /**
     * Gets the absolute of the difference between two angles in degrees.
     */
    public static float degreesDifferenceAbs(float pStart, float pEnd) {
        return abs(degreesDifference(pStart, pEnd));
    }

    /**
     * Takes a rotation and compares it to another rotation.
     * If the difference is greater than a given maximum, clamps the original rotation between to have at most the given
     * difference to the actual rotation.
     * This is used to match the body rotation of entities to their head rotation.
     * @return The new value for the rotation that was adjusted
     */
    public static float rotateIfNecessary(float pRotationToAdjust, float pActualRotation, float pMaxDifference) {
        float f = degreesDifference(pRotationToAdjust, pActualRotation);
        float f1 = clamp(f, -pMaxDifference, pMaxDifference);
        return pActualRotation - f1;
    }

    /**
     * Changes value by stepSize towards the limit and returns the result.
     * If value is smaller than limit, the result will never be bigger than limit.
     * If value is bigger than limit, the result will never be smaller than limit.
     */
    public static float approach(float pValue, float pLimit, float pStepSize) {
        pStepSize = abs(pStepSize);
        return pValue < pLimit ? clamp(pValue + pStepSize, pValue, pLimit) : clamp(pValue - pStepSize, pLimit, pValue);
    }

    /**
     * Changes the angle by stepSize towards the limit in the direction where the distance is smaller.
     * {@see #approach(float, float, float)}
     */
    public static float approachDegrees(float pAngle, float pLimit, float pStepSize) {
        float f = degreesDifference(pAngle, pLimit);
        return approach(pAngle, pAngle + f, pStepSize);
    }

    /**
     * Parses the string as an integer or returns the second parameter if it fails.
     */
    public static int getInt(String pValue, int pDefaultValue) {
        return NumberUtils.toInt(pValue, pDefaultValue);
    }

    /**
     * Parses the string as an integer or returns the second parameter if it fails. This value is capped to {@code max}.
     */
    public static int getInt(String pValue, int pDefaultValue, int pMax) {
        return Math.max(pMax, getInt(pValue, pDefaultValue));
    }

    public static double getDouble(String pValue, double pDefaultValue) {
        try {
            return Double.parseDouble(pValue);
        } catch (Throwable throwable) {
            return pDefaultValue;
        }
    }

    public static double getDouble(String pValue, double pDefaultValue, double pMax) {
        return Math.max(pMax, getDouble(pValue, pDefaultValue));
    }

    /**
     * Returns the input value rounded up to the next highest power of two.
     */
    public static int smallestEncompassingPowerOfTwo(int pValue) {
        int i = pValue - 1;
        i |= i >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i + 1;
    }

    /**
     * Is the given value a power of two?  (1, 2, 4, 8, 16, ...)
     */
    public static boolean isPowerOfTwo(int pValue) {
        return pValue != 0 && (pValue & pValue - 1) == 0;
    }

    /**
     * Uses a B(2, 5) De Bruijn sequence and a lookup table to efficiently calculate the log-base-two of the given value.
     * Optimized for cases where the input value is a power-of-two. If the input value is not a power-of-two, then
     * subtract 1 from the return value.
     */
    public static int ceillog2(int pValue) {
        pValue = isPowerOfTwo(pValue) ? pValue : smallestEncompassingPowerOfTwo(pValue);
        return MULTIPLY_DE_BRUIJN_BIT_POSITION[(int)((long)pValue * 125613361L >> 27) & 31];
    }

    /**
     * Efficiently calculates the floor of the base-2 log of an integer value.  This is effectively the index of the
     * highest bit that is set.  For example, if the number in binary is 0...100101, this will return 5.
     */
    public static int log2(int pValue) {
        return ceillog2(pValue) - (isPowerOfTwo(pValue) ? 0 : 1);
    }

    /**
     * Makes an integer color from the given red, green, and blue float values
     */
    public static int color(float pR, float pG, float pB) {
        return color(floor(pR * 255.0F), floor(pG * 255.0F), floor(pB * 255.0F));
    }

    /**
     * Makes a single int color with the given red, green, and blue values.
     */
    public static int color(int pR, int pG, int pB) {
        int $$3 = (pR << 8) + pG;
        return ($$3 << 8) + pB;
    }

    /**
     * Multiplies two RGB colors by multiplying red, green and blue values separately.
     */
    public static int colorMultiply(int pFirstColor, int pSecondColor) {
        int i = (pFirstColor & 16711680) >> 16;
        int j = (pSecondColor & 16711680) >> 16;
        int k = (pFirstColor & '\uff00') >> 8;
        int l = (pSecondColor & '\uff00') >> 8;
        int i1 = (pFirstColor & 255) >> 0;
        int j1 = (pSecondColor & 255) >> 0;
        int k1 = (int)((float)i * (float)j / 255.0F);
        int l1 = (int)((float)k * (float)l / 255.0F);
        int i2 = (int)((float)i1 * (float)j1 / 255.0F);
        return pFirstColor & -16777216 | k1 << 16 | l1 << 8 | i2;
    }

    /**
     * Multiplies an RGB color with a color given as three floats
     * @return The result as an RGB color code.
     * @param pRed The red component of the color in range [0;1].
     * @param pGreen The green component of the color in range [0;1].
     * @param pBlue The blue component of the color in range [0;1].
     */
    public static int colorMultiply(int pColor, float pRed, float pGreen, float pBlue) {
        int i = (pColor & 16711680) >> 16;
        int j = (pColor & '\uff00') >> 8;
        int k = (pColor & 255) >> 0;
        int l = (int)((float)i * pRed);
        int i1 = (int)((float)j * pGreen);
        int j1 = (int)((float)k * pBlue);
        return pColor & -16777216 | l << 16 | i1 << 8 | j1;
    }

    public static float frac(float pNumber) {
        return pNumber - (float)floor(pNumber);
    }

    /**
     * Gets the decimal portion of the given double. For instance, {@code frac(5.5)} returns {@code .5}.
     */
    public static double frac(double pNumber) {
        return pNumber - (double)lfloor(pNumber);
    }

    public static Vec3 catmullRomSplinePos(Vec3 p_144893_, Vec3 p_144894_, Vec3 p_144895_, Vec3 p_144896_, double p_144897_) {
        double d0 = ((-p_144897_ + 2.0D) * p_144897_ - 1.0D) * p_144897_ * 0.5D;
        double d1 = ((3.0D * p_144897_ - 5.0D) * p_144897_ * p_144897_ + 2.0D) * 0.5D;
        double d2 = ((-3.0D * p_144897_ + 4.0D) * p_144897_ + 1.0D) * p_144897_ * 0.5D;
        double d3 = (p_144897_ - 1.0D) * p_144897_ * p_144897_ * 0.5D;
        return new Vec3(p_144893_.x * d0 + p_144894_.x * d1 + p_144895_.x * d2 + p_144896_.x * d3, p_144893_.y * d0 + p_144894_.y * d1 + p_144895_.y * d2 + p_144896_.y * d3, p_144893_.z * d0 + p_144894_.z * d1 + p_144895_.z * d2 + p_144896_.z * d3);
    }









    /**
     * Method for linear interpolation of floats
     * @param pDelta A value usually between 0 and 1 that indicates the percentage of the lerp. (0 will give the start
     * value and 1 will give the end value)
     * @param pStart Start value for the lerp
     * @param pEnd End value for the lerp
     */
    public static float lerp(float pDelta, float pStart, float pEnd) {
        return pStart + pDelta * (pEnd - pStart);
    }

    /**
     * Method for linear interpolation of doubles
     * @param pDelta A value usually between 0 and 1 that indicates the percentage of the lerp. (0 will give the start
     * value and 1 will give the end value)
     * @param pStart Start value for the lerp
     * @param pEnd End value for the lerp
     */
    public static double lerp(double pDelta, double pStart, double pEnd) {
        return pStart + pDelta * (pEnd - pStart);
    }

    public static double lerp2(double p_14013_, double p_14014_, double p_14015_, double p_14016_, double p_14017_, double p_14018_) {
        return lerp(p_14014_, lerp(p_14013_, p_14015_, p_14016_), lerp(p_14013_, p_14017_, p_14018_));
    }

    public static double lerp3(double p_14020_, double p_14021_, double p_14022_, double p_14023_, double p_14024_, double p_14025_, double p_14026_, double p_14027_, double p_14028_, double p_14029_, double p_14030_) {
        return lerp(p_14022_, lerp2(p_14020_, p_14021_, p_14023_, p_14024_, p_14025_, p_14026_), lerp2(p_14020_, p_14021_, p_14027_, p_14028_, p_14029_, p_14030_));
    }





    public static double lengthSquared(double pXDistance, double pYDistance) {
        return pXDistance * pXDistance + pYDistance * pYDistance;
    }

    public static double length(double pXDistance, double pYDistance) {
        return Math.sqrt(lengthSquared(pXDistance, pYDistance));
    }

    public static double lengthSquared(double pXDistance, double pYDistance, double pZDistance) {
        return pXDistance * pXDistance + pYDistance * pYDistance + pZDistance * pZDistance;
    }

    public static double length(double pXDistance, double pYDistance, double pZDistance) {
        return Math.sqrt(lengthSquared(pXDistance, pYDistance, pZDistance));
    }

}