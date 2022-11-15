package com.example.exo3_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.conjuguerBtn);
        EditText verbeView = findViewById(R.id.verbe);
        TextView outputView = findViewById(R.id.output);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verbe = verbeView.getText().toString(),
                        res = conjuguer(verbe);

                if(!res.matches("")){
                    Toast.makeText(MainActivity.this, "félicitation !", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Ne corresponds pas à un verbe de 1er groupe !", Toast.LENGTH_SHORT).show();
                }
                outputView.setText(res);
            }
        });
    }

    public String conjuguer(String verbe){
        String res = "";
        String[] terminisons = {"e","es","e","ons","ez","ent"};
        String[] pronoms = {"je", "tu", "il/elle/on", "nous", "vous", "ils/elles"};

        // je vais utiliser le RegEx ((radical = groupe de 2lettre et plus) + er + et pas le verbe aller)
        Pattern pattern = Pattern.compile("(\\w{2,})er| !(aller)");
        Matcher matcher = pattern.matcher(verbe);

        if(matcher.matches()){
            String radical = matcher.group(1);
            for (int i = 0; i < pronoms.length ; i++) {
                res = res+pronoms[i]+" "+radical+terminisons[i]+"\n";
            }
        }
        return res;
    }
}