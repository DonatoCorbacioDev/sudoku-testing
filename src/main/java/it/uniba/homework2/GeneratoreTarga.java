package it.uniba.homework2;

import java.util.Random;

public class GeneratoreTarga {

    // Metodo per generare una targa con prefisso personalizzato
    public static String generaTargaConPrefisso(String prefisso, int lunghezzaNumero) {
        // Verifica che il prefisso sia valido
        if (prefisso.length() != 3) {
            throw new IllegalArgumentException("Il prefisso deve essere composto da 3 caratteri.");
        }

        // Verifica che la lunghezza del numero sia valida
        if (lunghezzaNumero < 1) {
            throw new IllegalArgumentException("La lunghezza del numero deve essere almeno 1.");
        }

        // Genera una targa casuale con il prefisso fornito e la lunghezza del numero specificata
        Random random = new Random();
        int min = (int) Math.pow(10, lunghezzaNumero - 1);
        int max = (int) Math.pow(10, lunghezzaNumero) - 1;
        int numeroCasuale = random.nextInt(max - min + 1) + min;
        return prefisso.toUpperCase() + String.format("%0" + lunghezzaNumero + "d", numeroCasuale);
    }
}
