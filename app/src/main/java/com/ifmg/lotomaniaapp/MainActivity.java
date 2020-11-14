package com.ifmg.lotomaniaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    
    private EditText inicioInterv;
    private EditText fimInterv;
    private EditText quant;
    private Button btnSortear;
    private EditText valoresSorteados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //faz o link entre o java e o xml
    inicioInterv = (EditText)findViewById(R.id.inicioTxt);
    fimInterv = (EditText)findViewById(R.id.fimTxt);
    quant = (EditText)findViewById(R.id.quantidadeTxt);
    btnSortear = (Button)findViewById(R.id.sortearBtn);
    valoresSorteados = (EditText)findViewById(R.id.valoresTxt);

    cadastraEventos();

    }
    private void cadastraEventos(){
        btnSortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!inicioInterv.getText().toString().isEmpty()
                        && !fimInterv.getText().toString().isEmpty()
                && !quant.getText().toString().isEmpty()){


                // leitura dos valores informados pelo user
                int inicio = Integer.parseInt(inicioInterv.getText().toString());
                int fim = Integer.parseInt(fimInterv.getText().toString());
                int qtd = Integer.parseInt(quant.getText().toString());
                //armazena os valores sorteados
                Vector<Integer> valores = new Vector<>();
                Random gerador = new Random();
                // sorteio de valores dentro do intervalo informado pelo user
                while(valores.size() < qtd){
                    int sorteado = gerador.nextInt(fim - inicio + 1 + inicio);

                    if(!valores.contains(sorteado)){
                        valores.add(sorteado);
                    }
                }

                //Ordena os valores sorteados em ordem crescente
                Collections.sort(valores);

                //transforma os valores em uma única string
                String resultado = " ";
                for (int i = 0; i < valores.size(); i++){
                    if(valores.get(i) < 10){
                        resultado += "0";

                    }
                    resultado += valores.get(i) + " ";
                }

                valoresSorteados.setText(resultado);

                } else {
                    //JOptionPane
                    Toast.makeText(MainActivity.this, "É preciso preencher todos os campos", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}