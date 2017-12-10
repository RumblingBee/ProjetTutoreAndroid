package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;

public class WordBank
{
    private ArrayList<Word> _wordList;

    public WordBank(int level)
    {
        Word w;
        int j;
        boolean existe;
        _wordList = new ArrayList<Word>(5);
        for(int i = 0; i < 5; i++)
        {
            do{
                w = new Word(level);
                j = 0;
                existe = false;
                while(j < _wordList.size() && !existe){
                    existe = _wordList.get(j).get_answer().equalsIgnoreCase(w.get_answer());
                    j++;
                }
            }while(existe);
            _wordList.add(w);
        }
    }

    public Word getWord(int i)
    {
        return _wordList.get(i);
    }
}
