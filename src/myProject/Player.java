package myProject;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private FileManager fileManager;
    private String userName;
    private ArrayList<String> listaDeJugadores;
    private int posicion;

    public Player(String name){
        userName = name;
        fileManager = new FileManager();
        listaDeJugadores = new ArrayList<>();
        listaDeJugadores = fileManager.leerUsuarios();
        userName = name;
    }

    public void registrarJugador()
    {
        fileManager.escribirTexto(userName + ": " + 0);
        listaDeJugadores.add(userName+ ": "+ 0);
        posicion = listaDeJugadores.size()-1;
    }
    public boolean ExisteJugador(){
        posicion = -1;
        if(listaDeJugadores.isEmpty()){
            for (int i = 0; i < listaDeJugadores.size() && !Objects.equals(listaDeJugadores.get(i), " "); i++)
            {
                String auxJugador = listaDeJugadores.get(i).substring(0, listaDeJugadores.get(i).lastIndexOf(":"));
                if (auxJugador.equals(userName))
                {
                    posicion = i;
                    break;
                }
            }
            if (posicion != -1){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
