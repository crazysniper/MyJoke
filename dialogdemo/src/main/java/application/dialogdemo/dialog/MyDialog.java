package application.dialogdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import application.dialogdemo.R;

/**
 * Created by Gao on 2018/12/7.
 */

public class MyDialog extends Dialog {
    private Context context;
    private MyDialogListener.OnCancelledListener cancelledListener;
    private MyDialogListener.OnClickListener clickListener;

    protected MyDialog(Context context) {
        super(context);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(Builder builder) {
        super(builder.context, builder.themeResId);
        this.context = builder.context;
//        View view = LayoutInflater.from(builder.context).inflate(R.layout.dialog_main, null);
        setContentView(R.layout.dialog_main);
        setCancelable(builder.cancelable);
        setCanceledOnTouchOutside(builder.outSideCancelable);

        TextView titleView = findViewById(R.id.title);
        TextView positiveView = findViewById(R.id.positive);
        TextView negativeView = findViewById(R.id.negative);
        TextView contentView = findViewById(R.id.content);

        titleView.setText(builder.title);
        contentView.setText(builder.content);
        positiveView.setText(builder.positiveButtonText);
        negativeView.setText(builder.negativeButtonText);

        setOnCancelListener(builder.cancelledListener);
        setPositiveListener(builder.clickListener);

        negativeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelledListener != null) {
                    cancelledListener.onCancel();
                }
                dismiss();
            }
        });

        positiveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick();
                }
                dismiss();
            }
        });
    }

    public void setOnCancelListener(MyDialogListener.OnCancelledListener cancelledListener) {
        this.cancelledListener = cancelledListener;
    }

    public void setPositiveListener(MyDialogListener.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class Builder {
        private Context context;
        private int themeResId;
        private boolean cancelable;
        private boolean outSideCancelable;
        private String title;
        private String content;
        private String positiveButtonText;
        private String negativeButtonText;
        private MyDialogListener.OnCancelledListener cancelledListener;
        private MyDialogListener.OnClickListener clickListener;

        public Builder build(Context context) {
            this.context = context;
            return this;
        }

        public Builder(Context context, int themeResId) {
            this.context = context;
            this.themeResId = themeResId;
        }

        public Builder setThemeResId(int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean outSideCancelable) {
            this.outSideCancelable = outSideCancelable;
            return this;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, final MyDialogListener.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.clickListener = listener;
            return this;
        }

        public Builder setPositiveButton(int textId, final MyDialogListener.OnClickListener listener) {
            this.clickListener = listener;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, final MyDialogListener.OnCancelledListener cancelledListener) {
            this.negativeButtonText = negativeButtonText;
            this.cancelledListener = cancelledListener;
            return this;
        }


        public Builder setNegativeButton(int textId, final MyDialogListener.OnCancelledListener cancelledListener) {
            this.cancelledListener = cancelledListener;
            return this;
        }

        public MyDialog create() {
            return new MyDialog(this);
        }

        public MyDialog show() {
            MyDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }
}
