package application.supportdesignview.ui;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.supportdesignview.R;
import application.supportdesignview.util.SupportDesignConstant;

@Route(path = SupportDesignConstant.AsyncTaskActivity)
public class AsyncTaskActivity extends BaseActivity implements View.OnClickListener {

    private Button start, pause, button;
    private TextView textView2, result;
    private MyAsyncTask task;

    @Override
    public int getLayoutId() {
        return R.layout.activity_async_task;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        start = findView(R.id.start);
        pause = findView(R.id.pause);
        button = findView(R.id.button);
        textView2 = findView(R.id.textView2);
        result = findView(R.id.result);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            if (task == null) {
                task = new MyAsyncTask();
                task.execute();
            }
        } else if (v.getId() == R.id.pause) {
            if (task != null) {
                task.cancel(true);
            }
        } else if (v.getId() == R.id.button) {
            if (task != null) {
                LogUtil.e("MyAsyncTask  当前状态是=" + task.isCancelled());
            }
        }
    }


    public class MyAsyncTask extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LogUtil.e("MyAsyncTask  onPreExecute");
        }

        @Override
        protected String doInBackground(Void... params) {
            int i = 0;
            for (i = 0; i < 30; i++) {
                LogUtil.e("MyAsyncTask  doInBackground i=" + i);
                if (isCancelled()) {
                    break;
                }
                publishProgress(i + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return i + "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result.setText(s);
            LogUtil.e("MyAsyncTask  onPostExecute   s=" + s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            LogUtil.e("MyAsyncTask  onProgressUpdate values=" + values[0]);
            textView2.setText(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            LogUtil.e("MyAsyncTask  onCancelled");
            super.onCancelled();
        }


    }

}
