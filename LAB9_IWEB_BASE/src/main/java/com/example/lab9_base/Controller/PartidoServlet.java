package com.example.lab9_base.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

import com.example.lab9_base.Dao.*;
import com.example.lab9_base.Bean.*;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet"})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        request.setCharacterEncoding("UTF-8");

        switch (action) {

            case "guardar":

                System.out.println("Guardando partido");

                //Primero se realizaran las validaciones de los parametros introducidos

                // 1. Recepcion de los datos

                String jornadaStr = request.getParameter("jornada");

                String fecha = request.getParameter("fecha");

                String idLocalStr = request.getParameter("local");

                String idVisitanteStr = request.getParameter("visitante");

                String idArbitroStr = request.getParameter("arbitro");

                // 2. Validacion de parametros no vacios (Usualmente tambien consideraria colocar como obligatorios en el codigo html pero
                // para poder probar que funcione en el backend no se coloco que todos los campos fueran obligatorios en el form html

                if (jornadaStr.isEmpty() || fecha.isEmpty() || idLocalStr.isEmpty() || idVisitanteStr.isEmpty() || idArbitroStr.isEmpty()) {
                    System.out.println("Valor vacio recibido");
                    response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=crear");
                    return;
                }

                System.out.println("Valores ingresados NO vacios");

                // 3. Se convierten los datos no String a sus respectivos tipos de datos

                int jornada = Integer.parseInt(jornadaStr);

                int idLocal = Integer.parseInt(idLocalStr);

                int idVisitante = Integer.parseInt(idVisitanteStr);

                int idArbitro = Integer.parseInt(idArbitroStr);

                //Ahora se verificara que no haya duplicidad de partidos, es decir, que no haya ningun partido que tenga
                //al mismo local contra el mismo visitante durante una misma jornada. Si se tienen 2 equipos A y B entonces estos solo pueden tener
                //hasta 2 partidos juntos, cuando A es local y cuando B es local. Cuando son jornadas diferentes sí puede ocurrir que A juegue contra B
                //otra vez (Se asume esa condicion)

                // 1. Se obtiene los partidos existentes

                DaoPartidos daoPartidos = new DaoPartidos();

                ArrayList<Partido> partidos = daoPartidos.listaDePartidos();

                // 2. Se itera sobre los partidos para detectar si hay repeticion de partidos

                for (Partido partido : partidos) {

                    boolean condicion = partido.getSeleccionLocal().getIdSeleccion() == idLocal && partido.getSeleccionVisitante().getIdSeleccion() == idVisitante && partido.getNumeroJornada() == jornada;

                    if (condicion) {
                        System.out.println("Partido repetido detectado");
                        response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=crear");
                        return;
                    }
                }

                System.out.println("Partido no duplicado, se procedera a registrarlo en la base de datos");

                Partido partidoNuevo = new Partido();

                partidoNuevo.setNumeroJornada(jornada);
                partidoNuevo.setFecha(fecha);
                partidoNuevo.getSeleccionLocal().setIdSeleccion(idLocal);
                partidoNuevo.getSeleccionVisitante().setIdSeleccion(idVisitante);
                partidoNuevo.getArbitro().setIdArbitro(idArbitro);

                daoPartidos.crearPartido(partidoNuevo);

                response.sendRedirect(request.getContextPath() + "/PartidoServlet");

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se obtiene el parametro del request y se declara el Request Dispatcher

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;

        //Se instancia el Dao respectivo

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":

                ArrayList<Partido> partidos = daoPartidos.listaDePartidos();

                request.setAttribute("partidos", partidos);

                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);

                break;
            case "crear":

                //Cargar los datos para los combobox de Seleccion Local, Seleccion visitante y Arbitro

                ArrayList<Seleccion> selecciones = daoSelecciones.listarSelecciones();
                request.setAttribute("selecciones", selecciones);

                ArrayList<Arbitro> arbitros = daoArbitros.listarArbitros();
                request.setAttribute("arbitros", arbitros);

                view = request.getRequestDispatcher("/partidos/form.jsp");
                view.forward(request, response);

                break;

        }

    }
}
