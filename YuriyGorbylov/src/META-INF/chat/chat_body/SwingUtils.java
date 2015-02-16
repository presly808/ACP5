package chat.chat_body;

import java.awt.*;

/**
 * Created by yuriy.gorbylev on 12.02.2015.
 */
public class SwingUtils {

    public static Dimension getScreenCenterSize(int frameWidth, int frameHeight){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (screenSize.width - frameWidth) / 2;
        int locationY = (screenSize.height - frameHeight) / 2;
        return new Dimension(locationX, locationY);
    }
}
