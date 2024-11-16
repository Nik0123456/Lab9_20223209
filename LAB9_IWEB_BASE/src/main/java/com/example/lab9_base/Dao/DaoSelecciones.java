package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Seleccion;
import java.util.ArrayList;
import java.sql.*;


public class DaoSelecciones extends DaoBase{

    public ArrayList<Seleccion> listarSelecciones() {

        ArrayList<Seleccion> selecciones = new ArrayList<>();

        String query = "SELECT s.idSeleccion, s.nombre FROM lab9.seleccion s";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                System.out.println("Se ha leido correctamente los datos de la tabla selecciones");

                Seleccion seleccion = new Seleccion();
                seleccion.setIdSeleccion(rs.getInt("idSeleccion"));
                seleccion.setNombre(rs.getString("nombre"));

                selecciones.add(seleccion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return selecciones;
    }

}
