package myMiniProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Words {
    private ArrayList<String> dictionary;
    private final ArrayList<String> RightWords;
    private final ArrayList<String> WrongWords;

    public Words() {

        dictionary = new ArrayList<>();
        RightWords = new ArrayList<>();
        WrongWords = new ArrayList<>();

        FileManager fileManager = new FileManager();
        dictionary = fileManager.leerPalabras();
    }

    public ArrayList<String> generarPalabrasCorrectas(int nroPalabras) {
        IntStream.range(0, nroPalabras).mapToObj(i -> new Random()).mapToInt(random -> random.nextInt(dictionary.size())).forEachOrdered(auxIndex -> {
            RightWords.add(dictionary.get(auxIndex));
            dictionary.remove(auxIndex);
        });
        return RightWords;
    }

    public ArrayList<String> generarPalabrasIncorrectas(int nroPalabras) {

        IntStream.range(0, nroPalabras).mapToObj(i -> new Random()).mapToInt(random -> random.nextInt(dictionary.size())).forEach(auxIndex -> {
            WrongWords.add(dictionary.get(auxIndex));
            dictionary.remove(auxIndex);
        });
        return WrongWords;
    }
}