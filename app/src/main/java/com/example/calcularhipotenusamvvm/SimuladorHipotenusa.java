package com.example.calcularhipotenusamvvm;

public class SimuladorHipotenusa {

    public static class Calculo {
        public double catetoA;
        public double catetoB;

        public Calculo(double catetoA, double catetoB) {
            this.catetoA = catetoA;
            this.catetoB = catetoB;
        }
    }

    interface Callback {
        void cuandoEsteCalculadaLaHipotenusa(double hipotenusa);
        void cuandoHayaErrorDeCatetoAInferiorAlMinimo(double catetoAMinimo);
        void cuandoHayaErrorDeCatetoBInferiorAlMinimo(double catetoBMinimo);
        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();
    }

    public void calcular(Calculo calculo, Callback callback) {
        double catetoAMinimo = 0;
        double catetoBMinimo = 0;

        callback.cuandoEmpieceElCalculo();

        try {
            Thread.sleep(2500);  // Tiempo de espera
            catetoAMinimo = 0.1;
            catetoBMinimo = 0.1;
        } catch (InterruptedException e) {}
        boolean error = false;
        if (calculo.catetoA < catetoAMinimo) {
            callback.cuandoHayaErrorDeCatetoAInferiorAlMinimo(catetoAMinimo);
            error = true;
        }

        if (calculo.catetoB < catetoBMinimo) {
            callback.cuandoHayaErrorDeCatetoBInferiorAlMinimo(catetoBMinimo);
            error = true;
        }

        if(!error) {
            callback.cuandoEsteCalculadaLaHipotenusa(calculo.capital * interes / 12 / (1 - Math.pow(1 + (interes / 12), -solicitud.plazo * 12)));
        }
        callback.cuandoFinaliceElCalculo();
    }
}