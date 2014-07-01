
package ir.home.view.utility;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Common {
    public static Bitmap base64ToBitmap(String base64) {
        if (base64 != null && !base64.isEmpty()) {
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
                    decodedString.length);
            return decodedByte;
        }
        return null;
    }
    
    public static String bitmapToBase64(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object   
        byte[] b = baos.toByteArray();
        return  Base64.encodeToString(b, Base64.DEFAULT);        
    }
}
