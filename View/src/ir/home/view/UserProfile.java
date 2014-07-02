
package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.view.utility.Common;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserProfile extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText status;
    private Button changePassword;
    private Button update;
    private ImageView userPicture;
    private TbUser currentUser;
    private static final int REQUEST_PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        firstName = (EditText) findViewById(R.id.userprofile_edittext_UserFirstName);
        lastName = (EditText) findViewById(R.id.userprofile_edittext_UserLastName);
        email = (EditText) findViewById(R.id.userprofile_edittext_UserEmail);
        status = (EditText) findViewById(R.id.userprofile_edittext_UserStatus);

        initUserPicture();

        initGetUserInformation();

        initChangePassword();

        initUpdateUserInformation();

    }

    private void initUserPicture() {
        userPicture = (ImageView) findViewById(R.id.userprofile_imageView_UserPicture);
        userPicture.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent pickImageIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickImageIntent, REQUEST_PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_IMAGE) {
            if (RESULT_OK == resultCode) {
                Uri imageUri = data.getData();
                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    userPicture.setImageBitmap(bitmap);                    
                    currentUser.setPicture(Common.bitmapToBase64(bitmap));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initChangePassword() {
        changePassword = (Button) findViewById(R.id.userprofile_button_changePassword);
        changePassword.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ChangePassword.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });
    }

    private void initGetUserInformation() {

        SharedPreferences sp = this.getSharedPreferences(
                "UserInformation", MODE_PRIVATE);

        int UserIdP = Integer.parseInt(sp.getString("UserId", "0"));
        if (UserIdP <= 0) {
            Toast.makeText(getBaseContext(),
                    "ÔäÇÓå ˜ÇÑÈÑ äÇãÚÊÈÑ ÇÓÊ",
                    Toast.LENGTH_LONG).show();
            return;
        }
        UserController controller = new UserController();
        try {
            currentUser = controller.getProfile(UserIdP);

            if (currentUser != null) {
                firstName.setText(currentUser.getFirstName());
                lastName.setText(currentUser.getLastName());
                status.setText(currentUser.getStatus());
                email.setText(currentUser.getEmail());

                String base64 = currentUser.getPicture();

                userPicture.setImageBitmap(Common.base64ToBitmap(base64));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (HabehException e) {
            Toast.makeText(getBaseContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void initUpdateUserInformation() {

        update = (Button) findViewById(R.id.userprofile_button_update);

        update.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                UserController controller = new UserController();
                try {
                    controller.SaveProfile(
                            currentUser.getUserName(),
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            email.getText().toString(),
                            status.getText().toString(),
                            currentUser.getPicture());

                    Toast.makeText(getBaseContext(),
                            "Update Your Profile Succesfully", Toast.LENGTH_LONG)
                            .show();

                    Intent myIntent = new Intent(arg0.getContext(), MainActivity.class);
                    startActivityForResult(myIntent, 0);
                    finish();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (HabehException e) {
                    Toast.makeText(getBaseContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(UserProfile.this, MainActivity.class);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }
}
