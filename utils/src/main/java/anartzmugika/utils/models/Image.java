package anartzmugika.utils.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

/**************************************************
 * Created by anartzmugika on 12/1/17.
 */

public class Image {

    // 'InputStream' bidez sortu 'Bitmap' bueltatzeko 'doInBackground' metodora
    public static Bitmap downloadImage(String url) {

        //Fresh Garbage Collector
        System.gc();

        //Default sample size
        int sample_size = 1;

        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inScaled=true;
        bmOptions.inDensity= 160;
        bmOptions.inPreferredConfig = Bitmap.Config.RGB_565;


        boolean imageSuccessFullyLoad = true;
        boolean image_width_small_than_screen_width = false;
        do
        {
            bmOptions.inSampleSize = sample_size; //Default sample_size = 1

            //url = ConstantValues.URL_LOCALHOST + url.substring(1);
            System.out.println("URL Image: "+ url);

            try {
                stream = getHttpConnection(url);

                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
                System.out.println("Downloaded image");
                //stream.close();

                //Restart
                imageSuccessFullyLoad = true;
                image_width_small_than_screen_width = true;

            }
            catch (OutOfMemoryError oom)
            {
                System.out.println ("Out Of Memory Error..");
                sample_size++;

                System.out.println("Sample size: "+ sample_size);
                imageSuccessFullyLoad = false;
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            catch (Exception eu)
            {
                System.out.println ("Exception");
            }
            System.out.println("Irudia ondo kargatua? "
                    +imageSuccessFullyLoad
                    + "\nTamaina egokitua?"+ image_width_small_than_screen_width+"\n"+
                    "SampleSize: "+sample_size);
        }
        while (!imageSuccessFullyLoad && image_width_small_than_screen_width);

        System.out.println ("Irudiari tamaina zehazten...");


        return bitmap;
    }

    private static InputStream getHttpConnection(String urlString)
            throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                stream = httpConnection.getInputStream();
            }
        }
        catch (SocketTimeoutException e)
        {
            Log.e("SocketTimeoutException", "SocketTimeoutException");
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return stream;
    }

}
