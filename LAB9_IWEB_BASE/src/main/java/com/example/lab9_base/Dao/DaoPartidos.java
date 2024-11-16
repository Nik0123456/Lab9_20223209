package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.util.ArrayList;
import java.sql.*;

public class DaoPartidos extends DaoBase{

    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();

        String query = "SELECT p.idPartido, p.numeroJornada, p.fecha, sl.nombre as local, sv.nombre as visitante, e.nombre as estadio, a.nombre as arbitro, "
                + "sl.idSeleccion as localId, sv.idSeleccion as visitaId FROM lab9.partido p "
                + "JOIN lab9.seleccion sl on sl.idSeleccion = p.seleccionLocal "
                + "JOIN lab9.estadio e on sl.estadio_idEstadio = e.idEstadio "
                + "JOIN lab9.seleccion sv on sv.idSeleccion = p.seleccionVisitante "
                + "JOIN lab9.arbitro a on a.idArbitro = p.arbitro";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                System.out.println("Se ha leido correctamente los datos de la tabla partidos");

                Partido partido = new Partido();

                partido.setIdPartido(rs.getInt("idPartido"));
                partido.setNumeroJornada(rs.getInt("numeroJornada"));
                partido.setFecha(rs.getString("fecha"));
                partido.getSeleccionLocal().setNombre(rs.getString("local"));
                partido.getSeleccionVisitante().setNombre(rs.getString("visitante"));
                partido.getSeleccionLocal().getEstadio().setNombre(rs.getString("estadio"));
                partido.getArbitro().setNombre(rs.getString("arbitro"));

                partido.getSeleccionLocal().setIdSeleccion(rs.getInt("localId"));
                partido.getSeleccionVisitante().setIdSeleccion(rs.getInt("visitaId"));

                partidos.add(partido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }

    public void crearPartido(Partido partido) {

        String query = "INSERT INTO lab9.partido (numeroJornada, fecha, seleccionLocal, seleccionVisitante, arbitro) " +
                "VALUES (?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            //Se configuran los parametros de entrada
            stmt.setInt(1, partido.getNumeroJornada());
            stmt.setString(2, partido.getFecha());
            stmt.setInt(3, partido.getSeleccionLocal().getIdSeleccion());
            stmt.setInt(4, partido.getSeleccionVisitante().getIdSeleccion());
            stmt.setInt(5, partido.getArbitro().getIdArbitro());

            //Se ejecuta el insert en la base de datos

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
