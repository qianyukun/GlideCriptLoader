package anim.qy.com.glide;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by Alter on 2018/3/26.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.image);
        AESDecryption.instance().init("AES", "@2#kl$8%1x2datv*", "1234567890123456");

        Glide.with(this)
                .load("/storage/emulated/0/vGallery/Camera/20180208_125238255_342d39a3d9aae87a407f690b7e09188b.jpg")
                .into(imageView);
    }
}
