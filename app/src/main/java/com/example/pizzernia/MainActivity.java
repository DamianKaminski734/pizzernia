
package com.example.pizzernia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rgRozmiar = findViewById(R.id.rgRozmiar);
        CheckBox cbGrubeCiasto = findViewById(R.id.cbGrubeCiasto);
        EditText etLiczbaSztuk = findViewById(R.id.etLiczbaSztuk);
        Button btnPodsumowanie = findViewById(R.id.btnPodsumowanie);

        btnPodsumowanie.setOnClickListener(v -> {

            String tekst = etLiczbaSztuk.getText()
                    .toString().trim();

            int sztuki;

            try {
                sztuki = Integer.parseInt(tekst);
            } catch (NumberFormatException e) {
                etLiczbaSztuk.setError(
                        "Podaj prawidłową liczbę sztuk"
                );
                return;
            }

            if (sztuki <= 0) {
                etLiczbaSztuk.setError(
                        "Liczba sztuk musi być większa od zera"
                );
                return;
            }

            int wybranyId = rgRozmiar.getCheckedRadioButtonId();

            if (wybranyId == -1) {
                Toast.makeText(
                        MainActivity.this,
                        "Wybierz rozmiar pizzy",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            String rozmiar;
            double cena;

            if (wybranyId == R.id.rbMala) {
                rozmiar = "Mała";
                cena = 25.0;
            } else if (wybranyId == R.id.rbSrednia) {
                rozmiar = "Średnia";
                cena = 35.0;
            } else {
                rozmiar = "Duża";
                cena = 45.0;
            }

            boolean grubeCiasto =
                    cbGrubeCiasto.isChecked();

            if (grubeCiasto) {
                cena += 5.0;
            }

            Intent intent = new Intent(
                    MainActivity.this,
                    PodsumowanieActivity.class
            );

            intent.putExtra("rozmiar", rozmiar);
            intent.putExtra("grubeCiasto", grubeCiasto);
            intent.putExtra("sztuki", sztuki);
            intent.putExtra("cena", cena);

            startActivity(intent);
        });
    }
}
