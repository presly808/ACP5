package chat.validators;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class NickValidator implements TextFieldValidator {
    @Override
    public boolean validate(String nick) {
        if (nick == null || nick.isEmpty()){
            return false;
        }

        if (nick.length() > 16){
            return false;
        }

        return true;
    }
}
