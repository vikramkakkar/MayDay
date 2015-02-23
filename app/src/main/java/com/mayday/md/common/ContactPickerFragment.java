package com.mayday.md.common;

import com.mayday.md.R;
import com.mayday.md.fragment.MessageTextFragment;

import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


import static android.app.Activity.RESULT_OK;
import static android.content.Intent.ACTION_GET_CONTENT;
import static android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
import static android.view.View.OnClickListener;

public class ContactPickerFragment extends Fragment {
    private static final int PICK_CONTACT_REQUEST_ID = 100;

    private ImageButton contactPickerButton;
    static public EditText phoneNumberEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("ContactPickerFragment", "ContactPickerFragment onCreateView");
        View view = inflater.inflate(R.layout.contact_picker_fragment, container, false);

        contactPickerButton = (ImageButton) view.findViewById(R.id.contact_picker_button);
        phoneNumberEditText = (EditText) view.findViewById(R.id.contact_edit_text);


        return view;
    }

//    private OnClickListener contactPickerListener = new OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            launchContactPicker(view);
//        }
//    };

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        contactPickerButton.setOnClickListener(contactPickerListener);
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactPickerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int wizardState = ApplicationSettings.getWizardState(getActivity());
//            	 if(wizardState != AppConstants.WIZARD_FLAG_HOME_READY){
                AppConstants.IS_BACK_BUTTON_PRESSED = true;
                AppConstants.IS_BACK_BUTTON_PRESSED = true;
//            	 }
                launchContactPicker(v);
            }
        });
    }

    public void launchContactPicker(View view) {
        Intent contactPickerIntent = new Intent(ACTION_GET_CONTENT);
        contactPickerIntent.setType(CONTENT_ITEM_TYPE);
        startActivityForResult(contactPickerIntent, PICK_CONTACT_REQUEST_ID);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, Context context) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ContactPickerFragment", "onActivityResult requestCode "+requestCode);
        Log.e("ContactPickerFragment", "onActivityResult resultCode "+resultCode);
        Log.e("ContactPickerFragment", "onActivityResult data "+data);

        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(data.getData(), null, null, null, null);
        String phone = "";
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                Log.e("ContactPickerFragment", "onActivityResult id "+id);
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.e("ContactPickerFragment", "onActivityResult name "+name);
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //Query phone here.  Covered next
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    Log.e("WizardActivity", "onActivityResult pCur "+pCur);
                    while (pCur.moveToNext()) {
                        // Do something with phones
                        phone = pCur.getString(
                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.e("ContactPickerFragment", "onActivityResult phone "+phone);
                    }
                    pCur.close();
                }
            }
        }
        Log.e("ContactPickerFragment", "onActivityResult phone "+phone);
        phone = phone==null?"":phone.trim();
        Log.e("ContactPickerFragment", "onActivityResult phoneNumberEditText "+phoneNumberEditText);
        phoneNumberEditText.setText(phone);
        //phoneNumberEditText.setText(phone);
        //phoneNumberEditText.setText((CharSequence)phone);
    }

}