package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    EditText etNim, etNama;
    Button bt1;
    public static String TAG = "RV1";
    ArrayList<Mahasiswa> data;
    MahasiswaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        bt1 = findViewById(R.id.bt1);

        data = getData();
        adapter = new MahasiswaAdapter(this, data, mahasiswa -> fillForm(mahasiswa));
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        bt1.setOnClickListener(v -> simpanData());
    }

    public ArrayList<Mahasiswa> getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));

        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            data.add(mhs);
        }
        return data;
    }

    private void fillForm(Mahasiswa mahasiswa) {
        etNim.setText(mahasiswa.nim);
        etNama.setText(mahasiswa.nama);
    }

    private void simpanData() {
        String nim = etNim.getText().toString();
        String nama = etNama.getText().toString();

        if (nim.isEmpty() || nama.isEmpty()) {
            Toast.makeText(this, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else {
            Mahasiswa newMahasiswa = new Mahasiswa();
            newMahasiswa.nim = nim;
            newMahasiswa.nama = nama;
            data.add(newMahasiswa);

            adapter.notifyDataSetChanged();

            etNim.setText("");
            etNama.setText("");

            Toast.makeText(this, "Data disimpan: " + nim + " - " + nama, Toast.LENGTH_SHORT).show();
        }
    }
}
