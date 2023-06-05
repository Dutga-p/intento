package myMiniProject;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private Words words;

    private ArrayList<String> palabrasAMostrar = new ArrayList<>();
    private boolean LevelCounter;
    int CurrentLevel, WordsPerLevel, Hits, Counter = 0;
    double HitPercentage;
    private ArrayList<String> CorrectWords = new ArrayList<>();
    private ArrayList<String> WrongWordlist = new ArrayList<>();
    private ArrayList<String> arrayDePalabrasAleatorias = new ArrayList<>();

    /**
     * Constructor of class
     */
    public Controller() {
        CurrentLevel = 1;
        setNivelActual();
    }

    private void setNivelActual() {
        words = new Words();
        Hits = 0;
        if (getApruebaNivel()) {
            CurrentLevel++;
        }
        asignarCantidadPalabrasPorNivel();
        PorcentajesNivel();
        CorrectWords = words.generarPalabrasCorrectas(WordsPerLevel / 2);
        WrongWordlist = words.generarPalabrasIncorrectas(WordsPerLevel / 2);
        miArraydePalabrasAleatorias();
    }

    private void asignarCantidadPalabrasPorNivel() {
        switch (CurrentLevel) {
            case 1-> WordsPerLevel =20;
            case 2-> WordsPerLevel =40;
            case 3-> WordsPerLevel =50;
            case 4-> WordsPerLevel =60;
            case 5-> WordsPerLevel =70;
            case 6-> WordsPerLevel =80;
            case 7-> WordsPerLevel =100;
            case 8-> WordsPerLevel =120;
            case 9-> WordsPerLevel =140;
            case 10-> WordsPerLevel =200;
        }

    }

    private void miArraydePalabrasAleatorias() {

        palabrasAMostrar.addAll(CorrectWords);
        palabrasAMostrar.addAll(WrongWordlist);
        ArrayList<String> auxPalabrasAMostrar = palabrasAMostrar;

        while (auxPalabrasAMostrar.size() > 0) {
            Random random = new Random();
            String palabra = auxPalabrasAMostrar.get(random.nextInt(auxPalabrasAMostrar.size()));
            int auxIndice = auxPalabrasAMostrar.indexOf(palabra);
            arrayDePalabrasAleatorias.add(palabra);
            auxPalabrasAMostrar.remove(auxIndice);
        }
    }

    private void PorcentajesNivel() {
        switch (CurrentLevel) {
            case 1, 2 -> HitPercentage = 0.7;
            case 3 -> HitPercentage = 0.75;
            case 4, 5 -> HitPercentage = 0.8;
            case 6 -> HitPercentage = 0.85;
            case 7, 8 -> HitPercentage = 0.9;
            case 9 -> HitPercentage = 0.95;

        }
    }

    public String getPalabrasMemorizar() {
        String palabraMemorizar = "";
        if (Counter < CorrectWords.size()) {
            palabraMemorizar = CorrectWords.get(Counter);
            Counter++;
        }
        return palabraMemorizar;
    }

    public boolean getApruebaNivel() {
        return LevelCounter;
    }

    public int getNivelActual() {
        return CurrentLevel;
    }
}