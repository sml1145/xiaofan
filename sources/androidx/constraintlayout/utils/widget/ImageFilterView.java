package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    private float mCrossfade;
    private ImageMatrix mImageMatrix;
    LayerDrawable mLayer;
    Drawable[] mLayers;
    private boolean mOverlay;
    private Path mPath;
    RectF mRect;
    private float mRound;
    private float mRoundPercent;
    ViewOutlineProvider mViewOutlineProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ImageMatrix {
        float[] m = new float[20];
        ColorMatrix mColorMatrix = new ColorMatrix();
        ColorMatrix mTmpColorMatrix = new ColorMatrix();
        float mBrightness = 1.0f;
        float mSaturation = 1.0f;
        float mContrast = 1.0f;
        float mWarmth = 1.0f;

        private void saturation(float saturationStrength) {
            float MS = 1.0f - saturationStrength;
            float Rt = 0.2999f * MS;
            float Gt = 0.587f * MS;
            float Bt = 0.114f * MS;
            this.m[0] = Rt + saturationStrength;
            this.m[1] = Gt;
            this.m[2] = Bt;
            this.m[3] = 0.0f;
            this.m[4] = 0.0f;
            this.m[5] = Rt;
            this.m[6] = Gt + saturationStrength;
            this.m[7] = Bt;
            this.m[8] = 0.0f;
            this.m[9] = 0.0f;
            this.m[10] = Rt;
            this.m[11] = Gt;
            this.m[12] = Bt + saturationStrength;
            this.m[13] = 0.0f;
            this.m[14] = 0.0f;
            this.m[15] = 0.0f;
            this.m[16] = 0.0f;
            this.m[17] = 0.0f;
            this.m[18] = 1.0f;
            this.m[19] = 0.0f;
        }

        private void warmth(float warmth) {
            float colorG;
            float colorR;
            float colorB;
            float color_b;
            float colorG2;
            float colorR2;
            float colorB2;
            float kelvin = 5000.0f / (warmth <= 0.0f ? 0.01f : warmth);
            float centiKelvin = kelvin / 100.0f;
            if (centiKelvin > 66.0f) {
                float tmp = centiKelvin - 60.0f;
                colorR = ((float) Math.pow(tmp, -0.13320475816726685d)) * 329.69873f;
                colorG = ((float) Math.pow(tmp, 0.07551484555006027d)) * 288.12216f;
            } else {
                colorG = (((float) Math.log(centiKelvin)) * 99.4708f) - 161.11957f;
                colorR = 255.0f;
            }
            if (centiKelvin < 66.0f) {
                if (centiKelvin > 19.0f) {
                    colorB = (((float) Math.log(centiKelvin - 10.0f)) * 138.51773f) - 305.0448f;
                } else {
                    colorB = 0.0f;
                }
            } else {
                colorB = 255.0f;
            }
            float tmpColor_r = Math.min(255.0f, Math.max(colorR, 0.0f));
            float tmpColor_g = Math.min(255.0f, Math.max(colorG, 0.0f));
            float colorR3 = Math.min(255.0f, Math.max(colorB, 0.0f));
            float centiKelvin2 = 5000.0f / 100.0f;
            if (centiKelvin2 <= 66.0f) {
                color_b = colorR3;
                colorG2 = (((float) Math.log(centiKelvin2)) * 99.4708f) - 161.11957f;
                colorR2 = 255.0f;
            } else {
                float tmp2 = centiKelvin2 - 60.0f;
                color_b = colorR3;
                colorR2 = ((float) Math.pow(tmp2, -0.13320475816726685d)) * 329.69873f;
                colorG2 = ((float) Math.pow(tmp2, 0.07551484555006027d)) * 288.12216f;
            }
            if (centiKelvin2 < 66.0f) {
                if (centiKelvin2 > 19.0f) {
                    colorB2 = (((float) Math.log(centiKelvin2 - 10.0f)) * 138.51773f) - 305.0448f;
                } else {
                    colorB2 = 0.0f;
                }
            } else {
                colorB2 = 255.0f;
            }
            float tmpColor_r2 = Math.min(255.0f, Math.max(colorR2, 0.0f));
            float tmpColor_g2 = Math.min(255.0f, Math.max(colorG2, 0.0f));
            float colorG3 = Math.min(255.0f, Math.max(colorB2, 0.0f));
            float color_r = tmpColor_r / tmpColor_r2;
            float color_g = tmpColor_g / tmpColor_g2;
            this.m[0] = color_r;
            this.m[1] = 0.0f;
            this.m[2] = 0.0f;
            this.m[3] = 0.0f;
            this.m[4] = 0.0f;
            this.m[5] = 0.0f;
            this.m[6] = color_g;
            this.m[7] = 0.0f;
            this.m[8] = 0.0f;
            this.m[9] = 0.0f;
            this.m[10] = 0.0f;
            this.m[11] = 0.0f;
            this.m[12] = color_b / colorG3;
            this.m[13] = 0.0f;
            this.m[14] = 0.0f;
            this.m[15] = 0.0f;
            this.m[16] = 0.0f;
            this.m[17] = 0.0f;
            this.m[18] = 1.0f;
            this.m[19] = 0.0f;
        }

        private void brightness(float brightness) {
            this.m[0] = brightness;
            this.m[1] = 0.0f;
            this.m[2] = 0.0f;
            this.m[3] = 0.0f;
            this.m[4] = 0.0f;
            this.m[5] = 0.0f;
            this.m[6] = brightness;
            this.m[7] = 0.0f;
            this.m[8] = 0.0f;
            this.m[9] = 0.0f;
            this.m[10] = 0.0f;
            this.m[11] = 0.0f;
            this.m[12] = brightness;
            this.m[13] = 0.0f;
            this.m[14] = 0.0f;
            this.m[15] = 0.0f;
            this.m[16] = 0.0f;
            this.m[17] = 0.0f;
            this.m[18] = 1.0f;
            this.m[19] = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void updateMatrix(ImageView view) {
            this.mColorMatrix.reset();
            boolean filter = false;
            if (this.mSaturation != 1.0f) {
                saturation(this.mSaturation);
                this.mColorMatrix.set(this.m);
                filter = true;
            }
            if (this.mContrast != 1.0f) {
                this.mTmpColorMatrix.setScale(this.mContrast, this.mContrast, this.mContrast, 1.0f);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                filter = true;
            }
            if (this.mWarmth != 1.0f) {
                warmth(this.mWarmth);
                this.mTmpColorMatrix.set(this.m);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                filter = true;
            }
            if (this.mBrightness != 1.0f) {
                brightness(this.mBrightness);
                this.mTmpColorMatrix.set(this.m);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                filter = true;
            }
            if (filter) {
                view.setColorFilter(new ColorMatrixColorFilter(this.mColorMatrix));
            } else {
                view.clearColorFilter();
            }
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        init(context, null);
    }

    public ImageFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        init(context, attrs);
    }

    public ImageFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImageFilterView);
            int N = a.getIndexCount();
            Drawable drawable = a.getDrawable(R.styleable.ImageFilterView_altSrc);
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ImageFilterView_crossfade) {
                    this.mCrossfade = a.getFloat(attr, 0.0f);
                } else if (attr == R.styleable.ImageFilterView_warmth) {
                    setWarmth(a.getFloat(attr, 0.0f));
                } else if (attr == R.styleable.ImageFilterView_saturation) {
                    setSaturation(a.getFloat(attr, 0.0f));
                } else if (attr == R.styleable.ImageFilterView_contrast) {
                    setContrast(a.getFloat(attr, 0.0f));
                } else if (attr == R.styleable.ImageFilterView_round) {
                    setRound(a.getDimension(attr, 0.0f));
                } else if (attr == R.styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(a.getFloat(attr, 0.0f));
                } else if (attr == R.styleable.ImageFilterView_overlay) {
                    setOverlay(a.getBoolean(attr, this.mOverlay));
                }
            }
            a.recycle();
            if (drawable != null) {
                this.mLayers = new Drawable[2];
                this.mLayers[0] = getDrawable();
                this.mLayers[1] = drawable;
                this.mLayer = new LayerDrawable(this.mLayers);
                this.mLayer.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
                super.setImageDrawable(this.mLayer);
            }
        }
    }

    private void setOverlay(boolean overlay) {
        this.mOverlay = overlay;
    }

    public void setSaturation(float saturation) {
        this.mImageMatrix.mSaturation = saturation;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getSaturation() {
        return this.mImageMatrix.mSaturation;
    }

    public void setContrast(float contrast) {
        this.mImageMatrix.mContrast = contrast;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getContrast() {
        return this.mImageMatrix.mContrast;
    }

    public void setWarmth(float warmth) {
        this.mImageMatrix.mWarmth = warmth;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getWarmth() {
        return this.mImageMatrix.mWarmth;
    }

    public void setCrossfade(float crossfade) {
        this.mCrossfade = crossfade;
        if (this.mLayers != null) {
            if (!this.mOverlay) {
                this.mLayer.getDrawable(0).setAlpha((int) ((1.0f - this.mCrossfade) * 255.0f));
            }
            this.mLayer.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
            super.setImageDrawable(this.mLayer);
        }
    }

    public float getCrossfade() {
        return this.mCrossfade;
    }

    public void setBrightness(float brightness) {
        this.mImageMatrix.mBrightness = brightness;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getBrightness() {
        return this.mImageMatrix.mBrightness;
    }

    public void setRoundPercent(float round) {
        boolean change = this.mRoundPercent != round;
        this.mRoundPercent = round;
        if (this.mRoundPercent != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (this.mViewOutlineProvider == null) {
                this.mViewOutlineProvider = new ViewOutlineProvider() { // from class: androidx.constraintlayout.utils.widget.ImageFilterView.1
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view, Outline outline) {
                        int w = ImageFilterView.this.getWidth();
                        int h = ImageFilterView.this.getHeight();
                        float r = (Math.min(w, h) * ImageFilterView.this.mRoundPercent) / 2.0f;
                        outline.setRoundRect(0, 0, w, h, r);
                    }
                };
                setOutlineProvider(this.mViewOutlineProvider);
            }
            setClipToOutline(true);
            int w = getWidth();
            int h = getHeight();
            float r = (Math.min(w, h) * this.mRoundPercent) / 2.0f;
            this.mRect.set(0.0f, 0.0f, w, h);
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, r, r, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (change) {
            invalidateOutline();
        }
    }

    public void setRound(float round) {
        if (Float.isNaN(round)) {
            this.mRound = round;
            float tmp = this.mRoundPercent;
            this.mRoundPercent = -1.0f;
            setRoundPercent(tmp);
            return;
        }
        float tmp2 = this.mRound;
        boolean change = tmp2 != round;
        this.mRound = round;
        if (this.mRound != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (this.mViewOutlineProvider == null) {
                this.mViewOutlineProvider = new ViewOutlineProvider() { // from class: androidx.constraintlayout.utils.widget.ImageFilterView.2
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view, Outline outline) {
                        int w = ImageFilterView.this.getWidth();
                        int h = ImageFilterView.this.getHeight();
                        outline.setRoundRect(0, 0, w, h, ImageFilterView.this.mRound);
                    }
                };
                setOutlineProvider(this.mViewOutlineProvider);
            }
            setClipToOutline(true);
            int w = getWidth();
            int h = getHeight();
            this.mRect.set(0.0f, 0.0f, w, h);
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, this.mRound, this.mRound, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (change) {
            invalidateOutline();
        }
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getRound() {
        return this.mRound;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (0 != 0) {
            canvas.restore();
        }
    }
}
