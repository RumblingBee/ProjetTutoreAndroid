package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Word
{
    private String _word;
    private String _answer;
    private char _sign;

    private ArrayList<String> listWords = new ArrayList<String>();
    private static String listShortWords[] = {"film", "doux", "defi", "flou", "fond", "epis", "epee", "fete", "fil", "gris", "bleu", "loup", "lune", "dent", "chez",
            "cent", "sol", "toi", "roi", "cle", "six", "coq", "dos", "jus", "ici", "lit"};
    private static String listMediumWords[] = {"livre", "epine", "ferme", "finir", "fleur", "drole", "fusee", "froid", "futur", "soupe", "veste", "jeune", "vivre", "pomme"};
    private static String listLongWords[] = {"vendre", "violet", "voisin"};

    public Word(int level)
    {
        switch(level){
            case 1 :
                listWords.addAll(Arrays.asList(listLongWords));
            case 2 :
                listWords.addAll(Arrays.asList(listShortWords));
                listWords.addAll(Arrays.asList(listMediumWords));
            case 3 :
                listWords.addAll(Arrays.asList(listMediumWords));
                listWords.addAll(Arrays.asList(listLongWords));
        }

        Collections.shuffle(listWords);

        this._answer = listWords.get(genererNombre(0, listWords.size() - 1));
        this._sign = genererSigne();
        this._word = codedWord(level, this._sign, this._answer);
    }

    // Codage du mot selon le niveau sélectionné
    private String codedWord(int level, char sign, String word)
    {
        for (char c : word.toCharArray())
        {
            if(level == 1)
                c = codedChar(c, sign, 1);
            else
                c = codedChar(c, sign, 2);
        }
        return word;
    }

    private char codedChar(char c, char sign, int d)
    {
        int ascii = (int)c;
        if(sign == '+')
            ascii = ascii + d;
        else
            ascii = ascii - d;
        return (char)ascii;
    }

    protected int genererNombre(int borneSup, int borneInf)
    {
        int nombre = borneInf + (int)(Math.random() * ((borneSup - borneInf) + 1));
        return nombre;
    }

    protected char genererSigne()
    {
        char signe;
        int cas = genererNombre(1,2);
        switch(cas)
        {
            case 1:
                signe = '+';
                break;
            case 2:
                signe = '-';
                break;
            default:
                signe = '+';
                break;
        }
        return signe;
    }

    public String get_word() {
        return _word;
    }

    public void set_word(String _word) {
        this._word = _word;
    }

    public String get_answer() {
        return _answer;
    }

    public void set_answer(String _answer) {
        this._answer = _answer;
    }
}
