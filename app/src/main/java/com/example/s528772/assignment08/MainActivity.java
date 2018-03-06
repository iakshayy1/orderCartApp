package com.example.s528772.assignment08;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final int Price = 5;
    public static final int Color = 0;
    EditText et1;
    EditText et2;
    int sharedprice;
    int sharedcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        sharedprice = sharedPreferences.getInt("price", 1);
        sharedcolor = sharedPreferences.getInt("color", 1);
        Log.d("shared ones","shared price: "+sharedprice+" shared color: "+sharedcolor);
        et1=(EditText) findViewById(R.id.editText);
        et2=(EditText) findViewById(R.id.editText2);
        et1.setText(String.valueOf(sharedprice));
        et2.setText(String.valueOf(sharedcolor));
        ConstraintLayout r = (ConstraintLayout) findViewById(R.id.Lid);
        switch (sharedcolor) {
            case 0:
                r.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 1:
                r.setBackgroundColor(getResources().getColor(R.color.Blue));
                break;
            case 2:
                r.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 3:
                r.setBackgroundColor(getResources().getColor(R.color.Green));
                break;
            case 4:
                r.setBackgroundColor(getResources().getColor(R.color.Red));
                break;
            case 5:
                r.setBackgroundColor(getResources().getColor(R.color.Yellow));
                break;
            default:
                Toast.makeText(getApplicationContext(),"Enter the color between 0 and 5",Toast.LENGTH_SHORT ).show();
                break;
        }
        final Intent i = new Intent(this, OrderActivity.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(i);
            }
        });




        Button b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Integer price;
                Integer color;
                SharedPreferences.Editor editor = pref.edit();
                if (et1.getText().toString().length() > 0) {
                    price = Integer.parseInt(et1.getText().toString());
                    Log.d("Test", "price" + price);
                    editor.putInt("price", price);
                    editor.putInt("orderNum",4);

                    if(et2.getText().toString().length()>0) {

                        color = Integer.parseInt(et2.getText().toString());
                        editor.putInt("color", color);
                        editor.commit();
                        if ((price <= 5 || price >= 0) && (color <= 5 || color >= 0)){
                            Log.d("Color : ", "  " + color);
                            ConstraintLayout r = (ConstraintLayout) findViewById(R.id.Lid);
                            switch (color) {
                                case 0:
                                    r.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                    break;
                                case 1:
                                    r.setBackgroundColor(getResources().getColor(R.color.Blue));
                                    break;
                                case 2:
                                    r.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                    break;
                                case 3:
                                    r.setBackgroundColor(getResources().getColor(R.color.Green));
                                    break;
                                case 4:
                                    r.setBackgroundColor(getResources().getColor(R.color.Red));
                                    break;
                                case 5:
                                    r.setBackgroundColor(getResources().getColor(R.color.Yellow));
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(),"Enter the color between 0 and 5",Toast.LENGTH_SHORT ).show();
                                    break;
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Enter the values between 0 and 5",Toast.LENGTH_SHORT ).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please enter value for color",Toast.LENGTH_SHORT ).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter price",Toast.LENGTH_SHORT ).show();
                }
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


