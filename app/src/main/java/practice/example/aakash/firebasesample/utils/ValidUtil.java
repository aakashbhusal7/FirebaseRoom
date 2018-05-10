package practice.example.aakash.firebasesample.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class ValidUtil {
    public static boolean isValidName(String name){
        Pattern pattern= Pattern.compile("^[a-zA-z]+$");
        return pattern.matcher(name).matches();
    }
    public static boolean isValidPhone(String phone){
        return Patterns.PHONE.matcher(phone).matches();
    }
    public static boolean isValidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
