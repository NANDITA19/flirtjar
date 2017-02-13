package utils;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by rutvik on 2/8/2017 at 5:10 PM.
 */

public class TypeFaceUtil
{

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName)
    {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                "font/" + fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface)
    {
        try
        {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
