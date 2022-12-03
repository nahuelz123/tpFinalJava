package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.*;

import java.io.*;
import java.util.*;

public class lecturaEscritura {

    private final File fileClient = new File ("clientes.json");
    private final File fileProduct = new File ("productos.json");

    public void grabaClientes (LinkedHashSet<Usuario> clientes){
        LinkedHashSet<Cliente> clientesAux = new LinkedHashSet<>();
        try {
            BufferedWriter escritura = new BufferedWriter(new FileWriter(fileClient));
            Gson gson = new Gson();
            for (Usuario usuarioAux : clientes){
                if (usuarioAux instanceof Cliente){
                    clientesAux.add((Cliente) usuarioAux);
                }
            }
            gson.toJson(clientesAux, LinkedHashSet.class, escritura);
            escritura.flush();
            escritura.newLine();
            escritura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashSet<Usuario> leeClientes (){
        LinkedHashSet<Usuario> usuarios = null;
        try {
            if (fileClient.length() > 0){
                BufferedReader lectura = new BufferedReader(new FileReader(fileClient));
                Gson gson = new Gson();
                Cliente[] clientes = gson.fromJson(lectura, Cliente[].class);
                usuarios = new LinkedHashSet<>(Arrays.asList(clientes));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void grabaProductos (ArrayList<Producto> listadoProductos){
        try {
            BufferedWriter escritura = new BufferedWriter(new FileWriter(fileProduct));
            Gson gson = new Gson();
            gson.toJson(listadoProductos, ArrayList.class, escritura);
            escritura.flush();
            escritura.newLine();
            escritura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Producto> leeProductos () {
        ArrayList<Producto> productos = null;
        try {
            if (fileProduct.length() > 0){
                BufferedReader lectura = new BufferedReader(new FileReader(fileProduct));
                Gson gson = new Gson();
                Producto[] producto = gson.fromJson(lectura, Producto[].class);
                productos = new ArrayList<>(Arrays.asList(producto));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return productos;
    }

}
