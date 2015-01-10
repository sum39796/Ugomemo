package com.example.ugomemoforsp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AnimationActivity extends Activity {

	private AnimationImageView mAnimImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		mAnimImageView = (AnimationImageView) findViewById(R.id.animationImageView);
		getGalleryImageUris(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	}
	
	private void getGalleryImageUris(Uri uriType) {
		ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
		CursorLoader cursorLoader = new CursorLoader(
			    this,
			    uriType,
			    null,
			    null,
			    null, // Selection args (none).
			    null);
		
		Cursor cursor = cursorLoader.loadInBackground();
		//���R�[�h�̎擾
        //Cursor cursor = managedQuery(uriType, null, null, null, null);
        cursor.moveToFirst();
        int fieldIndex;
        Long id;
        while (cursor.moveToNext()) {
            //�J����ID�̎擾
            fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            id = cursor.getLong(fieldIndex);
            if( getPath(ContentUris.withAppendedId(uriType, id)).contains(AppConfig.APPNAME) ) {
            	id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                if(bitmap!=null) {
                	frames.add(bitmap);
                }
            }
        }
        mAnimImageView.setFrame(frames);
    }
	
	/**
	 * UriからPathへの変換処理
	 * @param uri
	 * @return String
	 */
	private String getPath(Uri uri) {
	    ContentResolver contentResolver = getContentResolver();
	    String[] columns = { MediaStore.Images.Media.DATA };
	    Cursor cursor = contentResolver.query(uri, columns, null, null, null);
	    cursor.moveToFirst();
	    String path = cursor.getString(0);
	    cursor.close();
	    
	    Log.d("GalleryAcitivity getPath()", path);
	    return path;
	}

}
