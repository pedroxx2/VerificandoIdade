// Pedro Henrique Fortunato Silva Mariano

package Victor.com.app_verifica_a_idade;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

//    private Button btnVerificaIdade;
      private EditText data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnVerificaIdade = findViewById(R.id.btnVerificaIdade);

    }

    public void CalculaIdade(View v) {
        try {



            data = findViewById(R.id.data);
            String dataText = data.getText().toString();
            Date date = parseDate(dataText, "dd/MM/yyyy");

            int idade = calculaIdade(date);

            String resposta = situacaoIdade(idade);

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("O resultado é: ");
            alertDialog.setMessage(resposta);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        } catch (Exception ex) {

        }

    }

    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

    public static int calculaIdade(java.util.Date dataNasc) {

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(dataNasc);
        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        }
        else
        {
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade--;
            }
        }

        return idade;
    }

    private String situacaoIdade(int calculo) {
        String estatos = null;

        if (calculo <= 12) {
            estatos = "Você aé uma criança!";
        } else if (calculo >= 13 && calculo <= 18) {
            estatos = "Você é um adolescente!";
        } else if (calculo > 18) {
            estatos = "Você é adulto!";
        }

        return estatos;
    }
}