package barisemre.volleygsonexample;

/**
 * Created by barisemre on 24/06/2014.
 */
public interface MyRequestInterface {
    abstract void onSuccessMyRequest(MyResponse response);
    abstract void onErrorMyRequest();
    abstract void onNetworkErrorMyRequest();
}
