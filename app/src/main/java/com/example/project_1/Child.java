package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

public class Child extends AppCompatActivity {
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);


        final EditText name = (EditText) findViewById(R.id.name);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                userName = name.getText().toString();
                userName = userName.trim();
                Intent returnIntentObject = new Intent(); //creating return Intent Object
                Bundle bundleObject = new Bundle();
                bundleObject.putString("name", userName);
                returnIntentObject.putExtras(bundleObject); // pass to parent
                if(validateName(userName)){
                    setResult(Activity.RESULT_OK, returnIntentObject);
            }
                else {
                    setResult(Activity.RESULT_CANCELED, returnIntentObject);
            }
                finish();
                return true;
            }
        });
    }

    boolean validateName(String name) // Check for length and letters
    {
        StringTokenizer nameValidation = new StringTokenizer(name);

        if(nameValidation.countTokens()<2)
            return false;

        for(int i = 0; i<name.length(); i++){
            if(!Character.isLetter(name.charAt(i)) && name.charAt(i)!= ' ')
                return false;
        }

        return true;
    }
}
