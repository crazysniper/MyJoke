package application.supportdesignview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import application.supportdesignview.R;

public class TestDoubleClickActivity extends AppCompatActivity {

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_double_click);

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new DebouncingOnClickListener2() {
            @Override
            public void doClick(View v) {
//                ToastUtil.getInstance().showToast(TestDoubleClickActivity.this, "121212");
                startActivity(new Intent(TestDoubleClickActivity.this, ConstraintLayoutActivity.class));
            }
        });
    }


    abstract class DebouncingOnClickListener implements View.OnClickListener {
        boolean enabled = true;

        private final Runnable ENABLE_AGAIN = new Runnable() {
            @Override
            public void run() {
                enabled = true;
            }
        };

        @Override
        public final void onClick(View v) {
            if (enabled) {
                enabled = false;
                v.post(ENABLE_AGAIN);
                doClick(v);
            }
        }

        public abstract void doClick(View v);
    }

    abstract class DebouncingOnClickListener2 implements View.OnClickListener {
        boolean enabled = true;
        long lastTime = -1;

        private final Runnable ENABLE_AGAIN = new Runnable() {
            @Override
            public void run() {
                enabled = true;
            }
        };

        @Override
        public final void onClick(View v) {
            long nowTime = System.currentTimeMillis();
            if (nowTime - lastTime > 500) {
                doClick(v);
                lastTime = nowTime;
            }
        }

        public abstract void doClick(View v);
    }

}
