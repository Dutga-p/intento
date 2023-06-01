package myProject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader input;
    private BufferedWriter output;
    public static final String usuariosListados = "src/FilesTxt/usuariosListados.txt";
    public static final String bancoDePalabras = "src/myProject/files/bancoDePalabras.txt";
    private ArrayList users,words;
    public void escribirTexto(String linea) {
        try
        {
            fileWriter = new FileWriter(usuariosListados, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                output.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<String> leerPalabras(){
        String PalabrasLeidas = bancoDePalabras;
        users = new ArrayList<>();
        try
        {
            //To allows reading of UTF-8. Unicode and ISO 10646 character encoding format
            fileReader = new FileReader(PalabrasLeidas, StandardCharsets.UTF_8);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
                words.add(line);
                line = input.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }
    public  ArrayList<String> leerUsuarios(){
        String ArchivoLeido = usuariosListados;
        users = new ArrayList<>();
        try
        {
            //To allows reading of UTF-8. Unicode and ISO 10646 character encoding format
            fileReader = new FileReader(ArchivoLeido, StandardCharsets.UTF_8);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
                users.add(line);
                line = input.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;

    }
}
