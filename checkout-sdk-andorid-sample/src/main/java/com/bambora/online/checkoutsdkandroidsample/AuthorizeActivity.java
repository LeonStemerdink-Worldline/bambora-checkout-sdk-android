/*
 * Copyright (c) 2018 Bambora ( https://bambora.com/ )
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.bambora.online.checkoutsdkandroidsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);

        Intent intent = getIntent();
        String jsonPayload = intent.getStringExtra("payload");

        setViewData(jsonPayload);

        Button goToMain = (Button) findViewById(R.id.goToMainButton);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }

    private void setViewData(String jsonPayload)
    {
        try {
            JSONObject payload = new JSONObject(jsonPayload);
            JSONObject payloadData = payload.getJSONObject("data");

            TextView txnIdTextView = findViewById(R.id.txnIdDataTextView);
            txnIdTextView.setText(payloadData.getString("txnid"));

            TextView orderIdTextView = findViewById(R.id.orderidDataTextView);
            orderIdTextView.setText(payloadData.getString("orderid"));

            TextView referenceTextView = findViewById(R.id.referenceDataTextView);
            referenceTextView.setText(payloadData.getString("reference"));

            if(payloadData.getString("amount").length() > 0) {
                TextView amountTextView = findViewById(R.id.amountDataTextView);
                int amountInMinorunits = Integer.parseInt(payloadData.getString("amount"));
                double amount = (double) amountInMinorunits / 100;
                amountTextView.setText(String.valueOf(amount));
            }

            TextView currencyTextView = findViewById(R.id.currencyDataTextView);
            currencyTextView.setText(payloadData.getString("currency"));

            TextView dateTextView = findViewById(R.id.dateDataTextView);
            dateTextView.setText(payloadData.getString("date"));

            TextView timeTextView = findViewById(R.id.timeDataTextView);
            timeTextView.setText(payloadData.getString("time"));

            TextView feeIdTextView = findViewById(R.id.feeIdDataTextView);
            feeIdTextView.setText(payloadData.getString("feeid"));

            if(payloadData.getString("txnfee").length() > 0) {
                TextView txnFeeTextView = findViewById(R.id.txnFeeDataTextView);
                int feeAmountInMinorunits = Integer.parseInt(payloadData.getString("txnfee"));
                double feeAmount = (double)feeAmountInMinorunits / 100;
                txnFeeTextView.setText(String.valueOf(feeAmount));
            }

            TextView paymentTypeTextView = findViewById(R.id.paymentTypeDataTextView);
            paymentTypeTextView.setText(payloadData.getString("paymenttype"));

            TextView cardNoTextView = findViewById(R.id.cardNoDataTextView);
            cardNoTextView.setText(payloadData.getString("cardno"));

            TextView eciTextView = findViewById(R.id.eciDataTextView);
            eciTextView.setText(payloadData.getString("eci"));

            TextView issuerCountryTextView = findViewById(R.id.issuerCountryDataTextView);
            issuerCountryTextView.setText(payloadData.getString("issuercountry"));

            TextView hashTextView = findViewById(R.id.hashDataTextView);
            hashTextView.setText(payloadData.getString("hash"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
