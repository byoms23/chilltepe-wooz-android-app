package me.wooz.mobile.android.app.policies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.app.sinisters.SinisterTypeSelectionActivity;

public class PoliciesListActivity extends AppCompatActivity {

    protected static final String TAG_ADD_POLICY = "ADD_POLICY";
    protected static final int CODE_ADD_POLICY = 1;
    private MenuItem menuGoToSinisterTypeSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                AddPolicyDialogFragment.newInstance().show(getSupportFragmentManager(),
//                        TAG_ADD_POLICY);

                Intent intent = new Intent(view.getContext(), AddPolicyActivity.class);
                startActivityForResult(intent, CODE_ADD_POLICY);
            }
        });

        if(savedInstanceState == null) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_policies_list, menu);
        menuGoToSinisterTypeSelection = menu.findItem(R.id.action_go_to_sinister_type_selection);
        menuGoToSinisterTypeSelection.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_to_sinister_type_selection:
                Intent intent = new Intent(this, SinisterTypeSelectionActivity.class);
                startActivityForResult(intent, CODE_ADD_POLICY);
//                TODO
//                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case CODE_ADD_POLICY: {
                switch(resultCode) {
                    case RESULT_OK: {
                        onPolicyAdded(null);
                        break;
                    }
                    default: {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
                }
            }
            default: {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void onPolicyAdded(Uri uri) {
        if(menuGoToSinisterTypeSelection != null) {
            menuGoToSinisterTypeSelection.setVisible(true);
//            menuGoToSinisterTypeSelection.getMenuInfo()..getActionView().performLongClick();
        }
    }
}
