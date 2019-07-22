package com.luxuan.opengles.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.util.Log;

public class TextureUtils {

    private static final String TAG="TextureUtils";

    public static int loadTexture(Context context, int resourceId){
        final int[] textureIds=new int[1];
        GLES30.glGenTextures(1, textureIds, 0);
        if(textureIds[0]==0){
            Log.e(TAG, "Could not generate a new OpenGL textureId object.");
            return 0;
        }
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inScaled=false;
        final Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        if(bitmap==null){
            Log.e(TAG, "Resource ID"+ resourceId+" could not be decoded.");
            GLES30.glDeleteTextures(1, textureIds, 0);
            return 0;
        }

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureIds[0]);

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);

        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);

        GLES30.glGenerateMipmap(GLES30.GL_TEXTURE_2D);

        bitmap.recycle();

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0);

        return textureIds[0];
    }
}
