package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public class ConstraintAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    /* loaded from: classes.dex */
    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE
    }

    public AttributeType getType() {
        return this.mType;
    }

    public void setFloatValue(float value) {
        this.mFloatValue = value;
    }

    public void setColorValue(int value) {
        this.mColorValue = value;
    }

    public void setIntValue(int value) {
        this.mIntegerValue = value;
    }

    public void setStringValue(String value) {
        this.mStringValue = value;
    }

    public int noOfInterpValues() {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                return 4;
            default:
                return 1;
        }
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case INT_TYPE:
                return this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 0.0f : 1.0f;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] ret) {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int a = (this.mColorValue >> 24) & 255;
                int r = (this.mColorValue >> 16) & 255;
                int g = (this.mColorValue >> 8) & 255;
                int b = this.mColorValue & 255;
                float f_r = (float) Math.pow(r / 255.0f, 2.2d);
                float f_g = (float) Math.pow(g / 255.0f, 2.2d);
                float f_b = (float) Math.pow(b / 255.0f, 2.2d);
                ret[0] = f_r;
                ret[1] = f_g;
                ret[2] = f_b;
                ret[3] = a / 255.0f;
                return;
            case INT_TYPE:
                ret[0] = this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                ret[0] = this.mFloatValue;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                ret[0] = this.mBooleanValue ? 0.0f : 1.0f;
                return;
            case DIMENSION_TYPE:
                ret[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] value) {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = Color.HSVToColor(value);
                this.mColorValue = (this.mColorValue & ViewCompat.MEASURED_SIZE_MASK) | (clamp((int) (value[3] * 255.0f)) << 24);
                return;
            case INT_TYPE:
                this.mIntegerValue = (int) value[0];
                return;
            case FLOAT_TYPE:
                this.mFloatValue = value[0];
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((double) value[0]) > 0.5d;
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = value[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(ConstraintAttribute constraintAttribute) {
        if (constraintAttribute == null || this.mType != constraintAttribute.mType) {
            return false;
        }
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                return this.mColorValue == constraintAttribute.mColorValue;
            case INT_TYPE:
                return this.mIntegerValue == constraintAttribute.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue == constraintAttribute.mFloatValue;
            case STRING_TYPE:
                return this.mIntegerValue == constraintAttribute.mIntegerValue;
            case BOOLEAN_TYPE:
                return this.mBooleanValue == constraintAttribute.mBooleanValue;
            case DIMENSION_TYPE:
                return this.mFloatValue == constraintAttribute.mFloatValue;
            default:
                return false;
        }
    }

    public ConstraintAttribute(String name, AttributeType attributeType) {
        this.mName = name;
        this.mType = attributeType;
    }

    public ConstraintAttribute(String name, AttributeType attributeType, Object value) {
        this.mName = name;
        this.mType = attributeType;
        setValue(value);
    }

    public ConstraintAttribute(ConstraintAttribute source, Object value) {
        this.mName = source.mName;
        this.mType = source.mType;
        setValue(value);
    }

    public void setValue(Object value) {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = ((Integer) value).intValue();
                return;
            case INT_TYPE:
                this.mIntegerValue = ((Integer) value).intValue();
                return;
            case FLOAT_TYPE:
                this.mFloatValue = ((Float) value).floatValue();
                return;
            case STRING_TYPE:
                this.mStringValue = (String) value;
                return;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((Boolean) value).booleanValue();
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = ((Float) value).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap<String, ConstraintAttribute> extractAttributes(HashMap<String, ConstraintAttribute> base, View view) {
        HashMap<String, ConstraintAttribute> ret = new HashMap<>();
        Class<?> cls = view.getClass();
        for (String name : base.keySet()) {
            ConstraintAttribute constraintAttribute = base.get(name);
            try {
                if (name.equals("BackgroundColor")) {
                    ColorDrawable viewColor = (ColorDrawable) view.getBackground();
                    Object val = Integer.valueOf(viewColor.getColor());
                    ret.put(name, new ConstraintAttribute(constraintAttribute, val));
                } else {
                    Method method = cls.getMethod("getMap" + name, new Class[0]);
                    Object val2 = method.invoke(view, new Object[0]);
                    ret.put(name, new ConstraintAttribute(constraintAttribute, val2));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return ret;
    }

    public static void setAttributes(View view, HashMap<String, ConstraintAttribute> map) {
        Class<?> cls = view.getClass();
        for (String name : map.keySet()) {
            ConstraintAttribute constraintAttribute = map.get(name);
            String methodName = "set" + name;
            try {
                switch (constraintAttribute.mType) {
                    case COLOR_TYPE:
                        Method method = cls.getMethod(methodName, Integer.TYPE);
                        method.invoke(view, Integer.valueOf(constraintAttribute.mColorValue));
                        break;
                    case COLOR_DRAWABLE_TYPE:
                        Method method2 = cls.getMethod(methodName, Drawable.class);
                        ColorDrawable drawable = new ColorDrawable();
                        drawable.setColor(constraintAttribute.mColorValue);
                        method2.invoke(view, drawable);
                        break;
                    case INT_TYPE:
                        Method method3 = cls.getMethod(methodName, Integer.TYPE);
                        method3.invoke(view, Integer.valueOf(constraintAttribute.mIntegerValue));
                        break;
                    case FLOAT_TYPE:
                        Method method4 = cls.getMethod(methodName, Float.TYPE);
                        method4.invoke(view, Float.valueOf(constraintAttribute.mFloatValue));
                        break;
                    case STRING_TYPE:
                        Method method5 = cls.getMethod(methodName, CharSequence.class);
                        method5.invoke(view, constraintAttribute.mStringValue);
                        break;
                    case BOOLEAN_TYPE:
                        Method method6 = cls.getMethod(methodName, Boolean.TYPE);
                        method6.invoke(view, Boolean.valueOf(constraintAttribute.mBooleanValue));
                        break;
                    case DIMENSION_TYPE:
                        Method method7 = cls.getMethod(methodName, Float.TYPE);
                        method7.invoke(view, Float.valueOf(constraintAttribute.mFloatValue));
                        break;
                }
            } catch (IllegalAccessException e) {
                Log.e(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                Log.e(TAG, e2.getMessage());
                Log.e(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                Log.e(TAG, cls.getName() + " must have a method " + methodName);
            } catch (InvocationTargetException e3) {
                Log.e(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    private static int clamp(int c) {
        int c2 = (c & (~(c >> 31))) - 255;
        return (c2 & (c2 >> 31)) + 255;
    }

    public void setInterpolatedValue(View view, float[] value) {
        Class<?> cls = view.getClass();
        String methodName = "set" + this.mName;
        try {
            boolean z = true;
            switch (this.mType) {
                case COLOR_TYPE:
                    Method method = cls.getMethod(methodName, Integer.TYPE);
                    int r = clamp((int) (((float) Math.pow(value[0], 0.45454545454545453d)) * 255.0f));
                    int g = clamp((int) (((float) Math.pow(value[1], 0.45454545454545453d)) * 255.0f));
                    int b = clamp((int) (((float) Math.pow(value[2], 0.45454545454545453d)) * 255.0f));
                    int a = clamp((int) (value[3] * 255.0f));
                    int color = (a << 24) | (r << 16) | (g << 8) | b;
                    method.invoke(view, Integer.valueOf(color));
                    return;
                case COLOR_DRAWABLE_TYPE:
                    Method method2 = cls.getMethod(methodName, Drawable.class);
                    int r2 = clamp((int) (((float) Math.pow(value[0], 0.45454545454545453d)) * 255.0f));
                    int g2 = clamp((int) (((float) Math.pow(value[1], 0.45454545454545453d)) * 255.0f));
                    int b2 = clamp((int) (((float) Math.pow(value[2], 0.45454545454545453d)) * 255.0f));
                    int a2 = clamp((int) (value[3] * 255.0f));
                    int color2 = (a2 << 24) | (r2 << 16) | (g2 << 8) | b2;
                    ColorDrawable drawable = new ColorDrawable();
                    drawable.setColor(color2);
                    method2.invoke(view, drawable);
                    return;
                case INT_TYPE:
                    Method method3 = cls.getMethod(methodName, Integer.TYPE);
                    method3.invoke(view, Integer.valueOf((int) value[0]));
                    return;
                case FLOAT_TYPE:
                    Method method4 = cls.getMethod(methodName, Float.TYPE);
                    method4.invoke(view, Float.valueOf(value[0]));
                    return;
                case STRING_TYPE:
                    throw new RuntimeException("unable to interpolate strings " + this.mName);
                case BOOLEAN_TYPE:
                    Method method5 = cls.getMethod(methodName, Boolean.TYPE);
                    Object[] objArr = new Object[1];
                    if (value[0] <= 0.5f) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    method5.invoke(view, objArr);
                    return;
                case DIMENSION_TYPE:
                    Method method6 = cls.getMethod(methodName, Float.TYPE);
                    method6.invoke(view, Float.valueOf(value[0]));
                    return;
                default:
                    return;
            }
        } catch (IllegalAccessException e) {
            Log.e(TAG, "cannot access method " + methodName + "on View \"" + Debug.getName(view) + "\"");
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "no method " + methodName + "on View \"" + Debug.getName(view) + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public static void parse(Context context, XmlPullParser parser, HashMap<String, ConstraintAttribute> custom) {
        AttributeSet attributeSet = Xml.asAttributeSet(parser);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CustomAttribute);
        String name = null;
        Object value = null;
        AttributeType type = null;
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CustomAttribute_attributeName) {
                name = a.getString(attr);
                if (name != null && name.length() > 0) {
                    name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                }
            } else if (attr == R.styleable.CustomAttribute_customBoolean) {
                value = Boolean.valueOf(a.getBoolean(attr, false));
                type = AttributeType.BOOLEAN_TYPE;
            } else if (attr == R.styleable.CustomAttribute_customColorValue) {
                type = AttributeType.COLOR_TYPE;
                value = Integer.valueOf(a.getColor(attr, 0));
            } else if (attr == R.styleable.CustomAttribute_customColorDrawableValue) {
                type = AttributeType.COLOR_DRAWABLE_TYPE;
                value = Integer.valueOf(a.getColor(attr, 0));
            } else if (attr == R.styleable.CustomAttribute_customPixelDimension) {
                type = AttributeType.DIMENSION_TYPE;
                value = Float.valueOf(TypedValue.applyDimension(1, a.getDimension(attr, 0.0f), context.getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.CustomAttribute_customDimension) {
                type = AttributeType.DIMENSION_TYPE;
                value = Float.valueOf(a.getDimension(attr, 0.0f));
            } else if (attr == R.styleable.CustomAttribute_customFloatValue) {
                type = AttributeType.FLOAT_TYPE;
                value = Float.valueOf(a.getFloat(attr, Float.NaN));
            } else if (attr == R.styleable.CustomAttribute_customIntegerValue) {
                type = AttributeType.INT_TYPE;
                value = Integer.valueOf(a.getInteger(attr, -1));
            } else if (attr == R.styleable.CustomAttribute_customStringValue) {
                type = AttributeType.STRING_TYPE;
                value = a.getString(attr);
            }
        }
        if (name != null && value != null) {
            custom.put(name, new ConstraintAttribute(name, type, value));
        }
        a.recycle();
    }
}
