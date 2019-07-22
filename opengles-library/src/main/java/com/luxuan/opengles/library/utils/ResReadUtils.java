package com.luxuan.opengles.library.utils;

import android.content.res.Resources;

import com.luxuan.opengles.library.core.AppCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResReadUtils {


    public static String readResource(int resourceId) {
        StringBuilder builder=new StringBuilder();
        try {
            InputStream inputStream = AppCore.getInstance().getResources().openRawResource(resourceId);
            InputStreamReader streamReader=new InputStreamReader(inputStream);

            BufferedReader bufferedReader=new BufferedReader(streamReader);
            String textLine;
            while((textLine=bufferedReader.readLine())!=null){
                builder.append(textLine);
                builder.append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(Resources.NotFoundException e){
            e.printStackTrace();
        }

        return builder.toString();
    }
}
