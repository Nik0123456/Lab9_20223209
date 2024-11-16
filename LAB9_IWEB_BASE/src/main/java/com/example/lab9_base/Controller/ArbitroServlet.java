package com.example.lab9_base.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import com.example.lab9_base.Dao.*;
import com.example.lab9_base.Bean.*;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        DaoArbitros daoArbitros = new DaoArbitros();
        ArrayList<Arbitro> arbitros = daoArbitros.listarArbitros();

        switch (action) {

            case "buscar":

                String tipo = request.getParameter("tipo");
                String input = request.getParameter("buscar");

                //Tipo siempre va a tener un valor pues el combobox despliega un valor predeteminado asi que
                //hay que validar que el input no este vacio

                if(input.isEmpty()){

                    response.sendRedirect(request.getContextPath() + "/ArbitroServlet?action=lista");

                    return;
                }

                //Ahora toca generar la logica para Nombre y Pais

                ArrayList<Arbitro> arbitrosFiltrados = new ArrayList<>();

                System.out.println("El tipo de busqueda es " + tipo);
                System.out.println("El input es " + input);

                if(tipo.equals("nombre")){
                    for (Arbitro a : arbitros){
                        if(a.getNombre().contains(input)){
                            System.out.println("Ha habido una coincidencia para el nombre " + a.getNombre());
                            arbitrosFiltrados.add(a);
                        }
                    }
                }
                else{ //Cuando tipo = pais
                    for (Arbitro a : arbitros){
                        if(a.getPais().contains(input)){
                            System.out.println("Ha habido una coincidencia para el pais " + a.getPais());
                            arbitrosFiltrados.add(a);
                        }
                    }
                }

                request.setAttribute("arbitros", arbitrosFiltrados);
                request.setAttribute("busqueda", input);
                request.setAttribute("tipo", tipo);

                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);

                break;

            case "guardar":

                System.out.println("Guardando arbitro");

                //Primero se realizaran las validaciones de los parametros introducidos

                // 1. Recepcion de los datos

                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");

                // 2. Validacion de parametros no vacios

                if(nombre.isEmpty() || pais.isEmpty()){

                    System.out.println("Campo vacio detectado");

                    response.sendRedirect(request.getContextPath() + "/ArbitroServlet?action=crear");

                    return;
                }

                //Ahora se verificara que no haya duplicidad de arbitros (Ningun arbitro con el mismo nombre)

                for (Arbitro a : arbitros){

                    if(a.getNombre().equalsIgnoreCase(nombre)){
                        System.out.println("Arbitro repetido");
                        response.sendRedirect(request.getContextPath() + "/ArbitroServlet?action=crear");
                        return;
                    }

                }

                System.out.println("El arbitro no es duplicado, se procedera a registrar en la base de datos");

                Arbitro arbitro = new Arbitro();

                arbitro.setNombre(nombre);
                arbitro.setPais(pais);

                daoArbitros.crearArbitro(arbitro);

                System.out.println("El arbitro se ha guardado satisfactoriamente");

                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        DaoArbitros daoArbitros = new DaoArbitros();

        ArrayList<Arbitro> arbitros = daoArbitros.listarArbitros();

        switch (action) {
            case "lista":

                request.setAttribute("arbitros", arbitros);

                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":

                request.setAttribute("paises", paises);

                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);

                break;
            case "borrar":

                String idStr = request.getParameter("id");

                int id = Integer.parseInt(idStr);

                for (Arbitro a : arbitros){

                    if(a.getIdArbitro() == id){
                        System.out.println("Ha habido una coincidencia para el id " + id);
                        daoArbitros.borrarArbitro(id);
                        response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                        return;
                    }

                }

                System.out.println("No ha habido ninguna coincidencia por lo que no se efectuo un intento de borrado");

                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");

                break;
        }
    }
}
