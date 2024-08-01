package spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        return new Faker().regexify("[A-Za-z0-9 ,_-]{10}");
    }

    public static String generateDescription(){
        return new Faker().regexify("[A-Za-z0-9,./_-]{20}");
    }

}
