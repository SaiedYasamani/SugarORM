package sematecandroidfridays.sugarorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Models.Student;

public class SugarDBForm extends AppCompatActivity {

    EditText name;
    EditText mobile;
    Button save;
    Button show;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_db_form);

        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        save = (Button) findViewById(R.id.save);
        show = (Button) findViewById(R.id.show);
        result  = (TextView) findViewById(R.id.result);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student oModel = new Student();
                oModel.setName(name.getText().toString());
                oModel.setMobile(mobile.getText().toString());
                oModel.save();
                Toast.makeText(SugarDBForm.this,"Data Saved!",Toast.LENGTH_LONG).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> SList = Student.listAll(Student.class);
                for (Student S: SList) {
                    Toast.makeText(SugarDBForm.this,S.getName().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
