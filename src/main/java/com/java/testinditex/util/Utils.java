package com.java.testinditex.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Clase con funciones de utilidad
 */
public class Utils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

    /**
     * Funcion para comprobar si una lista esta vacia o es nula
     *
     * @param list Una lista de objetos
     * @return True en caso de estar vacia, false en caso de no estarlo
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Metodo para comprobar si una fecha es valida
     *
     * @param date Fecha en formato string
     * @return True en caso de ser una fecha valida, false en cualquier otro caso
     */
    public static boolean isValidDate(String date) {
        sdf.setLenient(false); // Establece el modo estricto
        try {
            sdf.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
