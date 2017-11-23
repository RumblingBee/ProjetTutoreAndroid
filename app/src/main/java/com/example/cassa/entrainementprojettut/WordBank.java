package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;

public class WordBank
{
    private ArrayList<Word> _wordList;
    private int _nextWordIndex;

    public WordBank(int level)
    {
        Word w;
        _wordList = new ArrayList<Word>(5);
        for(int i = 0; i < 5; i++)
        {
            w = new Word(level);
            while(_wordList.contains(w))
                w = new Word(level);
            _wordList.add(w);
        }

        _nextWordIndex = 0;
    }

    public Word getWord(int i)
    {
        return _wordList.get(i);
    }
}
