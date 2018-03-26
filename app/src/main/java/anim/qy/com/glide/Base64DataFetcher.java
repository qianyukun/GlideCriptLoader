package anim.qy.com.glide;

import android.support.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.File;
import java.io.InputStream;

public class Base64DataFetcher implements DataFetcher<InputStream> {

    private final String mFilePath;

    Base64DataFetcher(String model) {
        this.mFilePath = model;
    }

    @Override
    public void loadData(Priority priority, DataCallback<? super InputStream> callback) {
        callback.onDataReady(AESDecryption.instance().getInputStream(new File(mFilePath)));
    }

    @Override
    public void cleanup() {
        // Intentionally empty only because we're not opening an InputStream or another I/O resource!
    }

    @Override
    public void cancel() {
        // Intentionally empty.
    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}