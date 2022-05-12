/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author benha
 */
public class ControleSaisie {
    private static Matcher matcher;
       private static final String EMAIL_PATTERN= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
          private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    public static boolean isNull(String text){

         if(text.equals("")){

             return true; //null

         }

         return false ;//n'est pas vide

     }
     public static boolean isnum(String text) {



        if (text.matches("^[0-9]+$")&& text.length()== 8) {

            return true;

        } else {

            return false;

        }

    }
       
           public  static boolean validemail( String hex) {

        matcher = pattern.matcher(hex);

        return matcher.matches();

    }
}
