/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int noOfCoffees = 2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void add(View view1){
        if(noOfCoffees ==1){
            
        }
        noOfCoffees += 1;
        display(noOfCoffees);
    }
    public void subtract(View view2){
        if(noOfCoffees == 0){
            display(noOfCoffees = 0) ;
        }
        else{
        noOfCoffees -= 1 ;
        display(noOfCoffees); }
    }

    public void submitOrder(View view) {



        EditText text = (EditText) findViewById(R.id.album_description_view) ;
        String name = text.getText().toString() ;

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.notify_me_checkbox) ;
        boolean hasWippedCream =  whippedCreamCheckBox.isChecked() ;

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked() ;

        int price = calculatePrice(hasWippedCream,hasChocolate);
        String priceMessage = createOrderSummary(name,hasWippedCream,hasChocolate,price) ;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for"+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        }




    private int calculatePrice(boolean addWhippedCream,boolean addChocolate){

        int basePrice = 5 ;

        if(addWhippedCream){
            basePrice = basePrice+1 ;
        }
        if(addChocolate){
            basePrice = basePrice +2 ;
        }
        return noOfCoffees * basePrice ;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method displays the given text on the screen.
     */

    private String createOrderSummary(String name, boolean hasWippedCream,boolean hasChocolate,int Price){
       String abc = "Name: "+ name + "\n"+"Add whipped cream? "+ hasWippedCream +"\n"+"Add Chocolate? "+hasChocolate +"\n"+"Quantity: "+noOfCoffees +"\n" +"Total: $" + Price + "\n" + "Thank You!" ;
        return abc ;
    }

}
