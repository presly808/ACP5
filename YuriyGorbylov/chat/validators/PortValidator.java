package chat.validators;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class PortValidator implements TextFieldValidator {

    @Override
    public boolean validate(String port) {

        if (port == null || port.isEmpty()){
            return false;
        }

        try{
            int portInt = Integer.valueOf(port);
            if (portInt < 1024 || portInt > 65535 ){
                return false;
            }
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }
}
