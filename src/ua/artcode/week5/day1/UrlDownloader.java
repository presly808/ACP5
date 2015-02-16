package ua.artcode.week5.day1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by serhii on 16.02.15.
 */
public class UrlDownloader {

    public static final String SPEC = "http://www.ex.ua/86484771?r=28739,23777";

    public static void main(String[] args) throws IOException {
        download(SPEC, "/home/serhii/IdeaProjects/ACP5/temp/image.jpg", true);
    }


    public static void download(String url, String dest, boolean showContent) {
        InputStream is = null;
        try(FileOutputStream fos = new FileOutputStream(dest)){
            URL urlPath = new URL(url);
            is = urlPath.openStream();

            byte[] buff = new byte[10_000];
            int length = 0;
            while((length = is.read(buff)) != -1){
                if(showContent){
                    System.out.print(new String(buff));
                }
                fos.write(buff, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
