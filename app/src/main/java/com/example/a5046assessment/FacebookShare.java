package com.example.a5046assessment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class FacebookShare extends AppCompatActivity {
    Button buttonShareLink, buttonSharePhoto;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();

            if (ShareDialog.canShow(SharePhotoContent.class)) {
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                shareDialog.show(content);
            }
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.fullyInitialize();
        setContentView(R.layout.facebook);

        // Init View
        buttonShareLink = (Button) findViewById(R.id.buttonShareLink);
//        buttonSharePhoto = (Button) findViewById(R.id.buttonSharePhoto);

        // Init Facebook
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        buttonShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(FacebookShare.this, "Share successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(FacebookShare.this, "Share cancelled!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        Toast.makeText(FacebookShare.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = getIntent();
                if (intent != null && intent.hasExtra("recipeUrl")) {
                    String recipeUrl = intent.getStringExtra("recipeUrl");
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setQuote("This is a useful link")
                            .setContentUrl(Uri.parse(recipeUrl))
                            .build();
                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        shareDialog.show(linkContent);
                    }
                }

//                ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                        .setQuote("This is useful link")
//                        .setContentUrl(Uri.parse(recipeUrl))
//                        .build();
//                if(ShareDialog.canShow(ShareLinkContent.class))
//                {
//                    shareDialog.show(linkContent);
//                }
            }
        });

//        buttonSharePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // create callback
//                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//                    @Override
//                    public void onSuccess(Sharer.Result result) {
//                        Toast.makeText(FacebookShare.this, "Share successful!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(FacebookShare.this, "Share cancelled!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(@NonNull FacebookException e) {
//                        Toast.makeText(FacebookShare.this, "Error!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                Intent intent = getIntent();
//                if (intent != null && intent.hasExtra("recipeName")) {
//                    String recipeName = intent.getStringExtra("recipeName");
//
//                    // fetching photo from link and convert to bitmap
//                    Picasso.get()
//                            .load(recipeName)
//                            .into(target);
//                }
//            }
//        });
    }
}