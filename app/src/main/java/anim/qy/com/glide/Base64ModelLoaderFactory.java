package anim.qy.com.glide;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class Base64ModelLoaderFactory implements ModelLoaderFactory<String, InputStream> {

  @Override
  public ModelLoader<String, InputStream> build(MultiModelLoaderFactory unused) {
    return new Base64ModelLoader();
  }

  @Override
  public void teardown() { 
    // Do nothing.
  }
}