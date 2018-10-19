package application.baseview.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Gao on 2018/10/18.
 */

public class InputMoneyEditTextView extends EditText {
    private boolean flag = false;

    public InputMoneyEditTextView(Context context) {
        this(context, null);
    }

    public InputMoneyEditTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputMoneyEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        setFocusableInTouchMode(true); // 自定义EditText的时候，加上这段才可以弹出软键盘

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!flag) {
                    checkInput(s);
                }
            }
        });
    }

    public void checkInput(Editable s) {
        flag = true;
        String content = s.toString();
        int pointIndex = content.indexOf(".");
        if (pointIndex == 0) {
            content = "0" + content;
            setText(content);
            pointIndex++;
        }
        int length = s.length();

        if (length - pointIndex > 2 && pointIndex >= 0) {
            content = content.substring(0, pointIndex + 3);
        }
        setText(content);
        setSelection(content.length());
        flag = false;
    }
}
