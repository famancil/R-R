package com.example.famancil.myapplication;

/**
 * Creación de las constantes, en particular las de bases de datos.
 */
public class Cons {
    //Nombre Base de Datos
    public static final String DATABASE_NAME="rr.db";

    //Tabla Usuario
    public static final String USUARIO="Usuario";
    public static final String USUARIO_ID="UsuarioId";
    public static final String NOMBRE_USUARIO="Nombre";
    public static final String EMAIL="Email";
    public static final String UNIVERSIDAD="Universidad";
    public static final String ESTADISTICAS_USUARIO="Estadistica";

    //Tabla Horario
    public static final String HORARIO="Horario";
    public static final String HORARIO_ID="HorarioId";
    public static final String DIA="Dia";
    public static final String FECHA="Fecha";
    public static final String DESCRIPCION="Descripcion";
    public static final String USUARIO_ID_HORARIO="UsuarioId";

    //Tabla ActividadHorario
    public static final String ACTIVIDADHORARIO="ActividadHorario";
    public static final String HORARIO_ID_ACTIVIDADHORARIO="HorarioId";
    public static final String ACTIVIDAD_ID_ACTIVIDADHORARIO="ActividadId";

    //Tabla Actividad
    public static final String ACTIVIDAD="Actividad";
    public static final String ACTIVIDAD_ID="_id";
    public static final String NOMBRE_ACTIVIDAD="Nombre";
    public static final String INICIO="Inicio";
    public static final String TERMINO="Termino";
    public static final String CUMPLIDO="Cumplido";
    public static final String INVARIABLE="Invariable";

    //Tabla Prioridad
    public static final String PRIORIDAD="Prioridad";
    public static final String PRIORIDAD_ID="PrioridadId";
    public static final String ACTIVIDAD_ID_PRIORIDAD="ActividadId";
    public static final String GRADO="Grado";

    public static final String Dia(int Dia)
    {
        switch(Dia)
        {
            case 1: return "Domingo";
            case 2: return "Lunes";
            case 3: return "Martes";
            case 4: return "Miércoles";
            case 5: return "Jueves";
            case 6: return "Viernes";
            case 7: return "Sábado";
            default: return "falló el dia";

        }
    }

    public static final String Mes(int mes)
    {
        switch(mes)
        {
            case 0: return "Enero";
            case 1: return "Febrero";
            case 2: return "Marzo";
            case 3: return "Abril";
            case 4: return "Mayo";
            case 5: return "Junio";
            case 6: return "Julio";
            case 7: return "Agosto";
            case 8: return "Septiembre";
            case 9: return "Octubre";
            case 10: return "Noviembre";
            case 11: return "Diciembre";
            default: return "falló el mes";
        }
    }

}