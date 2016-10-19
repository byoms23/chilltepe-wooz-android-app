package me.wooz.mobile.android.app.policies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import me.wooz.mobile.android.app.R;

public class AddPolicyActivity extends AppCompatActivity {

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_policy);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.sp_ensurance_company);
        materialDesignSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> ensuranceTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner ensuranceTypeSpinner = (MaterialBetterSpinner)
                findViewById(R.id.sp_ensurance_type);
        ensuranceTypeSpinner.setAdapter(ensuranceTypeAdapter);

        findViewById(R.id.add_policy_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
