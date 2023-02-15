
package com.example.versesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText Para, Ayat, search_verse;
    Button btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Para=(EditText) findViewById(R.id.Para);
        Ayat=(EditText) findViewById(R.id.ayat);
        search_verse=(EditText) findViewById(R.id.verse);
        btn_search=(Button) findViewById(R.id.search_btn);

        //When button is clicked

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String para_num= Para.getText().toString();
                String ayat_num=Ayat.getText().toString();


                if(para_num.equals("") || ayat_num.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Provide Details!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    QuranRecord rec=new QuranRecord();
                    int num1 =rec.SSP[Integer.parseInt(para_num)];

                    int resultIndex=num1+Integer.parseInt(ayat_num);

                    if(resultIndex>rec.surahAyatCount[num1])
                    {
                        Toast.makeText(MainActivity.this, "Invalid record!!", Toast.LENGTH_SHORT).show();
                        search_verse.setText("");
                    }
                    else {
                        QuranArabicText Verse = new QuranArabicText();

                        String result_verse = Verse.GetData((resultIndex));
                        search_verse.setText(result_verse);

                        //Clear the text field
                        Para.setText("");
                        Ayat.setText("");
                    }
                }


            }
        });





        //Surah# 2 +verse No(15)
        //SSP[2]+15
        //8+15=23<SurahAyats[2]  //True

        //Surah 1 +verseNO[16]
        //SSP[1]+16
        //1+16>SurahAyats[1]  False


        //Surah 1 +verseNO[6]
        //        //SSP[1]+6
        //        //1+6=7<SurahAyats[1]  True

    }
}