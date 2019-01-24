package application.okhttpdemo.callback;

import java.io.IOException;

import application.okhttpdemo.bean.ResultBean;

/**
 * Created by Gao on 2019/1/23.
 */

public interface ResultCallBack {

    public void error(IOException e);

    public void success(ResultBean bean);

}
