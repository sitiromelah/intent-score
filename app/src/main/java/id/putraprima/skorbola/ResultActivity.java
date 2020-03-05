package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        hasil = findViewById(R.id.textView3);

        Bundle Extras = getIntent().getExtras();
        if (Extras != null){
            String win = getIntent().getExtras().getString("winner");
            hasil.setText(win);
        }
    }
}
