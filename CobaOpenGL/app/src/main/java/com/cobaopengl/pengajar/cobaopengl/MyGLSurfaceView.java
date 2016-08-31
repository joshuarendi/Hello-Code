package com.cobaopengl.pengajar.cobaopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by pengajar-a on 6/1/2016.
 */
public class MyGLSurfaceView extends GLSurfaceView{
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        setRenderer(mRenderer);

    }
}
