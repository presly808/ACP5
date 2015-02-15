package chat.validators;

/**
 * Created by yuriy.gorbylev on 12.02.2015.
 */
public class NumberValidator implements TextFieldValidator {
    @Override
    public boolean validate(String text) {
        if (text == null || text.equals("")){
            return false;
        }
        try{
            Integer.valueOf(text);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
