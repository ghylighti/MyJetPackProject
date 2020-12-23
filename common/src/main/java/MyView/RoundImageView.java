package MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.ghy.common.R;


/**
 * 圆角ImageView
 *
 * @author ghy
 */
public class RoundImageView extends AppCompatImageView {

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
       TypedArray typedArray= context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        rect_adius= typedArray.getInt(R.styleable.RoundImageView_radius,0);
        init();
    }

    public RoundImageView(Context context) {
        super(context);
        init();
    }

    private final RectF roundRect = new RectF();
    private float rect_adius = 0;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    private void init() {

        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        //
        float density = getResources().getDisplayMetrics().density;
        rect_adius = rect_adius * density;
    }

    public void setRectAdius(float adius) {
        rect_adius = adius;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint);

        //
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

    public boolean isWH() {
        return WH;
    }

    public void setWH(boolean WH) {
        this.WH = WH;
    }

    private boolean WH;//设置高度根据width还是height
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(WH)
        {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}