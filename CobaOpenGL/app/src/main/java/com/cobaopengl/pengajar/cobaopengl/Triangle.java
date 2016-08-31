package com.cobaopengl.pengajar.cobaopengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by pengajar-a on 6/1/2016.
 */
public class Triangle {
    //Untuk draw a shape harus mendefinisikan:
    //Vertex Shader - Code OPENGL ES  untuk render vertex
    //Fragment Shader - Code OpenGL ES untuk render texture atau color
    //Program - Objek OpenGL ES dengan shader yang di atas
    //OpenGL Shading Language (GLSL)
    private final String vertexShaderCode =
            "attribute vec4 vPosition;"+
                    "void main() {"+
                    " gl_Position = vPosition;"+
                    "}";
    private final String fragmentShaderCode =
            "precision mediump float;"+
                    "uniform vec4 vColor;"+
                    "void main() {"+
                    " gl_FragColor = vColor;"+
                    "}";

    private FloatBuffer vertexBuffer;
    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
            0.0f, 0.6f, 0.0f, //top
            -0.5f, -0.3f, 0.0f, //bottom left
            0.5f, -0.3f, 0.0f //bottom right
    };

    private final int mProgram;

    public Triangle(){
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length*4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        //compile shader code
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        //membuat program OpenGL ES kosong
        mProgram = GLES20.glCreateProgram();
        //add vertex to program
        GLES20.glAttachShader(mProgram, vertexShader);
        //add fragment to program
        GLES20.glAttachShader(mProgram, fragmentShader);
        //membuat executable program OpenGL ES
        GLES20.glLinkProgram(mProgram);
    }

    private int mPositionHandle;
    private int mColorHandle;
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX*4;

    public void draw(float color[]){
        //add program to OpenGL ES Environment
        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram,"vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,false,vertexStride,vertexBuffer);
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle,1,color,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0 , vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
