package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import java.util.ArrayList;
import java.sql.*;

public class DaoArbitros extends DaoBase{

    public ArrayList<Arbitro> listarArbitros() {

        ArrayList<Arbitro> arbitros = new ArrayList<>();

        String query = "SELECT a.idArbitro, a.nombre, a.pais FROM lab9.arbitro a";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                System.out.println("Se ha leido correctamente los datos de la tabla arbitros");

                Arbitro arbitro = new Arbitro();

                arbitro.setIdArbitro(rs.getInt("idArbitro"));
                arbitro.setNombre(rs.getString("nombre"));
                arbitro.setPais(rs.getString("pais"));

                arbitros.add(arbitro);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arbitros;
    }

    public void crearArbitro(Arbitro arbitro) {

        String query = "INSERT INTO lab9.arbitro (nombre, pais) VALUES (?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            //Se configuran los parametros de entrada
            stmt.setString(1, arbitro.getNombre());
            stmt.setString(2, arbitro.getPais());

            //Se ejecuta el insert en la base de datos

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {

        String query = "DELETE FROM lab9.arbitro WHERE idArbitro = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            //Se configuran los parametros de entrada
            stmt.setInt(1, id);

            //Se ejecuta el delete en la base de datos

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
