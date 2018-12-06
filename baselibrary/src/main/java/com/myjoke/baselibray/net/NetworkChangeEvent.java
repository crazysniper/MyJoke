package com.myjoke.baselibray.net;

/**
 * Created by Gao on 2018/12/2.
 */

public class NetworkChangeEvent {
    /**
     * 是否存在网络
     */
    public boolean isConnected;

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }

}
