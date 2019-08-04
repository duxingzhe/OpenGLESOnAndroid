package com.luxuan.opengles.sample.filter.filter;

public interface RendererFilter {

    void onSurfaceCreated();

    void onSurfaceChanged(int width, int height);

    void onDrawFrame();

    void onDestroy();
}
