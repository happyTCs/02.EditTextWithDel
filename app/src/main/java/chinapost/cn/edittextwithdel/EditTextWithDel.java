package chinapost.cn.edittextwithdel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.EventLogTags;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class EditTextWithDel extends AppCompatEditText {
    private static final String TAG = "EditTextWithDel";
    private Context mContext;
    private Drawable imgInAble;
    private Drawable imgAble;

    public EditTextWithDel(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }



    public EditTextWithDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }
    private void init() {
        //灰色叉号
        imgInAble = mContext.getResources().getDrawable(R.drawable.icon_cancel);
        //带色叉号
        imgAble = mContext.getResources().getDrawable(R.drawable.icon_delete_input);
        setDrawable();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });

    }
    //设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgInAble, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null,null,imgAble,null);
        }
    }
    //处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
          int rawX = (int) event.getRawX();
            int rawY = (int) event.getRawY();
            Log.e(TAG, "onTouchEvent:rawX= "+rawX+";rawY="+rawY );
            Rect rect = new Rect();
            //大矩形左边本来是全局可见的矩形的
            getGlobalVisibleRect(rect);
            //大矩形左边比右边小50；
            rect.left=rect.right-50;
            Log.e(TAG, "onTouchEvent:rect.left="+rect.left+",rect.right"+rect.right +"rect.top="+rect.top+"rect.bottom="+rect.bottom);
            if (rect.contains(rawX, rawY)) {
                setText("");
            }
//         setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
