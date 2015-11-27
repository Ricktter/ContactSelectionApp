package com.example.dell.contactsselectionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactIntentActivity extends AppCompatActivity {

    private final int PHONE = 0;
    private final int WEBSITE = 1;
    private ListView intentListView;
    private ArrayAdapter<String> adapter;
    private List<ContactObject> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_intent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        intentListView = (ListView) findViewById(R.id.listView1);

        //initialize the list and add item
        contactsList = new ArrayList<ContactObject>();
        contactsList.add(new ContactObject("Android One", "111-1111-1111", "www.androidATC.com"));
        contactsList.add(new ContactObject("Android Two", "222-2222-2222", "www.androidATC.com"));
        contactsList.add(new ContactObject("Android Three", "333-3333-3333", "www.androidATC.com"));
        contactsList.add(new ContactObject("Android Four", "444-4444-4444", "www.androidATC.com"));

        List<String> listName = new ArrayList<String>();
        for (int i = 0; i < contactsList.size(); i++) {
            listName.add(contactsList.get(i).getName());
        }
        //initialize the arrayAdapter object
        adapter = new ArrayAdapter<String>(ContactIntentActivity.this,
                android.R.layout.simple_list_item_1, listName);
        //Set the adapter of the listView
        intentListView.setAdapter(adapter);

        intentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ContactIntentActivity.this, ContactPageActivity.class);
                i.putExtra("Object", contactsList.get(position));
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_intent, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Bundle resultData = data.getExtras();
        String value = resultData.getString("value");
        switch (resultCode) {
            case PHONE:
                //Implicit intent to make a call
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + value)));
                break;
            case WEBSITE:
                //implicit intent to visit website
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + value)));
                break;
        }
    }
}
