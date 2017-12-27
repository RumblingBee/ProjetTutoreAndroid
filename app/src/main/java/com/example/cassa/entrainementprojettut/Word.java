package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Word
{
    private String _codedWord;
    private String _answer;
    private String _order;

    //3-4
    private static String listShortWords[] = {"film", "doux", "defi", "flou", "fond", "epis", "epee",
            "fete", "fil", "gris", "bleu", "loup", "lune", "dent", "chez", "cent", "sol", "toi",
            "roi", "cle", "six", "coq", "dos", "jus", "ici", "lit", "vis", "noir", "sac", "kiwi",
            "huit", "cube", "robe", "ours", "rue", "bras", "main", "bus", "nez", "rire"};
    //5
    private static String listMediumWords[] = {"livre", "epine", "ferme", "finir", "fleur", "drole",
            "fusee", "froid", "futur", "soupe", "veste", "jaune", "vivre", "pomme", "hiver", "porte",
            "botte", "chaud", "lampe", "voler", "tasse", "renne", "chien", "chat", "avion", "barbe",
            "aigle", "pelle", "lapin", "jambe", "panda", "pieds", "verre", "genou"};
    //6-7
    private static String listLongWords[] = {"vendre", "violet", "voisin", "dauphin", "patate",
            "requin", "baleine", "laitue", "maison", "triangle", "tambour", "sucette", "crayon",
            "poisson", "cercle", "robinet", "fantome", "lunette", "guitare", "canard", "manger",
            "jardin", "volant", "souris", "quatre"};

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
            case 4 :
                listWords.addAll(Arrays.asList(listMediumWords));
                listWords.addAll(Arrays.asList(listLongWords));
                break;
            case 5 :
                listWords.addAll(Arrays.asList(listLongWords));
                break;
        }

        Collections.shuffle(listWords);

        this._answer = listWords.get(genererNombre(0, listWords.size() - 1));
        char sign = genererSigne(level);
        int codeNumber = genererDecalage(level);
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
        if(ascii > 122){
            ascii = ascii - 26;
        }
        else if(ascii < 97){
            ascii = ascii + 26;
        }
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

    private char genererSigne(int level)
    {
        char signe;
        switch(level)
        {
            case 1: case 2: case 3:
                signe = '-';
                break;
            case 4: case 5:
                signe = '+';
                break;
            default:
                signe = '+';
                break;
        }
        return signe;
    }

    private int genererDecalage(int level)
    {
        int decalage;
        switch(level)
        {
            case 1: case 4:
                decalage = 1;
                break;
            case 2: case 5:
                decalage = 2;
                break;
            case 3:
                decalage = 3;
                break;
            default:
                decalage = 1;
                break;
        }
        return decalage;
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
