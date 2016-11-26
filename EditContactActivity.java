package de.davidgollasch.emiexercise2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;





public class EditContactActivity extends AppCompatActivity {

    private EditText
            eTitle,
            eFirstName,
            eLastName,
            eAddress,
            eCity,
            eZip,
            eCountry;

    private Button savebtn;

    public Contact contact;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent fromMain = getIntent();
        contact = (Contact) fromMain.getSerializableExtra("contact");

        InitializeActivity();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //contact = (Contact) intent.getSerializableExtra("contact");
        bundle.get("contact");
        contact = new Contact((String) bundle.get("title"),
                (String) bundle.get("first"),
                (String) bundle.get("last"),
                (String) bundle.get("street"),
                (String) bundle.get("zip"),
                (String) bundle.get("city"),
                (String) bundle.get("country"));
        //readDetails();
    }

    private void InitializeActivity() {

        eTitle = (EditText) findViewById(R.id.eTitle);
        eFirstName = (EditText) findViewById(R.id.eFirstName);
        eLastName = (EditText) findViewById(R.id.eLastName);
        eAddress = (EditText) findViewById(R.id.eAddress);
        eCity = (EditText) findViewById(R.id.eCity);
        eZip = (EditText) findViewById(R.id.eZip);
        eCountry = (EditText) findViewById(R.id.eCountry);

        savebtn  = (Button) findViewById(R.id.savebtn);

        eTitle.setText(contact.getTitle());     //Display of attributes of received instance of Contact
        eFirstName.setText(contact.getFirstName());
        eLastName.setText(contact.getLastName());
        eAddress.setText(contact.getAddress());
        eCity.setText(contact.getCity());
        eZip.setText(contact.getZip());
        eCountry.setText(contact.getCountry());

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChanges(contact);
            }
        });





    }

    private void saveChanges(Contact contact){
        contact.setTitle(eTitle.getText().toString());
        contact.setFirstName(eFirstName.getText().toString());
        contact.setLastName(eLastName.getText().toString());
        contact.setAddress(eAddress.getText().toString());
        contact.setCity(eCity.getText().toString());
        contact.setZip(eZip.getText().toString());
        contact.setCountry(eCountry.getText().toString());

        Intent startEditContactActivity = new Intent(this,EditContactActivity.class);
        startEditContactActivity.putExtra("contact",contact);




        Intent intent = new Intent();

        intent.putExtra("title", contact.getTitle());
        intent.putExtra("first", contact.getFirstName());
        intent.putExtra("last", contact.getLastName());
        intent.putExtra("street", contact.getAddress());
        intent.putExtra("zip", contact.getZip());
        intent.putExtra("city", contact.getCity());
        intent.putExtra("country", contact.getCountry());

        setResult(RESULT_OK, intent);
        finish();


    }


}
