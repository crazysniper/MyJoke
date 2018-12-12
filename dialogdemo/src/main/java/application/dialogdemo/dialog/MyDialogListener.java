package application.dialogdemo.dialog;

/**
 * Created by Gao on 2018/12/7.
 */

public interface MyDialogListener {

    interface OnCancelledListener {
        public void onCancel();
    }

    interface OnClickListener {
        public void onClick();
    }

}
