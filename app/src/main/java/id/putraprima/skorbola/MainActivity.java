package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MatchActivity.class.getCanonicalName();
    private static  final  int GALLERY_REQUEST_CODE = 1 & 2 ;

    private ImageView logoHome , logoAway;
    private Bitmap bitmap;
    private Intent intent;

    public static final String HOMENAME_KEY = "homeTeam";
    public static final String AWAYNAME_KEY = "awayTeam";
    public static final String HOMELOGO_KEY = "logohome";
    public static final String AWAYLOGO_KEY = "logoaway";

    public EditText hometeamname;
    public EditText awayteamname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hometeamname = findViewById(R.id.home_team);
        awayteamname = findViewById(R.id.away_team);
        logoHome = findViewById(R.id.home_logo);
        logoAway = findViewById(R.id.away_logo);

        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }
    @Override
    public void onActivityResult(int requestCode , int resultCode , @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode , data);
        if (resultCode == 0){
            return;
        }
        if (requestCode == 1){
            if (data != null){
                try{
                    Uri imageUri= data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() , imageUri);
                    logoHome.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this , "Can ' t Load Image" , Toast.LENGTH_SHORT).show();
                }
            }
        }if (requestCode == 2){
            if (data != null){
                try{
                    Uri imageUri= data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    logoAway.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this , "Can ' t Load Image" , Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void HandleProfil(View view){
        String homeTeam = hometeamname.getText().toString();
        String awayTeam = awayteamname.getText().toString();



        if  (!(homeTeam.equals("") && awayTeam.equals(""))){
            if (homeTeam.equals("")){
                Toast.makeText(this, "Enter Home Team Name", Toast.LENGTH_SHORT).show();
            }else if (awayTeam.equals("")){
                Toast.makeText(this, "Enter Away Team Name", Toast.LENGTH_SHORT).show();
            }else if (bitmap == null){
                Toast.makeText(this, "Isikan Gambar", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(this, MatchActivity.class);
                intent.putExtra(HOMENAME_KEY, homeTeam);
                intent.putExtra(AWAYNAME_KEY,awayTeam);

                Bundle extras =new Bundle();
                logoHome.buildDrawingCache();
                Bitmap logohome = logoHome.getDrawingCache();
                extras.putParcelable(HOMELOGO_KEY, logohome);

                logoAway.buildDrawingCache();
                Bitmap logoaway = logoAway.getDrawingCache();
                extras.putParcelable(AWAYLOGO_KEY, logoaway);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }else {
            Toast.makeText(this, "Enter your data", Toast.LENGTH_SHORT).show();
        }
    }
    public void changeImage1(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent , 1);
    }
    public void changeImage2(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

}
