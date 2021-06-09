package com.example.ellbooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText registrationID;
    private EditText phoneNumber;
    private EditText schoolYear;
    private EditText dateOfBirth;
    private EditText grade;
    private EditText citizenship;
    private EditText firstLanguage;
    private EditText secondLanguage;
    private EditText catchmentSchool;
    private EditText placedSchool;
    private String email;
    private ProgressBar progressBar;

    private Button btnSubmitUserData;
    private Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        registrationID = (EditText) findViewById(R.id.RegIDInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        schoolYear = (EditText) findViewById(R.id.yearInput);
        dateOfBirth = (EditText) findViewById(R.id.dobInput);
        grade = (EditText) findViewById(R.id.gradeInput);
        citizenship = (EditText) findViewById(R.id.citizenshipInput);
        firstLanguage = (EditText) findViewById(R.id.sLangInput);
        secondLanguage = (EditText) findViewById(R.id.secondLangInput);
        catchmentSchool = (EditText) findViewById(R.id.catchSchoolInput);
        placedSchool = (EditText) findViewById(R.id.placedSchoolInput);
        btnSubmitUserData = (Button) findViewById(R.id.SaveUserData);
        btnBack = (Button) findViewById(R.id.UserBackBtn);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSave);

        Intent incomingIntent = getIntent();
        email = incomingIntent.getStringExtra("email");

        setUserData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSubmitUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUserData();
            }
        });

    }

    private void setUserData() {

        firstName.setText("");
        lastName.setText("");
        registrationID.setText("");
        phoneNumber.setText("");
        schoolYear.setText("");
        dateOfBirth.setHint("dd-mm-yyyy");
        grade.setText("");
        citizenship.setText("");
        firstLanguage.setText("");
        secondLanguage.setText("");
        catchmentSchool.setText("");
        placedSchool.setText("");

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ref.child("users").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "User data doesn't exist", task.getException());
                }
                else {
                    if(task.getResult().getValue() == null) {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                        firstName.setText("");
//                        lastName.setText("");
//                        registrationID.setText("");
//                        phoneNumber.setText("");
//                        schoolYear.setText("");
//                        dateOfBirth.setText("");
//                        grade.setText("");
//                        citizenship.setText("");
//                        firstLanguage.setText("");
//                        secondLanguage.setText("");
//                        catchmentSchool.setText("");
//                        placedSchool.setText("");
                    } else {
                        firstName.setText(String.valueOf(task.getResult().child("firstName").getValue()));
                        lastName.setText(String.valueOf(task.getResult().child("lastName").getValue()));
                        registrationID.setText(String.valueOf(task.getResult().child("registrationID").getValue()));
                        phoneNumber.setText(String.valueOf(task.getResult().child("phoneNumber").getValue()));
                        schoolYear.setText(String.valueOf(task.getResult().child("schoolYear").getValue()));
                        dateOfBirth.setText(String.valueOf(task.getResult().child("dateOfBirth").getValue()));
                        grade.setText(String.valueOf(task.getResult().child("grade").getValue()));
                        citizenship.setText(String.valueOf(task.getResult().child("citizenship").getValue()));
                        firstLanguage.setText(String.valueOf(task.getResult().child("firstLanguage").getValue()));
                        secondLanguage.setText(String.valueOf(task.getResult().child("secondLanguage").getValue()));
                        catchmentSchool.setText(String.valueOf(task.getResult().child("catchmentSchool").getValue()));
                        placedSchool.setText(String.valueOf(task.getResult().child("placedSchool").getValue()));
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    }
                }
            }
        });

    }
    private void SaveUserData() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String firstNameText = firstName.getText().toString().trim();
        String lastNameText = lastName.getText().toString().trim();
        String registrationIDText = registrationID.getText().toString().trim();
        String phoneNumberText = phoneNumber.getText().toString().trim();
        String schoolYearText = schoolYear.getText().toString().trim();
        String dateOfBirthText = dateOfBirth.getText().toString().trim();
        String gradeText = grade.getText().toString().trim();
        String citizenshipText = citizenship.getText().toString().trim();
        String firstLanguageText = firstLanguage.getText().toString().trim();
        String secondLanguageText = secondLanguage.getText().toString().trim();
        String catchmentSchoolText = catchmentSchool.getText().toString().trim();
        String placedSchoolText = placedSchool.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        if(firstNameText.isEmpty()) {
            firstName.setError("First Name is required!");
            firstName.requestFocus();
            return;
        }

        if(lastNameText.isEmpty()) {
            lastName.setError("Last Name is required!");
            lastName.requestFocus();
            return;
        }

        if(registrationIDText.isEmpty()) {
            registrationID.setError("Registration ID is required!");
            registrationID.requestFocus();
            return;
        }

        if(phoneNumberText.isEmpty()) {
            phoneNumber.setError("Phone Number is required!");
            phoneNumber.requestFocus();
            return;
        }
        if(schoolYearText.isEmpty()) {
            schoolYear.setError("School Year is required!");
            schoolYear.requestFocus();
            return;
        }
        if(dateOfBirthText.isEmpty()) {
            dateOfBirth.setError("Date Of Birth is required!");
            dateOfBirth.requestFocus();
            return;
        }
        if(gradeText.isEmpty()) {
            grade.setError("Grade is required!");
            grade.requestFocus();
            return;
        }
        if(citizenshipText.isEmpty()) {
            citizenship.setError("Citizenship is required!");
            citizenship.requestFocus();
            return;
        }
        if(firstLanguageText.isEmpty()) {
            firstLanguage.setError("Spoken Language is required!");
            firstLanguage.requestFocus();
            return;
        }
//        if(secondLanguageText.isEmpty()) {
//            secondLanguageText = "null"
//        }
        if(catchmentSchoolText.isEmpty()) {
            catchmentSchool.setError("CatchmentSchool is required!");
            catchmentSchool.requestFocus();
            return;
        }
//        if(placedSchoolText.isEmpty()) {
//            placedSchool.setError("Email is required!");
//            placedSchool.requestFocus();
//            return;
//        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("firstName", firstNameText);
        map.put("lastName", lastNameText);
        map.put("registrationID", registrationIDText);
        map.put("phoneNumber", phoneNumberText);
        map.put("schoolYear", schoolYearText);
        map.put("dateOfBirth", dateOfBirthText);
        map.put("grade", gradeText);
        map.put("citizenship", citizenshipText);
        map.put("firstLanguage", firstLanguageText);
        map.put("secondLanguage", secondLanguageText);
        map.put("catchmentSchool", catchmentSchoolText);
        map.put("placedSchool", placedSchoolText);

        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).updateChildren(map);

        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
