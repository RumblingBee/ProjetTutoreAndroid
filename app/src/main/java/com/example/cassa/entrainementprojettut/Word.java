package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Word
{
    private String _codedWord;
    private String _answer;
    private String _order;

    private static String listShortWords[] = {"film", "doux", "defi", "flou", "fond", "epis", "epee",
            "fete", "fil", "gris", "bleu", "loup", "lune", "dent", "chez", "cent", "sol", "toi",
            "roi", "cle", "six", "coq", "dos", "jus", "ici", "lit"};
    private static String listMediumWords[] = {"livre", "epine", "ferme", "finir", "fleur", "drole",
            "fusee", "froid", "futur", "soupe", "veste", "jeune", "vivre", "pomme"};
    private static String listLongWords[] = {"vendre", "violet", "voisin"};

    public Word(int level)
    {
        ArrayList<String> listWords = new ArrayList<>();
        switch(level){
            case 1 :
                listWords.addAll(Arrays.asList(listShortWords));
                break;
            case 2 :
                listWords.addAll(Arrays.asList(listShortWords));
                listWords.addAll(Arrays.asList(listMediumWords));
                break;
            case 3 :
                listWords.addAll(Arrays.asList(listMediumWords));
                listWords.addAll(Arrays.asList(listLongWords));
                break;
        }

        Collections.shuffle(listWords);

        this._answer = listWords.get(genererNombre(0, listWords.size() - 1));
        char sign = genererSigne();
        int codeNumber;
        if(level == 1)
            codeNumber = 1;
        else
            codeNumber = 2;
        this._codedWord = codeWord(codeNumber, sign, this._answer);
        this._order = generateOrder(sign, codeNumber);
    }

    // Codage du mot selon le niveau sélectionné
    private String codeWord(int codeNumber, char sign, String word)
    {
        StringBuilder codedWord = new StringBuilder();
        for (char c : word.toCharArray())
        {
            c = codeChar(c, sign, codeNumber);
            codedWord.append(c);
        }
        return codedWord.toString();
    }

    // Codage de chaque caractère du mot
    private char codeChar(char c, char sign, int codeNumber)
    {
        int ascii = (int)c;
        if(sign == '+')
            ascii = ascii + codeNumber;
        else
            ascii = ascii - codeNumber;
        return (char)ascii;
    }

    private String generateOrder(char sign, int codeNumber)
    {
        String order = "Décale les lettres du mot codé de ";
        if(sign == '+')
            order += "- " + codeNumber;
        else
            order += "+ " + codeNumber;
        if(codeNumber == 1)
            order = order.concat(" lettre dans l'alphabet pour trouver le mot caché");
        else
            order = order.concat(" lettres dans l'alphabet pour trouver le mot caché");
        return  order;
    }

    private int genererNombre(int borneSup, int borneInf)
    {
        return borneInf + (int)(Math.random() * ((borneSup - borneInf) + 1));
    }

    private char genererSigne()
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

    public String get_codedWord() {
        return _codedWord;
    }

    public void set_word(String _codedWord) {
        this._codedWord = _codedWord;
    }

    public String get_answer() {
        return _answer;
    }

    public void set_answer(String _answer) {
        this._answer = _answer;
    }

    public String get_order() {
        return _order;
    }

    public void set_order(String _order) {
        this._order = _order;
    }
}
