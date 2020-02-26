package fr.taartopom.megagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    private EditText txtNumber =  null;
    private Button btnSubmit;
    private ProgressBar pgbScore;
    private TextView txtResult;
    private TextView txtHistory;

    private int searchedValue;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById( R.id.tv_title );
        txtNumber = findViewById ( R.id.tv_input_number );
        btnSubmit = findViewById( R.id.btn_submit );
        pgbScore = findViewById( R.id.pgb_score );
        txtResult = findViewById( R.id.tv_result );
        txtHistory = findViewById( R.id.tv_history );

        btnSubmit.setOnClickListener( btnSubmitListener );

        init();

    }

    private void init(){
        score = 0;
        searchedValue = 1 + (int)(Math.random() * 100);
        Log.i("DEBUG", "valeur cherchée: " + searchedValue );

        txtNumber.setText( "" );
        pgbScore.setProgress( score );
        txtResult.setText( "" );
        txtHistory.setText( "" );

        txtNumber.requestFocus();


    }
    private void congratulations(){
        txtResult.setText( R.string.strCongatulation );

        AlertDialog retryAlert =  new AlertDialog.Builder ( this ).create();
        retryAlert.setTitle( R.string.app_name );
        retryAlert.setMessage( getString(R.string.strMessage, score) );

        retryAlert.setButton( AlertDialog.BUTTON_POSITIVE, getString(R.string.strBtnPositif), new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });
        retryAlert.setButton( AlertDialog.BUTTON_NEGATIVE, getString(R.string.strBtnNegatif), new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });

        retryAlert.show();
    }

    private View.OnClickListener btnSubmitListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG","Bouton cliqué");

            String strNumber = txtNumber.getText().toString();
            if ( strNumber.equals( "" ) ) return;

            txtHistory.append( strNumber + "\r\n");
            pgbScore.incrementProgressBy(1);

            int enteredValue = Integer.parseInt( strNumber );
            if ( enteredValue == searchedValue ){
                congratulations();
            }else if ( enteredValue < searchedValue ){
                txtResult.setText( R.string.strTaller );
            }else{
                txtResult.setText( R.string.strSmaller );
            }
            txtNumber.setText( "" );
            txtNumber.requestFocus();
        }
    };
}
