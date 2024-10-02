package com.example.thuchanh2; // Thay đổi tên package theo project của bạn

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1; // Request code cho việc chọn ảnh từ thư viện
    private List<Country> countryList; // Danh sách các quốc gia
    private CustomSpinnerAdapter spinnerAdapter; // Adapter cho Spinner
    private ImageView flagImageView; // Để lưu trữ và hiển thị cờ chọn từ gallery

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Thiết lập layout cho activity

        // Khởi tạo danh sách quốc gia
        countryList = new ArrayList<>();
        countryList.add(new Country("USA", R.drawable.usa_flag)); // Thay đổi với cờ quốc gia thực tế
        countryList.add(new Country("France", R.drawable.france_flag)); // Thay đổi với cờ quốc gia thực tế

        // Khởi tạo Spinner và Adapter
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Spinner countrySpinner = findViewById(R.id.countrySpinner);
        spinnerAdapter = new CustomSpinnerAdapter(this, countryList);
        countrySpinner.setAdapter(spinnerAdapter);

        // Nút để thêm quốc gia mới
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button addCountryButton = findViewById(R.id.addCountryButton);
        addCountryButton.setOnClickListener(v -> showAddCountryDialog());
    }

    // Phương thức để hiển thị Dialog thêm quốc gia mới
    @SuppressLint("MissingInflatedId")
    private void showAddCountryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_country, null);

        EditText countryNameEditText = dialogView.findViewById(R.id.countryNameEditText);
        flagImageView = dialogView.findViewById(R.id.flagImageView);
        Button chooseFlagButton = dialogView.findViewById(R.id.chooseFlagButton);

        // Chọn hình từ thư viện (Tùy chọn)
        chooseFlagButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });

        builder.setView(dialogView);
        builder.setTitle("Add New Country");
        builder.setPositiveButton("Add", (dialog, which) -> {
            String countryName = countryNameEditText.getText().toString();
            int flagResource = R.drawable.default_flag; // Mặc định một lá cờ

            // Thêm quốc gia mới vào danh sách
            Country newCountry = new Country(countryName, flagResource);
            countryList.add(newCountry);
            spinnerAdapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            flagImageView.setImageURI(imageUri); // Hiển thị lá cờ chọn từ gallery
            // Lưu trữ imageUri để sử dụng khi thêm quốc gia mới nếu cần
        }
    }
}
