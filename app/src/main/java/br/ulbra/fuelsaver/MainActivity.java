package br.ulbra.fuelsaver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText edtNome, edtPlaca, edtDist, edtConsumo, edtPreco;
        Button btnCalc;
        TextView txtResposta;

        txtResposta = findViewById(R.id.txtResposta);
        edtNome = findViewById(R.id.edtNome);
        edtPlaca = findViewById(R.id.edtPlaca);
        edtDist = findViewById(R.id.edtDist);
        edtConsumo = findViewById(R.id.edtConsumo);
        edtPreco = findViewById(R.id.edtPreco);
        btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (edtNome.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de Nome não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edtDist.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de Distância não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edtConsumo.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de Consumo não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edtPlaca.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de Placa não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edtPreco.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de Preço não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                }

                double preco, consumo, custo, gas, dist;

                preco = Double.parseDouble(edtPreco.getText().toString());
                consumo = Double.parseDouble(edtConsumo.getText().toString());
                dist = Double.parseDouble(edtDist.getText().toString());

                gas = dist / consumo;
                custo = gas * preco;

                NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                String custoForm = formatoMoeda.format(custo);
                String litrosForm = String.format(Locale.getDefault(), "%.2f", gas);

                String resultado;
                resultado = "Resultado: \n" +
                        "Veículo: " + edtNome.getText() +
                        "\nPlaca: " + edtPlaca.getText() +
                        "\nCombustível necessário: " + litrosForm + " L" +
                        "\nCusto total da viagem: " + custoForm;

                txtResposta.setText(resultado);
            }
        });
    }
}