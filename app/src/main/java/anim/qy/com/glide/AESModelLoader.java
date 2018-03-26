package anim.qy.com.glide;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 * Loads an {@link InputStream} from a Base 64 encoded String.
 */
public final class AESModelLoader implements ModelLoader<String, InputStream> {

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(String model, int width, int height, Options options) {
        return new LoadData<>(new ObjectKey(model), new AESDataFetcher(model));
    }

    @Override
    public boolean handles(String model) {
        return true;
    }
}