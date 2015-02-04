package chat.validators;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class IPValidator implements TextFieldValidator {
    @Override
    public boolean validate(String ip) {
        if (ip == null || ip.isEmpty()){
            return false;
        }

        String[] parts = ip.split("\\.");
        if (parts.length != 4){
            return false;
        }

        if(ip.endsWith(".")) {
            return false;
        }

        for (String str: parts) {
            try{
                int i = Integer.valueOf(str);
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        }

        return true;
    }
}
