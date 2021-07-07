package sg.edu.rp.c346.id20045435.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText etDescription, etDate;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        etDescription = findViewById(R.id.editTextDescription);
        etDate = findViewById(R.id.editTextDate);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the activity's context
                DBHelper dbh = new DBHelper(MainActivity.this);

                // Insert a task
                dbh.insertTask("Submit RJ", "25 Apr 2021");
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the activity's context
                DBHelper dbh = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = dbh.getTaskContent();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    txt += i + ". " + data.get(i) + "" + "\n";
                }
                tvResults.setText(txt);

                if (asc) {
                    asc = false;
                }
                else {
                    asc = true;
                }

                al.clear();
                al.addAll(dbh.getTasks(true));
                aa.notifyDataSetChanged();
            }
        });
    }
}