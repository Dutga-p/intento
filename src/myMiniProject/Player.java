package myMiniProject;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private FileManager fileManager;
    private String userName;
    private ArrayList<String> PlayerLists;
    private int position;

    public Player(String name){
        userName = name;
        fileManager = new FileManager();
        PlayerLists = new ArrayList<>();
        PlayerLists = fileManager.leerUsuarios();
        userName = name;
    }

    public void registrarJugador()
    {
        fileManager.escribirTexto(userName + ":" + 0);
        PlayerLists.add(userName+ ":"+ 0);
        position = PlayerLists.size()-1;
    }
    public boolean ExisteJugador(){
        position = -1;
        if(PlayerLists.isEmpty() == false){
            return false;
        }else{
            for (int i = 0; i < PlayerLists.size() && !Objects.equals(PlayerLists.get(i), " "); i++)
            {
                String Jugador = PlayerLists.get(i).substring(0, PlayerLists.get(i).lastIndexOf(":"));
                if (Jugador.equals(userName))
                {
                    position = i;
                    break;
                }
            }
            if (position != -1){
                return true;
            }else{
                return false;
            }
        }
    }
    private int buscarJugador(){
        int position = -1;
        int i = 0;
        while (i < PlayerLists.size() && !Objects.equals(PlayerLists.get(i), " ")) {
            String auxJugador = PlayerLists.get(i).substring(0, PlayerLists.get(i).lastIndexOf(":"));
            if (auxJugador.equals(userName)){
                position=i;
                break;
            }

            i++;
        }
        return position;
    }
    public int getNivelDelJugador(){
        String usuario= PlayerLists.get(buscarJugador());
        String nivelesEnString=usuario.substring(usuario.lastIndexOf("=")+1);
        return Integer.parseInt(nivelesEnString);
    }
    public int setNivelDelJugador(){
        if(getNivelDelJugador()<10){
            fileManager.actualizarNivel(buscarJugador(),getNivelDelJugador()+1);
        }else{
            fileManager.actualizarNivel(buscarJugador(),0);
        }
        return getNivelDelJugador();
    }
}
