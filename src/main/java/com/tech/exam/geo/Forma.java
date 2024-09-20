package com.tech.exam.geo;

public class Forma {
    public static double calculaAreaTriangulo(double base, double altura) {
        return (base * altura) / 2;
    }

    public static double calculaAreaCirculo(double radio) {
        return Math.PI * radio * radio;
    }
}
