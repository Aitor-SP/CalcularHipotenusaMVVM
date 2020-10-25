package com.example.calcularhipotenusamvvm;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MiHipotenusaViewModel extends AndroidViewModel {

    Executor executor;

    SimuladorHipotenusa simulador;

    MutableLiveData<Double> hipotenusa = new MutableLiveData<>();
    MutableLiveData<Double> errorCatetoA = new MutableLiveData<>();
    MutableLiveData<Double> errorCatetoB = new MutableLiveData<>();
    MutableLiveData<Boolean> calculando = new MutableLiveData<>();

    public MiHipotenusaViewModel(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorHipotenusa();
    }

    public void calcular(double catetoA, double catetoB) {

        final SimuladorHipotenusa.Calculo calculo = new SimuladorHipotenusa.Calculo(catetoA, catetoB);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                simulador.calcular(calculo, new SimuladorHipotenusa.Callback() {
                    @Override
                    public void cuandoEsteCalculadaLaHipotenusa(double hipotenusaResultante) {
                        errorCatetoA.postValue(null);
                        errorCatetoB.postValue(null);
                        hipotenusa.postValue(hipotenusaResultante);
                    }

                    @Override
                    public void cuandoHayaErrorDeCatetoAInferiorAlMinimo(double catetoAMinimo){
                        errorCatetoA.postValue(catetoAMinimo);
                    }

                    @Override
                    public void cuandoHayaErrorDeCatetoBInferiorAlMinimo(double catetoBMinimo){
                        errorCatetoB.postValue(catetoBMinimo);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }
        });
    }
}