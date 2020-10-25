package com.example.calcularhipotenusamvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.calcularhipotenusamvvm.databinding.FragmentMiHipotenusaBinding;

public class MiHipotenusaFragment extends Fragment {
    private FragmentMiHipotenusaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMiHipotenusaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MiHipotenusaViewModel miHipotenusaViewModel = new ViewModelProvider(this).get(MiHipotenusaViewModel.class);

        binding.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double catetoA = Double.parseDouble(binding.catetoA.getText().toString());
                double catetoB = Double.parseDouble(binding.catetoB.getText().toString());

                miHipotenusaViewModel.calcular(catetoA, catetoB);
            }
        });

        miHipotenusaViewModel.hipotenusa.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double hipotenusa) {
                binding.hipotenusa.setText(String.format("%.2f",hipotenusa));
            }
        });

        miHipotenusaViewModel.errorCatetoA.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double catetoAMinimo) {
                if (catetoAMinimo != null) {
                    binding.catetoA.setError("El cateto A no puede ser inferor a " + catetoAMinimo + " cm");
                } else {
                    binding.catetoA.setError(null);
                }
            }
        });

        miHipotenusaViewModel.errorCatetoB.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double catetoBMinimo) {
                if (catetoBMinimo != null) {
                    binding.catetoB.setError("El cateto B no puede ser inferior a " + catetoBMinimo + " cm");
                } else {
                    binding.catetoB.setError(null);
                }
            }
        });

        miHipotenusaViewModel.calculando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean calculando) {
                if (calculando) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.hipotenusa.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.hipotenusa.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}