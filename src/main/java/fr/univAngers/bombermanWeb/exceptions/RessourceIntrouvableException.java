package fr.univAngers.bombermanWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceIntrouvableException extends RuntimeException {
    private String nomRessource;
    private String nomChamp;
    private Object valeurChamp;

    public RessourceIntrouvableException(String nomRessource, String nomChamp, Object valeurChamp) {
        super(String.format("%s introuvable avec  %s: '%s'",nomRessource,nomChamp,valeurChamp));
        this.nomRessource = nomRessource;
        this.nomChamp = nomChamp;
        this.valeurChamp = valeurChamp;
    }

    public String getNomRessource() {
        return nomRessource;
    }

    public String getNomChamp() {
        return nomChamp;
    }

    public Object getValeurChamp() {
        return valeurChamp;
    }
}
