package androidx.constraintlayout.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    double[] mArea;
    int mType;
    float[] mPeriod = new float[0];
    double[] mPosition = new double[0];
    double PI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }

    public void setType(int type) {
        this.mType = type;
    }

    public void addPoint(double position, float period) {
        int len = this.mPeriod.length + 1;
        int j = Arrays.binarySearch(this.mPosition, position);
        if (j < 0) {
            j = (-j) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, len);
        this.mPeriod = Arrays.copyOf(this.mPeriod, len);
        this.mArea = new double[len];
        System.arraycopy(this.mPosition, j, this.mPosition, j + 1, (len - j) - 1);
        this.mPosition[j] = position;
        this.mPeriod[j] = period;
        this.mNormalized = false;
    }

    public void normalize() {
        double totalArea = 0.0d;
        double totalCount = 0.0d;
        for (int i = 0; i < this.mPeriod.length; i++) {
            totalCount += this.mPeriod[i];
        }
        for (int i2 = 1; i2 < this.mPeriod.length; i2++) {
            float h = (this.mPeriod[i2 - 1] + this.mPeriod[i2]) / 2.0f;
            double w = this.mPosition[i2] - this.mPosition[i2 - 1];
            totalArea += h * w;
        }
        for (int i3 = 0; i3 < this.mPeriod.length; i3++) {
            float[] fArr = this.mPeriod;
            fArr[i3] = (float) (fArr[i3] * (totalCount / totalArea));
        }
        this.mArea[0] = 0.0d;
        for (int i4 = 1; i4 < this.mPeriod.length; i4++) {
            float h2 = (this.mPeriod[i4 - 1] + this.mPeriod[i4]) / 2.0f;
            double w2 = this.mPosition[i4] - this.mPosition[i4 - 1];
            this.mArea[i4] = this.mArea[i4 - 1] + (h2 * w2);
        }
        this.mNormalized = true;
    }

    double getP(double time) {
        double time2;
        if (time < 0.0d) {
            time2 = 0.0d;
        } else if (time <= 1.0d) {
            time2 = time;
        } else {
            time2 = 1.0d;
        }
        int index = Arrays.binarySearch(this.mPosition, time2);
        if (index > 0) {
            return 1.0d;
        }
        if (index == 0) {
            return 0.0d;
        }
        int index2 = (-index) - 1;
        double t = time2;
        double m = (this.mPeriod[index2] - this.mPeriod[index2 - 1]) / (this.mPosition[index2] - this.mPosition[index2 - 1]);
        double p = this.mArea[index2 - 1] + ((this.mPeriod[index2 - 1] - (this.mPosition[index2 - 1] * m)) * (t - this.mPosition[index2 - 1])) + ((((t * t) - (this.mPosition[index2 - 1] * this.mPosition[index2 - 1])) * m) / 2.0d);
        return p;
    }

    public double getValue(double time) {
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (getP(time) % 1.0d));
            case 2:
                return 1.0d - Math.abs((((getP(time) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
            case 3:
                return (((getP(time) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                return 1.0d - (((getP(time) * 2.0d) + 1.0d) % 2.0d);
            case 5:
                return Math.cos(this.PI2 * getP(time));
            case 6:
                double x = 1.0d - Math.abs(((getP(time) * 4.0d) % 4.0d) - 2.0d);
                return 1.0d - (x * x);
            default:
                return Math.sin(this.PI2 * getP(time));
        }
    }

    double getDP(double time) {
        double time2;
        if (time <= 0.0d) {
            time2 = 1.0E-5d;
        } else if (time < 1.0d) {
            time2 = time;
        } else {
            time2 = 0.999999d;
        }
        int index = Arrays.binarySearch(this.mPosition, time2);
        if (index > 0 || index == 0) {
            return 0.0d;
        }
        int index2 = (-index) - 1;
        double t = time2;
        double m = (this.mPeriod[index2] - this.mPeriod[index2 - 1]) / (this.mPosition[index2] - this.mPosition[index2 - 1]);
        double p = (m * t) + (this.mPeriod[index2 - 1] - (this.mPosition[index2 - 1] * m));
        return p;
    }

    public double getSlope(double time) {
        switch (this.mType) {
            case 1:
                return 0.0d;
            case 2:
                return getDP(time) * 4.0d * Math.signum((((getP(time) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
            case 3:
                return getDP(time) * 2.0d;
            case 4:
                return (-getDP(time)) * 2.0d;
            case 5:
                return (-this.PI2) * getDP(time) * Math.sin(this.PI2 * getP(time));
            case 6:
                return getDP(time) * 4.0d * ((((getP(time) * 4.0d) + 2.0d) % 4.0d) - 2.0d);
            default:
                return this.PI2 * getDP(time) * Math.cos(this.PI2 * getP(time));
        }
    }
}
