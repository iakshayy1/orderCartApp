package com.example.s528772.assignment08;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;
import java.util.Random;

public class OrderActivity extends AppCompatActivity {
    int sharedprice;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(OrderActivity.this);
        sharedprice = sharedPreferences.getInt("price", 1);
        color = sharedPreferences.getInt("color", 1);

        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

    }
    public void getPrice(View v) {

        EditText et1 = (EditText) findViewById(R.id.editText5);
        if (et1.getText().toString().length() > 0) {
            int boxes=Integer.parseInt(et1.getText().toString());
            TextView priceTv = (TextView) findViewById(R.id.textView10);

            priceTv.setText(String.valueOf(sharedprice*boxes));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter the no of boxes",Toast.LENGTH_SHORT ).show();
        }
    }

    public void placeorder(View v){
        EditText nameET = (EditText)findViewById(R.id.editText3);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        EditText boxescountET = (EditText)findViewById(R.id.editText5);
        TextView priceTV = (TextView)findViewById(R.id.textView10);
        String name = nameET.getText().toString();
        String cookietype =  String.valueOf(spinner1.getSelectedItem());
        String nofboxes = boxescountET.getText().toString();
        String totalprice = priceTV.getText().toString();
        Log.d("Test","Test"+totalprice);

        if(name.equals("") || cookietype.equals("") || nofboxes.equals("")){
            Toast.makeText(getApplicationContext(), "Enter the details to proceed", Toast.LENGTH_SHORT).show();
        } else if(totalprice.equals("")){
            Toast.makeText(getApplicationContext(), "First click on get price to place order", Toast.LENGTH_SHORT).show();
        } else {

            OrderInfo orders=new OrderInfo();
            orders.Person_Name=name;
            orders.Cookie_Type=cookietype;
            orders.Boxes_Count=Integer.parseInt(nofboxes);
            orders.Total_Price= Integer.parseInt(totalprice);

            Backendless.Data.of( OrderInfo.class ).save(orders, new AsyncCallback<OrderInfo>() {

                @Override
                public void handleResponse(OrderInfo response) {
                    Log.d("DB","Order placed"+response);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
                }
            });
            Toast.makeText(getApplicationContext(), "Order has been placed", Toast.LENGTH_SHORT).show();
            boxescountET.setText("");
            priceTV.setText("");
        }

    }

    public void printorder(View v){

        IDataStore<OrderInfo> orderStorage = Backendless.Data.of(OrderInfo.class);
        DataQueryBuilder query=DataQueryBuilder.create();
        orderStorage.find(query, new AsyncCallback<List<OrderInfo>>() {

            @Override
            public void handleResponse(List<OrderInfo> response) {
                Log.d("Print : ","Order Details: "+response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
            }
        });
        Toast.makeText(getApplicationContext(), "Printing Order Details", Toast.LENGTH_SHORT).show();

    }

    public void printbyname(View v) {

        EditText nameET = (EditText) findViewById(R.id.editText3);
        String name = nameET.getText().toString();
        if (name.length()>0) {

            IDataStore<OrderInfo> orderStorage = Backendless.Data.of(OrderInfo.class);
            DataQueryBuilder query = DataQueryBuilder.create();
            String value="Person_Name='"+name+"'";
            query.setWhereClause(value);
            orderStorage.find(query, new AsyncCallback<List<OrderInfo>>() {

                @Override
                public void handleResponse(List<OrderInfo> response) {
                    Log.d("Printing : ", "Order Details By Name: " + response);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("MYAPP", "Server reported an error " + fault.getMessage());
                }
            });
            Toast.makeText(getApplicationContext(), "Printing Order Details By Name", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Enter the Name", Toast.LENGTH_SHORT).show();
        }
    }
    public void printbycookie(View v) {


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        String cookietype =  String.valueOf(spinner1.getSelectedItem());
        if (cookietype.length()>0) {

            IDataStore<OrderInfo> orderStorage = Backendless.Data.of(OrderInfo.class);
            DataQueryBuilder query = DataQueryBuilder.create();
            String value="Cookie_Type='"+cookietype+"'";
            query.setWhereClause(value);
            orderStorage.find(query, new AsyncCallback<List<OrderInfo>>() {

                @Override
                public void handleResponse(List<OrderInfo> response) {
                    Log.d("Printing : ", "Order Details By Cookie Type: " + response);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("assignment", "Server reported an error " + fault.getMessage());
                }
            });
            Toast.makeText(getApplicationContext(), "Printing Order Details By Cookie Type", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Enter the Cookie Type", Toast.LENGTH_SHORT).show();
        }
    }

    public void printbycost(View v) {

        EditText costET = (EditText)findViewById(R.id.editText4);
        String cost = costET.getText().toString();

        if (cost.length()>0) {

            IDataStore<OrderInfo> orderStorage = Backendless.Data.of(OrderInfo.class);
            DataQueryBuilder query = DataQueryBuilder.create();
            String value="Total_Price="+Integer.parseInt(cost);
            query.setWhereClause(value);
            orderStorage.find(query, new AsyncCallback<List<OrderInfo>>() {

                @Override
                public void handleResponse(List<OrderInfo> response) {
                    Log.d("assignment : ", "Order Details By Cost: " + response);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("assignment", "Server reported an error " + fault.getMessage());
                }
            });
            Toast.makeText(getApplicationContext(), "Printing Order Details By Cost", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Enter the cost to print orders", Toast.LENGTH_SHORT).show();
        }
    }

}


