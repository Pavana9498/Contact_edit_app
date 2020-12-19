package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button)findViewById(R.id.btnChild);
        Log.i("main","inside main_activity");
        start.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intentObject = new Intent(MainActivity.this, Child.class); // Setting the intent to go to second activity
                startActivityForResult(intentObject, 1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intentObject)
    {

        Button edit = (Button)findViewById(R.id.btnEdit);
        if (requestCode == 1)  // if the result from same child activity
        {
            Bundle bundleObject = intentObject.getExtras();
            final String name = bundleObject.getString("name");

            if (resultCode == Activity.RESULT_OK ) // name validation : passed
            {
                edit.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Intent intentObject = new Intent(Intent.ACTION_INSERT);   // setting intent for contact insert
                        intentObject.setType(ContactsContract.Contacts.CONTENT_TYPE);
                        Bundle bundleObject = new Bundle();
                        bundleObject.putString(ContactsContract.Intents.Insert.NAME, name);//Inserting name
                        intentObject.putExtras(bundleObject);  // passing name to child Activity using extras
                        startActivity(intentObject);//Intent object starts another activity


                    }
                });
            }
            if (resultCode == Activity.RESULT_CANCELED) // name validation: failed
            {
                edit.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal name provided : "+name;
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration); // toast to handle incorrect message
                        toast.show();
                    }
                });

            }
        }
    }
}

