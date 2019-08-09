package com.example.condominiofb


import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.widget.Toast
import me.dm7.barcodescanner.zxing.ZXingScannerView
import com.google.zxing.Result


class SimpleScannerActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
        // Do something with the result here
        Log.v("QR CODE", rawResult.getText()) // Prints scan results
        Log.v("QR CODE", rawResult.barcodeFormat.toString()) // Prints the scan format (qrcode, pdf417 etc.)
        Toast.makeText(baseContext, rawResult.getText(), Toast.LENGTH_LONG).show()
        // If you would like to resume scanning, call this method below:
        mScannerView!!.resumeCameraPreview(this)
    }
}