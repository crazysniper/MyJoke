package application.mybind;

/**
 * Created by Gao on 2018/12/28.
 */

public interface MyUnbinder {
    void unbind();

    MyUnbinder EMPTY = new MyUnbinder() {
        @Override
        public void unbind() {

        }
    };
}
