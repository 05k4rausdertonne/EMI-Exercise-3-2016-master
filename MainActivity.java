package de.davidgollasch.emiexercise2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import static android.R.layout.simple_spinner_item;
import static de.davidgollasch.emiexercise2.R.id.textViewAddress;
import static de.davidgollasch.emiexercise2.R.id.textViewCity;
import static de.davidgollasch.emiexercise2.R.id.textViewCountry;
import static de.davidgollasch.emiexercise2.R.id.textViewFirstName;
import static de.davidgollasch.emiexercise2.R.id.textViewLastName;
import static de.davidgollasch.emiexercise2.R.id.textViewTitle;
import static de.davidgollasch.emiexercise2.R.id.textViewZip;

public class MainActivity extends AppCompatActivity {

    private Spinner spContacts;
    private TextView
            tvTitle,
            tvFirstName,
            tvLastName,
            tvAddress,
            tvZip,
            tvCity,
            tvCountry;
    private EditText
            ehTitle,
            ehFirstName,
            ehLastName,
            ehAddress,
            ehZip,
            ehCity,
            ehCountry;
    public List<Contact> contacts = new ArrayList<Contact>();
    private Button  ehbtn,
                    scbtn,
                    editbtn;
    private Contact contactX, contactY;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.tuHausfarbeBlauDunkel)));

        readContactFromFile(/*contacts*/);

        if (contacts.size() == 0) {
        /*Creates contacts Meyer, Stramm, Wennige, Beckmann and Watson*/
            contacts.add(new Contact("Herr", "Robert", "Meyer", "Apfelbergstraße 10", "St. Margrethen", "9430", "Schweiz"));
            contacts.add(new Contact("Frau", "Elisabeth", "Stramm", "Fritz-Konzert-Straße 1a", "Innsbruck", "6020", "Österreich"));
            contacts.add(new Contact("Herr", "Stefan", "Wennige", "Kirchplatz 13", "Wattens", "6112", "Österreich"));
            contacts.add(new Contact("Frau", "Ella", "Beckmann", "Falkenstraße 3", "Dresden", "01067", "Deutschland"));
            contacts.add(new Contact("Frau", "Anne", "Watson", "1 Langdon Park Rd", "London", "N6 5PS", "Vereinigtes Königreich"));

        }

        Contact contact;

        Intent fromMain = getIntent();
        contact = (Contact) fromMain.getSerializableExtra("contact");

        InitializeApp();
    }

    /**
     * Construct the Interactive Structure
     */
    private void InitializeApp() {
        spContacts = (Spinner) findViewById(R.id.spinnerContacts);
        ArrayAdapter adapter = new ArrayAdapter(this,simple_spinner_item, contacts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spContacts.setAdapter(adapter);

        tvTitle = (TextView) findViewById(textViewTitle);
        tvFirstName = (TextView) findViewById(R.id.textViewFirstName);
        tvLastName = (TextView) findViewById(R.id.textViewLastName);
        tvAddress = (TextView) findViewById(R.id.textViewAddress);
        tvZip = (TextView) findViewById(R.id.textViewZip);
        tvCity = (TextView) findViewById(R.id.textViewCity);
        tvCountry = (TextView) findViewById(R.id.textViewCountry);

        ehTitle = (EditText) findViewById(R.id.eTitle);
        ehFirstName = (EditText) findViewById(R.id.eFirstName);
        ehLastName = (EditText) findViewById(R.id.ehLastName);
        ehAddress = (EditText) findViewById(R.id.eAddress);
        ehZip = (EditText) findViewById(R.id.eZip);
        ehCity = (EditText) findViewById(R.id.eCity);
        ehCountry = (EditText) findViewById(R.id.eCountry);

        ehbtn = (Button) findViewById(R.id.ehbtn);
        scbtn = (Button) findViewById(R.id.scbtn);
        editbtn = (Button) findViewById(R.id.editbtn);

        contactX = (new Contact("", "", "", "", "", "", ""));
        contactY = (new Contact("", "", "", "", "", "", ""));






        /*
        Herr Robert Meyer       Frau Elisabeth Stramm       Herr Stefan Wennige
        Apfelbergstraße 10      Fritz-Konzert-Straße 1a     Kirchplatz 13
        9430 St. Margrethen     6020 Innsbruck              6112 Wattens
        Schweiz                 Österreich                  Österreich


        Frau Ella Beckmann      Frau Anne Watson
        Falkenstraße 3          1 Langdon Park Rd
        01067 Dresden           London N6 5PS
        Deutschland             Vereinigtes Königreich
         */


        spContacts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!(view instanceof TextView))
                    return;

                TextView item = (TextView) view;
                String contactName = item.getText().toString();

                /*

                Let's show the contact's details:
                 */
                DisplayContactDetails(contactName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }



        });
        ehbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ehTitle.setText(((TextView) findViewById(textViewTitle)).getText());
                ehFirstName.setText(((TextView) findViewById(textViewFirstName)).getText());
                ehLastName.setText(((TextView) findViewById(textViewLastName)).getText());
                ehAddress.setText(((TextView) findViewById(textViewAddress)).getText());
                ehZip.setText(((TextView) findViewById(textViewZip)).getText());
                ehCity.setText(((TextView) findViewById(textViewCity)).getText());
                ehCountry.setText(((TextView) findViewById(textViewCountry)).getText());

            }

        });


        scbtn.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                if (ehFirstName.getText().equals("First Name")){
                    return;
                }

                for (int i=0; i < contacts.size(); i++) {
                    if (contacts.get(i).toString().equals
                            (contactX.toString())) {
                        contacts.set(i, new Contact((ehTitle.getText().toString()),
                                (ehFirstName.getText().toString()),
                                (ehLastName.getText().toString()),
                                (ehAddress.getText().toString()),
                                (ehCity.getText().toString()),
                                (ehZip.getText().toString()),
                                (ehCountry.getText().toString())));
                        break;

                    }
                }

            }

        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditContactActivity(contactX);
            }
        });



    }

    /**
     * Set the displayed contact details by contact name
     * @param contactName contact's name
     */
    private void DisplayContactDetails(String contactName) {
        for (int i=0; i < contacts.size(); i++){
            if (contactName.equals(contacts.get(i).toString())){

                tvTitle.setText(contacts.get(i).getTitle());
                tvFirstName.setText(contacts.get(i).getFirstName());
                tvLastName.setText(contacts.get(i).getLastName());
                tvAddress.setText(contacts.get(i).getAddress());
                tvZip.setText(contacts.get(i).getZip());
                tvCity.setText(contacts.get(i).getCity());
                tvCountry.setText(contacts.get(i).getCountry());

                contactX.setTitle(contacts.get(i).getTitle());
                contactX.setFirstName(contacts.get(i).getFirstName());
                contactX.setLastName(contacts.get(i).getLastName());
                contactX.setAddress(contacts.get(i).getAddress());
                contactX.setZip(contacts.get(i).getZip());
                contactX.setCity(contacts.get(i).getCity());
                contactX.setCountry(contacts.get(i).getCountry());

                break;
            }

        }

    }

    private  void startEditContactActivity(Contact contact) {

        Intent startEditContactActivity = new Intent(this,EditContactActivity.class);
        startEditContactActivity.putExtra("contact",contact);
        startActivityForResult(startEditContactActivity, 1);

        editContact(contact);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent sendChangedContact) {
        super.onActivityResult(requestCode, resultCode, sendChangedContact);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                contactY.setTitle((String) sendChangedContact.getExtras().get("title"));
                contactY.setFirstName((String) sendChangedContact.getExtras().get("first"));
                contactY.setLastName((String) sendChangedContact.getExtras().get("last"));
                contactY.setAddress((String) sendChangedContact.getExtras().get("street"));
                contactY.setZip((String) sendChangedContact.getExtras().get("zip"));
                contactY.setCity((String) sendChangedContact.getExtras().get("city"));
                contactY.setCountry((String) sendChangedContact.getExtras().get("country"));

                editContact(contactY);

            }
        }
    }

    private void editContact(Contact editedcontact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).toString().equals
                    (contactX.toString())) {

                contacts.set(i, new Contact((editedcontact.getTitle()),
                        (editedcontact.getFirstName()),
                        (editedcontact.getLastName()),
                        (editedcontact.getAddress()),
                        (editedcontact.getCity()),
                        (editedcontact.getZip()),
                        (editedcontact.getCountry())));
                break;

            }

        }

    }

    //write contact information to file
    private void writeContactToFile(List<Contact> contacts) {
        XStream xstream = new XStream();
        String xml = xstream.toXML(contacts);

        try {
            OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput("contacts.xml",
                    this.MODE_PRIVATE));
            writer.write(xml);
            writer.close();
        }
        catch (IOException e) {
            //nothing
        }
    }

    //read contact information from file
    private List<Contact> readContactFromFile(/*List<Contact> contacts*/) {
        XStream xStream = new XStream();
        List<Contact> contacts = new ArrayList<Contact>();
        contacts.clear();
        try {
            contacts = (List<Contact>) xStream.fromXML(this.openFileInput("contacts.xml"));
        }
        catch (java.io.FileNotFoundException e){
            //nothing
        }
        return contacts;
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeContactToFile(contacts);
    }

    @Override
    protected void onStop() {
        super.onStop();
        writeContactToFile(contacts);
    }
}

