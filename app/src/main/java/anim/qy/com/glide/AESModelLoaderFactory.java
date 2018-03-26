package anim.qy.com.glide;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

public class AESModelLoaderFactory implements ModelLoaderFactory<String, InputStream> {

  @Override
  public ModelLoader<String, InputStream> build(MultiModelLoaderFactory unused) {
    return new AESModelLoader();
  }

  @Override
  public void teardown() { 
    // Do nothing.
  }
}