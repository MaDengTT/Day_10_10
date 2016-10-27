package cn.mds.common_model.base;

/**
 * Created by Administrator on 2016/10/18.
 */

public interface BaseView<T> {
    void showLoading(String title);

    void stopLoading();

    void setPresenter(T presenter);
}
