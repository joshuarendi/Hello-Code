package com.cobaopengl.pengajar.cobaopengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by pengajar-a on 6/1/2016.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer{
    private Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //set warna background frame
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);
        mTriangle = new Triangle();
    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        float color[]={0.0f, 1.0f, 0.0f, 1.0f};
        mTriangle.draw(color);
        float color2[]={1.0f, 0.0f, 0.0f, 1.0f};
        mTriangle.draw(color2);
    }

}
