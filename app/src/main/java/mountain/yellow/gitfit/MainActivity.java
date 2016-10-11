package mountain.yellow.gitfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static String SAVE_MESSAGE = "com.example.myfirstapp.SAVE_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, displayInput.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

    public void saveMessage(View view) {
        // Do something in response to button

        String whatToWrite = ((EditText)findViewById(R.id.edit_message)).getText().toString();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("myfile.txt", getApplicationContext().MODE_PRIVATE));
            outputStreamWriter.write(whatToWrite);
            outputStreamWriter.close();
        } catch (IOException e) {

        }
    }

    public void openMessage(View view) {
        // Do something in response to button
        String ret = "we've failed";

        try {
            InputStream inputStream = getApplicationContext().openFileInput("myfile.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                bufferedReader.close();
            }
        }
        catch (Exception e) {
            //Log.e("login activity", "File not found: " + e.toString());
        }

        Intent intent = new Intent(this, displayInput.class);
        String message = ret;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
