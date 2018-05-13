package me.jaisharma.findx.advancegooglesearch;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class ToGoogle extends MainActivity {

    EditText siteName, fileType, inUrl, inTitle, indexOf;
    String strSite, strFile, strUrl, strTitle, strIndex, dork;
    ArrayList<String> arrstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, "ca-app-pub-6513478276698267/8164680343");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "To the Search Engine!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



                siteName = findViewById(R.id.editSite);
                strSite = "site%3A" + siteName.getText().toString();

                arrstr = new ArrayList<String>();
                if(!(strSite.equals("site%3A"))){
                    arrstr.add(strSite);
                }else {
                    arrstr.add("");
                }

                fileType = findViewById(R.id.editFile);
                strFile = "filetype%3A" + fileType.getText().toString();
                if(!(strFile.equals("filetype%3A"))){
                    arrstr.add(strFile);
                }else {
                    arrstr.add("");
                }

                inUrl = findViewById(R.id.editUrl);
                strUrl = "inurl%3A" + inUrl.getText().toString();
                if(!(strUrl.equals("inurl%3A"))){
                    arrstr.add(strUrl);
                }else {
                    arrstr.add("");
                }

                inTitle = findViewById(R.id.editTitle);
                strTitle = "intext%3A" + inTitle.getText().toString();
                if(!(strTitle.equals("intext%3A"))){
                    arrstr.add(strTitle);
                }else {
                    arrstr.add("");
                }

                indexOf = findViewById(R.id.editIndex);
                strIndex = "indexof%3A" + indexOf.getText().toString();
                if(!(strIndex.equals("indexof%3A"))){
                    arrstr.add(strIndex);
                }else {
                    arrstr.add("");
                }


                dork = StringUtils.join(arrstr, " ");


                search();

            }
        });
    }

    String gSearch = "https://www.google.com/search?q=";

    //To the Google method calls defined here ;)
    public void search(){

        try{

            Uri uri = Uri.parse("googlechrome://navigate?url=" +gSearch+dork);
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } catch (ActivityNotFoundException e){

            Context context = getApplicationContext();
            CharSequence text = "Please install Chrome first!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

    }

}
