package xyz.tastewiki.tastewiki_for_android;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    private static final int REQEUST_ACCESS_FINE_LOCATION = 2;

    final LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    WebView webViewMain;
    Button button1, button2, recommendButton;
    View.OnClickListener cListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQEUST_ACCESS_FINE_LOCATION);
        }

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, mLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, mLocationListener);

        webViewMain = (WebView)findViewById(R.id.webViewMain);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        recommendButton = (Button)findViewById(R.id.recommendButton);

        cListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button1:
                        clickButton1();
                        break;
                    case R.id.button2:
                        clickButton2();
                        break;
                    case R.id.recommendButton:
                        recommendSpot();
                        break;
                    default:
                        break;
                }
            }
        };

        button1.setOnClickListener(cListener);
        button2.setOnClickListener(cListener);
        recommendButton.setOnClickListener(cListener);



        WebSettings webSettings = webViewMain.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        webViewMain.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webViewMain.setWebChromeClient(new WebChromeClient() {

        });

        webViewMain.loadUrl("https://www.tastewiki.xyz");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ACCESS_COARSE_LOCATION:
                for (int i = 0; i < permissions.length; ++i) {
                    if (permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        }
                        else {

                        }
                    }
                }
                break;
            case REQEUST_ACCESS_FINE_LOCATION:
                for (int i = 0; i < permissions.length; ++i) {
                    if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        }
                        else {

                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            //Log.d("Lat", lat);
            //Log.d("Lng", lng);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        if (webViewMain.canGoBack()) {
            webViewMain.goBack();
        } else {
            finish();
        }
    }

    void clickButton1() {

    }

    void clickButton2() {

    }

    void recommendSpot() {
        webViewMain.loadUrl("javascript:recommendSpot();");
    }
}
