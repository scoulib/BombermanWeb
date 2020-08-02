package fr.univAngers.bombermanWeb.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SecurityUtil {

    private SecurityUtil() {}


    public static String chiffrerMotDePasse(String motPasse) {
        return BCrypt.withDefaults().hashToString(8, motPasse.toCharArray());
    }

    public static boolean verifierMotPasse(String motPasse, String bcryptHash) {
        return BCrypt.verifyer().verify(motPasse.toCharArray(), bcryptHash).verified;
    }
}