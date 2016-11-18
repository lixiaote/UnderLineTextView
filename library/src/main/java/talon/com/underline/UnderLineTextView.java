package talon.com.underline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

import static android.R.attr.baseline;

/**
 * Created by li.tl on 2016/11/15.
 */

public class UnderLineTextView extends TextView {

    private Rect mRect;
    private Paint mPaint;
    private int mColor;
    private float density;
    private float mStrokeWidth;

    public UnderLineTextView(Context context) {
        this(context, null, 0);
    }
    public UnderLineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public UnderLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
          //获取屏幕密度
        density=context.getResources().getDisplayMetrics().density;
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.UnderlinedTextView,defStyleAttr,0);
        mColor=array.getColor(R.styleable.UnderlinedTextView_underlineColor,0xFFFF0000);
        mStrokeWidth=array.getDimension(R.styleable.UnderlinedTextView_underlineWidth,density*2);
        array.recycle();

        mRect=new Rect();
        mPaint =new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(mStrokeWidth);

        }

    @Override
    protected void onDraw(Canvas canvas) {

        int count=getLineCount();

        final Layout layout=getLayout();
        float x_start,x_stop,x_diff;
        int firstCharInLine, lastCharInLine;

        for (int i = 0; i < count; i++) {
            int baseline=getLineBounds(i,mRect);
            firstCharInLine=layout.getLineStart(i);
            lastCharInLine = layout.getLineEnd(i);

            x_start = layout.getPrimaryHorizontal(firstCharInLine);
            x_diff = layout.getPrimaryHorizontal(firstCharInLine + 1) - x_start;
            x_stop = layout.getPrimaryHorizontal(lastCharInLine - 1) + x_diff;

            canvas.drawLine(x_start,baseline + mStrokeWidth,x_stop, baseline + mStrokeWidth, mPaint);


        }
        super.onDraw(canvas);

    }

    public int getUnderLineColor() {
        return mColor;
    }

    public void setUnderLineColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }

    public float getUnderlineWidth() {
        return mStrokeWidth;
    }

    public void setUnderlineWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        invalidate();
    }

}
