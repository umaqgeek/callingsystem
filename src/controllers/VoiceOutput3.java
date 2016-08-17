/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

/**
 *
 * @author umarmukhtar
 */
public class VoiceOutput3 {
    
    public static void main(String[] args) throws GoogleAPIException {
        
        GoogleAPI.setHttpReferrer("http://tuffah.info");

    // Set the Google Translate API key
        // See: http://code.google.com/apis/language/translate/v2/getting_started.html
        GoogleAPI.setKey("AIzaSyCSwNtqLGnR3mdQ6-u4JQ26ozZIeInrUOw");

        String translatedText = Translate.DEFAULT.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

        System.out.println(translatedText);
    }
}
