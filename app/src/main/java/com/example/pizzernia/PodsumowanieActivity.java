
package com.example.pizzernia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PodsumowanieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podsumowanie);

        TextView tvRozmiar = findViewById(R.id.tvRozmiar);
        TextView tvCiasto = findViewById(R.id.tvCiasto);
        TextView tvSztuki = findViewById(R.id.tvSztuki);
        TextView tvRazem = findViewById(R.id.tvRazem);
        Button btnPowrot = findViewById(R.id.btnPowrot);

        String rozmiar = getIntent().getStringExtra("rozmiar");

        boolean grubeCiasto = getIntent()
                .getBooleanExtra("grubeCiasto", false);

        int sztuki = getIntent().getIntExtra("sztuki", 1);

        double cena = getIntent().getDoubleExtra("cena", 35.0);

        tvRozmiar.setText("Rozmiar: " + rozmiar);

        if (grubeCiasto) {
            tvCiasto.setText("Ciasto: Grube");
        } else {
            tvCiasto.setText("Ciasto: Cienkie");
        }

        tvSztuki.setText("Sztuk: " + sztuki);

        double suma = cena * sztuki;

        tvRazem.setText(
                String.format(
                        new Locale("pl", "PL"),
                        "Razem: %.2f PLN",
                        suma
                )
        );

        btnPowrot.setOnClickListener(v -> finish());
    }
}
