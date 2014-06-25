package barisemre.volleygsonexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class MyActivity extends Activity implements MyRequestInterface {
    private TextView dateTV;
    private MyRequest myRequest;
    private NetworkImageView imageView;
    private ImageLoader mImageLoader;
    private static final String IMAGE_URL="https://lh3.googleusercontent.com/-tUnSh4hL1b0/AAAAAAAAAAI/AAAAAAAAjNI/rZa8b8zsF1A/photo.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mImageLoader = AppManager.volleyImageLoader.getImageLoader();
        dateTV = (TextView)findViewById(R.id.dateTV);
        imageView = (NetworkImageView)findViewById(R.id.imageView);
        imageView.setImageUrl(IMAGE_URL,mImageLoader);
        myRequest = new MyRequest(this);
        myRequest.request();

    }


    @Override
    public void onSuccessMyRequest(MyResponse response) {
        dateTV.setText(response.getDate());
    }

    @Override
    public void onErrorMyRequest() {
        Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkErrorMyRequest() {
        Toast.makeText(this,"NETWORK_ERROR",Toast.LENGTH_SHORT).show();
    }
}
