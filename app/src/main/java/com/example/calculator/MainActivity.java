package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Declarando as variaveis
    private Button number0, number1, number2, number3, number4, number5, number6, number7, number8,
    number9, dot, sum, sub, mult, div, equal, clearButton;

    private TextView txtExpression, txtResult;

    private ImageView backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartComponents();
        getSupportActionBar().hide();

//      Setando o valor do número clicado na classe
        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        sum.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        mult.setOnClickListener(this);
        dot.setOnClickListener(this);

//      Método para zerar o visor e o resultado
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtExpression.setText("");
                txtResult.setText("");
            }
        });

//      Método para deletar apenas o último número digitado
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView expression = findViewById(R.id.txt_calculations);
                String string = expression.getText().toString();

                if(!string.isEmpty()){

                    byte var0 = 0;
                    int num_deleted = string.length()-1;
                    String txtExpression = string.substring(var0, num_deleted);
                    expression.setText(txtExpression);
                }

                txtResult.setText("");
            }
        });

//      Método igual fara as opreções necessarias usando o metodo evaluate
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Expression expression = new ExpressionBuilder(txtExpression.getText().toString()).build();
                double result = expression.evaluate();
                long longresult = (long) result;

//         convertendo o resultado em string dependendo do valor decimal ou inteiro(long ou result)
                if (result == (double)longresult){
                    txtResult.setText((CharSequence) String.valueOf(longresult));
                }else{
                    txtResult.setText((CharSequence) String.valueOf(result));
                }
            }
        });

    }


//    Pegando os ids da activity main
    private void StartComponents(){
        number0 = findViewById(R.id.number_0);
        number1 = findViewById(R.id.number_1);
        number2 = findViewById(R.id.number_2);
        number3 = findViewById(R.id.number_3);
        number4 = findViewById(R.id.number_4);
        number5 = findViewById(R.id.number_5);
        number6 = findViewById(R.id.number_6);
        number7 = findViewById(R.id.number_7);
        number8 = findViewById(R.id.number_8);
        number9 = findViewById(R.id.number_9);
        dot = findViewById(R.id.dot);
        sum = findViewById(R.id.sum);
        sub = findViewById(R.id.sub);
        mult = findViewById(R.id.multiplication);
        div = findViewById(R.id.division);
        equal = findViewById(R.id.equal);
        txtExpression = findViewById(R.id.txt_calculations);
        txtResult = findViewById(R.id.txt_resuls);
        backspace = findViewById(R.id.backspace);
    }

//    Função para acrescentar no visor o valor digitado
    public void ExpressionIncrease(String string, boolean clear_data){

        if(txtResult.getText().equals("")){
            txtExpression.setText(" ");
        }

        if(clear_data){
            txtResult.setText(" ");
            txtExpression.append(string);
        }else{
            txtExpression.append(txtResult.getText());
            txtExpression.append(string);
            txtResult.setText(" ");
        }
    }

//  Função para capturar o valor que o usuário clicou e enviar para o visor
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.number_0:
                ExpressionIncrease( "0",true);
                break;
            case R.id.number_1:
                ExpressionIncrease("1", true);
            case R.id.number_2:
                ExpressionIncrease("2", true);
            case R.id.number_3:
                ExpressionIncrease("3", true);
            case R.id.number_4:
                ExpressionIncrease("4", true);
            case R.id.number_5:
                ExpressionIncrease("5", true);
            case R.id.number_6:
                ExpressionIncrease("6", true);
            case R.id.number_7:
                ExpressionIncrease("7", true);
            case R.id.number_8:
                ExpressionIncrease("8", true);
            case R.id.number_9:
                ExpressionIncrease("9", true);
            case R.id.dot:
                ExpressionIncrease(".", true);
            case R.id.sum:
                ExpressionIncrease("+", false);
            case R.id.sub:
                ExpressionIncrease("-", false);
            case R.id.multiplication:
                ExpressionIncrease("*", false);
            case R.id.division:
                ExpressionIncrease("/", false);
        }
    }
}