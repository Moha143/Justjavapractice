package com.example.moha.omar.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
    }

    public void submitOrder(View view) {

        EditText namefield = (EditText)findViewById(R.id.name_field );
        String name = namefield.getText().toString();
        //    Log.v ("MainActivity", "Name:  "+name );
        CheckBox wippedCream=(CheckBox)  findViewById (R.id.wipped_cream_checkbox );

        boolean haswhippedcream= wippedCream.isChecked () ;
        //  Log.v ("MainActivity","has Whipped"+haswhippedcream ) ;
        CheckBox chocolateCheckBox = (CheckBox)  findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        //    Log.v ("MainActivity","has Chocolate"+hasChocolate  ) ;


        int price=calculatePrice (haswhippedcream ,hasChocolate ) ;
        String pricemessage=createOrderSummary (name,price ,haswhippedcream,hasChocolate  );
        Intent intent = new Intent (Intent.ACTION_SENDTO) ;
        intent.setData (Uri.parse ("mailto:")) ;
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java for "+name );
        if(intent.resolveActivity (getPackageManager () )!=null)
        {

            startActivity (intent) ;
        }

        displayMessage(pricemessage);
    }
    private String createOrderSummary(String name, int price , boolean addwhippedcream,boolean addChocolate)
    {
        String pricemessage="Name: "+name +"\n";
        pricemessage += "Add whipped Cream? "+addwhippedcream+"\n" ;
        pricemessage +="Add chocolate?"+addChocolate ;
        pricemessage =pricemessage + "\nQuantity:"+quantity;
        pricemessage =pricemessage  +  "\nTotal Price $" + price ;
        pricemessage = pricemessage+"\nThank you";
        return pricemessage ;

    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean addwhippedcream, boolean addChocolate ) {
        int baseprice=5;
        if(addwhippedcream )
        {
            baseprice =baseprice +1;

        }
        if(addChocolate )
        {
            baseprice =baseprice +2;
        }
        return quantity *baseprice ;
    }

    public void display (int number)
    {
        TextView quantityTextView= findViewById(R.id.quantity_view);
        quantityTextView.setText("" + number);

    }

    public void increment(View view) {
        if(quantity ==100)
        {
            Toast.makeText (this, "you cannot make more than 100 coffeas", Toast.LENGTH_SHORT).show ();
            return ;
        }
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity ==1)
        {
            Toast.makeText (this, "you cannot make less than 1 coffeas", Toast.LENGTH_SHORT).show ();
            return ;
        }
        quantity=quantity-1;
        display(quantity);
    }

    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.Order_summary_text_VIEW );
        OrderSummaryTextView .setText(message);
    }





}